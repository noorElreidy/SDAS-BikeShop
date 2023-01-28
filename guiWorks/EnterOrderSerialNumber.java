package guiWorks;

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import components.Order;
import javax.swing.border.EmptyBorder;

public class EnterOrderSerialNumber extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public EnterOrderSerialNumber(JPanel jp) {
		//ssetLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(100, 0, 0, 0));
		add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Serial No: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(20);
		
		
		//System.out.println(serial);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton btnNewButton = new JButton("Enter details to view all orders instead");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				CustomerLogin c = new CustomerLogin(jp);
				jp.add(c);
				c.setVisible(true);
				
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Submit serial Number");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serial = textField.getText();
				
				Order ord = Order.getOrderBySerialNo(serial);
				
				if (ord==null) {
					JOptionPane.showMessageDialog((Component)e.getSource(),"Serial Number incorrect."
							+ "Enter a valid serial Number or enter customer details to view "
							+ "all orders. ","Alert",JOptionPane.WARNING_MESSAGE);
					
				}else if (!(ord.statusString().equals("PENDING"))){
					System.out.println(ord.statusString());
					JOptionPane.showMessageDialog((Component)e.getSource(),"This is not a pending order."
							+ "Enter customer details to view all previous Orders" ,"Alert",JOptionPane.WARNING_MESSAGE);
					
				}else {
					panel.setVisible(false);
					OrderReceipt ordRec = new OrderReceipt(ord, jp);
					jp.removeAll();
					jp.add(ordRec);
					ordRec.setVisible(true);
					
				}
			}
		});
		panel_2.add(btnNewButton_1);

	}

}
