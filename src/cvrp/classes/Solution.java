/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.classes.Route;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class Solution {
    private List<Route> routes;
    private Instance instance;
    private int customerRoute[];
    private int customerPosition[];
    private int cost;

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    
    
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        String r = this.routes.size() + "\n";
        for(Route route: this.routes){
            r += route + "\n";
        }
        return r;
    }

    /** Returns the route where the customer belongs.
     * 
     * @param customer 
     */
    public Route getRoute(int customer) {
        return routes.get(customerRoute[customer]);
    }

    public int getPositionOf(int customer) {
        return customerPosition[customer];
    }
    
    
    
}
