package view;

import model.object.Monster;

public class FirstPersonMonster extends Monster{
	private String image;
	
	public FirstPersonMonster(String name, int health, String image) {
		super(name, health);
		setImage(image);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = new String("/img/firstperson/monster/"+image);
	}
}