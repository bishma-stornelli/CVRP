package cvrp.classes;

import cvrp.abstracts.TerminationCriteria;
import cvrp.exceptions.TerminationCriteriaNotStartedException;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class TerminationCriteriaImproving extends TerminationCriteria {
    
  private int threshold;

  public TerminationCriteriaImproving(int i) {
    this.threshold = i;
  }
  
  /**
   * Evaluates the improving termination criteria.
   * 
   * @param s a solution
   * @return true if it has completed the execution time for the current solution
   *         false if not
   * @throws TerminationCriteriaNotStartedException
   */
  @Override
  public boolean timeToFinish(Solution s) throws TerminationCriteriaNotStartedException {
    if (!started) throw new TerminationCriteriaNotStartedException();
    ++currentIteration;
    return (currentIteration - bestFoundIteration) > this.threshold;
  }

  // Getters and Setters
  
  public int getThreshold() {
    return threshold;
  }

  public void setThreshold(int threshold) {
    this.threshold = threshold;
  }
    
}
