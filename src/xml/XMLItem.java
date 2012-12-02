package xml;

import org.w3c.dom.*;

public class XMLItem implements XMLParser{
	private String name;
	private int weight;
	private String image;
	private String wall;
	private NodeList nodes;
	
	public XMLItem(NodeList nodes)
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
			else if(temp.getNodeName().equals("Weight"))
			{
				setWeight(Integer.parseInt(temp.getTextContent()));
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

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getWall() {
		return wall;
	}

	public void setWall(String wall) {
		this.wall = wall;
	}
	

}
