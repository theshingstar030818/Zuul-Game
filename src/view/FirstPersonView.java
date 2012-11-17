package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class FirstPersonView extends JFrame implements Observer {
	
	private GridLayout layout;
	private MapView map;
	
	public FirstPersonView(String name) {
		super(name);
		
		//Initialize the layout
		layout = new GridLayout(0,2);
		getContentPane().setLayout(layout);
		
		//Initialize the map
		map = new MapView("Map");
		
		//Setup the window
		setSize(1200,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof FirstPersonRoom) {
			FirstPersonRoom currentRoom = (FirstPersonRoom) arg1;
			
			getContentPane().removeAll();
			map.update(arg0, arg1);
			
			add(currentRoom.getView("north"));
			add(map.getContentPane());
			
			//Repaint the 3D View
			validate();
			repaint();
		}
	}
	
	public static void main(String args[]) {
		FirstPersonView view = new FirstPersonView("World of Zuul");
		view.setVisible(true);
	}

}
