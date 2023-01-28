package guiWorks;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import components.Order;

public class pendingOrders extends JPanel {

	private int numberOfOrders;

	private String[][] orders_array_2d;
	private ArrayList<Order> orders_array_list;
	
	
	/**
	 * Create the panel.
	 */
	public pendingOrders() {

			JPanel panel3 = new JPanel();
			add(panel3);

			panel3.setLayout(new BorderLayout(0, 0));
			JScrollPane scrollPane = new JScrollPane();
			
			panel3.add(scrollPane,BorderLayout.CENTER);
			DefaultTableModel tableModel = new DefaultTableModel();
			String col[]= {"serial_no_order","product_name","bike_serial_no","status","final_cost","staff_username","customer_id","collect_now"};
			
		    JTable table = new JTable(tableModel);
			
			
			//table_1 = new JTable();
			orders_array_list = Order.getOrdersList();
		    numberOfOrders = orders_array_list.size();	// stores number of order entries in variable for later use
		    orders_array_2d = Order.to2DArray(orders_array_list);
		    /*ArrayList<Order> orders=Order.getOrdersList();
		    DefaultTableModel model =  (DefaultTableModel)table_1.getModel();
		    Object[] orders2D=new Object[8];
		    for(int i=0; i<orders.size();i++)
		    {
				orders2D[0] = String.valueOf(orders.get(i).getCustomerID());
				orders2D[1] = orders.get(i).getSerialNo();
				orders2D[2] = orders.get(i).getProductName();
				orders2D[3] = String.valueOf(orders.get(i).getBike().getSerialNo());
				orders2D[4] = orders.get(i).statusString();
				orders2D[5] = String.valueOf(orders.get(i).getFinalCost());
				orders2D[6] = orders.get(i).getStaffUsername() ; 
				orders2D[7] = orders.get(i).collect();
				model.addRow(orders2D);
				
				
				
		    }
		    
		   
		    
		    sp.setViewportView(table_1);
		    
		    */
		   
		    for (int j = 0; j < 8; j++) {
		    	tableModel.addColumn(col[j]);		// creates 8 columns for order data
		    }
		    for (int i = 0; i < numberOfOrders; i ++) {		//creates correct number of rows for number of orders in db
		    	tableModel.insertRow(0, new Object[] {null, null, null, null, null, null, null, null});
		    }
			
		    
		    for (int i = 0; i < numberOfOrders; i ++) {		//iterates through orders table entering the values into the gui
				for (int j = 0; j < 8; j++) {
					table.setValueAt(orders_array_2d[i][j], i, j);
					//table.setValueAt(Order.to2DArray(Order.getOrdersList())[i][j], i, j);
					//System.out.println(Order.to2DArray(Order.getOrdersList())[i][j]);
					//System.out.println("test");
					
				}
				
			}
		    scrollPane.setViewportView(table);
			/*table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
				},
				new String[] {
					"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
				}
			));*/
			//table.setEditingColumn(0);
			//table.setEditingRow(0);
			//table.addRowSelectionInterval(0, 2);
			


		}

	}


