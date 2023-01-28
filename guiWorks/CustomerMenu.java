package guiWorks;

import javax.swing.JPanel;

import users.Customer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public CustomerMenu(Customer cus, JPanel jp) {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.setAlignmentX(RIGHT_ALIGNMENT);
		
		add(panel);
		
		JPanel panel2 = new JPanel() ; 
		panel2.setLayout(new GridLayout(2, 2, 0, 0));
		panel.add(panel2, BorderLayout.CENTER);
		
		
		//button to see pending orders
		//when clicked takes to view customer order panel
		JButton viewPendingButton = new JButton("View Pending Orders");
		viewPendingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				CustomerViewOrders cVO = new CustomerViewOrders(cus,"PENDING",jp);
				jp.add(cVO);
				cVO.setVisible(true);
				
			}
		});
		panel2.add(viewPendingButton);
		
		//button to see previous orders 
		//when clicked takes customer to panel where they can see their previous orders
		JButton viewPreviousButton = new JButton("View Previous Orders");
		viewPreviousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				CustomerViewOrders cVO = new CustomerViewOrders(cus,"PREVIOUS",jp);
				jp.add(cVO);
				cVO.setVisible(true);
			}
		});
		panel2.add(viewPreviousButton);
		
		JButton browseButton = new JButton("Browse Products");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				Browse2 b = new Browse2(cus,jp);
				jp.add(b);
				b.setVisible(true);
				
			}
		});
		panel2.add(browseButton);
		
		JButton btnNewButton_1 = new JButton("Edit details");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				EditCustomer eC = new EditCustomer(cus,jp) ; 
				jp.add(eC);
				eC.setVisible(true);
			}
		});
		panel2.add(btnNewButton_1);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainFr fr = new MainFr();
				fr.setVisible(true);
			}
		});
		panel.add(btnLogout, BorderLayout.SOUTH);
		
		

	}

}
