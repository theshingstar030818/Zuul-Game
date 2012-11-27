package editor;

import java.lang.Math;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

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
		rooms.remove(roomsArray[x][y]);
		roomsArray[x][y] = null;
		update();
	}
	
	public void addExit(int roomX, int roomY, int destX, int destY) {
		//Check that the rooms are within the bounds of the game
		if (!(checkXY(roomX, roomY) && checkXY(destX,destY))) {
			return;
		}
		
		//Check that the rooms are adjacent
		if (!(Math.abs(roomX-destY) <= 1 && Math.abs(roomY-destY) <= 1)) {
			return;
		}
		
		//Check that the specified rooms exist
		if (roomsArray[roomY][roomY].equals(null) || roomsArray[destX][destY].equals(null)) {
			return;
		}
		
		Room currentRoom = rooms.get(roomsArray[roomX][roomY]);
		Room neighbor = rooms.get(roomsArray[destX][destY]);
		
		boolean north = (roomX == destX) && (roomY-destY == 1);
		boolean south = (roomX == destX) && (destY-roomY == 1);
		boolean west = (roomY == destY) && (roomX-destX == 1);
		boolean east = (roomY == destY) && (destX-roomX == 1);
		
		if (north) {
			currentRoom.setExits(NORTH, neighbor);
			neighbor.setExits(SOUTH, currentRoom);
		} else if (south) {
			currentRoom.setExits(SOUTH, neighbor);
			neighbor.setExits(NORTH, currentRoom);
		} else if (west) {
			currentRoom.setExits(WEST, neighbor);
			neighbor.setExits(EAST, currentRoom);
		} else if (east) {
			currentRoom.setExits(EAST, neighbor);
			neighbor.setExits(WEST, currentRoom);
		}
		update();
	}
	
	public void removeExit(int roomX, int roomY, int destX, int destY) {
		//Check that the rooms are within the bounds of the game
		if (!(checkXY(roomX, roomY) && checkXY(destX,destY))) {
			return;
		}
		
		//Check that the rooms are adjacent
		if (!(Math.abs(roomX-destY) <= 1 && Math.abs(roomY-destY) <= 1)) {
			return;
		}
		
		//Check that the specified rooms exist
		if (roomsArray[roomY][roomY].equals(null) || roomsArray[destX][destY].equals(null)) {
			return;
		}
		
		Room currentRoom = rooms.get(roomsArray[roomX][roomY]);
		Room neighbor = rooms.get(roomsArray[destX][destY]);
		
		boolean north = (roomX == destX) && (roomY-destY == 1);
		boolean south = (roomX == destX) && (destY-roomY == 1);
		boolean west = (roomY == destY) && (roomX-destX == 1);
		boolean east = (roomY == destY) && (destX-roomX == 1);
		
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
		return x <= maxX && y <= maxY && x >= 0 && y >= 0;
	}
	
	private void update() {
		setChanged();
		notifyObservers(new EditorUpdateObject(roomsArray, rooms, x, y, player));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers(arg1);
		
	}

}