package guiWorks;

import javax.swing.JPanel;

import users.Customer;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import components.Order;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class deleteOrder extends JPanel {
	private JTextField textField;
	

	/**
	 * Create the panel.
	 */
	public deleteOrder(Customer cus, JPanel jp) {
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Enter Order ID : ");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		String orderID = textField.getText();
		
		
	    Order ord = Order.getOrderBySerialNo(orderID);
		
		
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton btnNewButton = new JButton("Back to Customer Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				CustomerMenu m = new CustomerMenu(cus,jp) ; 
				jp.add(m);
				m.setVisible(true);
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (ord.deleteOrder()) {
					JOptionPane.showMessageDialog((Component)e.getSource(),"Successfully Deleted. You will"
							+ " now return to the customer menu","Alert",JOptionPane.WARNING_MESSAGE);
					panel.setVisible(false);
					jp.removeAll();
					CustomerMenu m = new CustomerMenu(cus,jp) ; 
					jp.add(m);
					m.setVisible(true);
					
				}
			}
		});
		panel_2.add(btnNewButton_1);

	}

}
