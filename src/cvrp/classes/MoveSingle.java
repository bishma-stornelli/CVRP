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
public class MoveSingle implements Move {

  private int customer; // The customer to be moved
  private int targetPosition; // The position in the origin_route from where the customer is going to be inserted
  private int targetRoute; // The route where the customer is going to be moved

  public MoveSingle(int customer, int targetRoute, int targetPosition) {
    this.customer = customer;
    this.targetPosition = targetPosition;
    this.targetRoute = targetRoute;
  }

  @Override
  public List<Tabu> generateTabu() {
    List<Tabu> tabuList = new ArrayList<Tabu>(1);
    CustomerRouteTabu customerRouteTabu = new CustomerRouteTabu(customer, targetRoute);
    tabuList.add(customerRouteTabu);
    return tabuList;
  }

  @Override
  public void applyMove(Solution solution) 
          throws MaxCapacityExceededException, MaxDurationExceededException {
   
    int customerPosition = solution.getCustomerPosition(this.customer);
    Route origin_Route = solution.getRoute(solution.getRouteNumber(this.customer));
    Route target_Route = solution.getRoute(this.targetRoute);
    int duration = solution.getDuration();
    duration += origin_Route.remove(customerPosition, solution.getInstance());
    if(origin_Route.equals(target_Route) && this.targetPosition > customerPosition)
        this.targetPosition = this.targetPosition - 1;
    duration += target_Route.add(this.customer, this.targetPosition, solution.getInstance());
    solution.setDuration(duration);
    solution.setCustomerPosition(this.customer, this.targetPosition);
    solution.setCustomerRoute(this.customer, this.targetRoute);
    for(int index = customerPosition; index < origin_Route.size() -1; ++index)
      solution.setCustomerPosition(origin_Route.getCustomerAt(index), index);
    for(int index = this.targetPosition + 1; index < target_Route.size() -1; ++index)
      solution.setCustomerPosition(target_Route.getCustomerAt(index), index);
      
  }
  
  @Override
  public int applyMoveDuration(Solution solution) 
          throws MaxCapacityExceededException, MaxDurationExceededException {
  
    Instance instance = solution.getInstance();
    int customerPosition = solution.getCustomerPosition(this.customer);
    Route origin_Route = solution.getRoute(solution.getRouteNumber(this.customer));
    Route target_Route = solution.getRoute(this.targetRoute);
    int origin_RouteDuration = origin_Route.getDuration();
    int target_RouteDuration = target_Route.getDuration();
    int previous = origin_Route.getRoute().get(customerPosition - 1);
    int next = origin_Route.getRoute().get(customerPosition + 1); 
       
    int changeOnDuration = instance.getDistance(previous, next) 
                         - instance.getDistance(previous, this.customer)
                         - instance.getDistance(this.customer, next)
                         - instance.getDropTime();
    
    int totalChangeOnDuration = changeOnDuration;
    
    if (origin_RouteDuration + changeOnDuration > instance.getMaximumRouteTime()) {
      throw new MaxDurationExceededException();
    }
    
    int targetPositionAux = this.targetPosition;
    /*
    if(origin_Route.equals(target_Route) && this.targetPosition > customerPosition) {
      targetPositionAux = targetPositionAux - 1;
    }*/
    
    int demand = instance.getDemand(customer);
    int capacity = target_Route.getCapacity();
    
    if(origin_Route.equals(target_Route)) {
      target_RouteDuration += changeOnDuration;
      demand = 0;
    }
    
    previous = target_Route.getRoute().get(targetPositionAux - 1);
    next = target_Route.getRoute().get(targetPositionAux);
    
    if (demand + capacity > instance.getVehicleCapacity()) {
      throw new MaxCapacityExceededException();
    }
    
    changeOnDuration = instance.getDistance(previous, this.customer)
                     + instance.getDistance(this.customer, next)
                     + instance.getDropTime()
                     - instance.getDistance(previous, next);
    
    totalChangeOnDuration += changeOnDuration;
    
    if (target_RouteDuration + changeOnDuration > instance.getMaximumRouteTime()) {
      throw new MaxDurationExceededException();
    }
    int duration = solution.getDuration();
    duration += totalChangeOnDuration;
    return duration;
  }
  
  // Getters and Setters

  public int getCustomer() {
    return customer;
  }

  public void setCustomer(int customer) {
    this.customer = customer;
  }

  public int getTargetPosition() {
    return targetPosition;
  }

  public void setTargetPosition(int targetPosition) {
    this.targetPosition = targetPosition;
  }

  public int getTargetRoute() {
    return targetRoute;
  }

  public void setTargetRoute(int targetRoute) {
    this.targetRoute = targetRoute;
  }
  
}
