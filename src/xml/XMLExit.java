package xml;

import org.w3c.dom.*;

public class XMLExit implements XMLParser{
	private String direction;
	private String room;
	private NodeList nodes;
	
	public XMLExit(NodeList nodes)
	{
		this.nodes = nodes;
		parse();
	}
	
	@Override
	public void parse() {
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Node temp = nodes.item(i);
			if(temp.getNodeName().equals("Direction"))
			{
				setDirection(temp.getTextContent());
			}
			else if(temp.getNodeName().equals("Room"))
			{
				setRoom(temp.getTextContent());
			}
		}
		
	}

	public String getDirection() {
		return direction;
	}

	private void setDirection(String direction) {
		this.direction = direction;
	}

	public String getRoom() {
		return room;
	}

	private void setRoom(String room) {
		this.room = room;
	}
}
