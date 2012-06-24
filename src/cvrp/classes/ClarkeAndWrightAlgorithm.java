/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicente
 */
public class ClarkeAndWrightAlgorithm {
  
  private int [][] S;
  private List<Triplet> L;
  private Solution solution;

  public ClarkeAndWrightAlgorithm(Solution solution) {
    this.S = new int [solution.getInstance().getCustomersNumber()]
                     [solution.getInstance().getCustomersNumber()];
    this.L = new ArrayList();
    this.solution = solution;
  }

          
  public class Triplet {

    private int s;
    private int i;
    private int j;
    
    public Triplet(int s, int i, int j) {
      this.s = s;
      this.i = i;
      this.j = j;
    }
    
    // Getters and Setters

    public int getI() {
      return i;
    }

    public void setI(int i) {
      this.i = i;
    }

    public int getJ() {
      return j;
    }

    public void setJ(int j) {
      this.j = j;
    }

    public int getS() {
      return s;
    }

    public void setS(int s) {
      this.s = s;
    }
    
  }
  
}
