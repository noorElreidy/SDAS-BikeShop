package guiWorks;

import javax.swing.JPanel;

import users.Staff;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.Handlebars;

import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddHandleBars extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public AddHandleBars(Staff staff, JPanel jp) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_1.setPreferredSize(new Dimension(60,180));
		panel_1.setLayout(new GridLayout(6, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Serial Number");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("BrandName");
		panel_1.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Cost");
		panel_1.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Style");
		panel_1.add(lblNewLabel_4);
		
		JComboBox comboBox = new JComboBox();
		panel_1.add(comboBox);
		comboBox.addItem("HIGH");
		comboBox.addItem("DROPPED");
		comboBox.addItem("STRAIGHT");
		
		
		JLabel lblNewLabel_5 = new JLabel("Stock");
		panel_1.add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Return to Staff menu");
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
		
		JButton btnNewButton_1 = new JButton("Add HandleBar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serialNo = textField.getText();
				String productName = textField_1.getText();
				String brandName = textField_2.getText();
				
				if ( serialNo.equals("") || productName.equals("") ||  brandName.equals("") 
						|| textField_3.getText().equals("") ||  textField_4.getText().equals("") ) {
					JOptionPane.showMessageDialog((Component)e.getSource(),"All fields are required"
							,"Alert",JOptionPane.WARNING_MESSAGE);
				} else {
					double cost =  Double.valueOf(textField_3.getText());
					int styleInd = comboBox.getSelectedIndex();
					String style = "";
					if (styleInd == 1) {
						style = "HIGH";
						
					}
					if (styleInd == 2) {
						style = "DROPPED";
						
					}else {
						style = "STRAIGHT";
					}
					int stock = Integer.valueOf(textField_4.getText());
					
					Handlebars hl = new Handlebars(serialNo,brandName, productName,cost,style,stock);
					if (hl.insertHandleBar()) {
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
