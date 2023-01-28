package guiWorks;

import javax.swing.JPanel;

import users.Staff;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.Handlebars;
import components.Wheels;

import javax.swing.JComboBox;

public class AddWheels extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public AddWheels(Staff staff, JPanel jp) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_1.setPreferredSize(new Dimension(60,180));
		panel_1.setLayout(new GridLayout(8, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Serial Number  ");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name ");
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Brand Name  ");
		panel_1.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Cost ");
		panel_1.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Diameter ");
		panel_1.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Type ");
		panel_1.add(lblNewLabel_5);
		
		JComboBox comboBox = new JComboBox();
		panel_1.add(comboBox);
		comboBox.addItem("MOUNTAIN");
		comboBox.addItem("ROAD");
		comboBox.addItem("MOUNTAIN");
		
		JLabel lblNewLabel_6 = new JLabel("Brake type ");
		panel_1.add(lblNewLabel_6);
		
		
		JComboBox comboBox_1 = new JComboBox();
		panel_1.add(comboBox_1);
		comboBox_1.addItem("RIM");
		comboBox_1.addItem("DISK");
		
		JLabel lblNewLabel_7 = new JLabel("Stock ");
		panel_1.add(lblNewLabel_7);
		
		textField_5 = new JTextField();
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton btnNewButton = new JButton("Back to staff menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				StaffMenu sM = new StaffMenu(staff, jp);	
				jp.add(sM);		
				sM.setVisible(true);
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add Wheels");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serialNo = textField.getText();
				String productName = textField_1.getText();
				String brandName = textField_2.getText();
				
				if ( serialNo.equals("") || productName.equals("") ||  brandName.equals("") 
						|| textField_3.getText().equals("") ||  textField_4.getText().equals("") 
						|| textField_5.getText().equals("")) {
					JOptionPane.showMessageDialog((Component)e.getSource(),"All fields are required"
							,"Alert",JOptionPane.WARNING_MESSAGE);
				}else {
					double cost =  Double.valueOf(textField_3.getText());
					double diameter = Double.valueOf(textField_4.getText());
					String style = String.valueOf( comboBox.getSelectedItem());
					String brake = String.valueOf( comboBox_1.getSelectedItem());
					int stock = Integer.valueOf(textField_5.getText());
					
					Wheels whl = new Wheels(serialNo, brandName, productName,
							cost, diameter, style, brake, stock);
					if (whl.insertWheels()) {
						JOptionPane.showMessageDialog((Component)e.getSource(),"Successfully Added Handlebar"
								,"Alert",JOptionPane.WARNING_MESSAGE);
						panel.setVisible(false);
						jp.removeAll();
						AddHandleBars addHl = new AddHandleBars(staff, jp);	
						jp.add(addHl);		
						addHl.setVisible(true);	
						
					}else {
						JOptionPane.showMessageDialog((Component)e.getSource(),"This brand already uses this serial"
								+ " number for another product. ","Alert",JOptionPane.WARNING_MESSAGE);
					
					}
			  }
			}
		});
		panel_2.add(btnNewButton_1);

	}

}
