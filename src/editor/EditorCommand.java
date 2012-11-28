package editor;

public class EditorCommand {

	private int x;
	private int y;
	private String direction;
	private String monster;
	private String item;
	private String lookingDirection;
	private String command;
	
	public EditorCommand(String command, int x, int y, String direction, String monster, String item, String lookingDirection) {
		this.command = command;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.monster = monster;
		this.item = item;
		this.lookingDirection = lookingDirection;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getMonster() {
		return monster;
	}

	public void setMonster(String monster) {
		this.monster = monster;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getLookingDirection() {
		return lookingDirection;
	}

	public void setLookingDirection(String lookingDirection) {
		this.lookingDirection = lookingDirection;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
