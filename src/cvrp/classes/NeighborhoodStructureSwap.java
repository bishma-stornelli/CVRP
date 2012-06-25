package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.UnexpectedAmountOfCustomersException;
import cvrp.interfaces.NeighborhoodStructure;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class NeighborhoodStructureSwap implements NeighborhoodStructure {

  /**
   * Return the neighborhood by applying the swap structure generator.
   * 
   * @param s a solution
   * @param tabuList a tabu list
   * @return the swap neighborhood
   * @throws TabuListFullException 
   */
  @Override
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) 
          throws TabuListFullException {
    Instance instance = s.getInstance();
    
    if(instance.getNEIGHBORHOOD_GENERATOR().equals("F"))
      return this.generateFullNeighborhood(s, tabuList);
    else if(instance.getNEIGHBORHOOD_GENERATOR().equals("R"))
      return this.generateRandomNeighborhood(s, tabuList);
    else if(instance.getNEIGHBORHOOD_GENERATOR().equals("G"))
      return this.generateGranularNeighborhood(s, tabuList); 
    return null;
  }

  /**
   * Return a neighbor by applying the swap structure generator.
   * 
   * @param s a solution
   * @param tabuList a tabu list
   * @param customers the customer list
   * @return the neighbor
   * @throws UnexpectedAmountOfCustomersException
   */
  public Neighbor generateNeighbor(Solution s, List<Tabu> tabuList, List<Integer> customers) 
          throws UnexpectedAmountOfCustomersException {
    
    if(customers.size() != 2)
      throw new UnexpectedAmountOfCustomersException();
    
    int customerA = customers.get(0);
    int customerB = customers.get(1);
    int targetRouteA = s.getRouteNumber(customerB);
    int targetRouteB = s.getRouteNumber(customerA);
    int targetRoutePositionA = s.getCustomerPosition(customerB);
    int targetRoutePositionB = s.getCustomerPosition(customerA);
    
    MoveSwap m = new MoveSwap(customerA, customerB, targetRouteA, targetRouteB, 
            targetRoutePositionA, targetRoutePositionB);
    List<Tabu> generateTabus = m.generateTabu();
    for(Tabu tabu: generateTabus) {
      if(tabuList.contains(tabu))
        return null;
    }

    try {
      Neighbor n = new Neighbor(s, m);
      return n;   
    } catch (MaxCapacityExceededException ex) {
      // Logger.getLogger(NeighborhoodStructureSwap.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MaxDurationExceededException ex) {
      // Logger.getLogger(NeighborhoodStructureSwap.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  /**
   * Return the full neighborhood by applying the swap structure generator.
   * 
   * @param s a solution
   * @param tabuList the tabu list
   * @return the full neighborhood
   * @throws TabuListFullException 
   */
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
          
          Neighbor neighbor = this.generateNeighbor(s, tabuList, customer);
          if (neighbor != null)
            neighbors.add(neighbor);
        } catch (UnexpectedAmountOfCustomersException ex) {
          Logger.getLogger(NeighborhoodStructureClassic.class.getName()).log(Level.SEVERE, null, ex);
        }
        customer.remove(1);
      }
      customer.clear();
    }

    return neighbors;
  }
  
  /**
   * Return the random neighborhood by applying the swap structure generator.
   * 
   * @param s a solution
   * @param tabuList the tabu list
   * @return the random neighborhood
   */
  private List<Neighbor> generateRandomNeighborhood(Solution s , List<Tabu> tabuList) {
    
    Instance instance = s.getInstance();
    int customerNumber = instance.getCustomersNumber();
    int randomCustomers = (int)(Math.random()*customerNumber)+1;
    List<Neighbor> neighbors = new ArrayList<Neighbor>();
    List<Integer> customer = new ArrayList<Integer>(2);
    int i = 0;
    
    while(i != randomCustomers) {
      
      int customerA = (int)(Math.random()*customerNumber) + 1;
      int customerB = (int)(Math.random()*customerNumber) + 1;
      if(customerA == customerB)
        continue;
      else {
        customer.add(customerA);
        customer.add(customerB);
        try {
          Neighbor neighbor = this.generateNeighbor(s, tabuList, customer);
          if (neighbor != null)
            neighbors.add(neighbor);
        } catch (UnexpectedAmountOfCustomersException ex) {
          Logger.getLogger(NeighborhoodStructureClassic.class.getName()).log(Level.SEVERE, null, ex);        
        }
        
        customer.clear();
        ++i;
      }    
    }
    
    return neighbors;
  }

  /**
   * Return the granular neighborhood by applying the swap structure generator.
   * Not implement yet.
   * 
   * @param s a solution
   * @param tabuList the tabu list
   * @return the granular neighborhood
   * @throws TabuListFullException 
   */
  private List<Neighbor> generateGranularNeighborhood(Solution s , List<Tabu> tabuList) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}
