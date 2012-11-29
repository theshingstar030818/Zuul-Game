import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import editor.EditorDriver;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class WelcomeScreen {

	private JFrame frame;
	private GameDriver gameDriver;
	private EditorDriver editorDriver;
	private JTextField txtLoadgametext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen window = new WelcomeScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeScreen() {
		gameDriver = new GameDriver();
		editorDriver = new EditorDriver();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 347, 206);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblWelcomeToWorld = new JLabel("Welcome to World of Zuul!");
		frame.getContentPane().add(lblWelcomeToWorld, "4, 2");
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gameDriver.startDefaultGame();
			}
		});
		frame.getContentPane().add(btnStartGame, "4, 4");
		
		JLabel lblPath = new JLabel("Path:");
		frame.getContentPane().add(lblPath, "2, 6, right, default");
		
		txtLoadgametext = new JTextField();
		frame.getContentPane().add(txtLoadgametext, "4, 6, fill, default");
		txtLoadgametext.setColumns(10);
		
		JButton btnLoadGame = new JButton("Load Game");
		frame.getContentPane().add(btnLoadGame, "6, 6");
		
		JButton btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editorDriver.show();
			}
		});
		frame.getContentPane().add(btnLevelEditor, "4, 8");
	}

}
