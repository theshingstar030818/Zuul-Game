package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.object.Player;

import controller.FPKeyListener;

public class FirstPersonView extends Observable implements Observer {
	
	private GridLayout gridLayout;
	private BorderLayout borderLayout;
	private MapView map;
	private FirstPersonRoom currentRoom;
	private JPanel gamePanel;
	private JFrame mainFrame;
	private Player player;
	private boolean gameOver;
	
	private static final String GAME_OVER = "GAME OVER";
	
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String NORTH = "north";
	
	public FirstPersonView(String name, FPKeyListener listener) {
		gamePanel = new JPanel();
		mainFrame = new JFrame(name);
		
		//Initialize the layout
		gridLayout = new GridLayout(1,2);
		borderLayout = new BorderLayout();
		gamePanel.setLayout(gridLayout);
		mainFrame.getContentPane().setLayout(borderLayout);
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		
		//Initialize the map
		map = new MapView("Map");
		
		//Setup the window
		mainFrame.setSize(1200,600);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBackground(Color.BLACK);
		
		//Initialize the key listener
		mainFrame.addKeyListener(listener);
		
		currentRoom = new FirstPersonRoom(null);
		player = new Player(null, null, 0, 0);
		gameOver = false;
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Player) {
			player = (Player) arg1;
			currentRoom = (FirstPersonRoom) player.getCurrentPlayerRoom();
			map.update(arg0, arg1);
			refreshView();
		}
		
		if (arg1 instanceof String) {
			String command = (String)arg1;
			if (command.equals(GAME_OVER)) {
				gameOver = true;
				refreshView();
			}
		}
	}
	
	private void refreshView() {
		//Remove everything from the gamePanel and mainFrame
		gamePanel.removeAll();
		mainFrame.getContentPane().removeAll();

		//Remove everything from the glass pane
		JPanel glassPane = (JPanel) mainFrame.getGlassPane();
		glassPane.removeAll();
		glassPane.setLayout(null);
		
		if (!gameOver) {
			//Add the 3D perspective and the map perspective
			gamePanel.add(currentRoom.getView(player.getLookingDirection()));
			gamePanel.add(map.getContentPane());
			
			JLabel arrow = new JLabel("");
			if (player.getLookingDirection().equals(NORTH)) {
				arrow.setIcon(new ImageIcon(FirstPersonRoom.class
						.getResource("/img/firstperson/arrow/north.png")));
			} else if (player.getLookingDirection().equals(SOUTH)) {
				arrow.setIcon(new ImageIcon(FirstPersonRoom.class
						.getResource("/img/firstperson/arrow/south.png")));
			} else if (player.getLookingDirection().equals(EAST)) {
				arrow.setIcon(new ImageIcon(FirstPersonRoom.class
						.getResource("/img/firstperson/arrow/east.png")));
			} else if (player.getLookingDirection().equals(WEST)) {
				arrow.setIcon(new ImageIcon(FirstPersonRoom.class
						.getResource("/img/firstperson/arrow/west.png")));
			}
			
			arrow.setBounds(875, 280, 40, 40);
			glassPane.add(arrow);
			glassPane.setVisible(true);
			
			//Add the health panel
			HealthPanel healthPanel = new HealthPanel(player.getHealth(), player.getCurrentWeight(), player.getMaxWeight());
			healthPanel.setVisible(true);
			mainFrame.add(healthPanel, BorderLayout.SOUTH);
			
			//Repaint the gamePanel
			gamePanel.validate();
			gamePanel.repaint();
			
			//Add the gamePanel to mainFrame
			mainFrame.add(gamePanel, BorderLayout.CENTER);
		} else {
			JLabel temp = new JLabel("GAME OVER");
			temp.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
			temp.setHorizontalAlignment(SwingConstants.CENTER);
			temp.setForeground(Color.RED);
			mainFrame.add(temp, BorderLayout.CENTER);
		}
		
		//Repaint the mainFrame
		mainFrame.validate();
		mainFrame.repaint();
		
	}
	
	public void show() {
		mainFrame.setVisible(true);
		JOptionPane.showMessageDialog(mainFrame, "Welcome to Zuul! An incredibly boring adventure game. Use the left and right arrow keys to\n" +
				"look around the room. Click on a door to go through it, click on items to pick them up, and click on Monsters to\n" +
				"attack them. Enjoy!","Welcome", 0);
	}

}
