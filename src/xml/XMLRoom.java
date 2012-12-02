package xml;


import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import model.Game;

import org.w3c.dom.*;

public class XMLRoom implements XMLParser{
	private String name;
	private List<XMLExit> exits;
	private List<XMLMonster> monsters;
	private List<XMLItem> items;
	private NodeList nodes;
	private Game g;
	
	public XMLRoom(NodeList nodes, Game g)
	{
		this.nodes = nodes;
		setName(new String());
		exits = new ArrayList<XMLExit>();
		monsters = new ArrayList<XMLMonster>();
		items = new ArrayList<XMLItem>();
		this.g = g;
		parse();
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void parse() {
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Node n = nodes.item(i);
			if(n.getNodeName().equals("Name"))
			{
				this.name = n.getTextContent();
				g.addRoom(name);
			}
			else if(n.getNodeName().equals("Exit"))
			{
				this.exits.add(new XMLExit(n.getChildNodes()));
			}
			else if(n.getNodeName().equals("Monster"))
			{
				this.monsters.add(new XMLMonster(n.getChildNodes()));
			}
			else if(n.getNodeName().equals("Item"))
			{
				this.items.add(new XMLItem(n.getChildNodes()));
			}
		}
		System.out.println("First pass complete");
	}
	
	public void parseSecondPass() {
		for(XMLExit e: exits)
		{
			g.addExitToRoom(name, e.getRoom(), e.getDirection());
			System.out.println("Setting "+name+" "+e.getDirection()+" exit to "+e.getRoom());
		}
		if(!items.isEmpty())
		{
			for(XMLItem i : items)
			{
				g.addItemToRoom(name, i.getName(), i.getWeight(), i.getImage(), i.getWall());
			}
		}
		if(!monsters.isEmpty())
		{
			for(XMLMonster m : monsters)
			{
				g.addMonsterToRoom(name, m.getName(), m.getHealth(), m.getImage(), m.getWall());
			}
		}
	}

}
