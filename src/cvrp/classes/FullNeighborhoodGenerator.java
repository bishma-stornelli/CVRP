/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.UnexpectedAmountOfCustomersException;
import cvrp.interfaces.NeighborhoodGenerator;
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
public class FullNeighborhoodGenerator implements NeighborhoodGenerator {

  @Override
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) 
    throws TabuListFullException {
    Instance instance = s.getInstance();
    NeighborhoodStructure ns = instance.getNeighborhoodStructure();
    int customersNumber = ns.getNumberOfCustomerRequired();
    List<Neighbor> neighbors = new ArrayList<Neighbor>();
    List<Integer> customer = new ArrayList<Integer>();
    
    for(int i = 1; i <= s.getInstance().getCustomersNumber() ; i++) {
        customer.add(i);
        try {
            neighbors.add(ns.generateNeighbor(s, tabuList, customer));
        } catch (UnexpectedAmountOfCustomersException ex) {
            Logger.getLogger(FullNeighborhoodGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch ( TabuListFullException t ){
            throw t;
        }
        customer.clear();
    }
    return neighbors;
  }
    
}