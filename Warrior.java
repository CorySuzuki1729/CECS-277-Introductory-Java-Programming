/**
 * This is the Warrior class which extends the enemy class and implements the Fighter
 * interface. This class represents enemies in the game that act as antagonists against the hero.
 * Note that Warrior uses super() for the name and maximum hit points as a means to use these
 * parameters in this program as well through composition. These methods override the generalized
 * methods created in the Fighter interface.
 */


public class Warrior extends Enemy implements Fighter {
  /**
   * The Warrior constructor which passes in the name and maximum hit point stats
   * for the character.
   * @param n A string that represents the name of the enemy.
   * @param mHp An integer value which represents the maximum hit points of the enemy.
   */
  public Warrior(String n, int mHp) { super(n, mHp); }
  //------------------------------ Methods for composition

  /**
   * This is the attack method which passes in the hero object and assigns a randomized integer
   * to either execute the sword or the axe method.
   * @param h The hero passed in as an object.
   * @return The method in this class to be executed.
   */
  @Override
  public String attack(Hero h) {
    if ((int)(Math.random()*2+1) == 1) {  //Random restrictions between 1 and 2.
      return sword(h);
    }
    else {
      return axe(h);
    }
  }

  /**
   * The sword method passes in an entity and applies damage to the character, mainly the hero
   * inflicted by the enemy.
   * @param e The entity particularly referring to the hero.
   * @return The string message which notifies the player of how much damage the enemy has inflicted.
   */
  @Override
  public String sword(Entity e) {
    int damage = (int)(Math.random()*4+1); // 1 - 4 damage restriction
    e.takeDamage(damage);
    return ("The "+getName()+" slashes at you for "+damage+" damage.");
  }

  /**
   * The axe method passes in an entity and applies damage to the character, mainly the hero
   * inflicted by the enemy.
   * @param e The entity particularly referring to the hero.
   * @return The string message which notifies the player of how much damage the enemy has inflicted.
   */
  @Override
  public String axe(Entity e) {
    int damage = (int)(Math.random()*6+2); // 2 - 6 damage restriction
    e.takeDamage(damage);
    return ("The "+getName()+" lunges at you for "+damage+" damage.");
  }
}