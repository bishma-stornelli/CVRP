package cvrp.classes;

import cvrp.exceptions.TabuListFullException;
import cvrp.interfaces.NeighborhoodStructure;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class NeighborhoodStructureLambda implements NeighborhoodStructure {
  
  private NeighborhoodStructure classic = new NeighborhoodStructureClassic();
  private NeighborhoodStructure swap = new NeighborhoodStructureSwap();
  private NeighborhoodStructure twoOpt = new NeighborhoodStructureTwoOpt();
 
  
  /**
   * Return the neighborhood by applying the lambda structure generator.
   * 
   * @param s a solution
   * @param tabuList a tabu list
   * @return the lambda neighborhood
   * @throws TabuListFullException 
   */  
  @Override
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) 
          throws TabuListFullException {
    List<Neighbor> l = new ArrayList<Neighbor>();
        
    // l.addAll(swap.generateNeighborhood(s, tabuList));
    l.addAll(twoOpt.generateNeighborhood(s, tabuList));
    l.addAll(classic.generateNeighborhood(s, tabuList)); 
      
    return l;
  }
   
}
