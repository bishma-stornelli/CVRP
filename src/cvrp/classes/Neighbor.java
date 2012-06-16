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
import cvrp.interfaces.Tabu;
import cvrp.exceptions.NoSuchTabuTypeException;

/**
 *
 * @author tamerdark
 */
public class Neighbor {
    private Solution neighbor;
    private Move move;
    private int cost;

    public int getCost() {
        if ( cost == -1){            
            int customer = move.getCustomer();
            int customerPos = neighbor.getPositionOf(customer);
            cost = neighbor.getCost();
            Route originRoute = neighbor.getRoute(customer);
            Route targetRoute = move.getTargetRoute();
            Instance i = neighbor.getInstance();
            // Remove it from the current route
            cost -= i.getDistance(originRoute.getRoute().get(customerPos - 1) , customer);
            cost -= i.getDistance(customer, originRoute.getRoute().get(customerPos + 1));
            cost += i.getDistance(originRoute.getRoute().get(customerPos - 1), originRoute.getRoute().get(customerPos + 1));
            // Insert it into the new route
            cost -= i.getDistance(targetRoute.getRoute().get(move.getTargetPosition() - 1),
                                  targetRoute.getRoute().get(move.getTargetPosition()));
            cost += i.getDistance(targetRoute.getRoute().get(move.getTargetPosition() - 1),
                                    customer);
            cost += i.getDistance(customer,
                    targetRoute.getRoute().get(move.getTargetPosition()));            
        }
        return cost;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
        this.cost = -1;
    }

    public Solution getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(Solution neighbor) {
        this.neighbor = neighbor;
        this.setMove(null);
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
