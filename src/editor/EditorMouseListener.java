package editor;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.command.Command;

public class EditorMouseListener extends Observable implements MouseListener, ActionListener {
	
	private static final String REMOVE_ITEM = "removeItem";
	private static final String ADD_ITEM = "addItem";
	private static final String REMOVE_MONSTER = "removeMonster";
	private static final String ADD_MONSTER = "addMonster";
	private static final String REMOVE_EXIT = "removeExit";
	private static final String ADD_EXIT = "addExit";
	private static final String REMOVE_ROOM = "removeRoom";
	private static final String ADD_ROOM = "addRoom";
	
	private int x;
	private int y;
	
	private String direction;
	
	public EditorMouseListener() {
		x = 0;
		y = 0;
		direction = "north";
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
			
			if (command.equals(ADD_ROOM) || command.equals(REMOVE_ROOM)) {
				notifyObservers(new String(command + "," + x + "," + y));
				return;
			}
			
			if (command.equals(ADD_EXIT) || command.equals(REMOVE_EXIT)) {
				notifyObservers(new String(command + "," + x + "," + y + "," + direction));
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
			JComboBox source = (JComboBox)arg0.getSource();
			direction = (String)source.getSelectedItem();
		}
	}

}
