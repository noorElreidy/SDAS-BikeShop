package GUI;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import users.Address;
import users.Customer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class signUpFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signUpFrame frame = new signUpFrame();
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
	public signUpFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(12,2,2,2));
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel_6 = new JLabel("please enter your name and last name");	// asks user to enter name
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_5 = new JLabel("first name:");	//first name entry prompt
		contentPane.add(lblNewLabel_5);
		
		textField_6 = new JTextField();		
		contentPane.add(textField_6);		//takes in first name input
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("last name:");	//last name entry prompt
		contentPane.add(lblNewLabel_4);
		
		textField_5 = new JTextField();
		contentPane.add(textField_5);	//takes in first name input
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("please enter your address ");	// asks user to enter address details
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("house No./ flat No.");		//house number entry prompt
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1);		//takes in house number input
		textField_1.setColumns(10);
		
		JLabel lblAddressField = new JLabel("street name:");	//street name entry prompt
		contentPane.add(lblAddressField);
		
		textField = new JTextField();
		contentPane.add(textField);		//takes in street name input
		textField.setColumns(10);
		
		JLabel lblcity = new JLabel("city: ");		//city entry prompt
		contentPane.add(lblcity);
		
		textField_2 = new JTextField();
		contentPane.add(textField_2);		//takes in city input
		textField_2.setColumns(10);
		
		JLabel lblPostcode = new JLabel("postcode:");		//postcode entry prompt
		contentPane.add(lblPostcode);
		
		textField_3 = new JTextField();
		contentPane.add(textField_3);		//takes in postcode input
		textField_3.setColumns(10);

		
		JButton btnNewButton = new JButton("confrim details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstName = textField_6.getText();		//assigns user inputs to corresponding variables
				String lastName = textField_5.getText();
				
				String numberOfAddress=textField_1.getText();
				String streetName=textField.getText();
				String city=textField_2.getText();
				String postcode=textField_3.getText();
				
				
				
				Address address = new Address(numberOfAddress,streetName,postcode,city);	//creates address object passing relevant address variables
				Customer customer = new Customer(firstName, lastName, address);		//creates customer object passing relevant customer variables and address object
				customer.newCustomer();		//uses newcustomer method from customer class to fill in database
				System.out.println(address.getCityName());
				System.out.println("pass on this details to a function");
				
				dispose();
				
				
			}
		});
		contentPane.add(btnNewButton);
	}

}
