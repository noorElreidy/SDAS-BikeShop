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

public class loginFrame extends JFrame {

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
					loginFrame frame = new loginFrame();
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
	public loginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("hi please enter your loging details");
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("enter user name:");
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("enter password:");
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("login ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userInp=textField.getText();
				String passwordInp=String.valueOf(passwordField.getPassword());
				System.out.println(passwordInp);
				//function call to confirm login
				//if true  close this window and display regular option menu 
				//else wrong user or password error message 
				String correctPassword="lol";
				if (!(correctPassword.equals(userInp))) {
		            JOptionPane.showMessageDialog((Component)e.getSource(), "wrong username or password try again",
		                    "inccorect login", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					System.out.println("hahahahahahaha");
					dispose();
					assembleBikeFrame sf= new assembleBikeFrame();
					sf.setVisible(true);

				}
					
				
			}
		});
		contentPane.add(btnNewButton);
	}

}
