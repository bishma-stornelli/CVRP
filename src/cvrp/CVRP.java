/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class CVRP {
    
    private Solution solution;
    private int numberOfIterations;
    private int iterationOfTheBestSolution;
    private long startTime = 0;
    private long endTime = 0;
    private long timeOfTheBestSolution = 0;
    private long starTime;    
    private int maxNumberOfIteration;
    private long maxTime; // in seconds
    private Instance instance;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoSuchTabuTypeException {
        // TODO code application logic here
        Instance instance = new Instance(args[0],args[1]);
        instance.loadInstance();
        instance.loadSettings();
        CVRP resolver = new CVRP();
        resolver.configure(args);
        resolver.run();
        resolver.printSolution();
    }

    private void configure(String[] args) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void run() throws NoSuchTabuTypeException {
        startTime = System.currentTimeMillis();
        solution = getInitialSolution();
        List<Tabu> tabuList = null;
        numberOfIterations = 0;
        while(numberOfIterations < maxNumberOfIteration && 
                (System.currentTimeMillis() - starTime)/1000 < maxTime ){
            Neighbor n = selectNeighbor(solution, tabuList);
            if (n.getCost() < solution.getCost()){
                timeOfTheBestSolution = System.currentTimeMillis();
                iterationOfTheBestSolution = numberOfIterations;
                tabuList.add(n.getTabu(instance.getTABU_RESTRICTION()));
                solution = n.applyMove();
            }            
            ++numberOfIterations;
        }
        endTime = System.currentTimeMillis();
    }

    private void printSolution() throws IOException {
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

    private Solution getInitialSolution() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Neighbor selectNeighbor(Solution solution, List<Tabu> tabu_list) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
