package editor.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import editor.controller.EditorMouseListener;

public class EditorToolsPanel extends JPanel {

	private static final String REMOVE_ITEM = "removeItem";
	private static final String ADD_ITEM = "addItem";
	private static final String REMOVE_MONSTER = "removeMonster";
	private static final String ADD_MONSTER = "addMonster";
	private static final String REMOVE_EXIT = "removeExit";
	private static final String ADD_EXIT = "addExit";
	private static final String REMOVE_ROOM = "removeRoom";
	private static final String ADD_ROOM = "addRoom";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public EditorToolsPanel(EditorMouseListener mouseListener) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnCreateRoom = new JButton("Create Room");
		btnCreateRoom.setToolTipText(ADD_ROOM);
		btnCreateRoom.addMouseListener(mouseListener);
		add(btnCreateRoom, "2, 2, 3, 1");
		
		JComboBox directionBox = new JComboBox();
		directionBox.setModel(new DefaultComboBoxModel(new String[] {"Select One","north", "south", "east", "west"}));
		directionBox.addActionListener(mouseListener);
		
		JButton btnDeleteRoom = new JButton("Delete Room");
		btnDeleteRoom.setToolTipText(REMOVE_ROOM);
		btnDeleteRoom.addMouseListener(mouseListener);
		add(btnDeleteRoom, "2, 4, 3, 1");
		
		JSeparator separator = new JSeparator();
		add(separator, "2, 6, 3, 1");
		
		JLabel lblDirection_1 = new JLabel("Exit Direction:");
		add(lblDirection_1, "2, 8, right, default");
		directionBox.setToolTipText("direction");
		add(directionBox, "4, 8, fill, default");
		
		JComboBox monstersBox = new JComboBox();
		monstersBox.setModel(new DefaultComboBoxModel(new String[] {"Select One", "Kracken", "Goblin"}));
		monstersBox.addActionListener(mouseListener);
		
		JButton btnDeleteExit = new JButton("Delete Exit");
		btnDeleteExit.setToolTipText(REMOVE_EXIT);
		btnDeleteExit.addMouseListener(mouseListener);
		add(btnDeleteExit, "2, 10");
		
		JButton btnAddExit = new JButton("Add Exit");
		btnAddExit.setToolTipText(ADD_EXIT);
		btnAddExit.addMouseListener(mouseListener);
		add(btnAddExit, "4, 10");
		
		JSeparator separator_1 = new JSeparator();
		add(separator_1, "2, 12, 3, 1");
		
		JLabel lblMonster = new JLabel("Monster:");
		add(lblMonster, "2, 14, right, default");
		monstersBox.setToolTipText("monster");
		add(monstersBox, "4, 14, fill, default");
		
		JButton btnAddMonster = new JButton("Add Monster");
		btnAddMonster.setToolTipText(ADD_MONSTER);
		btnAddMonster.addMouseListener(mouseListener);
		add(btnAddMonster, "2, 16");
		
		JButton btnDeleteMonster = new JButton("Delete Monster");
		btnDeleteMonster.setToolTipText(REMOVE_MONSTER);
		btnDeleteMonster.addMouseListener(mouseListener);
		add(btnDeleteMonster, "4, 16");
		
		JSeparator separator_2 = new JSeparator();
		add(separator_2, "2, 18, 3, 1");
		
		JLabel lblItem = new JLabel("Item");
		add(lblItem, "2, 20, right, default");
		
		JComboBox itemBox = new JComboBox();
		itemBox.setModel(new DefaultComboBoxModel(new String[] {"Select One", "Plant", "Sword", "PogoStix"}));
		itemBox.addActionListener(mouseListener);
		itemBox.setToolTipText("item");
		add(itemBox, "4, 20, fill, default");
		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.setToolTipText(REMOVE_ITEM);
		btnDeleteItem.addMouseListener(mouseListener);
		add(btnDeleteItem, "2, 22");
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setToolTipText(ADD_ITEM);
		btnAddItem.addMouseListener(mouseListener);
		add(btnAddItem, "4, 22");
		
		JSeparator separator_3 = new JSeparator();
		add(separator_3, "2, 24, 3, 1");
		
		JButton btnTurnLeft = new JButton("Turn Left");
		btnTurnLeft.setToolTipText("left");
		btnTurnLeft.addMouseListener(mouseListener);
		add(btnTurnLeft, "2, 26");
		
		JButton btnTurnRight = new JButton("Turn Right");
		btnTurnRight.setToolTipText("right");
		btnTurnRight.addMouseListener(mouseListener);
		add(btnTurnRight, "4, 26");

	}

}
