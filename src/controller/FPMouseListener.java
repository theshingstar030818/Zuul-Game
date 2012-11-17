package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JLabel;

import model.command.Command;

public class FPMouseListener extends Observable implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() instanceof JLabel) {
			JLabel source = (JLabel)arg0.getSource();
			String[] temp = source.getToolTipText().split(",");
			setChanged();
			notifyObservers(new Command(temp[0],temp[1]));
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
