/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tamerdark
 */
public class PrintableSolution {
    
  private List< List<Integer> > routes;
  private int duration;

  public PrintableSolution(Solution s) {
    routes = new ArrayList< List<Integer> >();
    for(Route r : s.getRoutes()) {
      if(r.size() > 2) {
          List<Integer> l = new ArrayList<Integer>(r.size());
          for(int i = 0 ; i < r.size() ; ++i) {
              l.add(r.getCustomerAt(i));
          }
          routes.add(l);
      }
    }
    duration = s.getDuration();
  }

  @Override
  public String toString() {
    String s = duration + "\n";
    for(List<Integer> l : routes) {
      for(Integer i : l) {
          s += i + " ";
      }
      s += "\n";
    }
    return s;
  }

  // Getters and Setters
  
  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public List<List<Integer>> getRoutes() {
    return routes;
  }

  public void setRoutes(List<List<Integer>> routes) {
    this.routes = routes;
  }
  
}
