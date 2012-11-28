package editor;

import java.awt.Point;
import java.lang.Math;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import view.FirstPersonRoom;

import model.Room;
import model.object.Item;
import model.object.Monster;
import model.object.Player;

public class LevelEditor extends Observable implements Observer {
	
    private HashMap<String,Room> rooms;
    private String roomsArray[][];
    private Player player;
    
    private int maxX;
    private int maxY;
    
    private int x;
    private int y;
    
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String NORTH = "north";
	
	private static final String REMOVE_ITEM = "removeItem";
	private static final String ADD_ITEM = "addItem";
	private static final String REMOVE_MONSTER = "removeMonster";
	private static final String ADD_MONSTER = "addMonster";
	private static final String REMOVE_EXIT = "removeExit";
	private static final String ADD_EXIT = "addExit";
	private static final String REMOVE_ROOM = "removeRoom";
	private static final String ADD_ROOM = "addRoom";
	
	public LevelEditor(int maxX, int maxY, int playerHealth, int playerWeight) {
		rooms = new HashMap<String,Room>();
		roomsArray = new String[maxX][maxY];
		player = new Player("Player", playerWeight, playerHealth);
		
		//Initialize the size of the game
		this.maxX = maxX;
		this.maxY = maxY;
		
		//Initialize the position to (0,0)
		x = 0;
		y = 0;
		
		//Initialize the whole roomsArray to null (no rooms to begin with)
		for (int i=0; i<maxX; i++) {
			for (int j=0; j<maxY; j++) {
				roomsArray[i][j] = null;
			}
		}
		
		update();
	}
	
	public void addRoom(Room room, int x, int y) {
		rooms.put(room.getDescription(), room);
		roomsArray[x][y] = room.getDescription();
		update();
	}
	
	public void removeRoom(int x, int y) {
		//Remove any exits first
		removeExit(x,y,NORTH);
		removeExit(x,y,SOUTH);
		removeExit(x,y,EAST);
		removeExit(x,y,WEST);
		
		rooms.remove(roomsArray[x][y]);
		roomsArray[x][y] = null;
		update();
	}
	
	public void addExit(int roomX, int roomY, String direction) {
		//Check that the room is within the bounds of the game
		if (!checkXY(roomX, roomY)) {
			return;
		}
		
		//Check that the specified room exist
		if (roomsArray[roomX][roomY].equals(null)) {
			return;
		}
		
		Room currentRoom = rooms.get(roomsArray[roomX][roomY]);
		Room neighbor;

		
		boolean north = direction.equals(NORTH);
		boolean south = direction.equals(SOUTH);
		boolean west = direction.equals(WEST);
		boolean east = direction.equals(EAST);
		
		if (north) {
			if (!checkXY(roomX-1, roomY)) {
				return;
			}
			
			//Check that the specified rooms exist
			if (roomsArray[roomX-1][roomY] == null) {
				return;
			}
			neighbor = rooms.get(roomsArray[roomX-1][roomY]);
			currentRoom.setExits(NORTH, neighbor);
			neighbor.setExits(SOUTH, currentRoom);
			
		} else if (south) {
			if (!checkXY(roomX+1, roomY)) {
				return;
			}
			
			//Check that the specified rooms exist
			if (roomsArray[roomX+1][roomY] == null) {
				return;
			}
			neighbor = rooms.get(roomsArray[roomX+1][roomY]);
			
			currentRoom.setExits(SOUTH, neighbor);
			neighbor.setExits(NORTH, currentRoom);
			
		} else if (west) {
			if (!checkXY(roomX, roomY-1)) {
				return;
			}
			
			//Check that the specified rooms exist
			if (roomsArray[roomX][roomY-1] == null) {
				return;
			}
			neighbor = rooms.get(roomsArray[roomX][roomY-1]);
			
			currentRoom.setExits(WEST, neighbor);
			neighbor.setExits(EAST, currentRoom);
			
		} else if (east) {
			if (!checkXY(roomX, roomY+1)) {
				return;
			}
			
			//Check that the specified rooms exist
			if (roomsArray[roomX][roomY+1] == null) {
				return;
			}
			neighbor = rooms.get(roomsArray[roomX][roomY+1]);
			
			currentRoom.setExits(EAST, neighbor);
			neighbor.setExits(WEST, currentRoom);
		}
		update();
	}
	
	public void removeExit(int roomX, int roomY, String direction) {
		//Check that the rooms are within the bounds of the game
		if (!checkXY(roomX, roomY)) {
			return;
		}
		
		//Check that the specified rooms exist
		if (roomsArray[roomX][roomY].equals(null) || rooms.get(roomsArray[roomX][roomY]).getExit(direction) == null) {
			return;
		}
		
		Room currentRoom = rooms.get(roomsArray[roomX][roomY]);
		Room neighbor = currentRoom.getExit(direction);
		
		boolean north = direction.equals(NORTH);
		boolean south = direction.equals(SOUTH);
		boolean west = direction.equals(WEST);
		boolean east = direction.equals(EAST);
		
		if (north) {
			currentRoom.removeExit(NORTH);
			neighbor.removeExit(SOUTH);
		} else if (south) {
			currentRoom.removeExit(SOUTH);
			neighbor.removeExit(NORTH);
		} else if (west) {
			currentRoom.removeExit(WEST);
			neighbor.removeExit(EAST);
		} else if (east) {
			currentRoom.removeExit(EAST);
			neighbor.removeExit(WEST);
		}
		
		update();
	}
	
	public void addMonster(int x, int y, String direction, Monster monster) {
		if (checkXY(x,y)) {
			Room room = rooms.get(roomsArray[x][y]);
			if (room != null) {
				room.addMonster(monster, direction);
			}
		}
		update();
	}
	
	public void removeMonster(int x, int y, String monster) {
		if (checkXY(x,y)) {
			Room room = rooms.get(roomsArray[x][y]);
			if (room != null) {
				room.removeMonster(monster);
			}
		}
		update();
	}
	
	public void addItem(int x, int y, String direction, Item item) {
		if (checkXY(x,y)) {
			Room room = rooms.get(roomsArray[x][y]);
			if (room != null) {
				room.addItem(item, direction);
			}
		}
		update();
	}
	
	public void removeItem(int x, int y, String item) {
		if (checkXY(x,y)) {
			Room room = rooms.get(roomsArray[x][y]);
			if (room != null) {
				room.removeItem(item);
			}
		}
		update();
	}
	 
	private boolean checkXY(int x, int y) {
		return x < maxX && y < maxY && x >= 0 && y >= 0;
	}
	
	private void update() {
		setChanged();
		notifyObservers(new EditorUpdateObject(roomsArray, rooms, x, y, player));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof EditorUpdateObject) {
			EditorUpdateObject update = (EditorUpdateObject)arg1;
			
			x = update.getSelectedX();
			y = update.getSelectedY();
			
			roomsArray = update.getRoomsArray();
			rooms = update.getRooms();
			player = update.getPlayer();
			
			update();
		}
		
		if (arg1 instanceof Point) {
			Point point = (Point)arg1;
			int tempX = (int) point.getX();
			int tempY  = (int) point.getY();
			
			if (tempX >= 0 && tempX < maxX && tempY >=0 && tempY < maxY) {
				x = tempX;
				y = tempY;
			}
			
			update();
		}
		
		if (arg1 instanceof String) {
			String source = (String)arg1;
			String[] temp = source.split(",");
			
			if (temp[0].equals(ADD_ROOM)) {
				String name = JOptionPane.showInputDialog("Please enter a name for the room:");
				FirstPersonRoom room = new FirstPersonRoom(name);
				addRoom(room, Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
			} else if (temp[0].equals(REMOVE_ROOM)) {
				removeRoom(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
			} else if (temp[0].equals(ADD_EXIT)) {
				addExit(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), temp[3]);
			} else if (temp[0].equals(REMOVE_EXIT)) {
				removeExit(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), temp[3]);
			}
		}
	}

}