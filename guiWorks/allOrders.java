package guiWorks;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import components.Order;
import users.Staff;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class allOrders extends JPanel {
	private JTable table;
	
	
	//private JTable table_1;
	private int numberOfOrders;
	private String[][] orders_array_2d;
	private ArrayList<Order> orders_array_list;
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public allOrders(int allOrPending,Staff staff,JPanel jp) {
		DefaultTableModel tableModel = new DefaultTableModel();

		String col[]= {"bike_serial_no","serial_no_order","product_name","customer_id","status","final_cost","staff_username","collect_now"};//creating a Jtable with the corresponding column titles
		JPanel panel3 = new JPanel();
		add(panel3);
	    textField = new JTextField();//creating the entry box for the status change option to capture the serial no.
	    textField.setColumns(10);					

	    JTable table_1 = new JTable(tableModel);
	    table_1.addMouseListener(new MouseAdapter() {//adding a mouse listener to read the serial no of the selected row
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	            int row = table_1.rowAtPoint( e.getPoint() );
	            String s=table_1.getModel().getValueAt(row,1)+"";
	            
	            textField.setText(s);
	    	}
	    });    	  
	    JScrollPane pane = new JScrollPane(table_1);
	    pane.setPreferredSize(new Dimension(800, 400));
	    panel3.setLayout(new GridLayout(1,6,1,1));
	    panel3.add(pane, BorderLayout.CENTER);    
	    String statusOp[]={"PENDING","CONFIRMED","FULLFILLED"};//creating a combo box with the status options         
	    JComboBox comboBox=new JComboBox(statusOp);        
	    JButton btnNewButton = new JButton("update order's status");//creating button to allow for the change in status to occur 
	    JLabel label=new JLabel("welcome to a table of all orders");
	    JButton btnNewButton_1 = new JButton("return to staff menu");//creating button to return the staff back to the staff menu
	    btnNewButton_1.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
			panel3.setVisible(false);
			removeAll();
			StaffMenu sm = new StaffMenu(staff,jp); 
			add(sm);
			sm.setVisible(true);

	    	}
	    });
	    JButton btnNewButton_2 = new JButton("refresh table to view new entry");//button to allow the entire panel to be refreshed by fetching results from the database
	    btnNewButton_2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
			panel3.setVisible(false);
			removeAll();
			allOrders ao = new allOrders(allOrPending,staff,jp); 
			//CustomerOptions m = new CustomerOptions() ; 
			add(ao);
			ao.setVisible(true);

	    	}
	    });


	    btnNewButton.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 boolean statusCorrect = false;
	    		 String staffSerialNo=textField.getText();
	    		 String staffStatus = (String)comboBox.getSelectedItem();//retrieving the selected status 
	    		 Order order=Order.getOrderBySerialNo(staffSerialNo);//Retrieving the order current status 
					//JOptionPane.showMessageDialog((Component)e.getSource(), "welcome employee select any of the orders displayed in the table to change it's status",
				      //      "incorrect status change", JOptionPane.INFORMATION_MESSAGE);
	    		if  (order!=null)
	    		{
		    		 String orderStatus=(order).statusString();
		    		 System.out.println("good job"+orderStatus);
		             if(((orderStatus=="CONFIRMED"))&&(staffStatus=="FULLFILLED"))//checking if the order is changing from confirmed to FULLFILLED
		             {
		            	 order.fulfillOrder();//conditon true will allow for the order to be processed as FULLFILLED
		            	 tableModel.removeRow(table_1.getSelectedRow());
						JOptionPane.showMessageDialog((Component)e.getSource(), "order: "+order.getSerialNo()+" succesfully updated from "+orderStatus+" to "+staffStatus,
						"status updated", JOptionPane.INFORMATION_MESSAGE);
	
		             }
		             else if(((orderStatus=="PENDING")&&(staffStatus=="CONFIRMED")))
		             {
		       
		            	 order.confirmOrder(staff);	
		            	 tableModel.removeRow(table_1.getSelectedRow());
							JOptionPane.showMessageDialog((Component)e.getSource(), "order: "+order.getSerialNo()+" succesfully updated from "+orderStatus+" to "+staffStatus,
						            "status updated", JOptionPane.INFORMATION_MESSAGE);
		            	 
		             }
		             else {
							JOptionPane.showMessageDialog((Component)e.getSource(), " order :"+order.getSerialNo()+" cannot be moved from "+orderStatus+" to "+staffStatus,
				            "incorrect status change", JOptionPane.ERROR_MESSAGE);
		             		}		             
		             }
	    		else
	    		{
	    			JOptionPane.showMessageDialog((Component)e.getSource(), "empty sorry ",
		                    "incorrect status change", JOptionPane.ERROR_MESSAGE);	
	    		}

	    		
				panel3.setVisible(false);
				jp.removeAll();
				allOrders ao = new allOrders(allOrPending,staff,jp); 
				//CustomerOptions m = new CustomerOptions() ; 
				jp.add(ao);
				ao.setVisible(true);

	    	}
	    });		
	    if(allOrPending==0) {
	    	orders_array_list = Order.getOrdersList();
	    	add(label);
	    }
	    else {
	    	orders_array_list = Order.getPendingOrdersList();
	    	add(textField);
	    	add(comboBox);
	    	add(btnNewButton);
	    }
	    add(btnNewButton_1);
	    add(btnNewButton_2);
	    numberOfOrders = orders_array_list.size();	// stores number of order entries in variable for later use
	    orders_array_2d = Order.to2DArray(orders_array_list);


	    for (int j = 0; j < 8; j++) {
	    	tableModel.addColumn(col[j]);		// creates 8 columns for order data
	    }
	    for (int i = 0; i < numberOfOrders; i ++) {		//creates correct number of rows for number of orders in db
	    	tableModel.insertRow(0, new Object[] {null, null, null, null, null, null, null, null});
	    }
	    for (int i = 0; i < numberOfOrders; i ++) {		//iterates through orders table entering the values into the gui
	    	for (int j = 0; j < 8; j++) {
				table_1.setValueAt(orders_array_2d[i][j], i, j);
			}
		}


	}
}
