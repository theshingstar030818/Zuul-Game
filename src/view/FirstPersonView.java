package view;

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

import controller.FPKeyListener;

public class FirstPersonView extends Observable implements Observer {
	
	private GridLayout layout;
	private MapView map;
	private FPKeyListener keyListener;
	private String lookingDirection;
	private FirstPersonRoom currentRoom;
	private JFrame frame;
	
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	private static final String UP = "up";
	
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String NORTH = "north";
	
	public FirstPersonView(String name) {
		frame = new JFrame(name);
		
		//Initialize the layout
		layout = new GridLayout(0,2);
		frame.getContentPane().setLayout(layout);
		
		//Initialize the map
		map = new MapView("Map");
		
		//Setup the window
		frame.setSize(1200,600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);
		
		//Initialize the key listener
		keyListener = new FPKeyListener();
		frame.addKeyListener(keyListener);
		keyListener.addObserver(this);
		
		//Start off looking north by default
		lookingDirection = NORTH;
		
		currentRoom = new FirstPersonRoom(null);
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof FirstPersonRoom) {
			currentRoom = (FirstPersonRoom) arg1;
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
		//Remove everything from the content pane
		frame.getContentPane().removeAll();

		//Add the 3D perspective and the map perspective
		frame.add(currentRoom.getView(lookingDirection));
		frame.add(map.getContentPane());
		
		//Add the glasspane with the direction arrow
		JPanel glassPane = (JPanel)frame.getGlassPane();
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
		
		arrow.setBounds(875, 300, 40, 40);
		
		glassPane.add(arrow);
		glassPane.setVisible(true);
		
		//Repaint the 3D View
		frame.validate();
		frame.repaint();
		
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		FirstPersonView view = new FirstPersonView("World of Zuul");
		view.show();
	}

}
