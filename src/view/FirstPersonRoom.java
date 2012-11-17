package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstPersonRoom extends MapRoom {
	
	public FirstPersonRoom(String description) {
		super(description);
	}
	
	public JPanel getView(String direction) {
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		
		if (getExit(direction) != null) {
			JLabel door = new JLabel("");
			door.setIcon(new ImageIcon(FirstPersonRoom.class.getResource("/img/firstperson/door.png")));
			door.setBounds(219, 253, 180, 250);
			panel.add(door);
		}
		
		JLabel room = new JLabel("");
		room.setIcon(new ImageIcon(FirstPersonRoom.class.getResource("/img/firstperson/room.png")));
		room.setBounds(0, 0, 600, 600);
		panel.add(room);
		
		return panel;
	}

}
