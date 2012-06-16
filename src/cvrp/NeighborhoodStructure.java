/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

import java.util.List;

/** A neighborhood structure is the set of neighbors that can be obtained by
 * applying a determined kind of movement to a given solution.
 * 
 * The neighborhood structure can be:
 * 
 * Those neighbors obtained by moving a single customer from its current route,
 *  inserting it in the same route or in another route with sufficient residual capacity.
 * Those obtained by applying λ-interchanges where a customer can be moved from 
 *  its current route or can be swapped with another customer. *
 * Those obtained by ejection chains that are sequences of coordinated movements
 *  of customer from one route to another.
 * Swapping of sequences of several customers between routes, as in the cross-exchange.
 * 
 * When a customer is inserted in a route there are many places where you can put it:
 * 
 * Random
 * Best place
 * Generalized Insertion: tries to improve the route as the same time that it inserts the customer.
 *
 * @author tamerdark
 */
public interface NeighborhoodStructure {
    
    /** Generate a neighborhood by appliying different movements to solution s,
     * using the information in instance i.
     * 
     * @param s the solution from which the neighborhood is going to be generated
     * @param i the instance of the problem with information about arcs and nodes
     * @param tabuList The list of tabu movements
     * @return the list of neighbors generated
     */
    public List<Neighbor> generateNeighborhood(Solution s, Instance i, List<Tabu> tabuList);
}
