/**
 * This is the enemy generator class which generates a random enemy object from data
 * read from a provided enemy file and stored in a HashMap.
 */

import java.io.*;    //import statements to use the Collection class and the Scanner.
import java.util.*;

public class EnemyGenerator {
  private HashMap <String, Integer> enemies = new HashMap <String, Integer>();

  /**
   * This is the EnemyGenerator constructor which reads in the enemy file and stores the name of
   * the enemies and their maximum hit points inside of the private HashMap.
   */
  public EnemyGenerator() {
    try {
      Scanner reader = new Scanner(new File("Enemies.txt"));
      while (reader.hasNext()) {
        String line = reader.nextLine();  //Read the lines of the file.
        String [] vals = line.split(",");
        
        String name = vals[0];
        int health = Integer.parseInt(vals[1]);  //Read in the integers of the file.
        enemies.put(name, health);
      }
    }
    catch (FileNotFoundException fnf) {
      System.out.println("Error, file not found.");  //Required fnf error.
    }
  }

  /**
   * This is the generateEnemy method which generates a random number and uses an ArrarList to
   * organize the enemies into key-value pairs. Then the random number is used to assign
   * certain enemies to different classes of types of enemies.
   * @param level The level expressed as an integer to increase the difficulty of the enemies.
   * @return The enemy as a specific type, either a warrior, ranger, or wizard.
   */
  public Enemy generateEnemy(int level) {
    
    int randomEnemy = (int) (Math.random() * enemies.size());
    int enemyAbility = (int) (Math.random() * 3) + 1;   //Restricted from 1 to 3.

    List<String> keysAsArray = new ArrayList<String>(enemies.keySet());
    String enemyName = keysAsArray.get(randomEnemy);
    int enemyHp = enemies.get(enemyName) + level; // increases difficulty by adding level
    
    if (enemyAbility == 1) {
      return new Warrior (enemyName+" Warrior", enemyHp);
      
    }
    else if (enemyAbility == 2) {
      return new Wizard (enemyName+" Wizard", enemyHp);
      
    }
    else {
     return new Ranger (enemyName+" Ranger", enemyHp);
      
    }
  }
}