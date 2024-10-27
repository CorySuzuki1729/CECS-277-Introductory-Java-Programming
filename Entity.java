/**
 * This is the Entity abstract class which creates a new Entity that takes in a name in the form
 * of a string and the maximum hit points of that character. This class holds additional information
 * such as the heal() method to heal (particularly for the hero) and a method which applies
 * damage points to the entity.
 */

abstract class Entity {
  private String name;
  private int hp;
  private int maxHp;

  /**
   * This is the Entity constructor which sets the name, maximum hit points, and the current hp to the
   * maximum hit points during the creation of the new entity object.
   * @param n The name of the character as a string.
   * @param mHp The maxmum number of hit points expressed as an integer.
   */
  public Entity(String n, int mHp) {
    name = n;
    maxHp = mHp;
    hp = mHp;
  }

  /**
   * This is the getname method that returns the name of the character as a string.
   * @return The name of the character expressed as a string.
   */
  public String getName() {
    return name;
  }

  /**
   * This is the gethp method which returns the current hit points of the character, no damage or
   * damage taken depending on when the method is used.
   * @return The current hit points expressed as an integer.
   */
  public int getHp() {
    return hp;
  }

  /**
   * This is the heal method which sets the current hit points to the maximum hit points.
   */
  public void heal() {
    hp = maxHp;
  }

  /**
   * This is the takedmaage method which passes in an integer value that represents how much damage
   * is applied to the character's hit points through decrementation. It uses a conditional to
   * convert negative hit points to 0, the minimum number of hit points any entity in the game
   * could have.
   * @param d The integer that expresses the damage taken.
   */
  public void takeDamage(int d) {
    hp -= d;
    if(hp < 0){
      hp = 0; //Set the hit points to 0 if the damage is negative.
    }
  }

  /**
   * This is the tostring method which displays the statistics of the entity character, such as
   * their name, current hit points, and the maximum hit points as a string data type.
   * @return A string which encompasses the information mentioned in the description of the
   * method above.
   */
  public String toString() {
    return(name+": "+hp+"/"+maxHp);
  }
}