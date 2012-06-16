/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.interfaces.NeighborSelector;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class FirstNeighborSelector implements NeighborSelector {

    @Override
    public Neighbor selectNeighbor(List<Neighbor> neighbors, Solution bestFound) {
        Neighbor best = neighbors.get(0);
        for (Neighbor n: neighbors){
            if(n.getDuration() < bestFound.getDuration()){
                return n;
            }
            if( n.getDuration() < best.getDuration() ){
                best = n;
            }
        }
        return best;
    }
    
}
