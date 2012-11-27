package editor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;


public class EditorKeyListener extends Observable implements KeyListener {
	
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String NORTH = "north";
	
	public void keyReleased(KeyEvent arg0) {
		return;
	}

	public void keyTyped(KeyEvent arg0) {
		return;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        setChanged();
	        notifyObservers(WEST);
	        return;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        setChanged();
	        notifyObservers(EAST);
	        return;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			setChanged();
	        notifyObservers(NORTH);
	        return;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			setChanged();
	        notifyObservers(SOUTH);
	        return;
		}
	}


}
