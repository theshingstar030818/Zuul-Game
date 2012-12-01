package XML;

public class XMLExits {
	private String direction;
	private String room;
	
	public XMLExits(String room, String direction)
	{
		setRoom(room);
		setDirection(direction);
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
}
