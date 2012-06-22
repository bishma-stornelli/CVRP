/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.exceptions.NoSuchTabuTypeException;
import cvrp.interfaces.Move;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tamerdark
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

  public Tabu getTabu(char tabuType) throws NoSuchTabuTypeException { 
      /*switch(tabuType){
          case ROUTE_POSITION_TABU:
              return new RoutePositionTabu();
          case ROUTE_TABU:
              return new RouteTabu();
          default:
              throw new NoSuchTabuTypeException();
      }*/
      return null;
  }

  public List<Tabu> getTabus() {
      List<Tabu> l = new ArrayList<Tabu>(1);
      l.addAll(move.generateTabu());
      return l;
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
