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
public class SwapMove implements Move {

  private int customerA; // The customerA to be moved
  private int customerB; // The customerB to be moved
  private int targetRouteA; // The route where the customerA is going to be moved
  private int targetRouteB; // The route where the customerB is going to be moved
  private int targetPositionA; // The position in the target_route from where the customerA is going to be inserted
  private int targetPositionB; // The position in the target_route from where the customerB is going to be inserted

  public SwapMove(int customerA, int customerB, int targetRouteA, int targetRouteB, 
          int targetPositionA, int targetPositionB) {
    this.customerA = customerA;
    this.customerB = customerB;
    this.targetRouteA = targetRouteA;
    this.targetRouteB = targetRouteB;
    this.targetPositionA = targetPositionA;
    this.targetPositionB = targetPositionB;
  }

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
  public void applyMoves(Solution solution) 
          throws MaxCapacityExceededException, MaxDurationExceededException {
    
    SingleMove singleMoveA = new SingleMove(this.customerA,this.targetRouteA,this.targetPositionA);
    SingleMove singleMoveB = new SingleMove(this.customerB,this.targetRouteB,this.targetPositionB);
    singleMoveA.applyMoves(solution);
    singleMoveB.applyMoves(solution);

  }
    
}