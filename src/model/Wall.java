package model;

import java.io.Serializable;

import model.object.*;

public class Wall implements Serializable {

	private static final long serialVersionUID = 6451009201959840066L;
	private Room exit;
	private Monster monster;
	private Item item;

	public Room getExit() {
		return exit;
	}

	public void setExit(Room exit) {
		this.exit = exit;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
