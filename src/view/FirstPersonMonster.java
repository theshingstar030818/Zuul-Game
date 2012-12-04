package view;

import model.object.Monster;

public class FirstPersonMonster extends Monster implements Cloneable {

	private static final long serialVersionUID = -7830880791392214020L;
	private String image;
	private String imageShort;

	public FirstPersonMonster(String name, int health, String image) {
		super(name, health);
		setImage(image);
		setImageShort(image);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = new String("/img/firstperson/monster/" + image);
	}

	public String getImageShort() {
		return imageShort;
	}

	public void setImageShort(String imageShort) {
		this.imageShort = imageShort;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
			return null;
		}
	}

}