/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
                     [solution.getInstance().getCustomersNumber() + 1];
    for(int i = 0; i < solution.getInstance().getCustomersNumber(); ++i) {
      for(int j = 0; j < solution.getInstance().getCustomersNumber() + 1; ++j)
        S[i][j] = 0;
    }
    this.L = new ArrayList();
    this.solution = solution;
  }
     
  private class Triplet {

    private int s;
    private int i;
    private int j;
    private boolean considered;
    
    public Triplet(int s, int i, int j) {
      this.s = s;
      this.i = i;
      this.j = j;
      this.considered = false;
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

    public boolean isConsidered() {
      return considered;
    }

    public void setConsidered(boolean considered) {
      this.considered = considered;
    }
    
  }
  
  private class TripletComparator implements Comparator<Triplet> {
        
    @Override
    public int compare(Triplet t1, Triplet t2) {
      
      if(t1.getS() < t2.getS())
        return 1;
      else if(t1.getS() > t2.getS())
        return -1;
      return 0;
    }
    
  }
  
  public void excute() {
    
    this.fillSaving();
    this.sortSaving();
    this.parallel();

  }
  
  private void parallel() {
    
    Triplet t = L.get(0);
    L.remove(0);
    
    while((!L.isEmpty()) && (t.getS() > 0)) {
      
      int customerA = t.getI();
      int customerB = t.getJ();
      
      if(this.mergeFeasibility(customerA, customerB))
        this.merge(customerA, customerB);
      
      t = L.get(0);
      L.remove(0);
    }
 
  }
  
  private boolean mergeFeasibility(int customerA, int customerB) {
  
    Instance instance = solution.getInstance();
    int routeNumberA = solution.getRouteNumber(customerA);
    int routeNumberB = solution.getRouteNumber(customerB);
    
    Route routeA = solution.getRoute(routeNumberA);
    Route routeB = solution.getRoute(routeNumberB);
    
    if(routeA.getCapacity() + routeB.getCapacity() > instance.getVehicleCapacity())
      return false;
    
    int firstA = routeA.getCustomerAt(1);
    int firstB = routeB.getCustomerAt(1);
    int lastA = routeA.getCustomerAt(routeA.size()-2);
    int lastB = routeB.getCustomerAt(routeB.size()-2);
    
    if(routeNumberA == routeNumberB) {
      /*
      if((firstA == customerA && lastA == customerB) ||
         (firstA == customerB && lastA == customerA))
        return false; 
      */
      return false;
    }
    
    if((firstA != customerA && lastA != customerA) || 
       (firstB != customerB && lastB != customerB))
      return false;
 
    int durationA = routeA.getDuration();
    int durationB = routeB.getDuration();
    int changeOnDuration = instance.getDistance(customerA, customerB)
                         - instance.getDistance(0,customerA)
                         - instance.getDistance(0,customerB);
    if(durationA + durationB + changeOnDuration > instance.getMaximumRouteTime())
      return false;
    
    return true;
    
  }
  
  private void merge(int customerA, int customerB) {
    
    Instance instance = solution.getInstance();
    int routeNumberA = solution.getRouteNumber(customerA);
    int routeNumberB = solution.getRouteNumber(customerB);
    Route routeA = solution.getRoute(routeNumberA);
    Route routeB = solution.getRoute(routeNumberB);
    int customerPositionA = solution.getCustomerPosition(customerA);
    int customerPositionB = solution.getCustomerPosition(customerB);
    List<Integer> routeListA = routeA.getRoute();
    List<Integer> routeListB = routeB.getRoute();
      
    if(customerPositionA == 1)
      Collections.reverse(routeListA);
    if(customerPositionB == routeB.size() - 2)
      Collections.reverse(routeListB);
    
    routeListA.remove(routeA.size() - 1);
    for(int i = 1; i < routeB.size(); ++i)
      routeListA.add(routeListB.get(i));
    /*
    if(customerPositionA == 1) {

      if(customerPositionB == 1) {
        
        for(int i = 1; i < routeB.size() - 1; ++i)
          routeListA.set(routeA.size() -2 + i , routeListB.get(i));
        routeListA.
      }
      
    } else {
      
    }
    * 
    */
    
    int durationA = routeA.getDuration();
    int durationB = routeB.getDuration();
    int changeOnDuration = instance.getDistance(customerA, customerB)
                         - instance.getDistance(0,customerA)
                         - instance.getDistance(0,customerB);
    routeA.setDuration(durationA + durationB + changeOnDuration);
    routeB.setDuration(0);
    routeA.setCapacity(routeA.getCapacity() + routeB.getCapacity());
    routeB.setCapacity(0);
    List<Integer> l = new ArrayList<Integer>();
    l.add(0);
    l.add(0);
    routeB.setRoute(l);
    for(int i = 1; i < routeA.size() - 1; i++) {
      solution.setCustomerRoute(routeA.getCustomerAt(i), routeNumberA);
      solution.setCustomerPosition(routeA.getCustomerAt(i), i);
    }
    solution.setDuration(solution.getDuration() + changeOnDuration);
   
  }
  
  private void fillSaving() {
    
    Instance instance = solution.getInstance();
    int n = instance.getCustomersNumber();
    
    for(int i = 1; i < n; ++i) {
      
      for(int j = i + 1; j <= n; ++j) {
        
        S[i][j] = instance.getDistance(0, i)
                + instance.getDistance(j, 0)
                - instance.getDistance(i, j);
        Triplet t = new Triplet(S[i][j],i,j);
        L.add(t);
      }
      
    }
  }
  
  private void sortSaving() {
    TripletComparator tc = new TripletComparator();
    Collections.sort(L, tc);
  }
  
  // Getters and Setters

  public List<Triplet> getL() {
    return L;
  }

  public void setL(List<Triplet> L) {
    this.L = L;
  }

  public int[][] getS() {
    return S;
  }

  public void setS(int[][] S) {
    this.S = S;
  }

  public Solution getSolution() {
    return solution;
  }

  public void setSolution(Solution solution) {
    this.solution = solution;
  }
    
}
