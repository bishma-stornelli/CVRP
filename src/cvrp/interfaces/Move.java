package cvrp.interfaces;

import cvrp.classes.Solution;
import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import java.util.List;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public interface Move {
  
  public List<Tabu> generateTabu();
          
  public void applyMove(Solution solution) 
          throws MaxCapacityExceededException, MaxDurationExceededException;
  
  public int applyMoveDuration(Solution solution)
          throws MaxCapacityExceededException, MaxDurationExceededException;

}
