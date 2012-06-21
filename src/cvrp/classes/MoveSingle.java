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
import java.util.logging.Level;
import java.util.logging.Logger;

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
  public int applyMoves(Solution solution, boolean commit) throws MaxCapacityExceededException, MaxDurationExceededException {

    try {
      int customerPos = solution.getCustomerPosition(this.customer);
      Route origin_Route = solution.getRoute(solution.getRouteNumber(this.customer));
      Route target_Route = solution.getRoute(this.targetRoute);
      int duration = solution.getDuration();
      duration += origin_Route.remove(customerPos, solution.getInstance(), commit);

      // Si la ruta donde lo voy a meter es la misma de donde lo saque
      // y la posicion donde lo iba a meter es superior a donde estaba,
      // debo restarle uno a la posicion de destino para que se actualice.
      if(origin_Route.equals(target_Route) && this.targetPosition > customerPos && commit)
        this.targetPosition = this.targetPosition - 1;
      duration += target_Route.add(this.customer, this.targetPosition, solution.getInstance(), commit);
      if(commit)
        return duration;
      solution.setDuration(duration);
      int [] customerPosition = solution.getCustomerPosition();
      int [] customerRoute = solution.getCustomerRoute();
      customerPosition[this.customer] = this.targetPosition;
      customerRoute[this.customer] = this.targetRoute;
      for(int index = customerPos; index < origin_Route.size() -1; ++index)
        customerPosition[origin_Route.getCustomerAt(index)] = index;
      for(int index = this.targetPosition + 1; index < target_Route.size() -1; ++index)
        customerPosition[target_Route.getCustomerAt(index)] = index;
      solution.setCustomerPosition(customerPosition);
      solution.setCustomerRoute(customerRoute);
      return duration;
    } catch (MaxCapacityExceededException ex) {
            Logger.getLogger(Neighbor.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MaxDurationExceededException ex) {
            Logger.getLogger(Neighbor.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
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