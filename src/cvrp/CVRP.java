/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

import cvrp.abstracts.TerminationCriteria;
import cvrp.classes.Instance;
import cvrp.classes.Neighbor;
import cvrp.classes.Solution;
import cvrp.exceptions.NoSuchTabuTypeException;
import cvrp.exceptions.TerminationCriteriaNotStartedException;
import cvrp.interfaces.Tabu;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class CVRP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoSuchTabuTypeException, 
            TerminationCriteriaNotStartedException {
        // TODO code application logic here
        // Instance instance = new Instance(args[0],args[1]);
        Instance instance = new Instance("instanciasCVRP/vrpnc1.txt","settings");  
        instance.loadSettings();
        instance.loadInstance();
        instance.loadDistance();
        run(instance);
    }

    private static void run(Instance i) throws NoSuchTabuTypeException, 
            TerminationCriteriaNotStartedException, IOException {
        List<Tabu> tabuList = new ArrayList<Tabu>();
        Solution current = generateFirstSolution();
        Solution best = current;
        TerminationCriteria tc = i.getTerminationCriteria();
        while(tc.timeToFinish(current)){
            List<Neighbor> neighbors = i.getNeighborhoodGenerator().generateNeighborhood(current, i, tabuList);
            Neighbor neighbor = i.getNeighborSelector().selectNeighbor(current, neighbors);
            tabuList.addAll(neighbor.getTabus());
            current = neighbor.applyMoves();
            if( current.getCost() < best.getCost() ){
                best = current;
                tc.recordBest(best);                
            }
        }
        printSolution(best, tc);
    }

    private static void printSolution(Solution solution, TerminationCriteria tc) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("stat.")));
        out.write(solution.getCost() + "");
        out.newLine();
        out.write(iterationOfTheBestSolution + "");
        out.newLine();
        out.write(numberOfIterations + "");
        out.newLine();
        out.write((timeOfTheBestSolution - startTime)/1000 + "");
        out.newLine();
        out.write((endTime - startTime)/1000 + "");
        out.write(solution.toString());
    }

    private static Solution generateFirstSolution() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Neighbor selectNeighbor(Solution solution, List<Tabu> tabu_list) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
