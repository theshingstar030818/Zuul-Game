package model.object;

import java.util.Random;

import model.Room;

/**
 * Monster class
 * @author Sean Byron
 *	October 21st 2012
 */
public class Monster {

	private String name;
	private int health;
	private Room currentRoom;
	private Room lastRoom;

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
	 */
	public void decreaseHealth() {
		if (health > 0) {
			health--;
		}
	}

	/**
	 * Increases the health of the monster by one
	 */
	public void increaseHealth() {
		health++;
	}

	/**
	 * Indicates whether the monster is alive
	 * @return true if monster has health greater than 0
	 */
	public boolean isAlive() {
		return health>0;
	}

	/**
	 * Returns the current health value
	 * @return health
	 */
	public int getHealth() {
		return health;
	}
	public void setCurrentRoom(Room r){
		currentRoom = r;
	}
	public Room getCurrentRoom(){
		return currentRoom;
	}
	public String randomMove(){
		Random r = new Random();
		String[] exits = new String[]{"east","west","south","north"};
		return exits[r.nextInt(4)];
	}


}