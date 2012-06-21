/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.abstracts.TerminationCriteria;
import cvrp.exceptions.TerminationCriteriaNotStartedException;

/**
 *
 * @author vicente
 */
public class TerminationCriteriaIteration extends TerminationCriteria {

  private int endIteration;
  
  public TerminationCriteriaIteration(int endIteration) {
    super();
    this.endIteration = endIteration;
  }
  
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
