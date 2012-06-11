/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

/** A neighborhood generator determines how many neighbors and how they are going to be selected
 * in order to make a neighborhood from a given solution.
 * 
 * Examples of classes that implements this interface could be:
 * 
 * FullNeighborhoodGenerator generate all the neighborhoods in the search space
 * RandomNeighborhoodGenerator generate a random subset of the neighborhoods in the search space
 * GranularNeighborhoodGenerator only short arcs are considered to generate neighborhoods.
 *
 * @author tamerdark
 */
public interface NeighborhoodGenerator {
    
}
