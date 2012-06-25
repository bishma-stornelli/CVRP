package cvrp.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
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
    
    /**
     * Compare two triplet according to their saving value.
     * 
     * @param t1 a triplet
     * @param t2 a triplet
     * @return 1 if t1 is less than t2
     *        -1 if t2 is less than t1
     *         0 if t1 is equal to t2
     */
    @Override
    public int compare(Triplet t1, Triplet t2) {     
      if(t1.getS() < t2.getS())
        return 1;
      else if(t1.getS() > t2.getS())
        return -1;
      return 0;
    }
    
  }
    
      
  /**
   * Execute the Clarke and Wright algorithm.
   */
  public void excute() {
    this.fillSaving();
    this.sortSaving();
    this.parallel();
  }
  
  /**
   * Execute the parallel version of the Clarke and Wright algorithm.
   */
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
  
  /**
   * Check if a merge for the routes where the two customers belongs can be done.
   * 
   * @param customerA a customer
   * @param customerB a customer
   * @return true if the merge can be done.
   *         false if it cannot be done.
   */
  private boolean mergeFeasibility(int customerA, int customerB) {
  
    Instance instance = solution.getInstance();
    int routeNumberA = solution.getRouteNumber(customerA);
    int routeNumberB = solution.getRouteNumber(customerB);
    
    // Customers both in the same route.
    if(routeNumberA == routeNumberB)
      return false;
    
    Route routeA = solution.getRoute(routeNumberA);
    Route routeB = solution.getRoute(routeNumberB);
    
    // Overload of the vehicle capacity.
    if(routeA.getCapacity() + routeB.getCapacity() > instance.getVehicleCapacity())
      return false;
    
    int firstA = routeA.getCustomerAt(1);
    int firstB = routeB.getCustomerAt(1);
    int lastA = routeA.getCustomerAt(routeA.size()-2);
    int lastB = routeB.getCustomerAt(routeB.size()-2);
    
    // Internal customers.
    if((firstA != customerA && lastA != customerA) || 
       (firstB != customerB && lastB != customerB))
      return false;
 
    int durationA = routeA.getDuration();
    int durationB = routeB.getDuration();
    int changeOnDuration = instance.getDistance(customerA, customerB)
                         - instance.getDistance(0,customerA)
                         - instance.getDistance(0,customerB);
    
    // Overload of the route duration.
    if(durationA + durationB + changeOnDuration > instance.getMaximumRouteTime())
      return false;
    
    return true;
  }
  
  /**
   * Merge the routes where the two customers belongs.
   * 
   * @param customerA a customer
   * @param customerB a customer
   */
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
  
  /**
   * Compute the saving value for the instance a fill the matrix S with them.
   */
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
  
  /**
   * Sort in a nondecreasing order the list L according to the saving.
   */
  private void sortSaving() {
    TripletComparator tc = new TripletComparator();
    Collections.sort(L, tc);
  }
  
  // Getters and Setters

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
