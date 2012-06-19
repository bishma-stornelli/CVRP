/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.UnexpectedAmountOfCustomersException;
import cvrp.abstracts.Move;
import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.interfaces.Tabu;
import cvrp.interfaces.NeighborhoodStructure;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Itera entre todos los vecinos y para cada uno les selecciona una ruta y 
 * posicion dentro de la ruta aleatoria.
 * 
 * Note que puede insertarse en la misma ruta pero en diferente posicion y que
 * puede dejar rutas vacias como tambien crear nuevas rutas.
 *
 * @author tamerdark
 */
public class NeighborhoodStructure1 implements NeighborhoodStructure {

    @Override
    public int getNumberOfCustomerRequired() {
        return 1;
    }

    @Override
    public Neighbor generateNeighbor(Solution s, List<Tabu> tabuList, List<Integer> customers) 
            throws UnexpectedAmountOfCustomersException , TabuListFullException {
        if( customers.size() != 1 ){
            throw new UnexpectedAmountOfCustomersException();
        }
        int customer = customers.get(0);
        int customersSize = s.getInstance().getCustomersNumber();
        int iterationsWithoutMove = 0;
        while( true ) {
            int targetRoute = (int)(Math.random()*customersSize);
            Route r = s.getRoute(targetRoute);
            int positionInsideRoute = (int)(Math.random()*(r.size() - 1)) + 1;
            SingleMove m = new SingleMove(customer, targetRoute, positionInsideRoute);
            // If the route is the same, try to generate another number until
            // it has tried 2* tabulist.size() times.
            if( ( 
                    targetRoute == s.getRouteNumber(customer) 
                    && ( positionInsideRoute == s.getCustomerPosition(customer) 
                        || positionInsideRoute == s.getCustomerPosition(customer) + 1 )
                    
                )   || tabuList.contains(m.generateTabu())){
                if( iterationsWithoutMove > 2*tabuList.size()){
                    throw new TabuListFullException();
                }
                ++iterationsWithoutMove;
                continue;
            }
            try {
                Neighbor n = new Neighbor(s, m);
                return n;
            } catch (MaxCapacityExceededException ex) {
                //Logger.getLogger(NeighborhoodStructure1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MaxDurationExceededException ex) {
                //Logger.getLogger(NeighborhoodStructure1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
    }
    
}
