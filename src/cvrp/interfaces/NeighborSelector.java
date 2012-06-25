package cvrp.interfaces;

import cvrp.classes.Neighbor;
import cvrp.classes.Solution;
import java.util.List;

/** 
 * Select the neighbor where the meta-heuristic should move.
 * 
 * It may be:
 * 
 * The best of the neighborhood
 * The first better
 */

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public interface NeighborSelector {
    
  public Neighbor selectNeighbor(List<Neighbor> neighbors, Solution bestFound) 
          throws IndexOutOfBoundsException;
    
}
