package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.interfaces.Move;
import cvrp.interfaces.Tabu;
import java.util.List;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class Neighbor {
  
  private Solution neighbor;
  private Move move;
  private int duration;

  public Neighbor(Solution s, Move move) 
          throws MaxCapacityExceededException, MaxDurationExceededException {
    this.neighbor = s;
    this.move = move;
    this.duration = move.applyMoveDuration(neighbor);
  }

  /**
   * Return the tabu for this neighbor.
   * 
   * @return the neighbor's tabu
   */
  public List<Tabu> getTabus() {
    return move.generateTabu();
  }
  
  // Getters and Setters

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public Move getMove() {
    return move;
  }

  public void setMove(Move move) {
    this.move = move;
  }

  public Solution getNeighbor() {
    return neighbor;
  }

  public void setNeighbor(Solution neighbor) {
    this.neighbor = neighbor;
  }
     
}
