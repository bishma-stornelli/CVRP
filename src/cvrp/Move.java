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
    private int originPosition; // The position in the origin_route from where the customer is going to be removed 
    private int targetPosition; // The position in the origin_route from where the customer is going to be inserted
    private int originRoute; // The route where the customer is before the move
    private int targetRout; // The route where the customer is going to be moved

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getOriginPosition() {
        return originPosition;
    }

    public void setOriginPosition(int originPosition) {
        this.originPosition = originPosition;
    }

    public int getOriginRoute() {
        return originRoute;
    }

    public void setOriginRoute(int originRoute) {
        this.originRoute = originRoute;
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(int targetPosition) {
        this.targetPosition = targetPosition;
    }

    public int getTargetRout() {
        return targetRout;
    }

    public void setTargetRout(int targetRout) {
        this.targetRout = targetRout;
    }
    
    
    
}
