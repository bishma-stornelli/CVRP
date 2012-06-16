/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.abstracts;

import cvrp.classes.Route;

/**
 * 
 * @author tamerdark
 */
public abstract class Move {

    private int customer; // The customer to be moved
    private int targetPosition; // The position in the origin_route from where the customer is going to be inserted
    private Route targetRoute; // The route where the customer is going to be moved

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

    public void setTargetRoute(Route r) {
        this.targetRoute = r;
    }
    public Route getTargetRoute(){
        return this.targetRoute;
    }
    
    
}
