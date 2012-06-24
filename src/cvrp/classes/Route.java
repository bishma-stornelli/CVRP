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
public class Route {

  private List<Integer> route;
  private int capacity;
  private int duration;
  private int dropTime;

  public Route() {
    this.route = new ArrayList<Integer>();
    this.route.add(0);
    this.route.add(0);
    this.capacity = 0;
    this.duration = 0;
    this.dropTime = 0;
  }
  
  public void push(int customer, Instance instance) throws 
          MaxCapacityExceededException, MaxDurationExceededException {
    this.add(customer, this.size() - 1, instance);
  }
  
  public int add(int customer, int index, Instance instance) 
          throws MaxCapacityExceededException, MaxDurationExceededException {
    
    int demand = instance.getDemand(customer);
    if (demand + this.capacity > instance.getVehicleCapacity()) {
      throw new MaxCapacityExceededException();
    }
    
    int previous = this.route.get(index - 1);
    int next = this.route.get(index);
    int changeOnDuration = instance.getDistance(previous, customer)
                         + instance.getDistance(customer, next)
                         - instance.getDistance(previous, next);
    
    if (this.duration + instance.getDropTime() +changeOnDuration > instance.getMaximumRouteTime()) {
      throw new MaxDurationExceededException();
    }
    this.route.add(index, customer);
    this.capacity += demand;
    this.duration += changeOnDuration;
    this.dropTime += instance.getDropTime();
    
    return changeOnDuration;
  }

  public int remove(int index, Instance instance) 
          throws MaxDurationExceededException {

    int previous = this.route.get(index - 1);
    int customer = this.route.get(index);
    int next = this.route.get(index + 1);
    int changeOnDuration = instance.getDistance(previous, next) 
                         - instance.getDistance(previous, customer)
                         - instance.getDistance(customer, next);
    
    if (this.duration - instance.getDropTime() + changeOnDuration > instance.getMaximumRouteTime()) {
      throw new MaxDurationExceededException();
    }
    
    this.route.remove(index);
    this.duration += changeOnDuration;
    this.capacity -= instance.getDemand(customer);
    this.dropTime -= instance.getDropTime();
   
    return changeOnDuration;
  }

  @Override
  public String toString() {
    String r = "";
    for (Integer i : route) {
        r += i + " ";
    }
    return r;
  }

  public int size() {
      return route.size();
  }

  public int getCustomerAt(int index) {
      return this.route.get(index);
  }
  
  // Getters and Setters

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
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

  void calculateDuration(Instance ins) {
    int newDuration = 0;
    for(int i = 0 ; i < route.size() - 1 ; ++i){
        newDuration += ins.getDistance(route.get(i), route.get(i+1));
    }
    newDuration += ins.getDropTime() * (route.size() - 2);
    if (newDuration != this.duration){
        System.out.println("Error con la duracion de la ruta");
    }
  }
  
}
