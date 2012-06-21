/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.interfaces;

import cvrp.classes.Neighbor;
import cvrp.classes.Solution;
import java.util.List;

/** Select the neighbor where the metaheuristic should move.
 * 
 * It may be:
 * 
 * The best of the neighborhood
 * The first better
 *
 * @author tamerdark
 */
public interface NeighborSelector {
    
  public Neighbor selectNeighbor(List<Neighbor> neighbors, Solution bestFound) 
          throws IndexOutOfBoundsException;
    
}
