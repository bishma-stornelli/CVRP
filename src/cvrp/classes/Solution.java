/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.abstracts.Move;
import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tamerdark
 */
public class Solution {
    private List<Route> routes;
    private Instance instance;
    // The route inside routes where the customer is
    private int customerRoute[];
    // The customer position inside the route.
    private int customerPosition[];
    private int duration;
    
    public Solution(){}

    public Solution(Instance i) throws 
            MaxCapacityExceededException, MaxDurationExceededException {
        this.routes = new ArrayList<Route>();
        this.instance = i;
        this.customerRoute = new int[i.getCustomersNumber() + 1];
        this.customerPosition = new int[i.getCustomersNumber() + 1];
        this.duration = 0;
        for(int customer = 1 ; 
                customer <= this.instance.getCustomersNumber() ; 
                ++customer){
            Route r = new Route();
            r.push(customer, instance);
            this.duration += r.getDuration();
            this.routes.add(r);
            this.customerPosition[customer] = 1;
            this.customerRoute[customer] = customer - 1;
        }
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public int getDuration() {
        return duration;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    @Override
    public String toString() {
        String r = "";
        int totalRoutes = 0;
        for(Route route: this.routes){
            if(route.size() != 2){                    
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

    public Route getRoute(int targetRoute) {
        return this.routes.get(targetRoute);
    }

    public void applyMoves(Move move) {
        try {
            int customer = move.getCustomer(),
                    customerPos = this.getCustomerPosition(move.getCustomer());
            Route originRoute = this.getRoute(this.getRouteNumber(customer));
            Route targetRoute = this.getRoute(move.getTargetRoute());
            this.duration += originRoute.remove(customerPos, this.getInstance(), true);
            // Si la ruta donde lo voy a meter es la misma de donde lo saque
            // y la posicion donde lo iba a meter es superior a donde estaba,
            // debo restarle uno a la posicion de destino para que se actualice.
            if ( originRoute.equals(targetRoute) && move.getTargetPosition() > customerPos ){
                move.setTargetPosition(move.getTargetPosition() - 1);
            }
            this.duration += targetRoute.add(customer, move.getTargetPosition(), this.getInstance(), true);
            customerPosition[customer] = move.getTargetPosition();
            customerRoute[customer] = move.getTargetRoute();
            for (int index = customerPos ; index < originRoute.size() - 1 ; ++index){
                customerPosition[originRoute.getCustomerAt(index)] = index;
            }
            for (int index = move.getTargetPosition() + 1 ; index < targetRoute.size() - 1 ; ++index){
                customerPosition[targetRoute.getCustomerAt(index)] = index;
            }
        } catch (MaxCapacityExceededException ex) {
            Logger.getLogger(Neighbor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MaxDurationExceededException ex) {
            Logger.getLogger(Neighbor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PrintableSolution getPrintableSolution() {
        return new PrintableSolution(this);
    }
    
    
    
    
}
