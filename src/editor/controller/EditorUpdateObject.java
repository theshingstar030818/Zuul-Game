package editor.controller;

import java.util.HashMap;

import model.object.Player;
import view.FirstPersonRoom;

/**
 * An object which is passed using the observer pattern between EditorView and LevelEditor
 * @author sean
 *
 */

public class EditorUpdateObject {

	private String[][] roomsArray;
	private HashMap<String, FirstPersonRoom> rooms;
	private Player player;

	private int selectedX;
	private int selectedY;

	public EditorUpdateObject(String[][] roomsArray,
			HashMap<String, FirstPersonRoom> rooms, int x, int y, Player player) {
		this.roomsArray = roomsArray;
		this.rooms = rooms;
		this.selectedX = x;
		this.selectedY = y;
		this.player = player;
	}

	public String[][] getRoomsArray() {
		return roomsArray;
	}

	public HashMap<String, FirstPersonRoom> getRooms() {
		return rooms;
	}

	public int getSelectedX() {
		return selectedX;
	}

	public int getSelectedY() {
		return selectedY;
	}

	public Player getPlayer() {
		return player;
	}

}
