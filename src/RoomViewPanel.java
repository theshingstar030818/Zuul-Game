import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class RoomViewPanel extends Room {
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	
	private boolean hasPlayer = false;
	
	
	public RoomViewPanel(String name) {
		
		super(name);
		
		frame.add(panel);
		Container contentPane = frame.getContentPane();
		
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(panel,BorderLayout.CENTER);
		
		contentPane.setBackground(Color.lightGray);
		
		panel.setBorder(BorderFactory.createLineBorder (Color.blue, 2));
		
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel(getLongDescription()));
		if(hasItems()){
		//add the image of the item in the panel 
		}
		frame.pack();
		
	}
	


	public JFrame getRoomFrame(){
		return frame;
	}
	
	
	
}
