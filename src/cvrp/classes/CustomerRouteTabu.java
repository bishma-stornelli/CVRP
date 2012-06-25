package cvrp.classes;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class CustomerRouteTabu extends CustomerTabu {

  private int route;

  public CustomerRouteTabu(int route, int customer) {
    super(customer);
    this.route = route;
  }
  
  /**
   * Equal method for the CustomerRouteTabu.
   * 
   * @param obj an object
   * @return true if the two tabus are equals
   *         false if not
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    
    try {
      final CustomerTabu other = (CustomerTabu) obj;
      if (this.getCustomer() != other.getCustomer()) 
        return false;
      
      try {
        final CustomerRouteTabu other2 = (CustomerRouteTabu) obj;
        if (this.route != other2.route) 
          return false;
        
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
