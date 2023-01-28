package guiWorks;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import users.Address;
import users.Customer;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerLogin extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Create the panel.
	 */
	public CustomerLogin(JPanel jp) {
		
        setLayout(new GridLayout(1, 1, 0, 0));
		
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(6, 2, 0, 0));
		
		
		JLabel lblNewLabel = new JLabel("First name");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last name");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("House Number");
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Postcose");
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);
		
	
		
		btnNewButton = new JButton("Back to HomePage");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				panel.setVisible(false); 
				jp.removeAll();
				MainPanel p = new MainPanel(jp) ; 
				jp.add(p);
				p.setVisible(true);
				*/
				setVisible(false);
				MainFr fr = new MainFr();
				fr.setVisible(true);
			}
		});
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Address add = Address.getAddressByPK(textField_2.getText(), textField_3.getText());
				Customer cus = new Customer(textField.getText(), textField_1.getText(), add );
				
				if (cus.customerLogin()) {
					panel.setVisible(false);
					jp.removeAll();
					CustomerMenu m = new CustomerMenu(cus,jp) ; 
					//CustomerOptions m = new CustomerOptions() ; 
					jp.add(m);
					m.setVisible(true);
				}
				else {

					JOptionPane.showMessageDialog((Component)e.getSource(), "Wrong details",
		                    "inccorect login", JOptionPane.ERROR_MESSAGE);
      			}


				
					
				
			
			}
		});
		panel.add(btnNewButton_1);
		
	   

	}
  /*
	public CustomerLogin(JPanel contentPane) {
        setLayout(new GridLayout(1, 1, 0, 0));
		
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(6, 2, 0, 0));
		
		
		JLabel lblNewLabel = new JLabel("First name");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last name");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("House Number");
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Postcose");
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		btnNewButton = new JButton("Back to HomePage");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false); 
				MainPanel p = new MainPanel(fr) ; 
				contentPane.add(p);
				p.setVisible(true);
			}
		});
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Login");
		panel.add(btnNewButton_1);
	}*/

	

}
