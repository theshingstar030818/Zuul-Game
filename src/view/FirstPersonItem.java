package view;

import model.object.Item;

public class FirstPersonItem extends Item {

	private static final long serialVersionUID = -3332773896144830714L;
	private String image;
	private String imageShort;

	public FirstPersonItem(String itemName, double itemWeight, String image) {
		super(itemName, itemWeight);
		setImage(image);
		setImageShort(image);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = new String("/img/firstperson/item/" + image);
	}

	public String getImageShort() {
		return imageShort;
	}

	public void setImageShort(String imageShort) {
		this.imageShort = imageShort;
	}
}
