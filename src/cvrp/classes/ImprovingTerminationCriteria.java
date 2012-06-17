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
public class ImprovingTerminationCriteria extends TerminationCriteria {
    
    private int threshold;

    ImprovingTerminationCriteria(int i) {
        this.threshold = i;
    }

    @Override
    public boolean timeToFinish(Solution s) throws TerminationCriteriaNotStartedException {
        if (!started) throw new TerminationCriteriaNotStartedException();
        ++currentIteration;
        return (currentIteration - bestFoundIteration) > this.threshold;
    }
    
}
