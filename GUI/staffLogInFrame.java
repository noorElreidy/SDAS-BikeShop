package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import database.Encryption;
import users.Staff;

public class staffLogInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staffLogInFrame frame = new staffLogInFrame();
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
	public staffLogInFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("hi please enter your loging details");		// asks staff to login
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("enter user name:");		//user name entry prompt
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		contentPane.add(textField);			//takes in user name input
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("enter password:");		//password entry prompt
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField);		//takes in password input
		
		JButton btnNewButton = new JButton("login ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userInp=textField.getText();		//assigns user inputs to corresponding variables
				String passwordInp=String.valueOf(passwordField.getPassword());
				
				String hashedPassword = Encryption.doHash(passwordInp);	//hashes password and stores in var
				//System.out.println(doHash("hello"));
				//char[] passwordInp=passwordField.getPassword();
				//System.out.println(passwordInp);
				//System.out.println(hashedPassword);
				//function call to confirm login
				//if true  close this window and display regular option menu 
				//else wrong user or password error message 
				//String correctPassword="pass1";
				//String hashedCorrectPassword = Encryption.doHash(correctPassword);
				
				
				
				Staff staffNewLogin = new Staff(userInp, hashedPassword);		//creates new staff object
				if (staffNewLogin.staffLogin()) {			//checks if new staff object matches any known staff
					//System.out.println("Logged in");
					dispose();
					staffMenuFrame sf= new staffMenuFrame();		// staff details match, logs in
					sf.setVisible(true);					
				}
				else {
					//System.out.println("DB not matched");				
					
					JOptionPane.showMessageDialog((Component)e.getSource(), "wrong username or password try again",
		                    "inccorect login", JOptionPane.ERROR_MESSAGE);		//staff details don't match, didnt login
				}
				
				
				/*
				
				if (!(correctPassword.equals(passwordInp))) {
		            JOptionPane.showMessageDialog((Component)e.getSource(), "wrong username or password try again",
		                    "inccorect login", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					System.out.println("hahahahahahaha");
					dispose();
					staffMenuFrame sf= new staffMenuFrame();
					sf.setVisible(true);

				}
				*/	
				
			}
		});
		contentPane.add(btnNewButton);
	}

}
