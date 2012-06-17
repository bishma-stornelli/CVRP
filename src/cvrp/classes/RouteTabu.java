/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.interfaces.Tabu;

/**
 *
 * @author tamerdark
 */
public class RouteTabu implements Tabu{
    private int customer;
    private int route;

    public RouteTabu(int customer, int route) {
        this.customer = customer;
        this.route = route;
    }    

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RouteTabu other = (RouteTabu) obj;
        if (this.customer != other.customer) {
            return false;
        }
        if (this.route != other.route) {
            return false;
        }
        return true;
    }    
    
}
