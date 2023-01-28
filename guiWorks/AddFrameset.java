package guiWorks;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.Frameset;
import users.Staff;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.SwingConstants;

public class AddFrameset extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the panel.
	 */
	public AddFrameset(Staff staff, JPanel jp) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_1.setPreferredSize(new Dimension(60,180));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(8, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		
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
		
		JLabel lblNewLabel_4 = new JLabel("Size");
		panel_1.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Shocks");
		panel_1.add(lblNewLabel_5);
		
		JComboBox comboBox = new JComboBox();
		panel_1.add(comboBox);
		comboBox.addItem("NO");
		comboBox.addItem("YES");
		
		JLabel lblNewLabel_6 = new JLabel("Number of Gears");
		panel_1.add(lblNewLabel_6);
		
		textField_6 = new JTextField();
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Stock");
		panel_1.add(lblNewLabel_7);
		
		textField_7 = new JTextField();
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Return to staff menu");
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
		
		JButton btnNewButton_1 = new JButton("Add Frameset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serialNo = textField.getText();
				String productName = textField_1.getText();
				String brandName = textField_2.getText();
				
				if ( serialNo.equals("") || productName.equals("") ||  brandName.equals("") 
						|| textField_3.getText().equals("") ||  textField_4.getText().equals("") 
						|| textField_6.getText().equals("") || textField_7.getText().equals("")) {
					JOptionPane.showMessageDialog((Component)e.getSource(),"All fields are required"
							,"Alert",JOptionPane.WARNING_MESSAGE);
				}else {
					double cost =  Double.valueOf(textField_3.getText());
					double size = Double.valueOf(textField_4.getText());
					boolean shocks ;
					if (comboBox.getSelectedIndex() == 1) {
						shocks = false ;
					}else {
						shocks = true ; 
					}
					int gears = Integer.valueOf( textField_6.getText()) ; 
					int stock = Integer.valueOf( textField_7.getText()) ; 
					Frameset frameset = new Frameset(serialNo,brandName,productName,cost
							,size,shocks,gears,stock);
					if (frameset.insertFrameset()) {
						JOptionPane.showMessageDialog((Component)e.getSource(),"Successfully Added Frameset"
								,"Alert",JOptionPane.WARNING_MESSAGE);
						panel.setVisible(false);
						jp.removeAll();
						AddFrameset addf = new AddFrameset(staff, jp);
						jp.add(addf);		
						addf.setVisible(true);	
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
