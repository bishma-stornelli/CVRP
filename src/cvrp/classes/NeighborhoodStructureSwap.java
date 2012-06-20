/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.UnexpectedAmountOfCustomersException;
import cvrp.interfaces.NeighborhoodStructure;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vicente
 */
public class NeighborhoodStructureSwap implements NeighborhoodStructure {
  
  @Override
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) throws TabuListFullException {
    Instance instance = s.getInstance();
    
    if(instance.getNEIGHBORHOOD_GENERATOR().equals("F"))
      return this.generateFullNeighborhood(s, tabuList);
    else if(instance.getNEIGHBORHOOD_GENERATOR().equals("R"))
      return this.generateRandomNeighborhood(s, tabuList);
    else if(instance.getNEIGHBORHOOD_GENERATOR().equals("G"))
      return this.generateGranularNeighborhood(s, tabuList); 
    return null;
  }

  @Override
  public Neighbor generateNeighbor(Solution s, List<Tabu> tabuList, List<Integer> customers) throws UnexpectedAmountOfCustomersException, TabuListFullException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  
  private List<Neighbor> generateFullNeighborhood(Solution s , List<Tabu> tabuList) throws TabuListFullException {
    Instance instance = s.getInstance();
    int customersNumber = instance.getCustomersNumber();
    List<Neighbor> neighbors = new ArrayList<Neighbor>();
    List<Integer> customer = new ArrayList<Integer>(2);
    for(int i = 1; i <= customersNumber; i++) {
      customer.add(i);
      customer.add(i+1);
      try {
        neighbors.add(this.generateNeighbor(s, tabuList, customer));
      } catch (UnexpectedAmountOfCustomersException ex) {
        Logger.getLogger(NeighborhoodStructureClassic.class.getName()).log(Level.SEVERE, null, ex);
      } catch (TabuListFullException t){
        throw t;
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
