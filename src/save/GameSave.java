package save;

import java.util.HashMap;

import model.Room;
import model.object.Player;

public class GameSave {

	private Player player;
	private HashMap<String, Room> rooms;
	
	public GameSave(Player player, HashMap<String, Room> rooms) {
		this.player = player;
		this.rooms = rooms;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public HashMap<String, Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(HashMap<String, Room> rooms) {
		this.rooms = rooms;
	}
	
	public boolean serialize(String path) {
		return false;
	}
	
	public boolean saveToXML(String path) {
		return false;
	}
	
	public boolean loadFromSerial(String path) {
		return false;
	}
	
	public boolean loadFromXML(String path) {
		return false;
	}

}
