package guiWorks;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.Bike;
import components.Order;
import users.Address;
import users.Customer;

public class RegisterCustomer extends JPanel {
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public RegisterCustomer(Order ord, JPanel jp) {
        setLayout(new GridLayout(0, 1, 0, 0));
		
		
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(7, 2, 0, 0));
		
		JLabel firstNameLabel = new JLabel("First Name : ");
		panel.add(firstNameLabel);
		
		JTextField firstNameTextField = new JTextField();
		panel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Last Name : ");
		panel.add(lastNameLabel);
		
		JTextField LastNameTextField = new JTextField();
		panel.add(LastNameTextField);
		LastNameTextField.setColumns(10);
		
		JLabel houseNumLabel = new JLabel("House Number : ");
		panel.add(houseNumLabel);
		
		JTextField houseNumtextField = new JTextField();
		panel.add(houseNumtextField);
		houseNumtextField.setColumns(10);
		
		JLabel streetNameLabel = new JLabel("Street Name : ");
		panel.add(streetNameLabel);
		
		JTextField streetTextField = new JTextField();
		panel.add(streetTextField);
		streetTextField.setColumns(10);
		
		JLabel postCodeLabel = new JLabel("Post Code : ");
		panel.add(postCodeLabel);
		
		JTextField postCodeTextField = new JTextField();
		panel.add(postCodeTextField);
		postCodeTextField.setColumns(10);;
		
		JLabel cityLabel = new JLabel("City : ");
		panel.add(cityLabel);
		
		JTextField cityTextField = new JTextField();
		panel.add(cityTextField);
		cityTextField.setColumns(10);
		
		//when button clicked
		JButton mainMenuButton = new JButton("Back to Main Menu");
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				CustomerMenu m = new CustomerMenu(null,jp) ; 
				jp.add(m);
				m.setVisible(true);
			}
		});
		panel.add(mainMenuButton);
		
		//when button clicked
		JButton registerAndOrderButton = new JButton("Register and place order");
		registerAndOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String edFName = firstNameTextField.getText();
				String edLname = LastNameTextField.getText();
				String edHouseN = houseNumtextField.getText();
				String edStreet = postCodeTextField.getText();
				String edPostcode = postCodeTextField.getText();
				String edCity = cityTextField.getText();
				
				//creates new address and customer objects
				Address add = new Address(edHouseN, edStreet, edPostcode,edCity);
				Customer cus = new Customer(edFName, edLname, add);
				
				boolean register = cus.newCustomer();
				
				//if registering is successful, show successful message and place the order
				if (register) {
					ord.setCustomer(cus);
					if (ord.placeOrder()) {
						JOptionPane.showMessageDialog((Component)e.getSource(),"Successfully Registered and "
								+ "order placed . You will now return to customer menu","Alert",JOptionPane.WARNING_MESSAGE);
						panel.setVisible(false);
						jp.removeAll();
						CustomerMenu m = new CustomerMenu(cus,jp) ; 
						jp.add(m);
						m.setVisible(true);
							
					}
					//if registering was not successful show error message 
					else 
					{
					JOptionPane.showMessageDialog((Component)e.getSource(),"There was a problem placing order."
							+ "Try again.","Alert",JOptionPane.WARNING_MESSAGE);
					
					}
				}else 
				{
				JOptionPane.showMessageDialog((Component)e.getSource(),"There was a problem regestering. Try logging "
						+ "in instead.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		panel.add(registerAndOrderButton);

	}

	

}
