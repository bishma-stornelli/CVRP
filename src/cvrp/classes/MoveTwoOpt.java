/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

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
  private int customer1;
  private int customer2;

  public MoveTwoOpt(int route, int edge1, int edge2) {
    this.route = route;
    this.customer1 = edge1;
    this.customer2 = edge2;
  }

  @Override
  public int applyMoves(Solution s, boolean commit) throws MaxDurationExceededException{
    Route r = s.getRoute(route);
    Instance i = s.getInstance();
    int preCustomer1, postCustomer2;
    preCustomer1 = r.getCustomerAt(s.getCustomerPosition(customer1) - 1);
    postCustomer2 = r.getCustomerAt(s.getCustomerPosition(customer2) + 1);
    int newDuration = i.getDistance(customer1, postCustomer2)
            + i.getDistance(preCustomer1, customer2)
            - i.getDistance(preCustomer1, customer1)
            - i.getDistance(customer2, postCustomer2);
    if(newDuration > i.getMaximumRouteTime()) {
      throw new MaxDurationExceededException();
    }
    if(commit) {
      List<Integer> l = r.getRoute();
      List<Integer> reverse = new ArrayList<Integer>(customer2 - customer1);
      for(int index = customer1 ; index < customer2 + 1 ; ++index){
        reverse.add(l.get(index));
      }
      l.removeAll(reverse);
      l.addAll(customer1, reverse);
    }
    return newDuration;

  }

  @Override
  public List<Tabu> generateTabu() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  // Getters and Setters

  public int getCustomer1() {
    return customer1;
  }

  public void setCustomer1(int customer1) {
    this.customer1 = customer1;
  }

  public int getCustomer2() {
    return customer2;
  }

  public void setCustomer2(int customer2) {
    this.customer2 = customer2;
  }

  public int getRoute() {
    return route;
  }

  public void setRoute(int route) {
    this.route = route;
  }
  
}
