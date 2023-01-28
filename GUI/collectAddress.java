package GUI;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import users.Address;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class collectAddress extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					collectAddress frame = new collectAddress();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public collectAddress() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(6,2,2,2));
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel_2 = new JLabel("please enter your address ");
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("house No./ flat No. :");
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAddressField = new JLabel("street name:");
		contentPane.add(lblAddressField);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCity = new JLabel("city:");
		contentPane.add(lblCity);
		
		textField_2 = new JTextField();
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPostcode = new JLabel("postcode:");
		contentPane.add(lblPostcode);
		
		textField_3 = new JTextField();
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		
		JButton btnNewButton = new JButton("confirm address");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String numberOfAddress=textField_1.getText();
				String streetName=textField.getText();
				String city=textField_2.getText();
				String postcode=textField_3.getText();
				
				Address address = new Address(numberOfAddress,streetName,postcode,city);
				System.out.println(address.getCityName());
				System.out.println("pass on this details to a function");
				
				dispose();
				
				
			}
		});
		contentPane.add(btnNewButton);
	}

}
