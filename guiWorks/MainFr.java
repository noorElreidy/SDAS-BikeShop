package guiWorks;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainFr extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainFr frame = new MainFr();
					
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
	public MainFr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setBounds(100, 100, 450, 300);
		Toolkit tooly = Toolkit.getDefaultToolkit();
		Dimension dim = tooly.getScreenSize();
		setSize(dim.width, dim.height);
		
		//Toolkit toolkit = Toolkit.getDefaultToolkit();
		//Dimension dimension = toolkit.getScreenSize();
		//setSize(dimension.width/2, dimension.height/2);
		//setLocation(new Point(dimension.width/4, dimension.height/4));
		
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
        JPanel panel = new JPanel(); 
        
        
        panel.setLayout(new GridLayout(5, 1, 0, 0));
        contentPane.add(panel);
        
		JLabel lblNewLabel = new JLabel("Welcome to Bike Co");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Customer Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				contentPane.removeAll();
				CustomerLogin c = new CustomerLogin(contentPane);
				contentPane.add(c);
				c.setVisible(true);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Browse products");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				panel.setVisible(false);
				contentPane.removeAll();
				BrowseComponents b = new BrowseComponents(null,contentPane);
				contentPane.add(b);
				b.setVisible(true);
				*/
				panel.setVisible(false);
				contentPane.removeAll();
				Browse2 b = new Browse2(null,contentPane);
				contentPane.add(b);
				b.setVisible(true);
				
				
				
				
			}
		});
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Staff Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				contentPane.removeAll();
				StaffLogin sl = new StaffLogin(contentPane);
				contentPane.add(sl);
				sl.setVisible(true);
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Search Order By serial Number");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				EnterOrderSerialNumber sN = new EnterOrderSerialNumber(contentPane);
				contentPane.removeAll();
				contentPane.add(sN);
				sN.setVisible(true);
			}
		});
		panel.add(btnNewButton_3);


	}
		
		
		
		
		
	}


