/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.*;
import cvrp.interfaces.Move;
import cvrp.interfaces.Tabu;
import cvrp.interfaces.NeighborhoodStructure;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Itera entre todos los vecinos y para cada uno les selecciona una ruta y
 * posicion dentro de la ruta aleatoria.
 *
 * Note que puede insertarse en la misma ruta pero en diferente posicion y que
 * puede dejar rutas vacias como tambien crear nuevas rutas.
 *
 * @author tamerdark
 */
public class NeighborhoodStructure1 implements NeighborhoodStructure {

    @Override
    public List<Neighbor> generateNeighborhood(Solution s, List<Tabu> tabuList)
            throws TabuListFullException {
        List<Neighbor> neighbors = new ArrayList<Neighbor>();
        int n = s.getInstance().getCustomersNumber();
        for (int customer = 1; customer <= s.getInstance().getCustomersNumber(); customer++) {
            Random r = new Random();
            int targetRoute, targetPosition;
            int iterationsWithoutMove = 0;
            while (true) {
                try {
                    while ((targetRoute = Math.abs(r.nextInt()) % n) == s.getRouteNumber(customer));
                    Route route = s.getRoute(targetRoute);
                    targetPosition = Math.abs(r.nextInt()) % (route.size() - 1) + 1;
                    neighbors.add(generateNeighbor(s, tabuList, customer, targetRoute, targetPosition));
                    break;
                } catch (MoveTabuException ex) {
                } catch (MaxCapacityExceededException ex) {
                } catch (MaxDurationExceededException ex) {
                }
                ++iterationsWithoutMove;
                if (iterationsWithoutMove > 2 * tabuList.size()) {
                    throw new TabuListFullException();
                }
            }
        }
        return neighbors;
    }

    private Neighbor generateNeighbor(Solution s, List<Tabu> tabuList,
            int customer, int targetRoute, int targetPosition)
            throws MoveTabuException, MaxCapacityExceededException, MaxDurationExceededException {
        Move m = new SingleMove(customer, targetRoute, targetPosition);
        if (tabuList.contains(m.generateTabu())) {
            throw new MoveTabuException();
        }
        Neighbor n = null;
        n = new Neighbor(s, m);
        return n;

    }
}
