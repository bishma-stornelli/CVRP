/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

/** A neighborhood structure is the set of neighbors that can be obtained by
 * applying a determined kind of movement to a given solution.
 * 
 * The neighborhood structure can be:
 * 
 * Those neighbors obtained by moving a single customer from its current route,
 *  inserting it in the same route or in another route with sufficient residual capacity.
 * Those obtained by applying λ-interchanges where a customer can be moved from 
 *  its current route or can be swapped with another customer.
 * Those obtained by ejection chains that are sequences of coordinated movements
 *  of customer from one route to another.
 * Swapping of sequences of several customers between routes, as in the cross-exchange.
 * 
 * When a customer is inserted in a route there are many places where you can put it:
 * 
 * Random
 * Best place
 * Generalized Insertion 
 *
 * @author tamerdark
 */
public interface NeighborhoodStructure {
    
}
