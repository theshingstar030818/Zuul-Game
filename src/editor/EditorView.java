package editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Room;
import model.object.Player;
import view.FirstPersonRoom;
import view.MapView;
import controller.FPKeyListener;

public class EditorView extends Observable implements Observer {
	
	private GridLayout gridLayout;
	private BorderLayout borderLayout;
	private EditorMap map;
	private FirstPersonRoom currentRoom;
	private JPanel gamePanel;
	private JFrame mainFrame;
	private Player player;
	
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String NORTH = "north";
	
	private String[][] roomsArray;
	private HashMap<String,Room> rooms;
	private int x;
	private int y;
	
	private int maxX;
	private int maxY;
	
	private EditorMouseListener mouseListener;
	private EditorToolsPanel toolsPanel;
	
	public EditorView(String name, int maxX, int maxY, EditorMouseListener mouseListener) {
		gamePanel = new JPanel();
		mainFrame = new JFrame(name);
		
		//Initialize the layout
		gridLayout = new GridLayout(1,2);
		borderLayout = new BorderLayout();
		gamePanel.setLayout(gridLayout);
		mainFrame.getContentPane().setLayout(borderLayout);
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		
		//Set the size of the game
		this.maxX = maxX;
		this.maxY = maxY;
		
		//Initialize the starting position to (0,0)
		x = 0;
		y = 0;
		
		//Initialize the map
		map = new EditorMap("Map", maxX, maxY, mouseListener);
		
		//Setup the window
		mainFrame.setSize(1400,600);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBackground(Color.BLACK);
		
		currentRoom = new FirstPersonRoom(null);
		player = new Player(null, null, 0, 0);
		
		this.mouseListener = mouseListener;
		toolsPanel = new EditorToolsPanel();
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof EditorUpdateObject) {
			EditorUpdateObject update = (EditorUpdateObject)arg1;
			
			x = update.getSelectedX();
			y = update.getSelectedY();
			
			roomsArray = update.getRoomsArray();
			rooms = update.getRooms();
		}
		
		if (arg1 instanceof Point) {
			Point point = (Point)arg1;
			int tempX = (int) point.getX();
			int tempY  = (int) point.getY();
			
			if (tempX >= 0 && tempX < maxX && tempY >=0 && tempY < maxY) {
				x = tempX;
				y = tempY;
			}
		}
			
		if (x>=0 && x<maxX && y>=0 && y<maxY) {
			if (roomsArray[x][y] != null) {
				currentRoom = (FirstPersonRoom) rooms.get(roomsArray[x][y]);
			} else {
				currentRoom = null;
			}
		}
		map.update(this, arg1);
		refreshView();

	}
	
	private void refreshView() {
		//Remove everything from the gamePanel and mainFrame
		gamePanel.removeAll();
		mainFrame.getContentPane().removeAll();
		
		//Add the 3D perspective and the map perspective
		if (currentRoom != null) {
			gamePanel.add(currentRoom.getView(player.getLookingDirection()));
		} else {
			JPanel blank = new JPanel();
			blank.setSize(600, 600);
			blank.add(new JLabel("Please select a room"));
			gamePanel.add(blank);
		}
		gamePanel.add(map.getContentPane());
		
		//Repaint the gamePanel
		gamePanel.validate();
		gamePanel.repaint();
		
		//Add the gamePanel to mainFrame
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.add(toolsPanel, BorderLayout.WEST);
		
		//Repaint the mainFrame
		mainFrame.validate();
		mainFrame.repaint();
		
	}
	
	public void show() {
		mainFrame.setVisible(true);
	}
}
