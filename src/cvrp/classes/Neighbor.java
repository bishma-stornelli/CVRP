/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.abstracts.Move;
import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.interfaces.Tabu;
import cvrp.exceptions.NoSuchTabuTypeException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class Neighbor {
    private Solution neighbor;
    private Move move;
    private int duration;

    public Neighbor(Solution s, Move move) 
            throws MaxCapacityExceededException, MaxDurationExceededException {
        this.neighbor = s;
        this.move = move;
        this.duration = move.applyMoves(neighbor, false);
        
        
//        int customer = move.getCustomer();
//        int customerPos = neighbor.getCustomerPosition(customer);
//        this.duration = neighbor.getDuration();
//        Route originRoute = neighbor.getRoute(neighbor.getRouteNumber(customer));
//        Route targetRoute = neighbor.getRoute(move.getTargetRoute());
//        Instance i = neighbor.getInstance();
//        this.duration += originRoute.remove(customerPos, s.getInstance(), false);
//        this.duration += targetRoute.add(customer, move.getTargetPosition(), s.getInstance(), false);
    }

    public int getDuration() {
        return duration;
    }

    public Move getMove() {
        return move;
    }

    public Solution getNeighbor() {
        return neighbor;
    }


    public Solution applyMove() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Tabu getTabu(char tabuType) throws NoSuchTabuTypeException {
        /*switch(tabuType){
            case ROUTE_POSITION_TABU:
                return new RoutePositionTabu();
            case ROUTE_TABU:
                return new RouteTabu();
            default:
                throw new NoSuchTabuTypeException();
        }*/
        return null;
    }

    public List<Tabu> getTabus() {
        List<Tabu> l = new ArrayList<Tabu>(1);
        l.addAll(move.generateTabu());
        return l;
    }

    
}
