package cvrp.interfaces;

/** 
 * An aspiration criteria is an exception to a tabu.
 * 
 * Aspiration criteria allow to move to a neighbor even if the movements required
 * to obtain it are tabu.
 * 
 * One example is:
 * 
 * Allow a move, even if it's tabu, if it results in a solution better than the best-known solution.
 */

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public interface AspirationCriteria {
    
}
