import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import controller.FPKeyListener;
import model.Game;
import model.Room;
import model.object.Player;
import view.FirstPersonView;


public class GameDriver {
	
	private Game game;
	private FPKeyListener keyListener;
	private FirstPersonView view;

	/**
	 * @param args
	 */
	public GameDriver() {
		//Create a new game
    	game = new Game();
    	
    	//Create a key listener
    	keyListener = new FPKeyListener();
        keyListener.addObserver(game);
    	
    	//Create a 3D First Person View
    	view = new FirstPersonView("World of Zuul", keyListener);
    	
    	game.addObserver(view);
    	view.addObserver(game);
	}
	
	public void startDefaultGame() {
		String name = JOptionPane.showInputDialog("Please enter your name:");
		game.loadDefaultGame(name);
		view.show();
	}
	
	public void startGame(Player player, HashMap<String, Room> rooms) {
		game.loadGame(player, rooms);
		view.show();
	}
	
	public static void main(String args[]) {
		GameDriver driver = new GameDriver();
		driver.startDefaultGame();
	}

}
