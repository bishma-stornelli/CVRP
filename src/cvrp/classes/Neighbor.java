/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.classes.RoutePositionTabu;
import cvrp.classes.RouteTabu;
import cvrp.classes.Solution;
import cvrp.classes.Route;
import cvrp.abstracts.Move;
import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.interfaces.Tabu;
import cvrp.exceptions.NoSuchTabuTypeException;

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
        int customer = move.getCustomer();
        int customerPos = neighbor.getCustomerPosition(customer);
        this.duration = neighbor.getDuration();
        Route originRoute = neighbor.getRoute(neighbor.getRouteNumber(customer));
        Route targetRoute = move.getTargetRoute();
        Instance i = neighbor.getInstance();
        this.duration += originRoute.remove(customerPos, s.getInstance(), false);
        this.duration += targetRoute.add(customer, customerPos, s.getInstance(), false);
          
        
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
        switch(tabuType){
            case ROUTE_POSITION_TABU:
                return new RoutePositionTabu();
            case ROUTE_TABU:
                return new RouteTabu();
            default:
                throw new NoSuchTabuTypeException();
        }
    }
}
