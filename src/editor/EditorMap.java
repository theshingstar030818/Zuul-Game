package editor;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.MapRoom;

import model.Room;
import model.object.Player;

/**
 * MapView represents the 2D view of a room
 * @author Sean
 *
 */

public class EditorMap extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String NORTH = "north";
	
	private static int SIZE = 3;
	private static int WINDOW_SIZE = 600;
	private JPanel[][] tiles;
	private GridLayout layout;
	
	private String[][] roomsArray;
	private HashMap<String,Room> rooms;
	private int x;
	private int y;
	
	private int maxX;
	private int maxY;
	
	private EditorMouseListener mouseListener;
	
	public EditorMap(String name, int x, int y, EditorMouseListener mouseListener) {
		super(name);
		
		maxX = x;
		maxY = y;
		
		//Initialize the starting position to (0,0)
		x = 0;
		y = 0;
		
		//Initialize the layout
		layout = new GridLayout(SIZE,SIZE);
		getContentPane().setLayout(layout);
		
		tiles = new JPanel[SIZE][SIZE];
		
		//Setup the window
		setSize(WINDOW_SIZE,WINDOW_SIZE);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		
		this.mouseListener = mouseListener;
		
		roomsArray = new String[maxX][maxY];
		rooms = new HashMap<String, Room>();
		
		update(null,null);
	}

	/**
	 * Update the tiles with the current state of the game
	 */
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

		int tempX = 0;
		int tempY = 0;
		
		//Remove all the current tiles
		getContentPane().removeAll();
		
		for (int i=x-1; i<=x+1; i++) {
			for (int j=y-1; j<=y+1; j++) {
				if (i>=0 && j>=0 && i<maxX && j<maxY) {
					if (rooms.get(roomsArray[i][j]) != null) {
						MapRoom temp = (MapRoom)rooms.get(roomsArray[i][j]);
						tiles[tempX][tempY] = temp.getMapPanel();
					} else {
						tiles[tempX][tempY] = new JPanel();
						tiles[tempX][tempY].setBackground(Color.BLACK);
						tiles[tempX][tempY].setBorder(BorderFactory.createDashedBorder(Color.white));
						JLabel temp = new JLabel("(" + i + "," + j + ")");
						temp.setForeground(Color.white);
						tiles[tempX][tempY].add(temp);
					}
				} else {
					tiles[tempX][tempY] = new JPanel();
					tiles[tempX][tempY].setBackground(Color.WHITE);
					getContentPane().add(tiles[tempX][tempY]);
				}
				tiles[tempX][tempY].setToolTipText(i+","+j);
				tiles[tempX][tempY].addMouseListener(mouseListener);
				getContentPane().add(tiles[tempX][tempY]);
				tempY++;
			}
			tempY=0;
			tempX++;
		}

		//Set the border of the middle tile to red to indicate its selected
		tiles[1][1].setBorder(null);
		tiles[1][1].setBorder(BorderFactory.createLineBorder(Color.red));

		//Repaint the 2D View
		validate();
		repaint();

		
//		if (arg1 instanceof Player) {
//			Player player = (Player) arg1;
//			
//			//Cast arg1 as a drawable room
//			MapRoom currentRoom = (MapRoom)player.getCurrentPlayerRoom();
//			
//			//Create a new SIZExSIZE array to hold the current rooms
//			Room rooms[][] = new Room[SIZE][SIZE];
//			
//			//Set the middle room (position 1,1) to the current room
//			rooms[1][1] = currentRoom;
//			
//			//Recursively find rooms to display beginning at the center of the map
//			discoverRooms(1, 1, rooms);
//			
//			//Remove all the current tiles
//			getContentPane().removeAll();
//			
//			//Update the tiles to show the current state of the game
//			for (int i=0; i<SIZE; i++) {
//				for (int j=0; j<SIZE; j++) {
//					if (rooms[i][j] != null && rooms[i][j].hasBeenVisited()) {
//						MapRoom temp = (MapRoom)rooms[i][j];
//						tiles[i][j] = temp.getMapPanel();
//					} else {
//						tiles[i][j] = new JPanel();
//						tiles[i][j].setBackground(Color.BLACK);
//					}	
//					//Add the tile to the JFrame
//					getContentPane().add(tiles[i][j]);
//				}
//			}
//
//			//Repaint the 2D View
//			validate();
//			repaint();
//		}
	}

	/**
	 * This method recursively travels through the game and builds a 2d array 
	 * of size SIZExSIZE of the rooms for the MapView to display
	 * @param x
	 * @param y
	 * @param rooms
	 * @param previous
	 */
	private void discoverRooms(int x, int y, Room[][] rooms) {
		//Check north
		if (rooms[x][y].getExit(NORTH) != null) { //If there is a north exit
			if (x>0 && rooms[x-1][y] == null) { //If x is within the bounds of the array (x>0) and the room to the north hasn't already been visited
				rooms[x-1][y] = rooms[x][y].getExit(NORTH);
				discoverRooms(x-1, y, rooms);
			}
		}
		
		//Check south
		if (rooms[x][y].getExit(SOUTH) != null) { //If there is a south exit
			if (x<(SIZE-1) && rooms[x+1][y] == null) { //If x is within the bounds of the array (SIZE) and the room to the south hasn't already been visited
				rooms[x+1][y] = rooms[x][y].getExit(SOUTH);
				discoverRooms(x+1, y, rooms);
			}
		}
		
		
		//Check west
		if (rooms[x][y].getExit(WEST) != null) { //If there is a west exit
			if (y>0 && rooms[x][y-1] == null) { //If y is within the bounds of the array (y>0) and the room to the west hasn't already been visited
				rooms[x][y-1] = rooms[x][y].getExit(WEST);
				discoverRooms(x, y-1, rooms);
			}
		}	
		
		//Check east
		if (rooms[x][y].getExit(EAST) != null) { //If there is an east exit
			if (y<(SIZE-1) && rooms[x][y+1] == null) { //If Y is within the bounds of the array (SIZE) and the room to the east hasn't already been visited
				rooms[x][y+1] = rooms[x][y].getExit(EAST);
				discoverRooms(x, y+1, rooms);
			}
		}
	}
}
