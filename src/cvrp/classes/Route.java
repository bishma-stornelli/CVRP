/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import java.util.List;

/**
 *
 * @author tamerdark
 */
public class Route {
    private List<Integer> route;
    private int truck;
    private int cost;
    private int duration;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Integer> getRoute() {
        return route;
    }

    public void setRoute(List<Integer> route) {
        this.route = route;
    }

    public int getTruck() {
        return truck;
    }

    public void setTruck(int truck) {
        this.truck = truck;
    }

    @Override
    public String toString() {
        String r = "";
        for(Integer i: route){
            r += i + " ";
        }
        return r;
    }
    
    
}
