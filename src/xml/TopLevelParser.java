package xml;


import java.util.*;

import model.Game;

import org.w3c.dom.*;

import org.w3c.dom.Document;

public class TopLevelParser implements XMLParser{
	private List<XMLRoom> rooms;
	private Node n;
	private Game g;
	private String startingRoom;
	
	public TopLevelParser(Node n, Game g) {
		this.n = n;
		this.g = g;
		rooms = new ArrayList<XMLRoom>();
		parse();
	}
	
	
	public void parseRoom(Node n)
	{
		NodeList nodes = n.getChildNodes();
		rooms.add(new XMLRoom(nodes,g));
	}

	@Override
	public void parse() {
		NodeList nodes = n.getChildNodes();
		for(int i = 0; i < nodes.getLength(); i++)
		{
			if(nodes.item(i).getNodeName().equals("Room"))
				parseRoom(nodes.item(i));
			if(nodes.item(i).getNodeName().equals("Start"))
			{
				startingRoom = nodes.item(i).getTextContent();
			}
		}
		for(XMLRoom r : rooms)
		{
			r.parseSecondPass();
		}
		g.setStart(startingRoom);
	}
}
