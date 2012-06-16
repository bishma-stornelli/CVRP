/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.interfaces.NeighborhoodGenerator;
import cvrp.interfaces.NeighborhoodStructure;
import cvrp.interfaces.Tabu;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class FullNeighborhoodGenerator implements NeighborhoodGenerator {

  @Override
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) {
    NeighborhoodStructure ns = s.getInstance().getNeighborhoodStructure();
    
    
    
    // return ns.generateNeighborhood(s, i, tabuList);
  }
    
}
