/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

import cvrp.classes.Neighbor;
import cvrp.classes.Solution;
import cvrp.interfaces.Tabu;
import cvrp.interfaces.NeighborhoodStructure;
import cvrp.interfaces.NeighborhoodGenerator;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class FullNeighborhoodGenerator implements NeighborhoodGenerator {

    @Override
    public List<Neighbor> generateNeighborhood(Solution s, Instance i, List<Tabu> tabuList) {
        NeighborhoodStructure ns = i.getNeighborhoodStructure();
        return ns.generateNeighborhood(s, i, tabuList);
    }
    
}
