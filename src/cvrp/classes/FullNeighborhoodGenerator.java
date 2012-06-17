/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.abstracts.NeighborhoodGenerator;
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
 * @author tamerdark
 */
public class FullNeighborhoodGenerator extends NeighborhoodGenerator {

  @Override
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) throws TabuListFullException {
    
    Instance instance = s.getInstance();
    NeighborhoodStructure ns = instance.getNeighborhoodStructure();
    int customersNumber   = instance.getCustomersNumber();      
    int requiredCustomers = ns.getNumberOfCustomerRequired();
    int [] customersPermutations = super.generateCustomers(customersNumber, requiredCustomers);
    List<Integer> customer = new ArrayList<Integer>();
    List<Neighbor> neighbors = new ArrayList<Neighbor>();
    
    for(int i = 0; i < customersPermutations.length; i++) {
      if(requiredCustomers == 1)
        customer.add(customersPermutations[i]);
      if(requiredCustomers == 2) {
        customer.add(customersPermutations[i+1]);
        i++;
      }
      try {
        neighbors.add(ns.generateNeighbor(s, tabuList, customer));
      } catch (UnexpectedAmountOfCustomersException ex) {
        Logger.getLogger(FullNeighborhoodGenerator.class.getName()).log(Level.SEVERE, null, ex);
      } catch (TabuListFullException t){
        throw t;
      }
      customer.clear();
    }
    return neighbors;
  }
    
}