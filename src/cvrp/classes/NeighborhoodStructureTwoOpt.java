/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.interfaces.NeighborhoodStructure;
import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.UnexpectedAmountOfCustomersException;
import cvrp.interfaces.Move;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tamerdark
 */
public class NeighborhoodStructureTwoOpt implements NeighborhoodStructure {

  private int edge1; // Last edge removed #1
  private int edge2; // Last edge removed #2

  @Override
  public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList) 
          throws TabuListFullException {
    int routesNumber = s.getInstance().getCustomersNumber();
    List<Neighbor> neighbors = new ArrayList<Neighbor>(routesNumber);
    for(int i = 0 ; i < routesNumber ; ++i) {
      if(s.getRoute(i).size() > 4) {
        try {
          neighbors.add(this.generateNeighbor(s, tabuList, i, -1, -1));
        } catch (UnexpectedAmountOfCustomersException ex) {
            //Logger.getLogger(NeighborhoodStructureTwoOpt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MaxCapacityExceededException ex) {
            // Logger.getLogger(NeighborhoodStructureTwoOpt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MaxDurationExceededException ex) {
            // Logger.getLogger(NeighborhoodStructureTwoOpt.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return neighbors;
  }

  public Neighbor generateNeighbor(Solution s, List<Tabu> tabuList, int route, 
          int pos1, int pos2) throws UnexpectedAmountOfCustomersException, 
          TabuListFullException, MaxCapacityExceededException, MaxDurationExceededException {
    Route r = s.getRoute(route);
    Random random = new Random();
    
    edge1 = pos1 < 0 ? random.nextInt(r.size() - 3) + 1 : pos1;
    edge2 = pos2 < 0 ? random.nextInt(r.size() - (edge1 + 2)) + (edge1 + 1) : pos2;
    Move m = new MoveTwoOpt(route, edge1, edge2);
    return new Neighbor(s,m);
  }
}
