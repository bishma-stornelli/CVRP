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
public class IterationTerminationCriteria extends TerminationCriteria {

  private int endIteration;
  
  public IterationTerminationCriteria(int endIteration) {
        super();
        this.endIteration = endIteration;
  }
  
  @Override
  public boolean timeToFinish(Solution s) throws TerminationCriteriaNotStartedException {   
    ++this.currentIteration;
    return (this.currentIteration == this.endIteration);
  }
  
}