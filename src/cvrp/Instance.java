/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

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
  private char MULTI_THREAD;
  private char TABU_RESTRICTION;
  private char SELECT_ORIGIN_ROUTE;
  private char SELECT_CLIENT;
  private char SELECT_DESTINATION_ROUTE;
  private char SELECT_INSERT_POSITION;
  private int customersNumber;
  private int vehicleCapacity;
  private int maximumRouteTime;
  private int dropTime;
  private int [] quantity;
  private int [][] distance;
  private int [] depotCoordinate;
  private int [][] customersCoordinate;
  
  public Instance (String instanceName, String settingsName) {
    this.instanceName = instanceName;
    this.settingsName = settingsName;
    this.depotCoordinate = new int [2];
  }
  
  public void loadInstance() throws FileNotFoundException {
    
    File file = new File(this.instanceName);
    
    try {
      
      Scanner scanner = new Scanner(file);
      
      this.customersNumber = scanner.nextInt();
      this.vehicleCapacity = scanner.nextInt();
      this.maximumRouteTime = scanner.nextInt();
      this.dropTime = scanner.nextInt();
      this.depotCoordinate[0] = scanner.nextInt();
      this.depotCoordinate[1] = scanner.nextInt();
      this.quantity = new int [customersNumber];
      this.customersCoordinate = new int [customersNumber][2];
      int c = 0;
      
      while(scanner.hasNextInt()) {
        
        this.customersCoordinate[c][0] = scanner.nextInt();
        this.customersCoordinate[c][1] = scanner.nextInt();
        this.quantity[c] = scanner.nextInt();
        c++;
      }
    } catch(FileNotFoundException e) {
      
    }
  }
  
  public void loadSettings() throws FileNotFoundException {
    
    File file = new File(this.settingsName);
    
    try {
      
      Scanner scanner = new Scanner(file);
      
      while(scanner.hasNext()) {
        
        if("MULTI_THREAD".equals(scanner.next()))
          this.MULTI_THREAD = scanner.next().charAt(0);
        else if("TABU_RESTRICTION".equals(scanner.next()))
          this.TABU_RESTRICTION = scanner.next().charAt(0);
        else if("SELECT_ORIGIN_ROUTE".equals(scanner.next()))
          this.SELECT_ORIGIN_ROUTE = scanner.next().charAt(0);
        else if("SELECT_CLIENT".equals(scanner.next()))
          this.SELECT_CLIENT = scanner.next().charAt(0);
        else if("SELECT_DESTINATION_ROUTE".equals(scanner.next()))
          this.SELECT_DESTINATION_ROUTE = scanner.next().charAt(0);
        else if("SELECT_INSERT_POSITION".equals(scanner.next()))
          this.SELECT_INSERT_POSITION = scanner.next().charAt(0);
      }
        
    } catch(FileNotFoundException e) {
      
    }
  }

  public char getMULTI_THREAD() {
    return MULTI_THREAD;
  }

  public void setMULTI_THREAD(char MULTI_THREAD) {
    this.MULTI_THREAD = MULTI_THREAD;
  }

  public char getSELECT_CLIENT() {
    return SELECT_CLIENT;
  }

  public void setSELECT_CLIENT(char SELECT_CLIENT) {
    this.SELECT_CLIENT = SELECT_CLIENT;
  }

  public char getSELECT_DESTINATION_ROUTE() {
    return SELECT_DESTINATION_ROUTE;
  }

  public void setSELECT_DESTINATION_ROUTE(char SELECT_DESTINATION_ROUTE) {
    this.SELECT_DESTINATION_ROUTE = SELECT_DESTINATION_ROUTE;
  }

  public char getSELECT_INSERT_POSITION() {
    return SELECT_INSERT_POSITION;
  }

  public void setSELECT_INSERT_POSITION(char SELECT_INSERT_POSITION) {
    this.SELECT_INSERT_POSITION = SELECT_INSERT_POSITION;
  }

  public char getSELECT_ORIGIN_ROUTE() {
    return SELECT_ORIGIN_ROUTE;
  }

  public void setSELECT_ORIGIN_ROUTE(char SELECT_ORIGIN_ROUTE) {
    this.SELECT_ORIGIN_ROUTE = SELECT_ORIGIN_ROUTE;
  }

  public char getTABU_RESTRICTION() {
    return TABU_RESTRICTION;
  }

  public void setTABU_RESTRICTION(char TABU_RESTRICTION) {
    this.TABU_RESTRICTION = TABU_RESTRICTION;
  }

  public int[][] getCustomersCoordinate() {
    return customersCoordinate;
  }

  public void setCustomersCoordinate(int[][] customersCoordinate) {
    this.customersCoordinate = customersCoordinate;
  }

  public int getCustomersNumber() {
    return customersNumber;
  }

  public void setCustomersNumber(int customersNumber) {
    this.customersNumber = customersNumber;
  }

  public int[] getDepotCoordinate() {
    return depotCoordinate;
  }

  public void setDepotCoordinate(int[] depotCoordinate) {
    this.depotCoordinate = depotCoordinate;
  }

  public int[][] getDistance() {
    return distance;
  }

  public void setDistance(int[][] distance) {
    this.distance = distance;
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
  
}
