/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

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
    
    private int currentIteration = 0;
    private int bestFoundIteration = 0;
    private long startTime;
    private long endTime = -1;
    private long bestFoundTime;
    private Solution best;
    
    public TerminationCriteria(){
        startTime = System.currentTimeMillis();
        bestFoundTime = startTime;
    }
    
    public void reset(){
        currentIteration = 0;
        bestFoundIteration = 0;
        endTime = -1;
        startTime = System.currentTimeMillis();
        bestFoundTime = startTime;
    }
    
    public void recordBest(Solution s){
        best = s;
        bestFoundTime = System.currentTimeMillis();
        bestFoundIteration = currentIteration;
    }
    
    public abstract boolean timeToFinish(Solution s);
}
