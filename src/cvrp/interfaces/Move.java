/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.interfaces;

import cvrp.classes.Solution;
import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import java.util.List;

/**
 * 
 * @author tamerdark
 */
public interface Move {

    public int applyMoves(Solution aThis, boolean commit) throws 
            MaxCapacityExceededException, MaxDurationExceededException;

    public List<Tabu> generateTabu();
    
    
    
}
