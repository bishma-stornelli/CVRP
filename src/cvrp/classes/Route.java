/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.MaxDurationExceededException;
import cvrp.exceptions.MaxCapacityExceededException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class Route {

    private List<Integer> route;
    private int capacity;
    private int duration;

    public Route() {
        this.route = new ArrayList<Integer>();
        this.route.add(0);
        this.route.add(0);
        this.capacity = 0;
        this.duration = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        String r = "";
        for (Integer i : route) {
            r += i + " ";
        }
        return r;
    }

    public void push(int customer, Instance instance) throws
            MaxCapacityExceededException, MaxDurationExceededException {
        this.add(customer, this.size() - 1, instance, true);
    }

    public int remove(int index, Instance instance, boolean commit) {
        Integer previous = null,
                customer = null,
                next = null;
        previous = this.route.get(index - 1);
        customer = this.route.get(index);
        next = this.route.get(index + 1);
        int changeOnDuration = instance.getDistance(previous, next)
                - instance.getDistance(previous, customer)
                - instance.getDistance(customer, next)
                - instance.getDropTime();
        if (commit) {
            this.route.remove(index);
            this.duration += changeOnDuration;
            this.capacity -= instance.getDemand(customer);
        }
        return changeOnDuration;
    }

    public int add(int customer, int index, Instance instance, boolean commit)
            throws MaxCapacityExceededException, MaxDurationExceededException {
        int demand = instance.getDemand(customer);
        if (demand + this.capacity > instance.getVehicleCapacity()) {
            throw new MaxCapacityExceededException();
        }
        Integer previous = null, next = null;
        previous = this.route.get(index - 1);
        next = this.route.get(index);
        int changeOnDuration = instance.getDistance(previous, customer)
                + instance.getDistance(customer, next)
                + instance.getDropTime()
                - instance.getDistance(previous, next);
        if (this.duration + changeOnDuration > instance.getMaximumRouteTime()) {
            throw new MaxDurationExceededException();
        }
        if (commit) {
            this.route.add(index, customer);
            this.capacity += demand;
            this.duration += changeOnDuration;
        }
        return changeOnDuration;
    }

    public int size() {
        return route.size();
    }

    public int getCustomerAt(int index) {
        return this.route.get(index);
    }
}
