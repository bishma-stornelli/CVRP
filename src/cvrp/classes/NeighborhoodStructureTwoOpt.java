package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.UnexpectedAmountOfCustomersException;
import cvrp.interfaces.Move;
import cvrp.interfaces.NeighborhoodStructure;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class NeighborhoodStructureTwoOpt implements NeighborhoodStructure {

  private int edge1; // Last edge removed #1
  private int edge2; // Last edge removed #2

  /**
   * Return the neighborhood by applying the two-opt structure generator.
   * 
   * @param s a solution
   * @param tabuList a tabu list
   * @return the two-opt neighborhood
   * @throws TabuListFullException 
   */
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

  /**
   * Return a neighbor by applying the classic structure generator.
   * 
   * @param s a solution
   * @param tabuList a tabu list
   * @param route a route number
   * @param pos1 a position
   * @param pos2 a position
   * @return the neighbor
   * @throws UnexpectedAmountOfCustomersException
   * @throws TabuListFullException 
   * @throws MaxCapacityExceededException
   * @throws MaxDurationExceededException
   */
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
