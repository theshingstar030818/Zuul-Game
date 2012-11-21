package testSuite;

import junit.framework.TestCase;
import model.Game;
import model.object.*;
public class MosnterTest extends TestCase{
	Monster kracken;
	Player player1;
	protected void setUp() throws Exception {
		super.setUp();
		kracken = new Monster("Kracken",2);
		player1 = new Player("Dummy","Me",10,10);
	}
	public void testDecreaseHealth(){
		//check health decrease by one
		kracken.decreaseHealth();
		assertEquals(1,kracken.getHealth());
		//check if monster still decrease if it's health already 0
		kracken.decreaseHealth();
		kracken.decreaseHealth();
		kracken.decreaseHealth();
		assertEquals(0,kracken.getHealth());
	}
	public void testIncreaseHealth(){
		kracken.increaseHealth();
		assertEquals(3,kracken.getHealth());
	}
	public void testAlive(){
		kracken.decreaseHealth();
		kracken.decreaseHealth();
		assertEquals(false,kracken.isAlive());
	}
	public void testAttackPlayer(){
		kracken.attack(player1);
		assertEquals(9,player1.getHealth());
		
		kracken.attack(player1);
		kracken.attack(player1);
		assertEquals(7,player1.getHealth());
		
	}
	
	public void testUnAttackPlayer(){
		
		kracken.attack(player1);
		kracken.heal(player1);
		assertEquals(10,player1.getHealth());
		
		kracken.attack(player1);
		kracken.attack(player1);
		kracken.heal(player1);
		assertEquals(9,player1.getHealth());
		
	}
}
