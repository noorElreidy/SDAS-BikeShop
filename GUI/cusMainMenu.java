package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class cusMainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cusMainMenu frame = new cusMainMenu();
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
	public cusMainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5,1));
		
		JLabel lblNewLabel = new JLabel("hi welcome pick one of these options please");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("assemble bike");
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("view previous orders");
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("edit own details");
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("sign out and return to main page");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mainFrame sf= new mainFrame();
				sf.setVisible(true);
				//dispose();
				
			}
		});
		contentPane.add(btnNewButton_3);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

}
