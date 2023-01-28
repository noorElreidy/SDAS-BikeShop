package guiWorks;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GUI.staffMenuFrame;
import database.Encryption;
import users.Staff;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffLogin extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	
	private JPanel contentPane;

	/**
	 * Create the panel.
	 */
	public StaffLogin(JPanel jp) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		add(panel);
		
		
		
		panel.add(Box.createRigidArea(new Dimension(0, 300)));
		JLabel enterNameLabel = new JLabel("Enter Username:");	//user name entry prompt
		panel.add(enterNameLabel);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JTextField UsernameText = new JTextField();
		panel.add(UsernameText);		//takes in user name input
		UsernameText.setColumns(10);
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		
		JLabel passwordLabel = new JLabel("Enter Password:");	//password entry prompt
		panel.add(passwordLabel);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		passwordField = new JPasswordField();
		panel.add(passwordField);		//takes in password input
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JButton loginButton = new JButton("login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userInp=UsernameText.getText();		//assigns user inputs to corresponding variables
				String passwordInp=String.valueOf(passwordField.getPassword());
				
				String hashedPassword = Encryption.doHash(passwordInp);	//hashes password and stores in var
				
				Staff staffNewLogin = new Staff(userInp, hashedPassword);		//creates new staff object
				if (staffNewLogin.staffLogin()) {			//checks if new staff object matches any known staff
				
					
					panel.setVisible(false);		//hides staff login panel
					jp.removeAll();
					
					
					StaffMenu sm = new StaffMenu(staffNewLogin, jp);	//creates staff menu panel
					jp.add(sm);		//adds staff menu panel to frame
					sm.setVisible(true);	//shows staff menu panel
				}
				else {

					JOptionPane.showMessageDialog((Component)e.getSource(), "wrong username or password try again",
		                    "inccorect login", JOptionPane.ERROR_MESSAGE);		//staff details don't match, didnt login
				}
				
			}
		});
		
		panel.add(loginButton);

	}

}
