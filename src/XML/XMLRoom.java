package XML;

import java.util.ArrayList;

public class XMLRoom {
	private String name;
	private ArrayList<XMLExits> exits;
	
	public XMLRoom()
	{
		setName(new String());
		setExits(new ArrayList<XMLExits>());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<XMLExits> getExits() {
		return exits;
	}

	public void setExits(ArrayList<XMLExits> exits) {
		this.exits = exits;
	}
}
