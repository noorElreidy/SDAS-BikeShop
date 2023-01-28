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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditCustomer extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public EditCustomer(Customer cus, JPanel jp) {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		String firstName = cus.getFirstName();
		String lastName = cus.getLastName();
		String houseNum = cus.getAddress().getHouseNumber();
		String street = cus.getAddress().getStreetName();
		String postcode = cus.getAddress().getPostcode();
		String city = cus.getAddress().getCityName();
		
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(7, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("First Name : ");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		textField.setText(firstName);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name : ");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(lastName);
		
		JLabel lblNewLabel_2 = new JLabel("House Number : ");
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(houseNum);
		
		JLabel lblNewLabel_3 = new JLabel("Street Name : ");
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(street);
		
		JLabel lblNewLabel_4 = new JLabel("Post Code : ");
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		panel.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(postcode);
		
		JLabel lblNewLabel_5 = new JLabel("City : ");
		panel.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		panel.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setText(city);
		
		JButton btnNewButton = new JButton("Back to customer Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				CustomerMenu m = new CustomerMenu(cus,jp) ; 
				//CustomerOptions m = new CustomerOptions() ; 
				jp.add(m);
				m.setVisible(true);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String edFName = textField.getText();
				String edLname = textField_1.getText();
				String edHouseN = textField_2.getText();
				String edStreet = textField_3.getText();
				String edPostcode = textField_4.getText();
				String edCity = textField_5.getText();
				
				Address edAdd = new Address(edHouseN, edStreet, edPostcode,edCity);
				//System.out.println(edHouseN);
				
				boolean edit = cus.editCustomer(edFName, edLname, edAdd);
				
				if (edit ) {
					/**
					int  a = JOptionPane.showConfirmDialog((Component)e.getSource(),"Are you sure?");  
					if ( a == JOptionPane.OK_OPTION) {
					**/
					JOptionPane.showMessageDialog((Component)e.getSource(),"Successfully Updated. You will"
							+ " now return to the customer menu","Alert",JOptionPane.WARNING_MESSAGE);
					panel.setVisible(false);
					jp.removeAll();
					CustomerMenu m = new CustomerMenu(cus,jp) ; 
					//CustomerOptions m = new CustomerOptions() ; 
					jp.add(m);
					m.setVisible(true);
						
//					}
				}
				
			}
		});
		panel.add(btnNewButton_1);

	}

}
