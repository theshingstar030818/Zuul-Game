package testSuite;

import model.Game;
import model.command.Command;
import junit.framework.TestCase;

public class GameTest extends TestCase{
	private Game game;
	protected void setUp() throws Exception {
		super.setUp();
		game = new Game();
		game.loadDefaultGame("test", null);
	}
	public void testGo(){
		//cant go through door since monster block the way
		game.processCommand(new Command("go","south"), true);
		assertEquals("Entrance",game.getPlayer().getCurrentPlayerRoom().getDescription());
		//killmonster
		for(int i = 10;i>0;i--){
			game.processCommand(new Command("attack","Kracken"), true);
		}
		//no monster in the room.Test all direction
		game.processCommand(new Command("go","south"), true);
		assertEquals("Lobby",game.getPlayer().getCurrentPlayerRoom().getDescription());
		
		game.processCommand(new Command("go","west"), true);
		assertEquals("Waiting Room",game.getPlayer().getCurrentPlayerRoom().getDescription());
		
		game.processCommand(new Command("go","east"), true);
		assertEquals("Lobby",game.getPlayer().getCurrentPlayerRoom().getDescription());
		
		game.processCommand(new Command("go","north"), true);
		assertEquals("Entrance",game.getPlayer().getCurrentPlayerRoom().getDescription());
		
	}
	public void testPick(){
		game.processCommand(new Command("pick","Tree"), true);
		assertEquals(null, game.getPlayer().getItem("Tree"));
		game.processCommand(new Command("pick","Plant"), true);
		assertEquals("Plant", game.getPlayer().getItem("Plant").getItemName());
	}
	public void testDrop(){
		game.processCommand(new Command("drop","Plant"), true);
		//should be null since player dropped the plant
		assertEquals(null, game.getPlayer().getItem("Plant"));
	}
	public void testAttack(){
		//attack Kracken that is in the same room as player
		game.processCommand(new Command("attack","Kracken"), true);
		assertEquals(9,game.getPlayer().getCurrentPlayerRoom().getMonster("Kracken").getHealth());
		
		//attack monster that is not exist.Monster in the same room of player,Kracken's health shouldnt drop
		game.processCommand(new Command("attack","Booboo"), true);
		assertEquals(9,game.getPlayer().getCurrentPlayerRoom().getMonster("Kracken").getHealth());	
	}
	public void testUndo(){
		//TEST  UNDO FOR ATTACK 
		//attack monster once
		game.processCommand(new Command("attack","Kracken"), true);
		//undo
		game.processCommand(new Command("undo",null), true);
		assertEquals(10,game.getPlayer().getCurrentPlayerRoom().getMonster("Kracken").getHealth());
		
		//kill the monster first since it's blocking the door
		for(int i = 5;i>0;i--){
			game.processCommand(new Command("attack","Kracken"), true);
		}
		//TEST UNDO FOR GO COMMAND
		//go south
		game.processCommand(new Command("go","south"), true);
		//undo
		game.processCommand(new Command("undo",null), true);
		assertEquals("Entrance",game.getPlayer().getCurrentPlayerRoom().getDescription());
		
		//TEST UNDO FOR PICK COMMAND
		game.processCommand(new Command("pick","Plant"), true);
		game.processCommand(new Command("undo",null), true);
		//should be null since undo makes player dropped the plant
		assertEquals(null, game.getPlayer().getItem("Plant"));
		
		//TEST UNDO FOR DROP COMMAND
		game.processCommand(new Command("pick","Plant"), true);
		game.processCommand(new Command("drop","Plant"), true);
		game.processCommand(new Command("undo",null), true);
		//undo should make the player pick Plant back
		assertEquals("Plant", game.getPlayer().getItem("Plant").getItemName());
	}
	
	public void testRedo(){
		//TEST REDO FOR PICK COMMAND
		game.processCommand(new Command("pick","Plant"), true);
		game.processCommand(new Command("undo",null), true);
		game.processCommand(new Command("redo",null), true);
		//should be null since undo makes player dropped the plant
		assertEquals("Plant", game.getPlayer().getItem("Plant").getItemName());
		
		//TEST UNDO FOR DROP COMMAND
		game.processCommand(new Command("pick","Plant"), true);
		game.processCommand(new Command("drop","Plant"), true);
		game.processCommand(new Command("undo",null), true);
		game.processCommand(new Command("redo",null), true);
		//undo should make the player pick Plant back
		assertEquals(null, game.getPlayer().getItem("Plant"));
		
		//TEST REDO FOR ATTACK COMMAND
		//attack monster once
		game.processCommand(new Command("attack","Kracken"), true);
		//undo
		game.processCommand(new Command("undo",null), true);
		//redo
		game.processCommand(new Command("redo",null), true);
		assertEquals(9,game.getPlayer().getCurrentPlayerRoom().getMonster("Kracken").getHealth());
		
		//kill the monster first since it's blocking the door
		for(int i = 10;i>0;i--){
			game.processCommand(new Command("attack","Kracken"), true);
		}
		
		//TEST REDO FOR GO COMMAND
		game.processCommand(new Command("go","south"), true);
		//undo
		game.processCommand(new Command("undo",null), true);
		//redo
		game.processCommand(new Command("redo",null), true);
		assertEquals("Lobby",game.getPlayer().getCurrentPlayerRoom().getDescription());
	}
	

}
