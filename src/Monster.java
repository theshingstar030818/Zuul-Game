/**
 * Monster class
 * @author Sean Byron
 *	October 21st 2012
 */
public class Monster {
	
	private String name;
	private int health;
	
	/**
	 * Create a new monster
	 * @param name
	 * @param health
	 */
	public Monster(String name, int health) {
		this.name = name;
		this.health = health;
	}
	
	/**
	 * Returns the name of the monster
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Decreases the health of the monster by one
	 * @return true if monster is dead, false if monster is still alive
	 */
	public boolean decreaseHealth() {
		if (health > 0) {
			health--;
			if (health == 0) {
				return true;
			}
			return false;
		}
		return true;
	}
	
	/**
	 * Indicates whether the monster is alive
	 * @return true if monster has health greater than 0
	 */
	public boolean isAlive() {
		return health==0;
	}
	
	

}
