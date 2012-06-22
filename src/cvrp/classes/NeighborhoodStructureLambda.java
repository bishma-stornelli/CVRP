/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.interfaces.NeighborhoodStructure;
import cvrp.exceptions.TabuListFullException;
import cvrp.interfaces.Tabu;
import java.util.List;

/**
 *
 * @author vicente
 */
public class NeighborhoodStructureLambda implements NeighborhoodStructure {

  
/*
  public int getNumberOfCustomerRequired() {
    return (int)Math.ceil(Math.random()*2);
  }*/
  
  
  /*
  @Override
  public Neighbor generateNeighbor(Solution s, List<Tabu> tabuList, List<Integer> customers) throws UnexpectedAmountOfCustomersException, TabuListFullException {
    
    if((customers.size() != 1) || (customers.size() != 2))
      throw new UnexpectedAmountOfCustomersException();
    
    if(customers.size() == 1) 
      NeighborhoodStructure ns = new NeighborhoodStructureClassic();
      return ns.generateNeighbor(s, tabuList, customers);
    if(customers.size() == 2) {
      Instance instance = s.getInstance();
      int customerA = customers.get(0);
      int customerB = customers.get(1);
      int originRouteA = s.getRouteNumber(customerA);
      int originRouteB = s.getRouteNumber(customerB);
      Route routeA = s.getRoute(originRouteA);
      Route routeB = s.getRoute(originRouteB);
      int customerPosA = s.getCustomerPosition(customerA);
      int customerPosB = s.getCustomerPosition(customerB);
      return null;
      
    }
    
    return null;
  }
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
  
  public Neighbor generateNeighbor(Solution s, List<Tabu> tabuList) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  private List<Neighbor> generateFullNeighborhood(Solution s , List<Tabu> tabuList) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  private List<Neighbor> generateRandomNeighborhood(Solution s , List<Tabu> tabuList) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private List<Neighbor> generateGranularNeighborhood(Solution s , List<Tabu> tabuList) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
