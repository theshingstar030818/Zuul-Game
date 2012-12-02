package xml;

import org.w3c.dom.*;

public class XMLMonster implements XMLParser{
	private String name;
	private int health;
	private String image;
	private String wall;
	private NodeList nodes;
	
	public XMLMonster(NodeList nodes)
	{
		this.nodes = nodes;
		parse();
	}

	@Override
	public void parse() {
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Node temp = nodes.item(i);
			if(temp.getNodeName().equals("Name"))
			{
				setName(temp.getTextContent());
			}
			else if(temp.getNodeName().equals("Health"))
			{
				setHealth(Integer.parseInt(temp.getTextContent()));
			}
			else if(temp.getNodeName().equals("Img"))
			{
				setImage(temp.getTextContent());
			}
			else if(temp.getNodeName().equals("Wall"))
			{
				setWall(temp.getTextContent());
			}
		}
		
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	private void setHealth(int health) {
		this.health = health;
	}

	public String getImage() {
		return image;
	}

	private void setImage(String image) {
		this.image = image;
	}

	public String getWall() {
		return wall;
	}

	private void setWall(String wall) {
		this.wall = wall;
	}
	
	
}
