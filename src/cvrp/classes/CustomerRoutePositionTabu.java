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
public class CustomerRoutePositionTabu extends CustomerRouteTabu {

    private int previousCustomer;
    private int laterCustomer;

    public CustomerRoutePositionTabu(int previousCustomer, int laterCustomer, int route, int customer) {
        super(route, customer);
        this.previousCustomer = previousCustomer;
        this.laterCustomer = laterCustomer;
    }

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
                    final CustomerRoutePositionTabu other3 = (CustomerRoutePositionTabu) obj;
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
}
