package editor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import editor.controller.EditorMouseListener;
import editor.controller.EditorUpdateObject;

import model.Room;
import model.object.Player;
import view.FirstPersonRoom;

public class EditorView extends Observable implements Observer {
	
	private GridLayout gridLayout;
	private BorderLayout borderLayout;
	private EditorMap map;
	private FirstPersonRoom currentRoom;
	private JPanel gamePanel;
	private JFrame mainFrame;
	
	private String[][] roomsArray;
	private HashMap<String,Room> rooms;
	private int x;
	private int y;
	
	private int maxX;
	private int maxY;
	
	private EditorToolsPanel toolsPanel;
	
	private Player player;
	
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
		mainFrame.setSize(1505,600);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBackground(Color.BLACK);
		
		currentRoom = null;
		
		toolsPanel = new EditorToolsPanel(mouseListener);
		
		roomsArray = new String[maxX][maxY];
		rooms = new HashMap<String, Room>();
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof String) {
			//A command was passed through by the toolPane
		}
		
		if (arg1 instanceof EditorUpdateObject) {
			EditorUpdateObject update = (EditorUpdateObject)arg1;
			
			x = update.getSelectedX();
			y = update.getSelectedY();
			
			roomsArray = update.getRoomsArray();
			rooms = update.getRooms();
			player = update.getPlayer();
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
		refreshView();
		mainFrame.setVisible(true);
	}
}
