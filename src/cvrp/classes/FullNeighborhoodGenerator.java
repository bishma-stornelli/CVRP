/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.interfaces.NeighborhoodGenerator;
import cvrp.interfaces.NeighborhoodStructure;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class FullNeighborhoodGenerator implements NeighborhoodGenerator {

  @Override
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) {
    Instance instance = s.getInstance();
    NeighborhoodStructure ns = instance.getNeighborhoodStructure();
    int customersNumber = ns.getNumberOfCustomerRequired();
    List<Neighbor> neighbors = new ArrayList<Neighbor>();
    List<Integer> customer = new ArrayList<Integer>();
    
    for(int i = 1; i < customersNumber; i++) {
      customer.add(i);
      neighbors.add(ns.generateNeighbor(s, tabuList, customer));
      customer.clear();
    }
    return neighbors;
  }
    
}