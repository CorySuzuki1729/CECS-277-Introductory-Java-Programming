/**
 * This is the Wizard class which extends the enemy class and implements the Magical
 * interface. This class represents enemies in the game that act as antagonists against the hero.
 * Note that Wizard uses super() for the name and maximum hit points as a means to use these
 * parameters in this program as well through composition. These methods override the generalized
 * methods created in the Magical interface.
 */


public class Wizard extends Enemy implements Magical {

  /**
   * The Wizard constructor which passes in the name and maximum hit point stats
   * for the character.
   * @param n A string that represents the name of the enemy.
   * @param mHp An integer value which represents the maximum hit points of the enemy.
   */
  public Wizard(String n, int mHp) { super(n, mHp); }
  //------------------------------ Methods for composition

  /**
   * This is the attack method which passes in the hero object and assigns a randomized integer
   * to either execute the magic missile or the fireball method.
   * @param h The hero passed in as an object.
   * @return The method in this class to be executed.
   */
  @Override
  public String attack(Hero h) {
    if ((int)(Math.random()*2+1) == 1) {  //damage restricted between 1 and 2.
      return magicMissile(h);
    }
    else {
      return fireball(h);
    }
  }

  /**
   * The magic missile method passes in an entity and applies damage to the character, mainly the hero
   * inflicted by the enemy.
   * @param e The entity particularly referring to the hero.
   * @return The string message which notifies the player of how much damage the enemy has inflicted.
   */
  @Override
  public String magicMissile(Entity e) {
    int damage = (int)(Math.random()*5+1); // 1-5 damage restriction.
    e.takeDamage(damage);
    return ("The "+getName()+" zaps you for "+damage+" damage.");
  }

  /**
   * The fireball method passes in an entity and applies damage to the character, mainly the hero
   * inflicted by the enemy.
   * @param e The entity particularly referring to the hero.
   * @return The string message which notifies the player of how much damage the enemy has inflicted.
   */
  @Override
  public String fireball(Entity e) {
    int damage = (int)(Math.random()*8+2); // 2-8 damage restriction.
    e.takeDamage(damage);
    return ("The "+getName()+" launches a fireball at you for "+damage+" damage.");
  }
}