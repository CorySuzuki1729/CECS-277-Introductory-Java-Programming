/**
 * This is the Enemy class which is an abstract class that defines a constructor and an attack method
 * for a particular enemy class.
 */

abstract class Enemy extends Entity {

  /**
   * This is the Enemy class' constructor, which passes in a string n for name and the maximum hp
   * of the enemy that has its class overriding this abstract class.
   * @param n The name of the enemy as a string.
   * @param mHp The maximum hit points that the enemy can possess.
   */
  public Enemy(String n, int mHp) {
    super(n, mHp);
  }

  /**
   * This is the attack class which is an abstract class definition which passes in the Hero
   * object.
   * @param h The hero (player) object.
   * @return A String which has details of the attack damage applied to the hero inflicted
   * from the enemy.
   */
  public abstract String attack(Hero h);
} 