/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.abstracts;

import cvrp.classes.CustomerRouteTabu;
import cvrp.interfaces.Tabu;

/**
 * 
 * @author tamerdark
 */
public abstract class Move {

    protected int customer; // The customer to be moved
    protected int targetPosition; // The position in the origin_route from where the customer is going to be inserted
    protected int targetRoute; // The route where the customer is going to be moved

    public Move(int customer, int targetRoute, int targetPosition) {
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

    public Tabu generateTabu() {
        return new CustomerRouteTabu(customer, targetRoute);
    }
    
    
}
