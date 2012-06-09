/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

/**
 *
 * @author tamerdark
 */
public class RoutePositionTabu implements Tabu {
    private int customer;
    private int route;
    private int previousCustomer;
    private int laterCustomer;

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getLaterCustomer() {
        return laterCustomer;
    }

    public void setLaterCustomer(int laterCustomer) {
        this.laterCustomer = laterCustomer;
    }

    public int getPreviousCustomer() {
        return previousCustomer;
    }

    public void setPreviousCustomer(int previousCustomer) {
        this.previousCustomer = previousCustomer;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }
}
