package cvrp.classes;

import cvrp.interfaces.Tabu;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class CustomerTabu implements Tabu {
    
  private int customer;

  public CustomerTabu(int customer) {
    this.customer = customer;
  }

  /**
   * Equal method for the CustomerTabu.
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
      if (this.customer != other.customer)
        return false;
      return true;
    } catch(ClassCastException cce) {
      return false;
    }        
  }

  // Getters and Setters
  
  public int getCustomer() {
    return customer;
  }

  public void setCustomer(int customer) {
    this.customer = customer;
  }
       
}
