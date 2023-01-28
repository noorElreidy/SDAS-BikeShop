package components;

/**
 * Order.java used to represent an order 
 */

import users.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import database.DBDriver;
import users.Staff;

public class Order {
	
	private enum Status { PENDING, CONFIRMED, FULFILLED };
	private String serialNo ;
	private String productName ; 
	private Customer customer ; //not sure whether to put customer object or just customerID . using object for now
	private Bike bike ;
	private Staff staff ; //not sure whether to put staff object or just staffID . using object for now
	private Status status;
	private double finalCost ;
	private boolean collectNow ;
	
	final static double assemblyCost = 10.0 ;
	public final static int columns = 8 ; 

	/**
	 * constructor used to initiate an order when done by a customer. 
	 * @param customer
	 * @param bike
	 * @param productName
	 * @param collectNow
	 */
	public Order( Customer customer , Bike bike, String productName,
	    boolean collectNow) {
		this.serialNo = serialNoGen() ; 
		this.productName = productName ; 
		this.customer = customer;
		this.bike = bike ;
		this.staff = null; 
		this.status = Status.PENDING;
		this.finalCost = bike.getCost() + assemblyCost;
		this.collectNow = collectNow;
	} 	
	
	/**
	 * constructor used to initiate an order when done by  staff  
	 * @param customer
	 * @param bike
	 * @param productName
	 * @param collectNow
	 */
	public Order(Customer customer,Bike bike,String productName,
	    Staff staff , boolean collectNow) {
		this.serialNo = serialNoGen() ; 
		this.productName = productName ; 
		this.customer = customer;
		this.bike = bike ; 
		this.staff = staff; 
		this.status = Status.CONFIRMED;
		this.finalCost = bike.getCost() + assemblyCost;
		this.collectNow = collectNow;
	} 
	
	/**
	 * constructor for when getting order from database 
	 * @param serialNo
	 * @param productName
	 * @param customer
	 * @param bike
	 * @param staff
	 * @param status
	 * @param finalCost
	 * @param collectNow
	 */
	public Order(String serialNo, String productName, Customer customer, Bike bike, Staff staff, String status,
			double finalCost, boolean collectNow) {
		this.serialNo = serialNo;
		this.productName = productName;
		this.customer = customer;
		this.bike = bike;
		this.staff = staff;
		this.status = statusFromString(status);
		this.finalCost = finalCost;
		this.collectNow = collectNow;
	}

	public Bike getBike() {
		return bike;
	}
	
	public void setCustomer(Customer cus) {
		this.customer = cus ; 
	}

	public String getProductName() {
		return productName ; 
	}
	
	public Customer getCustomer() {
		return customer ; 
	}

	public String getStaffUsername() {
		return staff.getUserName();
	}
	
	public Staff getStaff() {
		return staff ; 
	}
    
	/**
	 * used to set staff when order is confirmed 
	 * @param staff
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
		DBDriver.update("orders", "staff_username", staff.getUserName() , "serial_no_order", this.serialNo);
	}
	
	public void setBike(Bike bike) {
		this.bike = bike ; 
	}

	public String getSerialNo() {
		return serialNo;
	}

	public int getCustomerID() {
		return customer.getCustomerID();
	}

	public int getBikeSerialNo() {
		return bike.getSerialNo() ; 
	}

	public Status getStatus() {
		return status;
	}

	public double getFinalCost() {
		return finalCost;
	}

	public boolean isCollectNow() {
		return collectNow;
	}
	
	
	public String collect() {
		if (collectNow)
			return "NOW";
		else 
			return "LATER" ; 
	}
	
	
	public String statusString() {
		switch(this.status) {
		case  CONFIRMED : return "CONFIRMED" ;
		case PENDING : return "PENDING" ; 
		default  : return "FULLFILLED" ;
		
		}
	}
	
	/**
	 *used to set staff by username 
	 * @param username
	 */
	public void setStaff(String username) {
		this.staff = Staff.getStaffByUserName(username); 
		DBDriver.update("orders", "staff_username", username , "serial_no_order", this.serialNo);
		
	}

	/**
	 * confirms order in the databse 
	 * @param staff
	 */
	public void confirmOrder(Staff staff) {
		this.staff=staff;
		DBDriver.update("orders", "staff_username",staff.getUserName(),"serial_no_order", this.serialNo);
		this.status = Status.CONFIRMED ;
		DBDriver.update("orders", "status", "CONFIRMED", "serial_no_order", this.serialNo);
	}
	
	/**
	 * fulfills order in the database and decreases stock of each component used by order
	 */
	public void fulfillOrder() {
		this.status = Status.FULFILLED ; 
		DBDriver.update("orders", "status", "FULLFILLED", "serial_no_order", this.serialNo);
		this.bike.getFrameset().decStock();
		this.bike.getHandlebars().decStock();
		this.bike.getWheels().decStock();
	}
	
	//--TESTED WORKS 
	private Status statusFromString(String status) {
		if ( status.equals("PENDING")) {
			return Status.PENDING;
		}
		else if (status.equals("CONFIRMED")) {
			return Status.CONFIRMED ; 
			
		}
		else 
			return Status.FULFILLED ; 
	}
	
	/**
	 * gets id of last order added to the database  --DOESNT WORK DONT USE
	 * @returns a string (order id)
	 */
	public static String getLastID() {		
		try {
			Connection conn = DBDriver.getConn();
			
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select serial_no_order from orders " +
				"Order By serial_no_order Desc");
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				 conn.close();
				 return rs.getString(1);
			}
            st.close();
			conn.close();
        
        }catch(SQLException ex) {
	       ex.printStackTrace();
        }
		
		return "";	
	}
	
    /**
     * gets order list from database
     * @returns ArrayList of orders
     */
	public static ArrayList<Order> getOrdersList(){
		ArrayList<Order> orders = new ArrayList<Order>();
			
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from orders"); 
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) {
			  Order order = new Order(rs.getString(1), rs.getString(2), Customer.getCustomerByID(rs.getInt(7))
				  ,Bike.getBikeBySerialNo(rs.getInt(3)),Staff.getStaffByUserName(rs.getString(6)),
				  rs.getString(4), rs.getDouble(5), DBDriver.toBoolean(rs.getInt(8)));
			  
			  orders.add(order);
			}
          
			conn.close();
      
      }catch(SQLException ex) {
	       ex.printStackTrace();
      }
		
		
		return orders;
	}
	
	/**
	 * gets list of pending orders
	 * @return arrayList of pending orders
	 */
	public static ArrayList<Order> getPendingOrdersList(){
		ArrayList<Order> orders = new ArrayList<Order>();
			
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from orders WHERE (status='PENDING') OR (status ='CONFIRMED')"); 
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) {
			  Order order = new Order(rs.getString(1), rs.getString(2), Customer.getCustomerByID(rs.getInt(7)),Bike.getBikeBySerialNo(rs.getInt(3)),
					  
			  Staff.getStaffByUserName(rs.getString(6)), rs.getString(4), rs.getDouble(5), DBDriver.toBoolean(rs.getInt(8)));
			  
			  orders.add(order);
			}
          
			conn.close();
      
      }catch(SQLException ex) {
	       ex.printStackTrace();
      }

		return orders;
	}	
	
	/**
	 * generates a serialNo that contains the days date and the orders number in that day 
	 * @returns a string (serialNo for order)
	 */
	public String serialNoGen(){
        LocalDate date = LocalDate.now(); // Create a date object
       // String s = Order.getLastID() ; //get last id from database 
        ArrayList<Order> orders = getOrdersList() ; 
        int lastOrderNum = 1 ; 
        for (Order ord : orders) {
        	String s = ord.getSerialNo();
        	if (s.substring(5,15).equals(String.valueOf(date))) {
        		if (Integer.valueOf(s.substring(0, 4)) >= lastOrderNum ) {
        			lastOrderNum =  Integer.valueOf(s.substring(0, 4)) ;
        		}
        		
        	}
        }
        
        String lastOrder = String.valueOf(lastOrderNum + 1);

        //makes it a 4 character long strong 
        while (lastOrder.length() < 4 )   {
            lastOrder = "0" + lastOrder ; 
        }

        return (lastOrder + "-" + date);
    }
	
	/**
	 * returns an order given the serial No  
	 * @param serialNo
	 * @return order Object 
	 */
	public static Order getOrderBySerialNo(String serialNo )  {
		Order ord = null ; 
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from orders where "
					+ "serial_no_order=?"); 
			st.setString(1, serialNo);
			ResultSet rs = st.executeQuery();
			
			
			if (rs.next()) {
				//System.out.println("llll");
				ord = new Order(rs.getString(1), rs.getString(2), Customer.getCustomerByID(rs.getInt(7))
						  ,Bike.getBikeBySerialNo(rs.getInt(3)),Staff.getStaffByUserName(rs.getString(6)),
						  rs.getString(4), rs.getDouble(5), DBDriver.toBoolean(rs.getInt(8)));
				System.out.println(rs.getString(4));
				return ord ; 
				
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return ord ; 
		

	}
	
	/**
	 * inserts order into database 
	 * @returns true if successfully inserted, otherwise false 
	 */
	public boolean placeOrder() {
		try {
			Connection conn = DBDriver.getConn();
		     
			int bikeSerial = bike.insertBike();
			
			PreparedStatement st = null ; 
			
			
			
			if (staff==null) {
				st = (PreparedStatement) conn.prepareStatement("insert into orders " +
						"(serial_no_order, product_name, bike_serial_no, status, final_cost "
						+ ", customer_id, collect_now) values(?,?,?,?,?,?,?)");
				st.setInt(6, this.customer.getCustomerID());
				st.setInt(7, DBDriver.toTinyInt(collectNow));
				
			}
			else {
				st = (PreparedStatement) conn.prepareStatement("insert into orders " +
						"(serial_no_order, product_name, bike_serial_no, status, final_cost "
						+ ", staff_username ,customer_id, collect_now) values(?,?,?,?,?,?,?,?)");
				st.setString(6, this.staff.getUserName() );
				st.setInt(7, this.customer.getCustomerID());
				st.setInt(8, DBDriver.toTinyInt(collectNow));
				
			}
			
			
			st.setString(1, this.serialNo );
			st.setString(2, this.productName);
			st.setInt(3, bikeSerial);
			st.setString(4, this.statusString());
			st.setDouble(5, this.finalCost);
			
			
			st.executeUpdate();
			st.close();
			conn.close();
			return true ; 
			
			
		}catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		
		return false ;
	
	}
	
	/**
	 * deletes order 
	 * @return true if successfully deleted, otherwise false 
	 */
	public boolean deleteOrder() {
		try {
            Connection conn = DBDriver.getConn();
			
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("delete from orders where "+
					"serial_no_order=?");
			st.setString(1, this.serialNo);
			st.executeUpdate();
			conn.close() ;
			return true ; 
			
			
		}catch(SQLException ex) {
		       ex.printStackTrace();
	    }
		return false ; 
	}
	
	/**
	 * used to display orders in GUI 
	 * @param orders
	 * @return a 2D array of orders
	 */
	public static String[][] to2DArray(ArrayList<Order> orders){
		String[][] orders2D = new String[orders.size()][columns];
		
		for (int i = 0 ; i < orders.size(); i ++) {
			orders2D[i][0] = String.valueOf(orders.get(i).getCustomerID());
			orders2D[i][1] = orders.get(i).getSerialNo();
			orders2D[i][2] = orders.get(i).getProductName();
			orders2D[i][3] = String.valueOf(orders.get(i).getBike().getSerialNo());
			orders2D[i][4] = orders.get(i).statusString();
			orders2D[i][5] = String.valueOf(orders.get(i).getFinalCost());
			if (orders.get(i).getStaff() == null) {
				orders2D[i][6] = "N/A"; 

			}else {
				orders2D[i][6] = orders.get(i).getStaffUsername() ;
				
			}
			orders2D[i][7] = orders.get(i).collect();
		}
		
		return orders2D ; 
		
		
	}
	


	/**
	 * used to display orders in GUI 
	 * @param orders
	 * @return a 2D array of orders
	 */
	public static String[][] to2DArrayBikeProducts(ArrayList<Order> orders){
		String[][] orders2D = new String[orders.size()][columns+4];
		
		for (int i = 0 ; i < orders.size(); i ++) {
			orders2D[i][0] = String.valueOf(orders.get(i).getCustomerID());
			orders2D[i][1] = orders.get(i).getSerialNo();
			orders2D[i][2] = orders.get(i).getProductName();
			orders2D[i][3] = String.valueOf(orders.get(i).getBike().getSerialNo());
			orders2D[i][4] = orders.get(i).getBike().getFrameset().getProductName() + " by " + 
					orders.get(i).getBike().getFrameset().getBrandName();
			orders2D[i][5] = orders.get(i).getBike().getHandlebars().getProductName() + " by " + 
					orders.get(i).getBike().getHandlebars().getBrandName();
			orders2D[i][6] = orders.get(i).getBike().getWheels().getProductName() + " by " + 
					orders.get(i).getBike().getWheels().getBrandName();
			orders2D[i][7] = orders.get(i).statusString();
			orders2D[i][8] = String.valueOf(orders.get(i).getFinalCost());
			if (orders.get(i).getStaff() == null) {
				orders2D[i][9] = "N/A"; 

			}else {
				orders2D[i][9] = orders.get(i).getStaffUsername() ;
				
			}
			orders2D[i][10] = orders.get(i).collect();
		}
		return orders2D ; 
	}
}
 
	
    	
	
 

