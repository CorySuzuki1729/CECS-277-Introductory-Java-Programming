/**
 * The Hero class contains information on the player such as name, hp, max hp, current level,
 * number of gold, number of potions, number of keys, and current location.
 */

import java.awt.*;
public class Hero extends Entity implements Archer, Magical, Fighter {
  private Point loc;
  private int level;
  private int gold;
  private int keys;
  private int potions;

  /**
   * The hero constructor creates a new hero object with the following stats.
   * @param n the name of the player
   */
//------------------------------ Stat Functions
  public Hero (String n) {
    super(n, 25); // max HP is 25
    loc = Map.getInstance().findStart();
    level = 1;
    gold = 25;
    potions = 1;
    keys = 0;
  }

  /**
   * Increments the level of the player and loads the map to the next level.
   */
  public void levelUp() {
    level += 1;
    Map.getInstance().loadMap(level);
  }

  /**
   * returns the current level of the hero.
   * @return the integer value of the hero's level.
   */
  public int getLevel() {
    return level;
  }

  /**
   * a string message that contains the hero's stats.
   * @return the string that displays hp, max hp, level, gold, potions, keys, and location.
   */
  public String toString() {
    return (getName()+"\nHP: "+getHp()+"/25\nLevel: "+getLevel()+"\nGold: "+getGold()+"\nP: "+potions+" K: "+keys+Map.getInstance().mapToString(loc));
  }

  /**
   * Allows the user to go north and restricts the user from going out of bounds of the map
   * in that direction.
   * @return the current location of the player after the move.
   */
//------------------------------ Map Functions
  public char goNorth () {
    try {
      loc.translate(-1, 0);
      return Map.getInstance().getCharAtLoc(loc);
      }
    catch (ArrayIndexOutOfBoundsException OOB) {
      loc.translate(1, 0);
      System.out.println("You cannot go that way.");
      }
    return 'x';
  }

  /**
   * Allows the user to go south and restricts the user from going out of bounds of the map
   * in that direction.
   * @return the current location of the player after the move.
   */
  public char goSouth() {
    try {
      loc.translate(1, 0);
      return Map.getInstance().getCharAtLoc(loc);
      }
    catch (ArrayIndexOutOfBoundsException OOB) {
      loc.translate(-1, 0);
      System.out.println("You cannot go that way.");
      }
    return 'x';
  }

  /**
   * Allows the user to go east and restricts the user from going out of bounds of the map
   * in that direction.
   * @return the current location of the player after the move.
   */
  public char goEast() {
    try {
      loc.translate(0, 1);
      return Map.getInstance().getCharAtLoc(loc);
      }
    catch (ArrayIndexOutOfBoundsException OOB) {
      loc.translate(0, -1);
      System.out.println("You cannot go that way.");
      }
    return 'x';
  }

  /**
   * Allows the user to go west and restricts the user from going out of bounds of the map
   * in that direction.
   * @return the current location of the player after the move.
   */
  public char goWest() {
    try {
      loc.translate(0, -1);
      return Map.getInstance().getCharAtLoc(loc);
      }
    catch (ArrayIndexOutOfBoundsException OOB) {
      loc.translate(0, 1);
      System.out.println("You cannot go that way.");
      }
    return 'x';
  }

  /**
   * This method returns a string that displays the menu of attack options.
   * @return The attack menu represented as a string.
   */
//------------------------------ Menu Functions
  public String getAttackMenu() {
    return "1. Physical Attack \n2. Magical Attack \n3. Ranged Attack";
  }

  /**
   * The number of attack menu options is returned here.
   * @return The number of attack menu options.
   */
  public int getNumAttackMenuItems() {
    return 3;
  }

  /**
   * This method calls the respective attack menu depending on the player's choice.
   * @param choice an integer value that is the player's choice.
   * @return the respective attack menu such as fighter, magic, or archer.
   */
  public String getSubAttackMenu(int choice) {
    if (choice == 1) {
      return FIGHTER_MENU;
    }
    else if (choice == 2) {
      return MAGIC_MENU;
    }
    else {
      return ARCHER_MENU;
    }
  }

  /**
   * The number of subattack menu items is returned here depending on the player's choice.
   * @param choice The integer that represents the player's choice.
   * @return the number of subattack menu options.
   */
  public int getNumSubAttackMenuItems(int choice) { 
    if(choice == 1){
      return NUM_FIGHTER_MENU_ITEMS;
    }
    else if(choice == 2){
      return NUM_MAGIC_MENU_ITEMS;
    }
    else{
      return NUM_ARCHER_MENU_ITEMS;
    }
  }

  /**
   * The attack method calls and executes the appropriate attack based on the player's choice against
   * an enemy by the hero.
   * @param e the enemy that the hero is fighting.
   * @param choice the integer value which is the user's choice.
   * @param subChoice the integer value which is the user's subchoice.
   * @return the method call that corresponds to the choices made by the player.
   */
//------------------------------ Hero's Attack
  public String attack(Enemy e, int choice, int subChoice) {
    if ( choice == 1 ) {
      if ( subChoice == 1 ) {
        return sword(e);
      } else {
        return axe(e);
      }
    }
    else if ( choice == 2 ) {
      if ( subChoice == 1 ) {
        return magicMissile(e);
      } else {
        return fireball(e);
      }
    }
    else {
      if ( subChoice == 1 ) {
        return arrow(e);
      } else {
        return fireArrow(e);
      }
    }
  }

  /**
   * This method returns the number of gold the player currently has.
   * @return the integer value of current gold.
   */
//------------------------------ Gold Functions
  public int getGold() {
    return gold;
  }

  /**
   * This method collects the gold and increments that amount to the player's current gold count.
   * @param g the amount of gold collected by the player.
   */
  public void collectGold(int g) {
    gold += g;
    Map.getInstance().removeCharAtLoc(loc); //collectGold only activates when an enemy is defeated. This removes the enemy from the map.
  }

  /**
   * This method decrements an amount of gold spent if the player has any gold and uses a
   * specified amount.
   * @param g amount of gold to be decremented.
   * @return true or false depending on if any gold was spent.
   */
  public boolean spendGold(int g) {
    if (gold >= g) {
      gold -= g;
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * This method verifies if the user has more than 0 keys.
   * @return true or false depending on if the user has any keys.
   */
//------------------------------ Key Functions
  public boolean hasKey() {
    if (keys > 0) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Increments the number of keys the player has by 1.
   */
  public void pickUpKey() {
    keys += 1;
  }

  /**
   * This method decrements the amount of keys used by 1 if the player has any keys.
   * @return true or false depending on if the user has any keys and uses them.
   */
  public boolean useKey() {
    if (hasKey()) {
      keys -= 1;
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * This method verifies if the player has potions currently.
   * @return true if the potion number is greater than 0, false otherwise.
   */
//------------------------------ Potion Functions
  public boolean hasPotion() {
    if (potions > 0) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Increments the number of potions by 1.
   */
  public void pickUpPotion() {
    potions += 1;
  }

  /**
   * This method decrements the amount of potions by 1.
   * @return true if the player has potions and uses 1, otherwise false.
   */
  public boolean usePotion() {
    if (hasPotion()) {
      potions -= 1;
      heal();
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * This method generates a random number to inflict damage onto the enemy and displays a
   * successful attack message.
   * @param e the enemy entity.
   * @return the string message that displays how much damage was inflicted by the hero
   * onto the monster.
   */
//------------------------------ Interface Method Implementations

  @Override
  public String arrow(Entity e) {
    int damage = (int)(Math.random()*6+1);  //random in [1,6]
    e.takeDamage(damage);
    return (getName()+" hits "+e.getName()+" with an arrow for "+damage+" damage.");
  }

  /**
   * This method generates a random number to inflict damage onto the enemy and displays a
   * successful attack message.
   * @param e the enemy entity.
   * @return the string message that displays how much damage was inflicted by the hero
   * onto the monster.
   */
  @Override
  public String fireArrow(Entity e) {
    int damage = (int)(Math.random()*8+1); //random in [1,8]
    e.takeDamage(damage);
    return (getName()+" hits "+e.getName()+" with a fire arrow for "+damage+" damage.");
  }

  /**
   * This method generates a random number to inflict damage onto the enemy and displays a
   * successful attack message.
   * @param e the enemy entity.
   * @return the string message that displays how much damage was inflicted by the hero
   * onto the monster.
   */
  @Override
  public String sword(Entity e) {
    int damage = (int)(Math.random()*8+1);  //random in [1,8]
    e.takeDamage(damage);
    return (getName()+" slashes "+e.getName()+" for "+damage+" damage.");
  }

  /**
   * This method generates a random number to inflict damage onto the enemy and displays a
   * successful attack message.
   * @param e the enemy entity.
   * @return the string message that displays how much damage was inflicted by the hero
   * onto the monster.
   */
  @Override
  public String axe(Entity e) {
    int damage = (int)(Math.random()*12+1); //random in [1,12]
    e.takeDamage(damage);
    return (getName()+" hits "+e.getName()+" for "+damage+" damage.");
  }

  /**
   * This method generates a random number to inflict damage onto the enemy and displays a
   * successful attack message.
   * @param e the enemy entity.
   * @return the string message that displays how much damage was inflicted by the hero
   * onto the monster.
   */
  @Override
  public String magicMissile(Entity e) {
    int damage = (int)(Math.random()*10+1);  //random in [1,10]
    e.takeDamage(damage);
    return (getName()+" fires a magic missile at "+e.getName()+" for "+damage+" damage.");
  }

  /**
   * This method generates a random number to inflict damage onto the enemy and displays a
   * successful attack message.
   * @param e the enemy entity.
   * @return the string message that displays how much damage was inflicted by the hero
   * onto the monster.
   */
  @Override
  public String fireball(Entity e) {
    int damage = (int)(Math.random()*16+1);   //random in [1,16]
    e.takeDamage(damage); 
    return (getName()+" blasts "+e.getName()+" with a fireball for "+damage+" damage.");
  }
}