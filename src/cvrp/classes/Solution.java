/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class Solution {
    private List<Route> routes;
    private Instance instance;
    // The route inside routes where the customer is
    private int customerRoute[];
    // The customer position inside the route.
    private int customerPosition[];
    private int duration;
    
    public Solution(){}

    public Solution(Instance i) throws 
            MaxCapacityExceededException, MaxDurationExceededException {
        this.routes = new ArrayList<Route>();
        this.instance = i;
        this.customerRoute = new int[i.getCustomersNumber() + 1];
        this.customerPosition = new int[i.getCustomersNumber() + 1];
        this.duration = 0;
        for(int customer = 1 ; 
                customer < this.instance.getCustomersNumber() ; 
                ++customer){
            Route r = new Route();
            r.push(customer, instance);
            this.duration += r.getDuration();
            this.routes.add(r);
            this.customerPosition[customer] = 1;
            this.customerRoute[customer] = customer - 1;
        }
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public int getDuration() {
        return duration;
    }

    public List<Route> getRoutes() {
        return routes;
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
    public int getRouteNumber(int customer) {
        return customerRoute[customer];
    }

    public int getCustomerPosition(int customer) {
        return customerPosition[customer];
    }

    public Route getRoute(int targetRoute) {
        return this.routes.get(targetRoute);
    }
    
    
    
}
