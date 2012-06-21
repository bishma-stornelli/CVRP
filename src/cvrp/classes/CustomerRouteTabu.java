/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

/**
 *
 * @author tamerdark
 */
public class CustomerRouteTabu extends CustomerTabu {

  private int route;

  public CustomerRouteTabu(int route, int customer) {
    super(customer);
    this.route = route;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    try {
      final CustomerTabu other = (CustomerTabu) obj;
      if (this.getCustomer() != other.getCustomer()) {
        return false;
      }
      try {
        final CustomerRouteTabu other2 = (CustomerRouteTabu) obj;
        if (this.route != other2.route) {
          return false;
        }
        return true;
      } catch (ClassCastException cce) {
          return true;
      }
    } catch (ClassCastException cce) {
        return false;
    }
  }
  
  // Getters and Setters

  public int getRoute() {
    return route;
  }

  public void setRoute(int route) {
    this.route = route;
  }
  
}
