package cvrp;

import cvrp.abstracts.TerminationCriteria;
import cvrp.classes.*;
import cvrp.exceptions.MaxCapacityExceededException;
import cvrp.exceptions.MaxDurationExceededException;
import cvrp.exceptions.TabuListFullException;
import cvrp.exceptions.TerminationCriteriaNotStartedException;
import cvrp.interfaces.Tabu;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class CVRP {
  
    /**
     * The main program for the CVRP.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, 
            TerminationCriteriaNotStartedException, MaxCapacityExceededException, MaxDurationExceededException, InterruptedException {
      // TODO code application logic here
      // Instance instance = new Instance(args[0],args[1]);
      
      /**
      for(int i = 1 ; i <= 14 ; ++i) {
        for(int j = 0 ; j <= 4 ; ++j) {
          Instance instance = new Instance("instanciasCVRP/vrpnc" + i +".txt", "settings");
          instance.loadEverything();
          run(instance);
          //Thread.sleep(2000);
          ++run;
        }
        run = 0;
      }
      */
      
      Instance instance = new Instance("instanciasCVRP/vrpnc1.txt", "settings");
      instance.loadEverything();
      run(instance);
      
        
    }
    
  /**
   * The TabuSearch Meta-Heuristic for the CVRP.
   * 
   * @param i an instance of the problem
   * @throws NoSuchTabuTypeException
   * @throws TerminationCriteriaNotStartedException
   * @throws MaxCapacityExceededException
   * @throws MaxDurationExceededException 
   */
  private static void run(Instance i) 
          throws TerminationCriteriaNotStartedException, 
          MaxCapacityExceededException, MaxDurationExceededException {

    List<Tabu> tabuList = new ArrayList<Tabu>();
    Solution current = generateFirstSolution(i);
    ClarkeAndWrightAlgorithm CWA = new ClarkeAndWrightAlgorithm(current);
    CWA.excute();
    current.correct();
    PrintableSolution best = current.getPrintableSolution();
    TerminationCriteria tc = i.getTerminationCriteria();
    tc.start();
    while (!tc.timeToFinish(current)) {
      try {
        List<Neighbor> neighbors = i.getNeighborhoodStructure().generateNeighborhood(current, tabuList);
        if (!neighbors.isEmpty()) {
          Neighbor neighbor = i.getNeighborSelector().selectNeighbor(neighbors, current);
          tabuList.addAll(neighbor.getTabus());
          neighbor.getMove().applyMove(current);
          /*
          if (!current.correct()) {
              break;
          }
          */
          //assert(current.getDuration() == neighbor.getDuration());
          if (current.getDuration() < best.getDuration()) {
            best = current.getPrintableSolution();
            tc.recordBest(current);
          }
        } else {
          // Intensificator. Was not necessary.
        }
      } catch (TabuListFullException ex) {
        try {
          int elementsToRemove = (int) (tabuList.size() * .1);
          // int elementsToRemove = tabuList.size()/2;
          for (int k = 0; k < elementsToRemove; k++)
            tabuList.remove(0);        
        } catch (ArrayIndexOutOfBoundsException a) {
          System.out.println("Were removed more elements of which had the tabuList");
          break;
        }
      }
    }
    tc.finish();
    current.correct();
    printSolution(best, tc);
  }
  
  /**
   * Write to a file the solution obtained.
   * 
   * @param solution a solution
   * @param tc a termination criteria
   */
  private static void printSolution(PrintableSolution solution, TerminationCriteria tc) {
    BufferedWriter out = null;
    try {
      String [] tokens = solution.getInstanceName().split("/");
      String statName = "stat."+tokens[tokens.length - 1];
      out = new BufferedWriter(new FileWriter(new File(statName)));
      out.write("Cost: " + solution.getDurationWithoutDropTime());
      out.newLine();
      out.write("Cost with drop time: " + solution.getDuration());
      out.newLine();
      out.write("Iteration until best found: " + tc.getBestFoundIteration());
      out.newLine();
      out.write("Total iterations: " + tc.getCurrentIteration());
      out.newLine();
      out.write("Time until best found: " + tc.getTimeToBest());
      out.newLine();
      out.write("Total time elapsed: " + tc.getTotalTime());
      out.newLine();
      out.write(solution.toString());
    } catch (IOException ex) {
      Logger.getLogger(CVRP.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        out.close();
      } catch (IOException ex) {
        Logger.getLogger(CVRP.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  /**
   * Generates a trivial solution.
   * 
   * @param i an instance of the problem
   * @return a solution of the problem instance
   */
  private static Solution generateFirstSolution(Instance i) {
    Solution s = null;
    try {
      s = new Solution(i);
    } catch (MaxCapacityExceededException ex) {
      System.err.println("The instance has no feasible solution");
      System.exit(1);
    } catch (MaxDurationExceededException ex) {
      System.err.println("The instance has no feasible solution");
      System.exit(1);
    }
    return s;
  }
}
