package editor;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.command.Command;

public class EditorMouseListener extends Observable implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {		
		if (arg0.getSource() instanceof JPanel) {
			JPanel source = (JPanel)arg0.getSource();
			String[] temp = source.getToolTipText().split(",");
			setChanged();
			notifyObservers(new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
		}
		if (arg0.getSource() instanceof JButton) {
			JButton source = (JButton)arg0.getSource();
			System.out.println(source.getToolTipText());
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
