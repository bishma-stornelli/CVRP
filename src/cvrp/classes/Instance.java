/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp.classes;

import cvrp.abstracts.TerminationCriteria;
import cvrp.interfaces.NeighborhoodStructure;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author vicente
 */
public class Instance {
  
  private String instanceName;
  private String settingsName;
  private String MULTI_THREAD;
  private String TABU_RESTRICTION;
  private String SELECT_ORIGIN_ROUTE;
  private String SELECT_CLIENT;
  private String SELECT_DESTINATION_ROUTE;
  private String SELECT_INSERT_POSITION;
  private String TERMINATION_CRITERIA;
  private String NEIGHBORHOOD_STRUCTURE;
  private int customersNumber;
  private int vehicleCapacity;
  private int maximumRouteTime;
  private int dropTime;
  private int [] quantity;
  private int [][] distances;
  private int [][] coordinates;
  private TerminationCriteria terminationCriteria;
  private NeighborhoodStructure neighborhoodStructure;
  
  public Instance (String instanceName, String settingsName) {
    this.instanceName = instanceName;
    this.settingsName = settingsName;    
    this.terminationCriteria = null;
    this.neighborhoodStructure = null;
  }
  
  public void loadInstance() throws FileNotFoundException {
    
    File file = new File(this.instanceName);
    
    try {
      
      Scanner scanner = new Scanner(file);
      
      this.customersNumber = scanner.nextInt();
      this.vehicleCapacity = scanner.nextInt();
      this.maximumRouteTime = scanner.nextInt();
      this.dropTime = scanner.nextInt();
      this.coordinates = new int [this.customersNumber+1][2];
      this.quantity = new int [this.customersNumber+1];
      this.coordinates[0][0] = scanner.nextInt();
      this.coordinates[0][1] = scanner.nextInt();
      this.quantity[0] = 0;
      int c = 1;
      
      while(scanner.hasNextInt()) {
        
        this.coordinates[c][0] = scanner.nextInt();
        this.coordinates[c][1] = scanner.nextInt();
        this.quantity[c] = scanner.nextInt();
        c++;
      }
    } catch(FileNotFoundException e) {
      
    }
  }
  
  public void loadDistance() {
    
    this.distances = new int [this.customersNumber+1][this.customersNumber+1];
    int distance = 0;
    for(int i = 0; i <= this.customersNumber; i++) {
      for(int j = i; j <= this.customersNumber; j++) {
        if(i == j)
          this.distances[i][j] = 0;
        else {
          distance = (int) Math.round(Math.sqrt(
                  Math.pow(this.coordinates[j][0] - this.coordinates[i][0],2) + 
                  Math.pow(this.coordinates[j][1] - this.coordinates[i][1],2)));
          
          this.distances[i][j] = distance;
          this.distances[j][i] = distance; 
        }          
      }
    }
  }
  
  public void loadSettings() throws FileNotFoundException {
    System.out.println(new File(".").getAbsolutePath());
    File file = new File(this.settingsName);
    
    try {
      
      Scanner scanner = new Scanner(file);
      String option = "";
      
      while(scanner.hasNext()) {
        option = scanner.next();
        if("MULTI_THREAD".equals(option))
          this.MULTI_THREAD = scanner.next();
        else if("TABU_RESTRICTION".equals(option))
          this.TABU_RESTRICTION = scanner.next();
        else if("SELECT_ORIGIN_ROUTE".equals(option))
          this.SELECT_ORIGIN_ROUTE = scanner.next();
        else if("SELECT_CLIENT".equals(option))
          this.SELECT_CLIENT = scanner.next();
        else if("SELECT_DESTINATION_ROUTE".equals(option))
          this.SELECT_DESTINATION_ROUTE = scanner.next();
        else if("SELECT_INSERT_POSITION".equals(option))
          this.SELECT_INSERT_POSITION = scanner.next();
        else if("TERMINATION_CRITERIA".equals(option))
          this.TERMINATION_CRITERIA = scanner.next();
        else if("NEIGHBORHOOD_STRUCTURE".equals(option))
          this.NEIGHBORHOOD_STRUCTURE = scanner.next();
      }
        
    } catch(FileNotFoundException e) {
      System.out.println("FileNotFoundException");
    }
  }
  
  private void assignTerminationCriteria() {
    if(this.TERMINATION_CRITERIA.equals("I"))
      this.terminationCriteria = new IterationTerminationCriteria(this.customersNumber*50);
    // Colocar las demas opciones aqui
  }

  private void assignNeighborhoodStructure() {
    if(this.NEIGHBORHOOD_STRUCTURE.equals("M"))
      this.neighborhoodStructure = new NeighborhoodStructure1();
    // Colocar las demas opciones aqui
  }
  
  public void printDistances() {
    for(int i = 0; i <= this.customersNumber; i++) {
      for(int j = 0; j <= this.customersNumber; j++)
        System.out.print(this.distances[i][j]+" ");
      System.out.println("\n");  
    } 
  }
  
  public void printCoordinates() {
    for(int i = 0; i <= this.customersNumber; i++)
      System.out.println(this.coordinates[i][0] + " " + this.coordinates[i][1] 
                         + " " + this.quantity[i]);
  }
  
  public void printSettings() {
    System.out.println("MULTI_THREAD: " + MULTI_THREAD);
    System.out.println("TABU_RESTRICTION: " + TABU_RESTRICTION);
    System.out.println("SELECT_ORIGIN_ROUTE: " + SELECT_ORIGIN_ROUTE);
    System.out.println("SELECT_CLIENT: " + SELECT_CLIENT);
    System.out.println("SELECT_DESTINATION_ROUTE: " + SELECT_DESTINATION_ROUTE);
    System.out.println("SELECT_INSERT_POSITION: " + SELECT_INSERT_POSITION);    
  }
  
  public int getDemand(int customer) {
    if(0 <= customer && customer < quantity.length)
      return quantity[customer];
    return 0;
  }
  
  public String getMULTI_THREAD() {
    return MULTI_THREAD;
  }

  public void setMULTI_THREAD(String MULTI_THREAD) {
    this.MULTI_THREAD = MULTI_THREAD;
  }

  public String getSELECT_CLIENT() {
    return SELECT_CLIENT;
  }

  public void setSELECT_CLIENT(String SELECT_CLIENT) {
    this.SELECT_CLIENT = SELECT_CLIENT;
  }

  public String getSELECT_DESTINATION_ROUTE() {
    return SELECT_DESTINATION_ROUTE;
  }

  public void setSELECT_DESTINATION_ROUTE(String SELECT_DESTINATION_ROUTE) {
    this.SELECT_DESTINATION_ROUTE = SELECT_DESTINATION_ROUTE;
  }

  public String getSELECT_INSERT_POSITION() {
    return SELECT_INSERT_POSITION;
  }

  public void setSELECT_INSERT_POSITION(String SELECT_INSERT_POSITION) {
    this.SELECT_INSERT_POSITION = SELECT_INSERT_POSITION;
  }

  public String getSELECT_ORIGIN_ROUTE() {
    return SELECT_ORIGIN_ROUTE;
  }

  public void setSELECT_ORIGIN_ROUTE(String SELECT_ORIGIN_ROUTE) {
    this.SELECT_ORIGIN_ROUTE = SELECT_ORIGIN_ROUTE;
  }

  public String getTABU_RESTRICTION() {
    return TABU_RESTRICTION;
  }

  public void setTABU_RESTRICTION(String TABU_RESTRICTION) {
    this.TABU_RESTRICTION = TABU_RESTRICTION;
  }
  
    public String getNEIGHBORHOOD_STRUCTURE() {
    return NEIGHBORHOOD_STRUCTURE;
  }

  public void setNEIGHBORHOOD_STRUCTURE(String NEIGHBORHOOD_STRUCTURE) {
    this.NEIGHBORHOOD_STRUCTURE = NEIGHBORHOOD_STRUCTURE;
  }

  public String getTERMINATION_CRITERIA() {
    return TERMINATION_CRITERIA;
  }

  public void setTERMINATION_CRITERIA(String TERMINATION_CRITERIA) {
    this.TERMINATION_CRITERIA = TERMINATION_CRITERIA;
  }

  public int getCustomersNumber() {
    return customersNumber;
  }

  public void setCustomersNumber(int customersNumber) {
    this.customersNumber = customersNumber;
  }

  public int[][] getDistances() {
    return distances;
  }

  public void setDistances(int[][] distances) {
    this.distances = distances;
  }

  public int getDropTime() {
    return dropTime;
  }

  public void setDropTime(int dropTime) {
    this.dropTime = dropTime;
  }

  public String getInstanceName() {
    return instanceName;
  }

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }

  public int getMaximumRouteTime() {
    return maximumRouteTime;
  }

  public void setMaximumRouteTime(int maximumRouteTime) {
    this.maximumRouteTime = maximumRouteTime;
  }

  public int[] getQuantity() {
    return quantity;
  }

  public void setQuantity(int[] quantity) {
    this.quantity = quantity;
  }

  public String getSettingsName() {
    return settingsName;
  }

  public void setSettingsName(String settingsName) {
    this.settingsName = settingsName;
  }

  public int getVehicleCapacity() {
    return vehicleCapacity;
  }

  public void setVehicleCapacity(int vehicleCapacity) {
    this.vehicleCapacity = vehicleCapacity;
  }
  
  public int getDistance(int customerA, int customerB){
      return this.distances[customerA][customerB];
  }

  public TerminationCriteria getTerminationCriteria() {
    return terminationCriteria;
  }

  public void setTerminationCriteria(TerminationCriteria terminationCriteria) {
    this.terminationCriteria = terminationCriteria;
  }
  
  public int[][] getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(int[][] coordinates) {
    this.coordinates = coordinates;
  }

  public NeighborhoodStructure getNeighborhoodStructure() {
    return neighborhoodStructure;
  }

  public void setNeighborhoodStructure(NeighborhoodStructure neighborhoodStructure) {
    this.neighborhoodStructure = neighborhoodStructure;
  }
  
  
   
}
