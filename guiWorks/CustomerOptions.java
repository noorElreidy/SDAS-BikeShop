package guiWorks;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.PartSelection;
import GUI.collectOrderIdFrame;
import GUI.mainFrame;

import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CustomerOptions extends JPanel {

	/**
	 * Create the panel.
	 */
	public CustomerOptions() {
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JButton btnNewButton = new JButton("view previous orders");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("view pending orders");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("edit address");
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("part selection ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_1 = new JButton("log out and return to main menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(btnNewButton_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("welcome back ! NAME,select one of the follwing options");
		add(lblNewLabel, BorderLayout.NORTH);
	}

	

}
