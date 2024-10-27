/**
 * This is the Archer interface which provides an abstraction of the methods arrow and
 * fireArrow, which are used in other classes in the program. These methods are to be
 * overwritten in the respective class in which the interface is implemented.
 */

public interface Archer {
  public static final String ARCHER_MENU = "1. Arrow\n2. Fire Arrow";  //Display the menu.
  public static final int NUM_ARCHER_MENU_ITEMS = 2;

  /**
   * This is the arrow method which applies the method to the entity e.
   * @param e An entity type that is used by classes such as the Hero class.
   * @return A String message which notifies the player of an attack's effectiveness.
   */
  public String arrow(Entity e);      //These methods are under the abstraction for arrow-based attacks.

  /**
   * This is the fireArrow method which is similar in applying the method to the entity e.
   * @param e An entity type that is used by classes such as the Hero class.
   * @return A String message which notifies the player of an attack's effectiveness.
   */
  public String fireArrow(Entity e);
}