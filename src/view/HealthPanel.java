package view;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class HealthPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HealthPanel(int health, int currWeight, int maxWeight) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblHealth = new JLabel("Health:");
		add(lblHealth);
		
		for (int i=0; i<health; i++) {
			JLabel heart = new JLabel("");
			heart.setIcon(new ImageIcon(HealthPanel.class.getResource("/img/firstperson/Heart.png")));
			add(heart);
		}
		
		JLabel lblInventoryWeight = new JLabel("Inventory Weight: " + currWeight + "/" + maxWeight);
		add(lblInventoryWeight);

	}

}
