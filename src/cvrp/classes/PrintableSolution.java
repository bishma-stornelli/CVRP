package cvrp.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class PrintableSolution {
    
  private List< List<Integer> > routes;
  private int duration;
  private int durationWithoutDropTime;
  private int dropTime;
  private String instanceName;

  public PrintableSolution(Solution s) {
    this.routes = new ArrayList< List<Integer> >();
    for(Route r : s.getRoutes()) {
      if(r.size() > 2) {
        List<Integer> l = new ArrayList<Integer>(r.size());
        for(int i = 0 ; i < r.size() ; ++i)
          l.add(r.getCustomerAt(i));
        routes.add(l);
      }
    }
    this.duration = s.getDuration();
    Instance i = s.getInstance();
    this.dropTime = i.getCustomersNumber()*i.getDropTime();
    this.durationWithoutDropTime = this.duration - this.dropTime;
    this.instanceName = i.getInstanceName();
  }

  /**
   * Generate the string representation of the printable solution.
   * 
   * @return the printable solution as a string
   */
  @Override
  public String toString() {
    String s = duration + "\n";
    for(List<Integer> l : routes) {
      for(Integer i : l) 
          s += i + " "; 
      s += "\n";
    }
    return s;
  }

  // Getters and Setters

  public int getDropTime() {
    return dropTime;
  }

  public void setDropTime(int dropTime) {
    this.dropTime = dropTime;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getDurationWithoutDropTime() {
    return durationWithoutDropTime;
  }

  public void setDurationWithoutDropTime(int durationWithoutDropTime) {
    this.durationWithoutDropTime = durationWithoutDropTime;
  }

  public List<List<Integer>> getRoutes() {
    return routes;
  }

  public void setRoutes(List<List<Integer>> routes) {
    this.routes = routes;
  }

  public String getInstanceName() {
    return instanceName;
  }

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }
 
}
