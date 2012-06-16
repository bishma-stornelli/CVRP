/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.classes.SingleMove;
import cvrp.classes.Solution;
import cvrp.classes.Route;
import cvrp.abstracts.Move;
import cvrp.interfaces.Tabu;
import cvrp.interfaces.NeighborhoodStructure;
import java.util.ArrayList;
import java.util.List;

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
    public List<Neighbor> generateNeighborhood(Solution s, Instance i, List<Tabu> tabuList) {
        int customersSize = i.getCustomersNumber();
        List<Neighbor> neighborhood = new ArrayList<Neighbor>(customersSize);
        for(int customer = 1 ; customer <= customersSize ; ++customer){
            int routeNumber = (int)(Math.random()*customersSize) + 1;
            Route r = s.getRoutes().get(routeNumber);
            int positionInsideRoute = (int)(Math.random()*r.getRoute().size());
            Move m = new SingleMove();
            m.setCustomer(customer);
            m.setTargetPosition(positionInsideRoute);
            m.setTargetRoute(r);
            Neighbor nb = new Neighbor();
            nb.setNeighbor(s);
            nb.setMove(m);
            neighborhood.add(nb);
        }
        return neighborhood;
    }
    
}
