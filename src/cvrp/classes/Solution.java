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
  private int customerRoute[]; // The route inside routes where the customer is
  private int customerPosition[]; // The customer position inside the route.
  private int duration;
    
  public Solution() {}

  public Solution(Instance i) throws 
          MaxCapacityExceededException, MaxDurationExceededException {
    this.routes = new ArrayList<Route>();
    this.instance = i;
    this.customerRoute = new int[i.getCustomersNumber() + 1];
    this.customerPosition = new int[i.getCustomersNumber() + 1];
    this.duration = 0;
    for(int customer = 1; customer <= this.instance.getCustomersNumber(); ++customer) {
      Route r = new Route();
      r.push(customer, instance);
      this.duration += r.getDuration();
      this.routes.add(r);
      this.customerPosition[customer] = 1;
      this.customerRoute[customer] = customer - 1;
    }
  }
  
  public PrintableSolution getPrintableSolution() {
    return new PrintableSolution(this);
  }   

  @Override
  public String toString() {
    String r = "";
    int totalRoutes = 0;
    for(Route route: this.routes) {
      if(route.size() != 2) {                    
        r +=  route + "\n";
        ++totalRoutes;
      }
    }
    return totalRoutes + "\n" + r;
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
  
  public void setCustomerPosition(int customer, int position) {
    this.customerPosition[customer] = position;
  }
  
  public void setCustomerRoute(int customer, int route) {
    this.customerRoute[customer] = route;
  }

  public Route getRoute(int targetRoute) {
    return this.routes.get(targetRoute);
  }

  // Getters and Setters
  
  public int[] getCustomerPosition() {
    return customerPosition;
  }

  public void setCustomerPosition(int[] customerPosition) {
    this.customerPosition = customerPosition;
  }

  public int[] getCustomerRoute() {
    return customerRoute;
  }

  public void setCustomerRoute(int[] customerRoute) {
    this.customerRoute = customerRoute;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public Instance getInstance() {
    return instance;
  }

  public void setInstance(Instance instance) {
    this.instance = instance;
  }

  public List<Route> getRoutes() {
    return routes;
  }

  public void setRoutes(List<Route> routes) {
    this.routes = routes;
  }

    public boolean correct() {
        int ri = 0;
        int totalDuration = 0;
        for (Route r: this.routes) {
            int rp = 0;
            int capacity = 0 , duration = 0;
            List<Integer> route = r.getRoute();
            for (int i = 0 ; i < route.size() - 1; ++i){
                Integer c = route.get(i);
                Integer c2 = route.get(i+1);
                duration += instance.getDistance(c, c2);
                duration += ( c2 != 0 ? instance.getDropTime() : 0);
                capacity += (c2 != 0 ? instance.getDemand(c2) : 0);
                if (c != 0 && (customerPosition[c] != rp || customerRoute[c] != ri))
                    return false;                    
                ++rp;
            }
            if (capacity > instance.getVehicleCapacity()) {
                System.out.println("Violacion de capacidad");
                return false;
            }
            if (duration > instance.getMaximumRouteTime()) {
                System.out.println("Violacion de duracion");
                return false;
            }
            
            if(duration != r.getDuration()) {
              System.out.println("Violacion de duracion en la ruta");
              return false;
            }
            totalDuration += duration;
            ++ri;
        }
        if(totalDuration != this.duration){
            System.out.println("Violacion de duracion total");
            return false;
        }
        return true;
    }
  
}
