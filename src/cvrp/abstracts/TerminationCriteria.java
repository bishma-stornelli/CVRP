package cvrp.abstracts;

import cvrp.classes.Solution;
import cvrp.exceptions.TerminationCriteriaNotStartedException;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */

/** A termination criteria specify when to stop the search.
 * 
 * Usually, the termination criteria used are:
 * 
 * After a fixed number of iterations. Option I
 * After a fixed number of iterations without improvement. Option B
 * When an specific threshold value is reached. Option V. Not implemented.
 * After a fixed time. Option T. Not implemented.
 */
public abstract class TerminationCriteria {
    
  protected int currentIteration;
  protected int bestFoundIteration;
  protected long startTime;
  protected long endTime;
  protected long bestFoundTime;
  protected Solution best;
  protected boolean started = false;
  
  /**
   * Start the termination criteria.
   */
  public void start() {
    currentIteration = 0;
    bestFoundIteration = 0;
    endTime = -1;
    startTime = System.currentTimeMillis();
    bestFoundTime = startTime;
    started = true;
  }
  
  /**
   * Record a solution.
   * 
   * @param s a solution
   * @throws TerminationCriteriaNotStartedException 
   */
  public void recordBest(Solution s) throws TerminationCriteriaNotStartedException {
    if (!started) throw new TerminationCriteriaNotStartedException();
    best = s;
    bestFoundTime = System.currentTimeMillis();
    bestFoundIteration = currentIteration;
  }
  
  public abstract boolean timeToFinish(Solution s) throws TerminationCriteriaNotStartedException;
  
  /**
   * Finish the time counter.
   */
  public void finish(){
    endTime = System.currentTimeMillis();
  }
 
  /**
   * Return the time to found the best solution in seconds.
   * 
   * @return the best found time
   */
  public long getTimeToBest(){
    return (bestFoundTime - startTime)/1000;
  }
  
  /**
   * Return the total execute time.
   * 
   * @return the total execute time
   */
  public long getTotalTime(){
    return (endTime - startTime)/1000;
  }
  
  /**
   * Return the iterations number without improving the solution.
   * 
   * @return iterations without improving
   */
  public int iterationsWithoutImproving() {
    return currentIteration - bestFoundIteration;
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
