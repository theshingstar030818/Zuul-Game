package view;

import model.object.Item;

public class FirstPersonItem extends Item{
	private String image;
	
	public FirstPersonItem(String itemName, double itemWeight, String image) {
		super(itemName, itemWeight);
		setImage(image);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = new String("/img/firstperson/item/"+image);
	}
}
