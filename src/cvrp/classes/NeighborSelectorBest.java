package cvrp.classes;

import cvrp.interfaces.NeighborSelector;
import java.util.List;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class NeighborSelectorBest implements NeighborSelector {

  /**
   * Return the best neighbor from the neighborhood.
   * 
   * @param neighbors the neighborhood
   * @param bestFound a solution
   * @return the best neighbor from the neighborhood
   * @throws IndexOutOfBoundsException 
   */
  @Override
  public Neighbor selectNeighbor(List<Neighbor> neighbors, Solution bestFound) 
          throws IndexOutOfBoundsException {
    Neighbor best = neighbors.get(0);
    for (Neighbor n: neighbors) {
      if(n.getDuration() < best.getDuration())
        best = n;
    }
    return best;
  }
    
}
