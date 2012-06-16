/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.abstracts;

import cvrp.classes.Solution;
import cvrp.exceptions.TerminationCriteriaNotStartedException;

/** A termination criteria specify when to stop the search.
 * 
 * Usually, the termination criterias used are:
 * 
 * After a fixed number of iterations
 * After a fixed number of iterations without improvement
 * When an specific threshold value is reached.
 * After a fixed time.
 *
 * @author tamerdark
 */
public abstract class  TerminationCriteria {
    
    private int currentIteration;
    private int bestFoundIteration;
    private long startTime;
    private long endTime;
    private long bestFoundTime;
    private Solution best;
    private boolean started = false;
    
    public void start(){
        currentIteration = 0;
        bestFoundIteration = 0;
        endTime = -1;
        startTime = System.currentTimeMillis();
        bestFoundTime = startTime;
    }
    
    public void recordBest(Solution s) 
            throws TerminationCriteriaNotStartedException {
        if (!started) throw new TerminationCriteriaNotStartedException();
        best = s;
        bestFoundTime = System.currentTimeMillis();
        bestFoundIteration = currentIteration;
    }
    
    public abstract boolean timeToFinish(Solution s) throws TerminationCriteriaNotStartedException;
}
