/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.interfaces.Tabu;

/**
 *
 * @author tamerdark
 */
public class CustomerTabu implements Tabu {
    
    private int customer;

    public CustomerTabu(int customer) {
        this.customer = customer;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try{
            final CustomerTabu other = (CustomerTabu) obj;
            if (this.customer != other.customer) {
                return false;
            }
            return true;
        }catch(ClassCastException cce){
            return false;
        }        
    }
    
    
}
