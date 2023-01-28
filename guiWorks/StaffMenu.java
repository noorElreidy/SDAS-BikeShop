package guiWorks;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;




import components.Order;
import users.Staff;

public class StaffMenu extends JPanel {

	/**
	 * Create the panel.
	 * @param staffNewLogin 
	 */
	public StaffMenu(Staff staffNewLogin, JPanel jp) {
		
		JPanel panel2=new JPanel();
		add(panel2);
		panel2.setLayout(new GridLayout(10, 1, 0, 0));		
		JLabel lblNewLabel = new JLabel("Hello employee welcome to the staff menu");
		panel2.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("create order");
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);	
				jp.removeAll();//hides staff menu panel
				createOrder co = new createOrder(staffNewLogin,jp);		//creates all orders panel
				jp.add(co);		//adds all orders panel to frame
				co.setVisible(true);	//shows all orders panel
			}
		});
		
		panel2.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("view all orders");
		btnNewButton_3.addActionListener(new ActionListener() {		//runs when view all orders is pressed
			public void actionPerformed(ActionEvent e) {
			
				//System.out.println((Order.getOrdersList()));
				/*for (int i = 0; i < Order.getOrdersList().size(); i ++) {
					for (int j = 0; j < 8; j++) {
						System.out.println(Order.to2DArray(Order.getOrdersList())[i][j]);
						
					}
					
				}*/
				//System.out.println(Order.to2DArray(Order.getOrdersList())[0][0]);
				//System.out.println(Order.getOrdersList().size());
				panel2.setVisible(false);	//hides staff menu panel
				allOrders ao = new allOrders(0,staffNewLogin, jp);		//creates all orders panel
				ao.setVisible(true);	//shows all orders panel
				panel2.setVisible(false);
				jp.removeAll();	
				jp.add(ao);		
				ao.setVisible(true);	


			}
		});
		panel2.add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("edit order status");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);	//hides staff menu panel

                jp.removeAll();
			//creates all orders panel

				allOrders ao = new allOrders(1,staffNewLogin, jp);		//creates all orders panel

				jp.add(ao);		//adds all orders panel to frame
				ao.setVisible(true);	//shows all orders panel
			}
		});
		panel2.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("edit order");
		panel2.add(btnNewButton);
		
		
		
		JButton btnNewButton_4 = new JButton("View products available and edit stock");
		panel2.add(btnNewButton_4);
		
		JButton btnNewButton_7 = new JButton("Add Frameset to store");
		panel2.add(btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);
				jp.removeAll();
				AddFrameset addf = new AddFrameset(staffNewLogin, jp);
				jp.add(addf);		
				addf.setVisible(true);	

			
			}
		});
		
		
		
		JButton btnNewButton_5 = new JButton("Add HandleBars to store");
		panel2.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				System.out.println("lllll");
				panel2.setVisible(false);
				jp.removeAll();
				AddHandleBars addHl = new AddHandleBars(staffNewLogin, jp);	
				jp.add(addHl);		
				addHl.setVisible(true);	
			
			
			}
		});
		
		JButton btnNewButton_6 = new JButton("Add Wheels to store");
		panel2.add(btnNewButton_6);
		
		JLabel lblNewLabel_1 = new JLabel("side note:searching and editing status will take several seconds please be patient");
		panel2.add(lblNewLabel_1);
		btnNewButton_6.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);
				jp.removeAll();
				AddWheels addWhl = new AddWheels(staffNewLogin,jp);
				jp.add(addWhl);		
				addWhl.setVisible(true);
			
			}
		});

	}

}
