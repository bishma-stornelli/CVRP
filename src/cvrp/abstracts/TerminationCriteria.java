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
    
    public void recordBest(Solution s) 
            throws TerminationCriteriaNotStartedException {
        if (!started) throw new TerminationCriteriaNotStartedException();
        best = s;
        bestFoundTime = System.currentTimeMillis();
        bestFoundIteration = currentIteration;
    }
    
    public abstract boolean timeToFinish(Solution s) throws TerminationCriteriaNotStartedException;
    
    public void finish(){
        endTime = System.currentTimeMillis();
    }

    public int getBestFoundIteration() {
        return bestFoundIteration;
    }

    public int getCurrentIteration() {
        return currentIteration;
    }
    
    public long getTimeToBest() {
        return (bestFoundTime - startTime)/1000;
    }
    public long getTotalTime() {
        return (endTime - startTime)/1000;
    }
}
