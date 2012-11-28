package editor.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class EditorMouseListener extends Observable implements MouseListener, ActionListener {
	
	private static final String REMOVE_ITEM = "removeItem";
	private static final String ADD_ITEM = "addItem";
	private static final String REMOVE_MONSTER = "removeMonster";
	private static final String ADD_MONSTER = "addMonster";
	private static final String REMOVE_EXIT = "removeExit";
	private static final String ADD_EXIT = "addExit";
	private static final String REMOVE_ROOM = "removeRoom";
	private static final String ADD_ROOM = "addRoom";
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String NORTH = "north";
	
	private int x;
	private int y;
	
	private String direction;
	private String monster;
	private String item;
	private String lookingDirection;
	
	public EditorMouseListener() {
		x = 0;
		y = 0;
		lookingDirection = NORTH;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {		
		if (arg0.getSource() instanceof JPanel) {
			JPanel source = (JPanel)arg0.getSource();
			String[] temp = source.getToolTipText().split(",");
			
			x = Integer.parseInt(temp[0]);
			y = Integer.parseInt(temp[1]);
			
			setChanged();
			notifyObservers(new Point(x, y));
		}
		
		if (arg0.getSource() instanceof JButton) {
			JButton source = (JButton)arg0.getSource();
			String command = source.getToolTipText();
			setChanged();

			if (command.equals(ADD_ROOM) || command.equals(REMOVE_ROOM) || command.equals(REMOVE_MONSTER) || command.equals(REMOVE_ITEM)) {
				notifyObservers(new String(command));
				return;
			}
			
			if (command.equals(ADD_EXIT) || command.equals(REMOVE_EXIT)) {
				notifyObservers(new String(command + "," + direction));
				return;
			}
			
			if (command.equals(ADD_MONSTER)) {
				notifyObservers(new String(command + "," + monster));
				return;
			}
			
			if (command.equals(ADD_ITEM)) {
				notifyObservers(new String(command + "," + item));
			}
			
			if (command.equals(LEFT)) {
				if (lookingDirection.equals(NORTH)) {
					lookingDirection = WEST;
				} else if (lookingDirection.equals(SOUTH)) {
					lookingDirection = EAST;
				} else if (lookingDirection.equals(EAST)) {
					lookingDirection = NORTH;
				} else if (lookingDirection.equals(WEST)) {
					lookingDirection = SOUTH;
				}
				notifyObservers(new String("look," + lookingDirection));
				return;
			}
			
			if (command.equals(RIGHT)) {
				if (lookingDirection.equals(NORTH)) {
					lookingDirection = EAST;
				} else if (lookingDirection.equals(SOUTH)) {
					lookingDirection = WEST;
				} else if (lookingDirection.equals(EAST)) {
					lookingDirection = SOUTH;
				} else if (lookingDirection.equals(WEST)) {
					lookingDirection = NORTH;
				}
				notifyObservers(new String("look," + lookingDirection));
				return;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		return;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		return;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		return;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		return;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JComboBox) {
			@SuppressWarnings("rawtypes")
			JComboBox source = (JComboBox)arg0.getSource();
			String comboBox = source.getToolTipText();
			
			if (comboBox.equals("direction")) {
				direction = (String)source.getSelectedItem();
			} else if (comboBox.equals("monster")) {
				monster = (String)source.getSelectedItem();
			} else if (comboBox.equals("item")) {
				item = (String)source.getSelectedItem();
			}
		}
	}

}
