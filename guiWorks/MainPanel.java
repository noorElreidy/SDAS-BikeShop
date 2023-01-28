package guiWorks;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainPanel(JPanel jp) {
		
		JPanel panel = new JPanel(); 
		
        panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Welcome to Bikes Co.");
		panel.add(lblNewLabel);
		
		CustomerLogin c = new CustomerLogin(jp);
		JButton btnNewButton = new JButton("Customer Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				jp.add(c);
				c.setVisible(true);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("browse products");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				BrowseComponents b = new BrowseComponents(null, jp);
				jp.add(b);
				b.setVisible(true);
			}
		});
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Staff Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				StaffLogin sl = new StaffLogin(jp);
				jp.add(sl);
				sl.setVisible(true);
			}
		});
		panel.add(btnNewButton_1);
		add(panel);

	}

}
