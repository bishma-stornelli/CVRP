/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

import java.util.List;

/**
 *
 * @author tamerdark
 */
public class Solution {
    private List<Route> routes;
    private int cost;

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
    
    
}
