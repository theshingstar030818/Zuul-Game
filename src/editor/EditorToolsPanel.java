package editor;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnCreateRoom = new JButton("Create Room");
		btnCreateRoom.setToolTipText(ADD_ROOM);
		btnCreateRoom.addMouseListener(mouseListener);
		add(btnCreateRoom, "2, 2, 3, 1");
		
		JButton btnDeleteRoom = new JButton("Delete Room");
		btnDeleteRoom.setToolTipText(REMOVE_ROOM);
		btnDeleteRoom.addMouseListener(mouseListener);
		add(btnDeleteRoom, "2, 4, 3, 1");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"north", "south", "east", "west"}));
		comboBox.addActionListener(mouseListener);
		add(comboBox, "2, 8, fill, default");
		
		JButton btnAddExit = new JButton("Add Exit");
		btnAddExit.setToolTipText(ADD_EXIT);
		btnAddExit.addMouseListener(mouseListener);
		add(btnAddExit, "4, 8");
		
		JButton btnDeleteExit = new JButton("Delete Exit");
		btnDeleteExit.setToolTipText(REMOVE_EXIT);
		btnDeleteExit.addMouseListener(mouseListener);
		add(btnDeleteExit, "4, 10");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Kracken", "Goblin"}));
		add(comboBox_1, "2, 14, fill, default");
		
		JButton btnAddMonster = new JButton("Add Monster");
		btnAddMonster.setToolTipText(ADD_MONSTER);
		btnAddMonster.addMouseListener(mouseListener);
		add(btnAddMonster, "4, 14");
		
		JButton btnDeleteMonster = new JButton("Delete Monster");
		btnDeleteMonster.setToolTipText(REMOVE_MONSTER);
		btnDeleteMonster.addMouseListener(mouseListener);
		add(btnDeleteMonster, "2, 16, 3, 1");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Plant", "Sword", "PogoStix"}));
		add(comboBox_2, "2, 20, fill, default");
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setToolTipText(ADD_ITEM);
		btnAddItem.addMouseListener(mouseListener);
		add(btnAddItem, "4, 20");
		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.setToolTipText(REMOVE_ITEM);
		btnDeleteItem.addMouseListener(mouseListener);
		add(btnDeleteItem, "2, 22, 3, 1");

	}

}
