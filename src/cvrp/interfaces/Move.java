/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.interfaces;

import cvrp.classes.Solution;
import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import java.util.List;

/**
 *
 * @author vicente
 */
public interface Move {
  
  public List<Tabu> generateTabu();
          
  public int applyMoves(Solution solution, boolean commit) 
          throws MaxCapacityExceededException, MaxDurationExceededException;

}
