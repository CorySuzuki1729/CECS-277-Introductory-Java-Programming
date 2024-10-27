/**
 * This is the Magical interface class which provides a template for the methods to be
 * overwritten in the respective class which implements the Magical interface. It provides
 * the generic templates for a magic missile and fireball attack which are magical actions
 * that can be executed in the game.
 */

public interface Magical {
  public static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball";
  public static final int NUM_MAGIC_MENU_ITEMS = 2;

  /**
   * This is the magic missile method which passes in an entity and returns a string that reveals how
   * much damage this attack has done. However since this is an interface, it is generalized
   * here and given a random number and override in the class that implements this interface.
   * @param e The entity that represents a character in the game such as the hero.
   * @return A string which reveals how much damage the magic missile attack has inflicted.
   */
  public String magicMissile(Entity e);

  /**
   * This is the fireball method which passes in an entity and returns a string that reveals how
   * much damage this attack has done. However since this is an interface, it is generalized
   * here and given a random number and override in the class that implements this interface.
   * @param e The entity that represents a character in the game such as the hero.
   * @return A string which reveals how much damage the fireball attack has inflicted.
   */
  public String fireball(Entity e); 
}