package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;


public class FPKeyListener extends Observable implements KeyListener {

	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	private static final String UP = "up";
	
	public void keyReleased(KeyEvent arg0) {
		return;
	}

	public void keyTyped(KeyEvent arg0) {
		return;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        setChanged();
	        notifyObservers(LEFT);
	        return;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        setChanged();
	        notifyObservers(RIGHT);
	        return;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			setChanged();
	        notifyObservers(UP);
	        return;
		}
	}


}
