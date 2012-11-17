package view;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FirstPersonRoom extends MapRoom {
	
	private MouseListener listener;
	
	public FirstPersonRoom(String description) {
		super(description);
		listener = null;
	}
	
	public FirstPersonRoom(String description, MouseListener listener) {
		super(description);
		this.listener = listener;
	}
	
	public JPanel getView(String direction) {
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		if (getExit(direction) != null) {
			JLabel roomLabel = new JLabel(getExit(direction).getDescription());
			roomLabel.setHorizontalAlignment(SwingConstants.CENTER);
			roomLabel.setForeground(Color.WHITE);
			roomLabel.setBounds(238, 280, 141, 16);
			panel.add(roomLabel);
			
			JLabel door = new JLabel("");
			door.setToolTipText("go," + direction);
			door.setIcon(new ImageIcon(FirstPersonRoom.class.getResource("/img/firstperson/door.png")));
			door.setBounds(219, 253, 180, 250);
			door.addMouseListener(listener);
			panel.add(door);
		}
		
		JLabel room = new JLabel("");
		room.setIcon(new ImageIcon(FirstPersonRoom.class.getResource("/img/firstperson/room.png")));
		room.setBounds(0, 0, 600, 600);
		panel.add(room);
		
		return panel;
	}

}
