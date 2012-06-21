/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.abstracts;

import cvrp.classes.Solution;
import cvrp.exceptions.TerminationCriteriaNotStartedException;

/** A termination criteria specify when to stop the search.
 * 
 * Usually, the termination criteria used are:
 * 
 * After a fixed number of iterations. Option T
 * After a fixed number of iterations without improvement. Option I
 * When an specific threshold value is reached. Option V
 * After a fixed time. Option T
 *
 * @author tamerdark
 */
public abstract class TerminationCriteria {
    
  protected int currentIteration;
  protected int bestFoundIteration;
  protected long startTime;
  protected long endTime;
  protected long bestFoundTime;
  protected Solution best;
  protected boolean started = false;
    
  public void start() {
    currentIteration = 0;
    bestFoundIteration = 0;
    endTime = -1;
    startTime = System.currentTimeMillis();
    bestFoundTime = startTime;
    started = true;
  }

  public void recordBest(Solution s) throws TerminationCriteriaNotStartedException {
    if (!started) throw new TerminationCriteriaNotStartedException();
    best = s;
    bestFoundTime = System.currentTimeMillis();
    bestFoundIteration = currentIteration;
  }
    
  public abstract boolean timeToFinish(Solution s) throws TerminationCriteriaNotStartedException;

  public void finish(){
      endTime = System.currentTimeMillis();
  }

  public long getTimeToBest(){
      return (bestFoundTime - startTime)/1000;
  }
  public long getTotalTime(){
      return (endTime - startTime)/1000;
  }

  // Getters and Setters
  
  public Solution getBest() {
    return best;
  }

  public void setBest(Solution best) {
    this.best = best;
  }

  public int getBestFoundIteration() {
    return bestFoundIteration;
  }

  public void setBestFoundIteration(int bestFoundIteration) {
    this.bestFoundIteration = bestFoundIteration;
  }

  public long getBestFoundTime() {
    return bestFoundTime;
  }

  public void setBestFoundTime(long bestFoundTime) {
    this.bestFoundTime = bestFoundTime;
  }

  public int getCurrentIteration() {
    return currentIteration;
  }

  public void setCurrentIteration(int currentIteration) {
    this.currentIteration = currentIteration;
  }

  public long getEndTime() {
    return endTime;
  }

  public void setEndTime(long endTime) {
    this.endTime = endTime;
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public boolean isStarted() {
    return started;
  }

  public void setStarted(boolean started) {
    this.started = started;
  }
  
}
