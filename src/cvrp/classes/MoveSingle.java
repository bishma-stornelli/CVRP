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
    
    
    /*
    try {
      int customerPos = solution.getCustomerPosition(this.customer);
      
      Route origin_Route = solution.getRoute(solution.getRouteNumber(this.customer));
      Route target_Route = solution.getRoute(this.targetRoute);
      int duration = solution.getDuration();
      duration += origin_Route.remove(customerPos, solution.getInstance(), commit);

      // Si la ruta donde lo voy a meter es la misma de donde lo saque
      // y la posicion donde lo iba a meter es superior a donde estaba,
      // debo restarle uno a la posicion de destino para que se actualice.
      int targetPositionAux = this.targetPosition;
      if(origin_Route.equals(target_Route) && this.targetPosition > customerPos)
        this.targetPosition = this.targetPosition - 1;
      duration += target_Route.add(this.customer, this.targetPosition, solution.getInstance(), commit);
      if(!commit) {
        this.targetPosition = targetPositionAux;
        return duration;
      }
      solution.setDuration(duration);
      solution.setCustomerPosition(this.customer, this.targetPosition);
      solution.setCustomerRoute(this.customer, this.targetRoute);
      for(int index = customerPos; index < origin_Route.size() -1; ++index)
        solution.setCustomerPosition(origin_Route.getCustomerAt(index), index);
      for(int index = this.targetPosition + 1; index < target_Route.size() -1; ++index)
        solution.setCustomerPosition(target_Route.getCustomerAt(index), index);
      
      // solution.setCustomerPosition(customerPosition);
      // solution.setCustomerRoute(customerRoute);
      return duration;
    } catch (MaxCapacityExceededException ex) {
            // Logger.getLogger(Neighbor.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MaxDurationExceededException ex) {
           // Logger.getLogger(Neighbor.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return 0;*/
  }
  
  @Override
  public int applyMoveDuration(Solution solution) throws 
          MaxCapacityExceededException, MaxDurationExceededException {
  
    Instance instance = solution.getInstance();
    int customerPosition = solution.getCustomerPosition(this.customer);
    Route origin_Route = solution.getRoute(solution.getRouteNumber(this.customer));
    Route target_Route = solution.getRoute(this.targetRoute);
    int duration = solution.getDuration();
    int previous = origin_Route.getRoute().get(customerPosition - 1);
    int next = origin_Route.getRoute().get(customerPosition + 1); 
    
    int changeOnDuration = instance.getDistance(previous, next) 
                         - instance.getDistance(previous, this.customer)
                         - instance.getDistance(this.customer, next)
                         - instance.getDropTime();
    
    int targetPositionAux = this.targetPosition;
    if(origin_Route.equals(target_Route) && this.targetPosition > customerPosition)
      ++targetPositionAux;
    
    previous = target_Route.getRoute().get(targetPositionAux - 1);
    next = target_Route.getRoute().get(targetPositionAux);
    
    int demand = instance.getDemand(customer);
    int capacity = target_Route.getCapacity();
    if (demand + capacity > instance.getVehicleCapacity()) {
      throw new MaxCapacityExceededException();
    }
    
    changeOnDuration += instance.getDistance(previous, this.customer)
                      + instance.getDistance(this.customer, next)
                      + instance.getDropTime()
                      - instance.getDistance(previous, next);
    
    if (duration + changeOnDuration > instance.getMaximumRouteTime()) {
      throw new MaxDurationExceededException();
    }
    
    duration += changeOnDuration;
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
