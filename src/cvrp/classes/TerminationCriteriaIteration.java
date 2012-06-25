package cvrp.classes;

import cvrp.abstracts.TerminationCriteria;
import cvrp.exceptions.TerminationCriteriaNotStartedException;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class TerminationCriteriaIteration extends TerminationCriteria {

  private int endIteration;
  
  public TerminationCriteriaIteration(int endIteration) {
    super();
    this.endIteration = endIteration;
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
    ++this.currentIteration;
    return (this.currentIteration == this.endIteration);
  }

  // Getters and Setters
  
  public int getEndIteration() {
    return endIteration;
  }

  public void setEndIteration(int endIteration) {
    this.endIteration = endIteration;
  }
  
}
