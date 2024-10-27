/**
 * This is the Ranger class which extends the enemy class and implements the Archer
 * interface. This class represents enemies in the game that act as antagonists against the hero.
 * Note that Ranger uses super() for the name and maximum hit points as a means to use these
 * parameters in this program as well through composition. These methods override the generalized
 * methods created in the Archer interface.
 */



public class Ranger extends Enemy implements Archer {

    /**
     * The Ranger constructor which passes in the name and maximum hit point stats
     * for the character.
     * @param n A string that represents the name of the enemy.
     * @param mHp An integer value which represents the maximum hit points of the enemy.
     */
  public Ranger(String n, int mHp) { super(n, mHp); }
  //------------------------------ Methods for composition

    /**
     * This is the attack method which passes in the hero object and assigns a randomized integer
     * to either execute the arrow or the firearrow method.
     * @param h The hero passed in as an object.
     * @return The method in this class to be executed.
     */
  @Override
  public String attack(Hero h) {
   if ((int)(Math.random()*2+1) == 1) {   //Random restriction from 1 to 2.
     return (arrow(h));
   }
   else {
      return (fireArrow(h));
    }
  }

    /**
     * The arrow method passes in an entity and applies damage to the character, mainly the hero
     * inflicted by the enemy.
     * @param e The entity particularly referring to the hero.
     * @return The string message which notifies the player of how much damage the enemy has inflicted.
     */
  @Override
  public String arrow(Entity e) {
    int damage = (int)(Math.random()*2+1); // 1 - 2 random restriction.
    e.takeDamage(damage);
    return ("The "+getName()+" fires an arrow at you for "+damage+" damage.");
  }

    /**
     * The firearrow method passes in an entity and applies damage to the character, mainly the hero
     * inflicted by the enemy.
     * @param e The entity particularly referring to the hero.
     * @return The string message which notifies the player of how much damage the enemy has inflicted.
     */
  @Override
  public String fireArrow(Entity e) {
    int damage = (int)(Math.random()*4+1); // 1 - 4 damages restriction.
    e.takeDamage(damage);
    return "The "+getName()+" fires a fire arrow at you for "+damage+" damage.";
  }
}