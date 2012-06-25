package cvrp.interfaces;

/** An intensificator try to improve a solution by exploring portions of the
 * solution that seem promising to make a better solution.
 * 
 * For example:
 * 
 * Fix the arcs that have been used for the largest number of iterations 
 * and perform a restricted search on the remaining arcs.
 * 
 * Change the Neighborhood Structure to allow more complex movements.
 */

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public interface Intensificator {
    
}
