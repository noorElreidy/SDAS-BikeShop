package guiWorks;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import components.Order;
import users.Customer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class CustomerViewOrders extends JPanel {
	private JTable table;
	
	//contains all columns for confirmed and fulfilled orders 
	private String[] tableHead = {"Customer ID", "Order Serial No", "Product Name", "Bike Serial No",
			"Frameset", "Handlebars", "Wheels", "Status", "Final Cost", "staff", "collect"} ; 
	
	
	

	/**
	 * Create the panel.
	 */
	public CustomerViewOrders(Customer cus, String status ,JPanel jp) {
		
		/*
		Toolkit tooly = Toolkit.getDefaultToolkit();
		Dimension dim = tooly.getScreenSize();
		*/
		
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		//panel.setLayout(new BorderLayout(0, 0));
		//panel.setSize(dim.width/2, dim.height/2);
		JPanel panel_1 = new JPanel();
		//panel_1.setPreferredSize(new Dimension(180,180));
		panel.add(panel_1);
		
	    String[][] orders ;
	 
	    ArrayList<Order> ord = cus.getCustomerOrders(status); 
	    

	    	 orders = Order.to2DArrayBikeProducts(ord); 
	
	    	 JTable table = new JTable(orders, tableHead);

	 		 JScrollPane sp=new JScrollPane(table); 
	 		
	 		 
	 		 
	 		 
	 		// sp.setSize(1000, 300);
	         panel_1.add(sp);
	        
	           
	    	
	    
	    
	   
		
		
		
		JPanel panel_2 = new JPanel();
		
		panel.add(panel_2);
		
		if (status.equals("PENDING")) {
			JLabel label=new JLabel("click on the table to select the order you wish to delete");
			panel_2.add(label);
		}
		
		
		JTextField text= new JTextField();
		text.setColumns(12);
		JTextField text1= new JTextField();
		text1.setColumns(3);
 	    table.addMouseListener(new MouseAdapter() {//adding a mouse listener to read the serial no of the selected row
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		text.setText("");
	    		text1.setText("");
	            int row = table.rowAtPoint( e.getPoint() );
	             String s =table.getModel().getValueAt(row,1)+"";
	             text.setText(s);
	             text1.setText(String.valueOf(row));
	            
	            
	    	}
	    });
 	   panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
 	   
 	   panel_2.add(text);
		JButton btnNewButton = new JButton("Back to Customer Menu");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				jp.removeAll();
				CustomerMenu m = new CustomerMenu(cus,jp) ; 
				//CustomerOptions m = new CustomerOptions() ; 
				jp.add(m);
				m.setVisible(true);
				
				
			}
		});
		panel_2.add(btnNewButton);
		
		
		
		
		
		
		if (status.equals("PENDING")) {
			
			panel.setLayout(new GridLayout(2,1,5,0));
			JPanel panel_3 = new JPanel();
			panel.add(panel_3);
			
			JButton btnNewButton_2 = new JButton("Delete an order");
			panel_3.add(btnNewButton_2);
			
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					System.out.println(text.getText());
					Order ord = Order.getOrderBySerialNo(text.getText());
					if (ord !=null) {
					System.out.println(ord.getSerialNo());
					ord.deleteOrder();
					text1.getText();
					
					panel.setVisible(false);
					jp.removeAll();
					CustomerMenu m = new CustomerMenu(cus,jp) ; 
					jp.add(m);
					m.setVisible(true);
					JOptionPane.showMessageDialog((Component)e.getSource(),"Successfully Deleted"
							,"Pending order status",JOptionPane.INFORMATION_MESSAGE);
					//panel.setVisible(false);
					//jp.removeAll();
					//deleteOrder dO = new deleteOrder(cus,jp);
					//jp.add(dO);
					//dO.setVisible(true);
				}
				else
				{					JOptionPane.showMessageDialog((Component)e.getSource(),"order not found"
						,"warning",JOptionPane.WARNING_MESSAGE);
				
					
				}
			}});
			
			
			JButton btnNewButton_1 = new JButton("View Previous Confirmed and Fullfilled Orders");
			
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel.setVisible(false);
					jp.removeAll();
					CustomerViewOrders cVO = new CustomerViewOrders(cus,"PREVIOUS",jp);
					jp.add(cVO);
					cVO.setVisible(true);
				}
			});
			
			panel_2.add(btnNewButton_1);
			
		}else {
			text.setVisible(false);
			JButton btnNewButton_1 = new JButton("View Pending Orders");
			
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel.setVisible(false);
					jp.removeAll();
					CustomerViewOrders cVO = new CustomerViewOrders(cus,"PENDING",jp);
					jp.add(cVO);
					cVO.setVisible(true);
					
					
				}
			});
			panel_2.add(btnNewButton_1);
			
		}
		
		

	}

}
