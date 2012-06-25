package cvrp.classes;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class CustomerRouteTabuPosition extends CustomerRouteTabu {

  private int previousCustomer;
  private int laterCustomer;

  public CustomerRouteTabuPosition(int previousCustomer, int laterCustomer, int route, int customer) {
    super(route, customer);
    this.previousCustomer = previousCustomer;
    this.laterCustomer = laterCustomer;
  }

  /**
   * Equal method for the CustomerRouteTabuPosition.
   * 
   * @param obj an object
   * @return true if the two tabus are equals
   *         false if not
   */
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
        if (this.getRoute() != other2.getRoute()) {
          return false;
        }
        try {
          final CustomerRouteTabuPosition other3 = (CustomerRouteTabuPosition) obj;
          if (this.laterCustomer != other3.laterCustomer || 
              this.previousCustomer != other3.previousCustomer) {
            return false;
          }
          return true;
        } catch (ClassCastException cce) {
            return true;
        }
      } catch (ClassCastException cce) {
          return true;
      }
    } catch (ClassCastException cce) {
        return false;
    }
  }
  
  // Getters and Setters

  public int getLaterCustomer() {
    return laterCustomer;
  }

  public void setLaterCustomer(int laterCustomer) {
    this.laterCustomer = laterCustomer;
  }

  public int getPreviousCustomer() {
    return previousCustomer;
  }

  public void setPreviousCustomer(int previousCustomer) {
    this.previousCustomer = previousCustomer;
  }
  
}
