/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.UnexpectedAmountOfCustomersException;
import cvrp.interfaces.Tabu;
import java.util.List;

/**
 *
 * @author vicente
 */
public class NeighborhoodStructureLambda extends NeighborhoodStructure {

  
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

  public Neighbor generateNeighbor(Solution s, List<Tabu> tabuList) {
    throw new UnsupportedOperationException("Not supported yet.");
  }


}
