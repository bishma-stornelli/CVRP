/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

import java.util.List;

/**
 *
 * @author tamerdark
 */
public class CVRP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CVRP resolver = new CVRP();
        resolver.configure(args);
        resolver.run();
        resolver.printSolution();
    }

    private void configure(String[] args) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void run() {
        Solution solution = getInitialSolution();
        List<Tabu> tabu_list = null;
        while(!terminationCriteriaSatisfied()){
            Neighbor n = selectNeighbor(solution, tabu_list);
            if (n.getCost() < solution.getCost()){
                solution = n.applyMove();
                tabu_list.add(n.getTabu());
            }            
        }
    }

    private void printSolution() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Solution getInitialSolution() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean terminationCriteriaSatisfied() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Neighbor selectNeighbor(Solution solution, List<Tabu> tabu_list) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
