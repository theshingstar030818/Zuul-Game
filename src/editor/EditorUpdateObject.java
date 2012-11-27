package editor;

import java.util.HashMap;

import model.Room;

public class EditorUpdateObject {
	
	private String[][] roomsArray;
	private HashMap<String,Room> rooms;
	
	private int selectedX;
	private int selectedY;
	
	public EditorUpdateObject(String[][] roomsArray, HashMap<String,Room> rooms, int x, int y) {
		this.roomsArray = roomsArray;
		this.rooms = rooms;
		this.selectedX = x;
		this.selectedY = y;
	}
	
	public String[][] getRoomsArray() {
		return roomsArray;
	}
	public HashMap<String,Room> getRooms() {
		return rooms;
	}
	public int getSelectedX() {
		return selectedX;
	}
	public int getSelectedY() {
		return selectedY;
	}

}
