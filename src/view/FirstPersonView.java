package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controller.FPKeyListener;

public class FirstPersonView extends JFrame implements Observer {
	
	private GridLayout layout;
	private MapView map;
	private FPKeyListener keyListener;
	private String lookingDirection;
	private FirstPersonRoom currentRoom;
	
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String NORTH = "north";
	
	public FirstPersonView(String name) {
		super(name);
		
		//Initialize the layout
		layout = new GridLayout(0,2);
		getContentPane().setLayout(layout);
		
		//Initialize the map
		map = new MapView("Map");
		
		//Setup the window
		setSize(1200,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		
		//Initialize the keylistener
		keyListener = new FPKeyListener();
		addKeyListener(keyListener);
		keyListener.addObserver(this);
		
		//Start off looking north by default
		lookingDirection = NORTH;
		
		currentRoom = new FirstPersonRoom(null);
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof FirstPersonRoom) {
			currentRoom = (FirstPersonRoom) arg1;
			
			getContentPane().removeAll();
			map.update(arg0, arg1);
			
			add(currentRoom.getView(lookingDirection));
			add(map.getContentPane());
			
			//Repaint the 3D View
			validate();
			repaint();
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
			}
			
			getContentPane().removeAll();
			add(currentRoom.getView(lookingDirection));
			add(map.getContentPane());
			
			//Repaint the 3D View
			validate();
			repaint();
		}
	}
	
	public static void main(String args[]) {
		FirstPersonView view = new FirstPersonView("World of Zuul");
		view.setVisible(true);
	}

}
