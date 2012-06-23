/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.interfaces.Move;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;

  

/**
 *
 * @author tamerdark
 */
public class MoveTwoOpt implements Move {
    
  private int route;
  private int customerPositionA;
  private int customerPositionB;

  public MoveTwoOpt(int route, int edge1, int edge2) {
    this.route = route;
    this.customerPositionA = edge1;
    this.customerPositionB = edge2;
  }
  
  
  @Override
  public void applyMove(Solution solution) 
          throws MaxCapacityExceededException, MaxDurationExceededException {
    Route r = solution.getRoute(route);
    int routeDuration = r.getDuration();
    Instance instance = solution.getInstance();
    int customerA = r.getCustomerAt(customerPositionA);
    int customerB = r.getCustomerAt(customerPositionB); 
    int preCustomerA  = r.getCustomerAt(customerPositionA - 1);
    int postCustomerB = r.getCustomerAt(customerPositionB + 1);
    int ChangeOnDuration = instance.getDistance(customerPositionA, postCustomerB)
                         + instance.getDistance(preCustomerA, customerPositionB)
                         - instance.getDistance(preCustomerA, customerPositionA)
                         - instance.getDistance(customerPositionB, postCustomerB);
    if(routeDuration + ChangeOnDuration > instance.getMaximumRouteTime())
      throw new MaxDurationExceededException();
    
    List<Integer> routeList = r.getRoute();
    
    List<Integer> reverse = routeList.subList(customerPositionA, customerPositionB + 1);

    int end = reverse.size()/2;
    for(int k = 0; k < end; ++k) {
      int aux = reverse.get(k);
      reverse.set(k, reverse.get(reverse.size() - k - 1));
      reverse.set(reverse.size() - k - 1, aux);     
    }
    
    for(int k = customerPositionA; k <= customerPositionB; ++k)
      solution.setCustomerPosition(routeList.get(k), k);
    
    int duration = solution.getDuration();
    duration += ChangeOnDuration; 
    solution.setDuration(duration);
    r.setDuration(r.getDuration() + ChangeOnDuration);
    /*
    List<Integer> l = r.getRoute();
    List<Integer> reverse = new ArrayList<Integer>(customerPositionB - customerPositionA);
    for(int index = customerPositionA ; index < customerPositionB + 1 ; ++index) {
        reverse.add(l.get(index));
    }
    l.removeAll(reverse);
    l.addAll(customerPositionA, reverse);
    solution.getDuration();*/
  }

  @Override
  public List<Tabu> generateTabu() {
    List<Tabu> tabuList = new ArrayList<Tabu>(2);
    CustomerRouteTabu customerRouteTabuA = new CustomerRouteTabu(1, 1);
    CustomerRouteTabu customerRouteTabuB = new CustomerRouteTabu(1, 1);
    tabuList.add(customerRouteTabuA);
    tabuList.add(customerRouteTabuB);
    return tabuList;
  }

  @Override
  public int applyMoveDuration(Solution solution) throws MaxCapacityExceededException, MaxDurationExceededException {
    Route r = solution.getRoute(route);
    int routeDuration = r.getDuration();
    Instance instance = solution.getInstance();
    int customerA = r.getCustomerAt(customerPositionA);
    int customerB = r.getCustomerAt(customerPositionB); 
    int preCustomerA  = r.getCustomerAt(customerPositionA - 1);
    int postCustomerB = r.getCustomerAt(customerPositionB + 1);
    
    int ChangeOnDuration = instance.getDistance(customerPositionA, postCustomerB)
                         + instance.getDistance(preCustomerA, customerPositionB)
                         - instance.getDistance(preCustomerA, customerPositionA)
                         - instance.getDistance(customerPositionB, postCustomerB);
    if(routeDuration + ChangeOnDuration > instance.getMaximumRouteTime())
      throw new MaxDurationExceededException();
    
    int duration = solution.getDuration();
    duration += ChangeOnDuration; 
    if(duration < 0) {
      System.out.println("hola");
    }
    return duration;
  }
 
  // Getters and Setters

  public int getCustomerPositionA() {
    return customerPositionA;
  }

  public void setCustomerPositionA(int customerPositionA) {
    this.customerPositionA = customerPositionA;
  }

  public int getCustomerPositionB() {
    return customerPositionB;
  }

  public void setCustomerPositionB(int customerPositionB) {
    this.customerPositionB = customerPositionB;
  }

  public int getRoute() {
    return route;
  }

  public void setRoute(int route) {
    this.route = route;
  }
 
}
