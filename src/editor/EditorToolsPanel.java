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
		btnCreateRoom.setToolTipText("addRoom");
		btnCreateRoom.addMouseListener(mouseListener);
		add(btnCreateRoom, "2, 2, 3, 1");
		
		JButton btnDeleteRoom = new JButton("Delete Room");
		btnDeleteRoom.setToolTipText("removeRoom");
		btnDeleteRoom.addMouseListener(mouseListener);
		add(btnDeleteRoom, "2, 4, 3, 1");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"north", "south", "east", "west"}));
		add(comboBox, "2, 8, fill, default");
		
		JButton btnAddExit = new JButton("Add Exit");
		btnAddExit.setToolTipText("addExit");
		btnAddExit.addMouseListener(mouseListener);
		add(btnAddExit, "4, 8");
		
		JButton btnDeleteExit = new JButton("Delete Exit");
		btnDeleteExit.setToolTipText("removeExit");
		btnDeleteExit.addMouseListener(mouseListener);
		add(btnDeleteExit, "4, 10");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Kracken", "Goblin"}));
		add(comboBox_1, "2, 14, fill, default");
		
		JButton btnAddMonster = new JButton("Add Monster");
		btnAddMonster.setToolTipText("addMonster");
		btnAddMonster.addMouseListener(mouseListener);
		add(btnAddMonster, "4, 14");
		
		JButton btnDeleteMonster = new JButton("Delete Monster");
		btnDeleteMonster.setToolTipText("removeMonster");
		btnDeleteMonster.addMouseListener(mouseListener);
		add(btnDeleteMonster, "2, 16, 3, 1");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Plant", "Sword", "PogoStix"}));
		add(comboBox_2, "2, 20, fill, default");
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setToolTipText("addItem");
		btnAddItem.addMouseListener(mouseListener);
		add(btnAddItem, "4, 20");
		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.setToolTipText("removeItem");
		btnDeleteItem.addMouseListener(mouseListener);
		add(btnDeleteItem, "2, 22, 3, 1");

	}

}
