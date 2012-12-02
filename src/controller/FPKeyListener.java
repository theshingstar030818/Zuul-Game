package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import model.command.Command;

public class FPKeyListener extends Observable implements KeyListener {

	public void keyReleased(KeyEvent arg0) {
		return;
	}

	public void keyTyped(KeyEvent arg0) {
		return;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			setChanged();
			notifyObservers(new Command("turn", "left"));
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			setChanged();
			notifyObservers(new Command("turn", "right"));
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			setChanged();
			notifyObservers(new Command("straight", null));
			return;
		}
	}

}
