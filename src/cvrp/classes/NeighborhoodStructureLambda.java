/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.TabuListFullException;
import cvrp.interfaces.NeighborhoodStructure;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicente
 */
public class NeighborhoodStructureLambda implements NeighborhoodStructure {
  
  private NeighborhoodStructure classic = new NeighborhoodStructureClassic();
  private NeighborhoodStructure swap = new NeighborhoodStructureSwap();
  private NeighborhoodStructure twoOpt = new NeighborhoodStructureTwoOpt();
  private int changeStructure = 0;
 
  @Override
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) 
          throws TabuListFullException {
    List<Neighbor> l = new ArrayList<Neighbor>();
        
    //if(this.changeStructure == 10) {
      //l.addAll(swap.generateNeighborhood(s, tabuList));
      //this.changeStructure = 0;
    //} else
    //  if (s.getInstance().getTerminationCriteria().iterationsWithoutImproving() >
            //  s.getInstance().getCustomersNumber() * 5){
      //l.addAll(swap.generateNeighborhood(s, tabuList)); // Esta muy lento
      l.addAll(twoOpt.generateNeighborhood(s, tabuList)); // Mejora
      //}
      l.addAll(classic.generateNeighborhood(s, tabuList)); // Mejora 
      
    //this.changeStructure++;
    return l;
  }
   
}
