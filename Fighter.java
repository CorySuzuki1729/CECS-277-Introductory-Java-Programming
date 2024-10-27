// Cory: Verified that the interfaces are good to go.

/**
 * This is the Fighter interface class which provides a template for the methods to be
 * overwritten in the respective class which implements the Fighter interface. It provides
 * the generic templates for a sword and axe attack which are physical actions that can be
 * executed in the game.
 */
public interface Fighter { 
  public static final String FIGHTER_MENU = "1. Sword\n2. Axe";
  public static final int NUM_FIGHTER_MENU_ITEMS = 2;

  /**
   * This is the sword method which passes in an entity and returns a string that reveals how
   * much damage this attack has done. However since this is an interface, it is generalized
   * here and given a random number and override in the class that implements this interface.
   * @param e The entity that represents a character in the game such as the hero.
   * @return A string which reveals how much damage the sword attack has inflicted.
   */
  public String sword(Entity e);

  /**
   * This is the axe method which passes in an entity and returns a string that reveals how
   * much damage this attack has done. However since this is an interface, it is generalized
   * here and given a random number and override in the class that implements this interface.
   * @param e The entity that represents a character in the game such as the hero.
   * @return A string which reveals how much damage the axe attack has inflicted.
   */
  public String axe(Entity e);
}