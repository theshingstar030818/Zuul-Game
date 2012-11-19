package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.command.Command;
import model.object.Player;

import controller.FPKeyListener;
import controller.FPMouseListener;

public class FirstPersonView extends Observable implements Observer {
	
	private GridLayout gridLayout;
	private BorderLayout borderLayout;
	private MapView map;
	private FPKeyListener keyListener;
	private String lookingDirection;
	private FirstPersonRoom currentRoom;
	private JPanel gamePanel;
	private JFrame mainFrame;
	private Player player;
	
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	private static final String UP = "up";
	
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String NORTH = "north";
	
	public FirstPersonView(String name) {
		gamePanel = new JPanel();
		mainFrame = new JFrame(name);
		
		//Initialize the layout
		gridLayout = new GridLayout(1,2);
		borderLayout = new BorderLayout();
		gamePanel.setLayout(gridLayout);
		mainFrame.getContentPane().setLayout(borderLayout);
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		
		//Initialize the map
		map = new MapView("Map");
		
		//Setup the window
		mainFrame.setSize(1200,600);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBackground(Color.BLACK);
		
		//Initialize the key listener
		keyListener = new FPKeyListener();
		mainFrame.addKeyListener(keyListener);
		keyListener.addObserver(this);
		
		//Start off looking north by default
		lookingDirection = NORTH;
		
		currentRoom = new FirstPersonRoom(null);
		player = new Player(null, null, 0, 0);
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Player) {
			player = (Player) arg1;
			currentRoom = (FirstPersonRoom) player.getCurrentPlayerRoom();
			map.update(arg0, arg1);
			refreshView();
		}
		
		if (arg0 instanceof FPKeyListener && arg1 instanceof String) {
			String direction = (String) arg1;
			
			if (direction.equals(LEFT)) {
				if (lookingDirection.equals(NORTH)) {
					lookingDirection = WEST;
				} else if (lookingDirection.equals(SOUTH)) {
					lookingDirection = EAST;
				} else if (lookingDirection.equals(EAST)) {
					lookingDirection = NORTH;
				} else if (lookingDirection.equals(WEST)) {
					lookingDirection = SOUTH;
				}
			} else if (direction.equals(RIGHT)) {
				if (lookingDirection.equals(NORTH)) {
					lookingDirection = EAST;
				} else if (lookingDirection.equals(SOUTH)) {
					lookingDirection = WEST;
				} else if (lookingDirection.equals(EAST)) {
					lookingDirection = SOUTH;
				} else if (lookingDirection.equals(WEST)) {
					lookingDirection = NORTH;
				}
			} else if (direction.equals(UP)) {
				setChanged();
				notifyObservers(new Command("go",lookingDirection));
			}
			
			refreshView();
		}
	}
	
	private void refreshView() {
		//Remove everything from the gamePanel and mainFrame
		gamePanel.removeAll();
		mainFrame.getContentPane().removeAll();

		//Add the 3D perspective and the map perspective
		gamePanel.add(currentRoom.getView(lookingDirection));
		gamePanel.add(map.getContentPane());
		
		//Add the glasspane with the direction arrow
		JPanel glassPane = (JPanel)mainFrame.getGlassPane();
		glassPane.removeAll();
		glassPane.setLayout(null);
		
		JLabel arrow = new JLabel("");
		if (lookingDirection.equals(NORTH)) {
			arrow.setIcon(new ImageIcon(FirstPersonRoom.class.getResource("/img/firstperson/arrow/north.png")));
		} else if (lookingDirection.equals(SOUTH)) {
			arrow.setIcon(new ImageIcon(FirstPersonRoom.class.getResource("/img/firstperson/arrow/south.png")));
		} else if (lookingDirection.equals(EAST)) {
			arrow.setIcon(new ImageIcon(FirstPersonRoom.class.getResource("/img/firstperson/arrow/east.png")));
		} else if (lookingDirection.equals(WEST)) {
			arrow.setIcon(new ImageIcon(FirstPersonRoom.class.getResource("/img/firstperson/arrow/west.png")));
		}
		
		arrow.setBounds(875, 280, 40, 40);
		
		glassPane.add(arrow);
		glassPane.setVisible(true);
		
		//Add the health panel
		HealthPanel healthPanel = new HealthPanel(player.getHealth(), player.getCurrentWeight(), player.getMaxWeight());
		healthPanel.setVisible(true);
		mainFrame.add(healthPanel, BorderLayout.SOUTH);
		
		//Repaint the gamePanel
		gamePanel.validate();
		gamePanel.repaint();
		
		//Add the gamePanel
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		
		//Repaint the mainFrame
		mainFrame.validate();
		mainFrame.repaint();
		
	}
	
	public void show() {
		mainFrame.setVisible(true);
	}
	
	public static void main(String args[]) {
		FirstPersonView view = new FirstPersonView("World of Zuul");
		view.show();
	}

}
