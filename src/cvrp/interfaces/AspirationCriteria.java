/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.interfaces;

/** An aspiration criteria is an exception to a tabu.
 * 
 * Aspiration criterias allow to move to a neighbor even if the movements required
 * to obtain it are tabu.
 * 
 * One example is:
 * 
 * Allow a move, even if it's tabu, if it results in a solution better than the best-known solution.
 *
 * @author tamerdark
 */
public interface AspirationCriteria {
    
}
