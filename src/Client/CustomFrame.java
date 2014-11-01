package Client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomFrame extends JFrame {

	public JLabel label;

	public CustomFrame(String programId) {
		this.setSize(400, 150);
		this.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel();
		label = new JLabel("Start");
		mainPanel.add(label);

		this.setContentPane(mainPanel);

		this.setVisible(true);
		this.setTitle("Program Id: " + programId);
	}
}
