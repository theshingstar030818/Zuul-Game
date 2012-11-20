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
//	private Room lastRoom;

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

	public void attack(Player p) {
		Random r = new Random();
		int temp = r.nextInt()%8;
		if(temp % 2 == 0)//50% chance attack does one damage
		{
			p.attacked(getName(), 1);
		}
		else if(temp == 1)//1 in 8 chance attack does double damage
		{
			p.attacked(getName(), 2);
		}
		else//3 in 8 chance attack misses
		{
			System.out.println("Monster "+name+" attacked but missed");
		}
		p.attacked(name, 1);
		
	}


}