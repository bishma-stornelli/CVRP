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
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class MoveSwap implements Move {

  private int customerA; // The customerA to be moved
  private int customerB; // The customerB to be moved
  private int targetRouteA; // The route where the customerA is going to be moved
  private int targetRouteB; // The route where the customerB is going to be moved
  private int targetPositionA; // The position in the target_route from where the customerA is going to be inserted
  private int targetPositionB; // The position in the target_route from where the customerB is going to be inserted

  public MoveSwap(int customerA, int customerB, int targetRouteA, int targetRouteB,
          int targetPositionA, int targetPositionB) {
    this.customerA = customerA;
    this.customerB = customerB;
    this.targetRouteA = targetRouteA;
    this.targetRouteB = targetRouteB;
    this.targetPositionA = targetPositionA;
    this.targetPositionB = targetPositionB;
    // assert (targetRouteA != targetRouteB);
  }

  /**
  * Return a tabu list for this move.
  * 
  * @return the tabu list
  */
  @Override
  public List<Tabu> generateTabu() {
    List<Tabu> tabuList = new ArrayList<Tabu>(2);
    CustomerRouteTabu customerRouteTabuA = new CustomerRouteTabu(customerA, targetRouteA);
    CustomerRouteTabu customerRouteTabuB = new CustomerRouteTabu(customerB, targetRouteB);
    tabuList.add(customerRouteTabuA);
    tabuList.add(customerRouteTabuB);
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
  public void applyMove(Solution s)
          throws MaxCapacityExceededException, MaxDurationExceededException {


  /*MoveSingle singleMoveA = new MoveSingle(this.customerA,
    this.targetRouteA, this.targetPositionA);
    singleMoveA.applyMove(s); MoveSingle singleMoveB = new
    MoveSingle(this.customerB, this.targetRouteB, this.targetPositionB);
    singleMoveB.applyMove(s);*/

    Route rA = s.getRoute(this.targetRouteB), rB = s.getRoute(this.targetRouteA);
    int preA = rA.getCustomerAt(this.targetPositionB - 1), 
               A = this.customerA,
               postA = rA.getCustomerAt(this.targetPositionB + 1),
               preB = rB.getCustomerAt(this.targetPositionA - 1),
               B = this.customerB,
               postB = rB.getCustomerAt(this.targetPositionA + 1);
    Instance i = s.getInstance();
    int changeOnDurationA, changeOnDurationB;
    boolean sameRoute = this.targetRouteA == this.targetRouteB;
    if (sameRoute) {
      changeOnDurationA = changeOnDurationB = i.getDistance(preA, B) 
                                            + i.getDistance(B, postA) 
                                            + i.getDistance(preB, A) 
                                            + i.getDistance(A, postB)
                                            - i.getDistance(preA, A)
                                            - i.getDistance(A, postA)
                                            - i.getDistance(preB, B)
                                            - i.getDistance(B, postB);
      if(Math.abs(this.targetPositionA - this.targetPositionB) == 1) {
        changeOnDurationA = changeOnDurationB = 
                                              - i.getDistance(preA,A)
                                              - i.getDistance(B,postB)
                                              + i.getDistance(preA,B)
                                              + i.getDistance(A, postB);
      }
    } else {
      changeOnDurationA = i.getDistance(preA, B)
                        + i.getDistance(B, postA)
                        - i.getDistance(preA, A)
                        - i.getDistance(A, postA);
      changeOnDurationB = i.getDistance(preB, A)
                        + i.getDistance(A, postB)
                        - i.getDistance(preB, B)
                        - i.getDistance(B, postB);
    }
    int capA = rA.getCapacity() - i.getDemand(A) + i.getDemand(B),
        capB = rB.getCapacity() - i.getDemand(B) + i.getDemand(A);
    if (sameRoute)
      capA = capB = rA.getCapacity();

    if (rA.getDuration() + changeOnDurationA > i.getMaximumRouteTime()
      || rB.getDuration() + changeOnDurationB > i.getMaximumRouteTime())
      throw new MaxDurationExceededException();

    if (capA > i.getVehicleCapacity() || capB > i.getVehicleCapacity())
      throw new MaxCapacityExceededException();

    rA.getRoute().set(this.targetPositionB, B);
    rB.getRoute().set(this.targetPositionA, A);
    rA.setDuration(rA.getDuration() + changeOnDurationA);
    rA.calculateDuration(i);
    if(!sameRoute) rB.setDuration(rB.getDuration() + changeOnDurationB);
    s.setDuration(s.getDuration() + changeOnDurationA + (sameRoute ? 0 : changeOnDurationB));
    s.setCustomerPosition(A, targetPositionA);
    s.setCustomerPosition(B, targetPositionB);
    s.setCustomerRoute(A, targetRouteA);
    s.setCustomerRoute(B, targetRouteB);

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

  /*MoveSingle singleMoveA = new MoveSingle(this.customerA, this.targetRouteA, this.targetPositionA); 
    MoveSingle singleMoveB = new MoveSingle(this.customerB, this.targetRouteB, this.targetPositionB); 
    int durationA = singleMoveA.applyMoveDuration(s); 
    int duration = s.getDuration(); 
    s.setDuration(durationA); 
    int durationB = singleMoveB.applyMoveDuration(s);
    s.setDuration(duration); 
    return durationB;*/

    Route rA = s.getRoute(this.targetRouteB), rB = s.getRoute(this.targetRouteA);
    int preA = rA.getCustomerAt(this.targetPositionB - 1),
               A = this.customerA,
               postA = rA.getCustomerAt(this.targetPositionB + 1),
               preB = rB.getCustomerAt(this.targetPositionA - 1),
               B = this.customerB,
               postB = rB.getCustomerAt(this.targetPositionA + 1);
    Instance i = s.getInstance();
    boolean sameRoute = this.targetRouteA == this.targetRouteB;
    int changeOnDurationA, changeOnDurationB;

    if (sameRoute) {
      changeOnDurationA = changeOnDurationB = i.getDistance(preA, B) 
                                            + i.getDistance(B, postA) 
                                            + i.getDistance(preB, A) 
                                            + i.getDistance(A, postB)
                                            - i.getDistance(preA, A)
                                            - i.getDistance(A, postA)
                                            - i.getDistance(preB, B)
                                            - i.getDistance(B, postB);
      if(Math.abs(this.targetPositionA - this.targetPositionB) == 1) {
        changeOnDurationA = changeOnDurationB =
                                              - i.getDistance(preA,A)
                                              - i.getDistance(B,postB)
                                              + i.getDistance(preA,B)
                                              + i.getDistance(A, postB);
      }                  
    } else {
        changeOnDurationA = i.getDistance(preA, B)
                          + i.getDistance(B, postA)
                          - i.getDistance(preA, A)
                          - i.getDistance(A, postA);
        changeOnDurationB = i.getDistance(preB, A)
                          + i.getDistance(A, postB)
                          - i.getDistance(preB, B)
                          - i.getDistance(B, postB);
    }

    int capA = rA.getCapacity() - i.getDemand(A) + i.getDemand(B),
        capB = rB.getCapacity() - i.getDemand(B) + i.getDemand(A);
    
    if (sameRoute)
        capA = capB = rA.getCapacity();
    
    if (rA.getDuration() + changeOnDurationA > i.getMaximumRouteTime()
     || rB.getDuration() + changeOnDurationB > i.getMaximumRouteTime()) 
      throw new MaxDurationExceededException();
    
    if (capA > i.getVehicleCapacity() || capB > i.getVehicleCapacity())
      throw new MaxCapacityExceededException();
    
    return s.getDuration() + changeOnDurationA + (sameRoute ? 0 :changeOnDurationB);

  }

  // Getters and Setters

  public int getCustomerA() {
      return customerA;
  }

  public void setCustomerA(int customerA) {
      this.customerA = customerA;
  }

  public int getCustomerB() {
      return customerB;
  }

  public void setCustomerB(int customerB) {
      this.customerB = customerB;
  }

  public int getTargetPositionA() {
      return targetPositionA;
  }

  public void setTargetPositionA(int targetPositionA) {
      this.targetPositionA = targetPositionA;
  }

  public int getTargetPositionB() {
      return targetPositionB;
  }

  public void setTargetPositionB(int targetPositionB) {
      this.targetPositionB = targetPositionB;
  }

  public int getTargetRouteA() {
      return targetRouteA;
  }

  public void setTargetRouteA(int targetRouteA) {
      this.targetRouteA = targetRouteA;
  }

  public int getTargetRouteB() {
      return targetRouteB;
  }

  public void setTargetRouteB(int targetRouteB) {
      this.targetRouteB = targetRouteB;
  }
  
}
