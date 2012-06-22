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
 * @author vicente
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
  }

  @Override
  public List<Tabu> generateTabu() {
    List<Tabu> tabuList = new ArrayList<Tabu>(2);
    CustomerRouteTabu customerRouteTabuA = new CustomerRouteTabu(customerA, targetRouteA);
    CustomerRouteTabu customerRouteTabuB = new CustomerRouteTabu(customerB, targetRouteB);
    tabuList.add(customerRouteTabuA);
    tabuList.add(customerRouteTabuB);
    return tabuList;
  }
  
  @Override
  public void applyMove(Solution solution) 
          throws MaxCapacityExceededException, MaxDurationExceededException {
    
    MoveSingle singleMoveA = 
            new MoveSingle(this.customerA, this.targetRouteA, this.targetPositionA);
    singleMoveA.applyMove(solution);
    MoveSingle singleMoveB =
            new MoveSingle(this.customerB, this.targetRouteB, this.targetPositionB);
    singleMoveB.applyMove(solution); 
  }

  @Override
  public int applyMoveDuration(Solution solution) 
          throws MaxCapacityExceededException, MaxDurationExceededException{
    MoveSingle singleMoveA = 
            new MoveSingle(this.customerA, this.targetRouteA, this.targetPositionA);
    int durationA = singleMoveA.applyMoveDuration(solution);
    int duration = solution.getDuration();
    solution.setDuration(durationA);
    MoveSingle singleMoveB =
            new MoveSingle(this.customerB, this.targetRouteB, this.targetPositionB);
    int durationB = singleMoveB.applyMoveDuration(solution);
    solution.setDuration(duration);
    return durationB;
    
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