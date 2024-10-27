
/**
 * CECS 277 Group Project
 * Sec 01- Group 5
 * 17 April 2022
 * Alex Dauscher
 * Cory Suzuki
 * Kyongsu Kang
 *
 * Description: This program simulates a dungeon crawler game where the user can input any name
 * and play as a character through 3 selected levels of the dungeon. The user is to encounter
 * randomly generated monsters and conduct full-scale mini-battles with them, resulting
 * in the dropping of a randomized amount of gold. The player can use potions to heal, gold
 * to obtain keys or potions, and must make it from the start point to the end point
 * of each level's map. In particular this master class runs the entirety of the program
 * utilizing each of the different types of classes and design patterns learned in CECS 277.
 *
 * Assignments: Alex was tasked with writing the code for the main, fighter, archer, entity,
 * and magical classes. Cory was tasked with writing the code for Map, hero, warrior, wizard, and
 * the Javadocs for this program. Kyongsu was tasked with writing the code for the Ranger, enemy,
 * enemy generator, archer, and depicted the UML diagram. Each author contributed to
 * error catching/exceptions and debugging.
 */

/**
 * As mentioned above, this class is the main class which runs the program. It obtains a new
 * hero's name and uses it to create a new player object. Then if the player makes it past
 * level 3, they win the game. Otherwise if the player dies (no hp left), then their game is
 * over. The player-enemy turn cycle, player movement in the map, and the store are all also
 * ran in this class.
 */
class Main {
  public static void main(String[] args) {
    Map.getInstance().loadMap(1);
    System.out.print("What is your name, traveler? ");
    String player_name = CheckInput.getString();
    Hero player = new Hero(player_name);
    while(player.getHp() > 0) {
      System.out.println(player);
      mainMenu(player);
    }
    if(player.getHp() == 0){
      System.out.println("Game Over.");
      System.exit(0);
     }
  }

  /**
   * This method creates a new enemy generator to generate enemies and displays the main
   * movement menu. In addition, messages will be displayed if the user finds a key or
   * needs a hint, and when the user finds the potion; these messages are displayed
   * in the console.
   * @param h the hero object
   * @return the hero's current hp.
   */
  public static int mainMenu(Hero h) {
    EnemyGenerator eGen = new EnemyGenerator();
    char roomType = 'x';
    int select;
    
    System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
    select = CheckInput.getIntRange(1,5);
    if (select == 1) {
      roomType = h.goNorth();
    }
    else if (select == 2) {
      roomType = h.goSouth();
    }
    else if (select == 3) {
      roomType = h.goEast();
    }
    else if (select == 4) {
      roomType = h.goWest();
    }
    else {
      System.out.println("\nGame Over");
      System.exit(0);
    }

    if (roomType == 'x' || roomType == 'n') {}
    else if (roomType == 's') {
      store(h);
    }
    else if (roomType == 'f') {
      System.out.print("You have found a locked gate. ");
      if (h.hasKey()) {
        System.out.println("Luckily you have a key! You proceed to the next area.");
        h.useKey();
        h.levelUp();
      } else { System.out.println("If only you had a key..."); }
    }
    else if (roomType == 'i') {
      int item = (int)(Math.random()*2+1);
      if (item == 1 ) {
        System.out.println("You found a key!");
        h.pickUpKey();
      }
      else {
        System.out.println("You found a potion!");
        h.pickUpPotion();
      }
    }
    else if (roomType == 'm') {
      monsterRoom(h, eGen.generateEnemy(h.getLevel()));
    }

      
    return h.getHp();
  }

  /**
   * This method executes when the player enters a room where a monster is randomly
   * generated, and allows the user to either fight or run away.
   * @param h the hero object
   * @param e the enemy object
   * @return a true or false boolean value depending on if the player runs away or
   * decides to fight the monster.
   */
  public static boolean monsterRoom(Hero h, Enemy e) {
    int select;
    int eMaxHp = e.getHp();
    System.out.println("You've encountered a "+e.getName());
    while (h.getHp() > 0 && e.getHp() > 0) {
      System.out.println(e.getName()+"\nHP: "+e.getHp()+"/"+eMaxHp);
      
      System.out.println("1. Fight");
      System.out.println("2. Run Away");
      if(h.hasPotion()){
        System.out.println("3. Drink Potion"); // Kyongsu: I fixed it
        select = CheckInput.getIntRange(1,3);
      }else{
        select = CheckInput.getIntRange(1,2);
      }
      if (select == 1) {
        fight(h,e);
      }
      else if (select == 2) {
        return true;
      }
      else{
        h.usePotion();
      }
    }
    return false;
  }

  /**
   * This method regulates the fights between a monster and the hero, in which the hero's
   * choices are taken into consideration to execute certain classes and their methods to
   * execute certain attacks. If the player defeats the enemy, gold is dropped as a reward,
   * and otherwise the player will get a game over.
   * @param h the hero object
   * @param e the enemy object
   * @return true or false boolean depending on if the hero won the battle or not.
   */
  public static boolean fight(Hero h, Enemy e) {
//Players Turn
    int typeChoice;
    int attackChoice;
    System.out.println(h.getAttackMenu());
    typeChoice = CheckInput.getIntRange(1, h.getNumAttackMenuItems()); //Always 1-3
    System.out.println(h.getSubAttackMenu(typeChoice));
    attackChoice = CheckInput.getIntRange(1, h.getNumSubAttackMenuItems(typeChoice)); //Always 1-2
    System.out.println(h.attack(e, typeChoice, attackChoice));
//Enemies Turn
    if (e.getHp() > 0) {
      System.out.println(e.attack(h));
    }
    else {
      int reward = (int)(Math.random()*20+1);
      System.out.println("You found "+reward+" gold on the corpse.");
      h.collectGold(reward);
    }
    
    if (h.getHp() == 0) {
      return false;
    }
    else {
      return true;
    }
  }

  /**
   * This method displays the menu options of the store in which the Hero can purchase
   * helpful items from. If the user spends their gold on a potion or key, then the posted
   * amount is deducted from the hero's total amount of gold.
   * @param h the hero object.
   */
  public static void store(Hero h) {
    int select;
    System.out.println("Welcome to the store. What would you like to buy?");
    while (true) {
      System.out.println("1. Health Potion - 25g \n2. Key - 50g \n3. Nothing, just browsing...");
      select = CheckInput.getIntRange(1,3);
      if (select == 1) {
        if (h.spendGold(25)) {
          h.pickUpPotion();
        }
      }
      else if (select == 2) {
        if (h.spendGold(50)) {
          h.pickUpKey();
        }
      }
      else {
        return;
      }
    }
  }
}