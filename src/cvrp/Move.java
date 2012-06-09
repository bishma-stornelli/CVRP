/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

/**
 * 
 * @author tamerdark
 */
public class Move {

    private int customer; // The customer to be moved
    private int origin_position; // The position in the origin_route from where the customer is going to be removed 
    private int target_position; // The position in the origin_route from where the customer is going to be inserted
    private int origin_route; // The route where the customer is before the move
    private int target_route; // The route where the customer is going to be moved

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getOrigin_position() {
        return origin_position;
    }

    public void setOrigin_position(int origin_position) {
        this.origin_position = origin_position;
    }

    public int getOrigin_route() {
        return origin_route;
    }

    public void setOrigin_route(int origin_route) {
        this.origin_route = origin_route;
    }

    public int getTarget_position() {
        return target_position;
    }

    public void setTarget_position(int target_position) {
        this.target_position = target_position;
    }

    public int getTarget_route() {
        return target_route;
    }

    public void setTarget_route(int target_route) {
        this.target_route = target_route;
    }
    
    
}
