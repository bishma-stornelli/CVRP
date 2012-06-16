/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

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
            throws UnexpectedAmountOfCustomersException {
        if( customers.size() != 1 ){
            throw new UnexpectedAmountOfCustomersException();
        }
        int customer = customers.get(0);
        int customersSize = s.getInstance().getCustomersNumber();
        while( true ) {
            int targetRoute = (int)(Math.random()*customersSize) + 1;
            Route r = s.getRoute(targetRoute);
            int positionInsideRoute = (int)(Math.random()*r.size());
            Move m = new SingleMove(customer, r, positionInsideRoute);
            try {
                return new Neighbor(s, m);
            } catch (MaxCapacityExceededException ex) {
                Logger.getLogger(NeighborhoodStructure1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MaxDurationExceededException ex) {
                Logger.getLogger(NeighborhoodStructure1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
    }
    
}
