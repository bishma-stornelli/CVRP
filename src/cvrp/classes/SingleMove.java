/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.interfaces.Move;
import cvrp.interfaces.Tabu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class SingleMove implements Move {

    protected int customer; // The customer to be moved
    protected int targetPosition; // The position in the origin_route from where the customer is going to be inserted
    protected int targetRoute; // The route where the customer is going to be moved

    public SingleMove(int customer, int targetRoute, int targetPosition) {
        this.customer = customer;
        this.targetPosition = targetPosition;
        this.targetRoute = targetRoute;
    }    

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(int targetPosition) {
        this.targetPosition = targetPosition;
    }

    public void setTargetRoute(int r) {
        this.targetRoute = r;
    }
    public int getTargetRoute(){
        return this.targetRoute;
    }

    @Override
    public List<Tabu> generateTabu() {
        List<Tabu> l = new ArrayList<Tabu>();
        l.add(new CustomerRouteTabu(customer, targetRoute));
        return l;
    }

    @Override
    public int applyMoves(Solution aThis, boolean commit) throws MaxCapacityExceededException, MaxDurationExceededException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
