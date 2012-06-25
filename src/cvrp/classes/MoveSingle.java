package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.interfaces.Move;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
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
  
  /**
   * Return a tabu list for this move.
   * 
   * @return the tabu list
   */
  @Override
  public List<Tabu> generateTabu() {
    List<Tabu> tabuList = new ArrayList<Tabu>(1);
    CustomerRouteTabu customerRouteTabu = new CustomerRouteTabu(customer, targetRoute);
    tabuList.add(customerRouteTabu);
    return tabuList;
  }

  /**
   * Apply this move to a solution.
   * 
   * @param solution a solution
   * @throws MaxCapacityExceededException
   * @throws MaxDurationExceededException 
   */
  @Override
  public void applyMove(Solution solution)
          throws MaxCapacityExceededException, MaxDurationExceededException {


    int customerPosition = solution.getCustomerPosition(this.customer);
    Route origin_Route = solution.getRoute(solution.getRouteNumber(this.customer));
    Route target_Route = solution.getRoute(this.targetRoute);
    int duration = solution.getDuration();
    duration += origin_Route.remove(customerPosition, solution.getInstance());
    if (origin_Route.equals(target_Route) && this.targetPosition > customerPosition)
      this.targetPosition = this.targetPosition - 1;
    
    duration += target_Route.add(this.customer, this.targetPosition, solution.getInstance());
    solution.setDuration(duration);
    solution.setCustomerPosition(this.customer, this.targetPosition);
    solution.setCustomerRoute(this.customer, this.targetRoute);
    for (int index = customerPosition; index < origin_Route.size() - 1; ++index)
      solution.setCustomerPosition(origin_Route.getCustomerAt(index), index);
    
    for (int index = this.targetPosition + 1; index < target_Route.size() - 1; ++index) 
      solution.setCustomerPosition(target_Route.getCustomerAt(index), index);
    
/**
  * Old applyMove method.
  * 
  * Instance i = solution.getInstance(); boolean sameRoute =
  * solution.getRouteNumber(customer) == this.targetRoute; if (sameRoute)
  * { System.out.print("En single move se está cambiando a la misma
  * ruta"); } else { System.out.println("Diferentes"); }// Caso en el que
  * son diferentes Route rA =
  * solution.getRoute(solution.getRouteNumber(customer)), rB =
  * solution.getRoute(this.targetRoute); int Apos =
  * solution.getCustomerPosition(customer); int preA =
  * rA.getCustomerAt(Apos - 1), A = this.customer, postA =
  * rA.getCustomerAt(Apos + 1), preB =
  * rB.getCustomerAt(this.targetPosition - 1), postB =
  * rB.getCustomerAt(this.targetPosition); int codA, codB; if (sameRoute)
  * { codA = codB = -i.getDistance(preA, A) - i.getDistance(A, postA) +
  * i.getDistance(preA, postA) + i.getDistance(preB, A) +
  * i.getDistance(A, postB) - i.getDistance(preB, postB); } else { codA =
  * -i.getDistance(preA, A) - i.getDistance(A, postA) - i.getDropTime() +
  * i.getDistance(preA, postA); codB = i.getDistance(preB, A) +
  * i.getDistance(A, postB) + i.getDropTime() - i.getDistance(preB,
  * postB); } int capA = rA.getCapacity() - (sameRoute ? 0 :
  * i.getDemand(A)); int capB = rB.getCapacity() + (sameRoute ? 0 :
  * i.getDemand(A)); if (capA > i.getVehicleCapacity() || capB >
  * i.getVehicleCapacity()) { throw new MaxCapacityExceededException(); }
  * if (rA.getDuration() + codA > i.getMaximumRouteTime() ||
  * rB.getDuration() + codB > i.getMaximumRouteTime()) { throw new
  * MaxDurationExceededException(); }
  *
  * // Todas las validaciones pasaron, hora de cambiarlo de ruta if
  * (!solution.correct()) { return; } for (int pos = Apos; pos <
  * rA.getRoute().size() - 1; ++pos) { rA.getRoute().set(pos,
  * rA.getCustomerAt(pos + 1)); if (rA.getCustomerAt(pos) != 0) {
  * solution.setCustomerPosition(rA.getCustomerAt(pos), pos); } }
  * rA.getRoute().remove(rA.getRoute().size() - 1); rA.setCapacity(capA);
  * rA.setDuration(rA.getDuration() + codA); // Ahora ruta B // Si es la
  * misma tengo que tomar precauciones if (sameRoute && Apos <
  * this.targetPosition) { --this.targetPosition; } rB.getRoute().add(0);
  * for (int pos = rB.getRoute().size() - 2; pos > this.targetPosition;
  * --pos) { rB.getRoute().set(pos, rB.getCustomerAt(pos - 1));
  * solution.setCustomerPosition(rB.getCustomerAt(pos), pos); }
  * rB.getRoute().set(this.targetPosition, this.customer);
  * solution.setCustomerPosition(this.customer, this.targetPosition);
  * solution.setCustomerRoute(this.customer, this.targetRoute);
  * rB.setCapacity(capB); rB.setDuration(rB.getDuration() + codB); if
  * (!solution.correct()) { System.out.print(""); }
  */

  }

  /**
   * Return the solution duration that would result if we apply this move.
   * 
   * @param s a solution
   * @return the solution duration after apply this move
   * @throws MaxCapacityExceededException
   * @throws MaxDurationExceededException 
   */
  @Override
  public int applyMoveDuration(Solution s)
          throws MaxCapacityExceededException, MaxDurationExceededException {

    Instance i = s.getInstance();
    int customerPosition = s.getCustomerPosition(this.customer);
    Route rA = s.getRoute(s.getRouteNumber(this.customer));
    Route rB = s.getRoute(this.targetRoute);
    int origin_RouteDuration = rA.getDuration();
    int target_RouteDuration = rB.getDuration();
    int previous = rA.getRoute().get(customerPosition - 1);
    int next = rA.getRoute().get(customerPosition + 1);
    int changeOnDuration = i.getDistance(previous, next) 
                         - i.getDistance(previous, this.customer) 
                         - i.getDistance(this.customer, next)
                         - i.getDropTime();

    int totalChangeOnDuration = changeOnDuration;

    if (origin_RouteDuration + changeOnDuration > i.getMaximumRouteTime()) 
      throw new MaxDurationExceededException();
    
    int targetPositionAux = this.targetPosition;

    /*
      * if(origin_Route.equals(target_Route) && this.targetPosition >
      * customerPosition) { targetPositionAux = targetPositionAux - 1; }
      */

    int demand = i.getDemand(customer);
    int capacity = rB.getCapacity();

    if (s.getRouteNumber(this.customer) == this.targetRoute) {
        target_RouteDuration += changeOnDuration;
        demand = 0;
    }

    previous = rB.getRoute().get(targetPositionAux - 1);
    next = rB.getRoute().get(targetPositionAux);

    if (demand + capacity > i.getVehicleCapacity())
      throw new MaxCapacityExceededException();

    changeOnDuration = i.getDistance(previous, this.customer)
                     + i.getDistance(this.customer, next) 
                     + i.getDropTime()
                     - i.getDistance(previous, next);

    totalChangeOnDuration += changeOnDuration;

    if (target_RouteDuration + changeOnDuration > i.getMaximumRouteTime())
      throw new MaxDurationExceededException();
    
    int duration = s.getDuration();
    duration += totalChangeOnDuration;

    return duration;


//        Instance i = s.getInstance();
//        boolean sameRoute = s.getRouteNumber(customer) == this.targetRoute;
//        if (sameRoute) {
//            System.out.print("En single move se está cambiando a la misma ruta");
//        } else {
//            System.out.print("");
//        }// Caso en el que son diferentes
//        Route rA = s.getRoute(s.getRouteNumber(customer)), rB = s.getRoute(this.targetRoute);
//        int preA = rA.getCustomerAt(s.getCustomerPosition(customer) - 1),
//                A = this.customer,
//                postA = rA.getCustomerAt(s.getCustomerPosition(customer) + 1),
//                preB = rB.getCustomerAt(this.targetPosition - 1),
//                postB = rB.getCustomerAt(this.targetPosition);
//        int codA, codB;
//        if (sameRoute) {
//            codA = codB = -i.getDistance(preA, A)
//                    - i.getDistance(A, postA)
//                    + i.getDistance(preA, postA)
//                    + i.getDistance(preB, A)
//                    + i.getDistance(A, postB)
//                    - i.getDistance(preB, postB);
//        } else {
//            codA = -i.getDistance(preA, A)
//                    - i.getDistance(A, postA)
//                    - i.getDropTime()
//                    + i.getDistance(preA, postA);
//            codB = i.getDistance(preB, A)
//                    + i.getDistance(A, postB)
//                    + i.getDropTime()
//                    - i.getDistance(preB, postB);
//        }
//        int capA = rA.getCapacity() - (sameRoute ? 0 : i.getDemand(A));
//        int capB = rB.getCapacity() + (sameRoute ? 0 : i.getDemand(A));
//        if (capA > i.getVehicleCapacity() || capB > i.getVehicleCapacity()) {
//            throw new MaxCapacityExceededException();
//        }
//        int totalDuration = s.getDuration() + codA + codB;
//        int routeADuration = rA.getDuration() + codA;
//        int routeBDuration = rB.getDuration() + codB;
//
//        if (routeADuration > i.getMaximumRouteTime() || 
//                routeBDuration > i.getMaximumRouteTime()){
//            throw new MaxDurationExceededException();
//        }
//        return totalDuration;
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
