/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.UnexpectedAmountOfCustomersException;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vicente
 */
public class NeighborhoodStructureSwap extends NeighborhoodStructure {
  
  public Neighbor generateNeighbor(Solution s, List<Tabu> tabuList, List<Integer> customers) 
          throws UnexpectedAmountOfCustomersException {
  
    if(customers.size() != 2)
      throw new UnexpectedAmountOfCustomersException();
    int customerA = customers.get(0);
    int customerB = customers.get(1);
    int targetRouteA = s.getRouteNumber(customerB);
    int targetRouteB = s.getRouteNumber(customerA);
    int targetRoutePosA = s.getCustomerPosition(customerB);
    int targetRoutePosB = s.getCustomerPosition(customerA);
    
    MoveSwap m = new MoveSwap(customerA, customerB, targetRouteB, targetRouteA, 
            targetRoutePosB, targetRoutePosA);
    
    if(tabuList.contains(m.generateTabu()))
      return null;
    
    try {
      Neighbor n = new Neighbor(s, m);
      return n;
      
    } catch (MaxCapacityExceededException ex) {
      Logger.getLogger(NeighborhoodStructureSwap.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MaxDurationExceededException ex) {
      Logger.getLogger(NeighborhoodStructureSwap.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  private List<Neighbor> generateFullNeighborhood(Solution s , List<Tabu> tabuList) throws TabuListFullException {
    Instance instance = s.getInstance();
    int customersNumber = instance.getCustomersNumber();
    List<Neighbor> neighbors = new ArrayList<Neighbor>();
    List<Integer> customer = new ArrayList<Integer>(2);
    for(int i = 1; i < customersNumber; i++) {
      customer.add(i);
      for(int j = i+1; j <= customersNumber; j++) {
        customer.add(i+1);
        try {
          neighbors.add(this.generateNeighbor(s, tabuList, customer));
        } catch (UnexpectedAmountOfCustomersException ex) {
          Logger.getLogger(NeighborhoodStructureClassic.class.getName()).log(Level.SEVERE, null, ex);
        }
        customer.get(1);
      }
      customer.clear();
    }
    return neighbors;
  }
  
  private List<Neighbor> generateRandomNeighborhood(Solution s , List<Tabu> tabuList) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private List<Neighbor> generateGranularNeighborhood(Solution s , List<Tabu> tabuList) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}
