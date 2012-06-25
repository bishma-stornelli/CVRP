package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.UnexpectedAmountOfCustomersException;
import cvrp.interfaces.NeighborhoodStructure;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */

/**
 * Iterates between the neighbors and for each select a route and position 
 * within the random route.
 * 
 * Note that can be inserted on the same route but in a different position and
 * you can leave empty routes as well as create new routes.
 * 
 */
public class NeighborhoodStructureClassic implements NeighborhoodStructure {
  
  /**
   * Return the neighborhood by applying the classic structure generator.
   * 
   * @param s a solution
   * @param tabuList a tabu list
   * @return the classic neighborhood
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
   * Return a neighbor by applying the classic structure generator.
   * 
   * @param s a solution
   * @param tabuList a tabu list
   * @param customers the customer list
   * @return the neighbor
   * @throws UnexpectedAmountOfCustomersException
   * @throws TabuListFullException 
   */
  public Neighbor generateNeighbor(Solution s, List<Tabu> tabuList, List<Integer> customers) 
          throws UnexpectedAmountOfCustomersException, TabuListFullException {
    if(customers.size() != 1)
      throw new UnexpectedAmountOfCustomersException();
    
    int customer = customers.get(0);
    int customersSize = s.getInstance().getCustomersNumber();
    int iterationsWithoutMove = 0;
    while(true) {
      int targetRoute = (int)(Math.random()*customersSize);
      Route r = s.getRoute(targetRoute);
      int positionInsideRoute = (int)(Math.random()*(r.size() - 2)) + 1;
      MoveSingle m = new MoveSingle(customer, targetRoute, positionInsideRoute);
      // If the route is the same, try to generate another number until
      // it has tried 2* tabulist.size() times.
      if((targetRoute == s.getRouteNumber(customer) && 
         (positionInsideRoute == s.getCustomerPosition(customer) 
       || positionInsideRoute == s.getCustomerPosition(customer) + 1)) 
       || tabuList.contains(m.generateTabu().get(0))
       || (r.size() == 2 && Math.random() < 0.1))
         {
          
          if(iterationsWithoutMove > 2*tabuList.size()) 
            throw new TabuListFullException();
          
          ++iterationsWithoutMove;
          continue;
        }
        try {
          Neighbor n = new Neighbor(s, m);
          return n; 
        } catch (MaxCapacityExceededException ex) {
          // Logger.getLogger(NeighborhoodStructureClassic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MaxDurationExceededException ex) {
          // Logger.getLogger(NeighborhoodStructureClassic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
  }  
  
  /**
   * Return the full neighborhood by applying the classic structure generator.
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
    List<Integer> customer = new ArrayList<Integer>(1);
    for(int i = 1; i <= customersNumber; i++) {
      customer.add(i);
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

  /**
   * Return the random neighborhood by applying the classic structure generator.
   * 
   * @param s a solution
   * @param tabuList the tabu list
   * @return the random neighborhood
   * @throws TabuListFullException 
   */
  private List<Neighbor> generateRandomNeighborhood(Solution s , List<Tabu> tabuList) throws TabuListFullException {
    Instance instance = s.getInstance();
    int customerNumber = instance.getCustomersNumber();
    int randomCustomers = (int)(Math.random()*customerNumber)+1;
    int randomCustomer = 0;
    List<Neighbor> neighbors = new ArrayList<Neighbor>();
    List<Integer> customer = new ArrayList<Integer>(1);
    Set<Integer> customerSet = new LinkedHashSet<Integer>(randomCustomers);
    int i = 0;
    
    while(i != randomCustomers) {
      randomCustomer = (int)(Math.random()*customerNumber) + 1;
      if(!customerSet.contains(randomCustomer)) {
        customerSet.add(randomCustomer);
        customer.add(randomCustomer);
        try {
          neighbors.add(this.generateNeighbor(s, tabuList, customer));
        } catch (UnexpectedAmountOfCustomersException ex) {
          Logger.getLogger(NeighborhoodStructureClassic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TabuListFullException t) {
          throw t;
        }
        customer.clear();
        ++i;
      }
    }
    return neighbors;
  }

  /**
   * Return the granular neighborhood by applying the classic structure generator.
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
