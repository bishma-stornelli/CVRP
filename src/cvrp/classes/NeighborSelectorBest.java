/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.interfaces.NeighborSelector;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class NeighborSelectorBest implements NeighborSelector {

  @Override
  public Neighbor selectNeighbor(List<Neighbor> neighbors, Solution bestFound) 
          throws IndexOutOfBoundsException {
    Neighbor best = neighbors.get(0);
    for (Neighbor n: neighbors) {
      if(n.getDuration() < best.getDuration()) {
        best = n;
      }
    }
    return best;
  }
    
}
