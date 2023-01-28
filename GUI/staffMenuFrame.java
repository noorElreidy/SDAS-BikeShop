package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class staffMenuFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staffMenuFrame frame = new staffMenuFrame();
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
	public staffMenuFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Hello employee welcome to the staff menu");
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("create order");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empPartSelection sf=new empPartSelection();
				sf.setVisible(true);
				dispose();
				
			}
		});
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("edit order");
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("view all orders");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAllOrdersFrame sf=new searchAllOrdersFrame();
				sf.setVisible(true);
				
			}
		});
		getContentPane().add(btnNewButton_3);
	}

}
