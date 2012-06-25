package cvrp.classes;

import cvrp.interfaces.NeighborSelector;
import java.util.List;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class NeighborSelectorFirst implements NeighborSelector {

  /**
   * Return the first neighbor from the neighborhood.
   * 
   * @param neighbors the neighborhood
   * @param bestFound a solution
   * @return the first neighbor from the neighborhood
   * @throws IndexOutOfBoundsException 
   */
  @Override
  public Neighbor selectNeighbor(List<Neighbor> neighbors, Solution bestFound) 
          throws IndexOutOfBoundsException {
    Neighbor best = neighbors.get(0);
    for (Neighbor n: neighbors) {
      if(n.getDuration() < bestFound.getDuration()) 
        return n;
      
      if(n.getDuration() < best.getDuration()) 
        best = n;  
    }
    return best;
  }
    
}
