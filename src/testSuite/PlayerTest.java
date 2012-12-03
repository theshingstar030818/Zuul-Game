package testSuite;

import junit.framework.TestCase;
import model.*;
import model.object.*;

public class PlayerTest extends TestCase {
	private Player player1;
	private Room entrance;
	private Room lobby;
	private Item key;
	private Monster kracken;

	protected void setUp() throws Exception {
		super.setUp();
		player1 = new Player("Dummy", 10, 10);
		entrance = new Room("entrance");
		lobby = new Room("lobby");
		key = new Item("key", 8);
		entrance.setExits("north", lobby);
		lobby.setExits("south", entrance);
		lobby.addItem(key, "north");
		kracken = new Monster("Kracken", 10);

	}

	public void testCurrentRoom() {
		player1.setCurrentRoom(entrance);
		assertEquals(entrance.getDescription(), player1.getCurrentPlayerRoom()
				.getDescription());

	}

	public void testPickAndWeight() {
		player1.setCurrentRoom(lobby);
		player1.pick("key", key);
		assertEquals("key", player1.getItem("key").getItemName());
		assertEquals(8, player1.getCurrentWeight());
	}

	public void testDropAndWeight() {
		player1.pick("key", key);
		Item keyTest = player1.drop("key");
		assertEquals("key", keyTest.getItemName());
		assertEquals(0, player1.getCurrentWeight());
	}

	public void testMaxWeight() {
		assertEquals(10, player1.getMaxWeight());
		player1.pick("key", key);
		assertEquals(false, player1.pick("key", key));	
	}

	public void playerAttackedTest() {
		player1.attacked(kracken.getName());
		assertEquals(10, player1.getHealth());
	}
	public void playerEatTest(){
		player1.attacked(kracken.getName());
		player1.attacked(kracken.getName());
		player1.eat();
		assertEquals(10, player1.getHealth());
	}
	public void playerUnEatTest(){
		player1.attacked(kracken.getName());
		player1.attacked(kracken.getName());
		player1.eat();
		player1.unEat();
		assertEquals(8, player1.getHealth());
		
	}

}
