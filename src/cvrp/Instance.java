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
}
