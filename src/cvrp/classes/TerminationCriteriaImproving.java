/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.abstracts.TerminationCriteria;
import cvrp.exceptions.TerminationCriteriaNotStartedException;

/**
 *
 * @author tamerdark
 */
public class TerminationCriteriaImproving extends TerminationCriteria {
    
  private int threshold;

  public TerminationCriteriaImproving(int i) {
    this.threshold = i;
  }

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
