/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

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
