package cvrp.classes;

import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
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
  
  /**
   * Adds a customer at the end of a route.
   * 
   * @param customer a customer
   * @param instance an instance of the problem
   * @return the change on duration for the route
   * @throws MaxCapacityExceededException
   * @throws MaxDurationExceededException 
   */
  public int push(int customer, Instance instance) throws 
          MaxCapacityExceededException, MaxDurationExceededException {
    return this.add(customer, this.size() - 1, instance);
  }
  
  /**
   * Adds a customer to a route in a specific position.
   * 
   * @param customer a customer
   * @param index the position to add the customer
   * @param instance an instance of the problem
   * @return the change on duration for the route
   * @throws MaxCapacityExceededException
   * @throws MaxDurationExceededException 
   */
  public int add(int customer, int index, Instance instance) 
          throws MaxCapacityExceededException, MaxDurationExceededException {
    
    int demand = instance.getDemand(customer);
    if (demand + this.capacity > instance.getVehicleCapacity())
      throw new MaxCapacityExceededException();
    
    int previous = this.route.get(index - 1);
    int next = this.route.get(index);
    int changeOnDuration = instance.getDistance(previous, customer)
                         + instance.getDistance(customer, next)
                         + instance.getDropTime()
                         - instance.getDistance(previous, next);
    
    if (this.duration + changeOnDuration > instance.getMaximumRouteTime())
      throw new MaxDurationExceededException();
    
    this.route.add(index, customer);
    this.capacity += demand;
    this.duration += changeOnDuration;
    
    return changeOnDuration;
  }

  /**
   * Removes a customer to a route in a specific position.
   * 
   * @param index the position to remove the customer
   * @param instance an instance of the problem
   * @return the change on duration for the route
   * @throws MaxDurationExceededException 
   */
  public int remove(int index, Instance instance) 
          throws MaxDurationExceededException {

    int previous = this.route.get(index - 1);
    int customer = this.route.get(index);
    int next = this.route.get(index + 1);
    int changeOnDuration = instance.getDistance(previous, next) 
                         - instance.getDistance(previous, customer)
                         - instance.getDistance(customer, next)
                         - instance.getDropTime();
    
    if (this.duration + changeOnDuration > instance.getMaximumRouteTime())
      throw new MaxDurationExceededException();
        
    this.route.remove(index);
    this.duration += changeOnDuration;
    this.capacity -= instance.getDemand(customer);
   
    return changeOnDuration;
  }
  
  /**
   * Check the correctness of the route duration.
   * 
   * @param ins an instance of the problem
   */
  void calculateDuration(Instance ins) {
      int newDuration = 0;
      for(int i = 0 ; i < route.size() - 1 ; ++i)
        newDuration += ins.getDistance(route.get(i), route.get(i+1));
      
      newDuration += ins.getDropTime() * (route.size() - 2);
      if (newDuration != this.duration)
        System.out.println("Route Duration Violation");
      
  }

  /**
   * Generate the string representation of the route.
   * 
   * @return the route as a string
   */
  @Override
  public String toString() {
    String r = "";
    for (Integer i : route) {
      r += i + " ";
    }
    return r;
  }

  /**
   * The size of the route.
   * 
   * @return the route size
   */
  public int size() {
    return route.size();
  }

  /**
   * Return the customer at the specific position.
   * 
   * @param index a position inside the route
   * @return the customer
   */
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
  
}
