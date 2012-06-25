package cvrp.interfaces;

import cvrp.classes.Neighbor;
import cvrp.classes.Solution;
import cvrp.exceptions.TabuListFullException;
import java.util.List;

/** A neighborhood structure is the set of neighbors that can be obtained by
 * applying a determined kind of movement to a given solution.
 * 
 * The neighborhood structure can be:
 * 
 * Those neighbors obtained by moving a single customer from its current route,
 * inserting it in the same route or in another route with sufficient residual capacity.
 * Option: M
 * Those obtained by applying λ-interchanges where a customer can be moved from 
 * its current route or can be swapped with another customer. *
 * Option: L
 * Those obtained by ejection chains that are sequences of coordinated movements
 * of customer from one route to another.
 * Option: C
 * Swapping of sequences of several customers between routes, as in the cross-exchange.
 * Option: S
 * 
 * When a customer is inserted in a route there are many places where you can put it:
 * 
 * Random
 * Best place
 * Generalized Insertion: tries to improve the route as the same time that it inserts the customer.
 */

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public interface NeighborhoodStructure {
 
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) 
          throws TabuListFullException;
  
}
