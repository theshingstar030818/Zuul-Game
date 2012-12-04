package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Observable;

import javax.swing.JLabel;

import model.command.Command;

/**
 * Mouse listener for the first person view. Used to detect when monsters and items are clicked.
 * Relays commands to Game
 * @author sean
 *
 */
public class FPMouseListener extends Observable implements MouseListener, Serializable {

	private static final long serialVersionUID = 6940778970055072432L;

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() instanceof JLabel) {
			JLabel source = (JLabel) arg0.getSource();
			String[] temp = source.getToolTipText().split(",");
			setChanged();
			notifyObservers(new Command(temp[0], temp[1]));
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

}
