package cvrp.interfaces;

/** 
 * A tabu is a restriction on a given solution that prohibits to move to a 
 * neighbor to avoid cycling.
 * 
 * Possible tabus for the CVRP are:
 * 
 * Prohibit to move a customer C from route A to B for a number given of iterations
 * Prohibit to move a customer C to a route A for a number given of iterations
 * Prohibit to move a customer C for a number given of iterations
 */

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public interface Tabu {
    
}
