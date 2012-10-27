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
	
	private JFrame frame = new JFrame("Roon View Frame");
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
		
		//creat buttons for exits 
		//black color buttons mean there is no exit
		JButton nB = new JButton("North Button");
		JButton sB = new JButton("South Button");
		JButton eB = new JButton("East Button");
		JButton wB = new JButton("West Button");
		
		frame.add(nB,BorderLayout.NORTH);
		frame.add(sB,BorderLayout.SOUTH);
		frame.add(eB,BorderLayout.EAST);
		frame.add(wB,BorderLayout.WEST);
		
		if(getExits("north")!=null){
			nB.setBackground(Color.green);
			nB.setForeground(Color.black);
		}else{
			nB.setBackground(Color.black);
			nB.setForeground(Color.white);
		}
		if(getExits("south")!=null){
			sB.setBackground(Color.green);
			sB.setForeground(Color.black);
		}else{
			sB.setBackground(Color.black);
			sB.setForeground(Color.white);
		}
		if(getExits("east")!=null){
			eB.setBackground(Color.green);
			eB.setForeground(Color.black);
		}else{
			eB.setBackground(Color.black);
			eB.setForeground(Color.white);
		}
		if(getExits("west")!=null){
			wB.setBackground(Color.green);
			wB.setForeground(Color.black);
		}else{
			wB.setBackground(Color.black);
			wB.setForeground(Color.white);
		}
		
		if(hasItems()){
		//add the image of the item in the panel 
			
		}
		frame.pack();
		
	}
	


	public JFrame getRoomFrame(){
		return frame;
	}
	
	public static void main(String args[]) {
		RoomViewPanel panel = new RoomViewPanel("Test");
		panel.getRoomFrame().setVisible(true);
	}
	
}
