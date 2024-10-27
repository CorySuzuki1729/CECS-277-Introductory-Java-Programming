/**
 * This is the Map class which simulates the map board used in the dungeon game, where the
 * player traverses through it. Two 2d arrays are initialized to represent the map and one is
 * used to keep track of the areas that the player already visited.
 */

import java.util.*; 
import java.io.*;
import java.awt.*;

public class Map {
  private char [][] map;
  private boolean [][] revealed;
  private static Map instance = null;  //singleton pattern characteristic, set to null.

  /**
   * This is the Map constructor which sets the dimensions of the two 2d arrays into
   * rows and columns, particularly 5 by 5.
   */
  private Map() {
    map = new char[5][5];
    revealed = new boolean[5][5];
  }

  /**
   * This is the Map getinstance method which uses the singleton design pattern to create
   * a new instance of the Map.
   * @return the game's map expressed as a Map object of itself.
   */
  public static Map getInstance() {
    if(instance == null){
      instance = new Map();
    }
    return instance;
  }

  /**
   * This is the loadmap method which reads in the respective map and fills in the map with
   * *'s to hide each room's contents and locates the start point. The point class is used here
   * to express each position on the board in terms of the Cartesian corrdinate plane in R^2
   * (2nd dimensional plane of the real numbers).
   * @param mapNum The integer value of the map to be loaded.
   */
  public void loadMap(int mapNum) {
    map = new char[5][5];
    revealed = new boolean[5][5];

    String mapFileName = "";
    if (mapNum == 1) {
      mapFileName = "Map1.txt";
    }
    else if (mapNum == 2) {
      mapFileName = "Map2.txt";
    }
    else if (mapNum == 3) {
      mapFileName = "Map3.txt";
    }
    else {
      if((mapNum % 3) == 1) {
        mapFileName = "Map1.txt";
      }
      else if ((mapNum % 3) == 2) {
        mapFileName = "Map2.txt";
      }
      else if ((mapNum % 3) == 0) {
        mapFileName = "Map3.txt";
      }
    }

    try {
      Scanner read = new Scanner( new File( mapFileName ) );  //read in the file.
      int row = 0;
      while (read.hasNextLine()) { 
        String line = read.nextLine();
        String [] vals = line.split(" ");
        for(int col = 0; col < 5; col++){
          map [row][col] = vals[col].charAt(0);
        }
        row++;
      }
      read.close();
    } catch ( FileNotFoundException fnf) {         //required fnf error.
      System.out.println("Error, file not found.");
    }
  }

  /**
   * This is the getcharatLoc method which passes in a point class object and returns the point's
   * x and y coordinates on the map.
   * @param P The point class object.
   * @return The coordinates of map expressed in terms of x and y on the corrdinate plane with
   * respect to the map.
   */
  public char getCharAtLoc(Point P) {
    return map[P.x][P.y];
  }

  /**
   * This is the findstart method which searches for the starting point on the map indicated
   * by the character 's', and initializes a point there at that location to represent
   * the player's initial position.
   * @return The point object which represents the starting point of the map.
   */
  public Point findStart() {
    for (int i = 0; i<5; i++) {
      for (int j = 0; j<5; j++) {   //search each row and column until an 's' is found.
        if (map[i][j] == 's') {
          Point s = new Point (i, j);  //Place a point at that found location.
          reveal(s);
          return s;
        }
      }
    }
    System.out.println("Error In File: No Start 's' Found");
    return null;
  }

  /**
   * This is the reveal method which sets the revealed 2d array to true if the point has been
   * visited by the player.
   * @param P the point object which represents the player's current location on the map.
   */
  public void reveal(Point P) {
    revealed[P.x][P.y] = true;
  }

  /**
   * This is the removecharatloc method which marks an empty location with the character 'n'.
   * @param P The point object that expresses the player's current position.
   */
  public void removeCharAtLoc(Point P) { //Assuming this means replace the encounter with "nothing" or 'n'
    map[P.x][P.y] = 'n';
  }

  /**
   * This is the maptostring method which returns the 2d array map which displays the map
   * as a string to the player's console.
   * @param P The point P object assuming the player's position.
   * @return The current map expressed as a string.
   */
  public String mapToString(Point P) { //Assuming P here is the player's position.
    reveal(P);
    String mapString = "";
    for (int i = 0; i<5; i++) {
      mapString += "\n";
      for (int j = 0; j<5; j++) {
        if (revealed[i][j] == true) {
          if (i == P.x && j == P.y) {  //if the player is at that location, mark it with an aterisk.
            mapString+= "* ";
          }
          else {
            mapString+=(map[i][j]+" ");
          }
        }
        else {
          mapString+= "x ";  //Mark unvisited rooms in the map with an 'x'.
        }
      }
    }
    return mapString;
  }
}