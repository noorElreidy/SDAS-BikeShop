package database;
/**
 * DBDriver.java 
 * used to access database and create a connection in all other classes 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import components.Bike;
import components.Component;
import components.Frameset;
import components.Handlebars;
import components.Order;
import components.Wheels;
import users.Address;
import users.Customer;
import users.Staff; 

public class DBDriver {
	
	static String URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/";
	static String DBNAME = "team011";
	static String USER = "team011";
	static String PASSWORD = "c6b485a0";
	
	
	/**
	 * used to connect to DB. Used by all other classes
	 * @return a connection
	 */
	public static Connection getConn() { 
		Connection conn = null ; 
		try {
			 conn = DriverManager.getConnection(URL+DBNAME,USER,PASSWORD);
			
		} catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		return conn; 
	}
	 
	
	
    /**
     * convert DB's tinyInt to Boolean 
     * @param bool
     * @return boolean 
     */
	public static boolean toBoolean(int bool) {
		if (bool ==1 )
			return true; 
		else 
			return false ; 
	}
	
	/**
	 * converts boolean to TinyInt to insert into DB
	 * inverse of toBoolean
	 * @param bool
	 * @return 0 or 1 for false or true respectively 
	 */
	public static int toTinyInt(boolean bool) {
		if (bool)
			return 1 ; 
		else 
			return 0 ; 
	}
	

	
	/**
	 * given a table name , a value and column to update and a condition 
	 * column and condiction value , where all columns are strings, updates table 
	 * @param table
	 * @param updateColumn
	 * @param value
	 * @param conditionCol
	 * @param condition
	 */
	public static void update(String table, String updateColumn, String value, 
			String conditionCol, String condition ) {
		try {
			Connection conn = DriverManager.getConnection(URL+DBNAME,USER,PASSWORD);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("update " + table + " set " + 
			updateColumn + " = ?  where " + conditionCol + " = ? " );
			st.setString(1, value);
			st.setString(2, condition);
			st.executeUpdate();
			st.close();
			conn.close();
			
		}catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		
	}
	
	
	/**
	 * given a table name , a value and column to update and a condition 
	 * column and condiction value , where updated is column is string and 
	 * condition column is int , updates row  
	 * @param table
	 * @param updateColumn
	 * @param value
	 * @param conditionCol
	 * @param condition
	 */
	public static void updateCondInt(String table, String updateColumn, String value, 
			String conditionCol, int condition ) {
		try {
			Connection conn = DriverManager.getConnection(URL+DBNAME,USER,PASSWORD);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("update " + table + " set " + 
			updateColumn + " = ?  where " + conditionCol + " = ? " );
			st.setString(1, value);
			st.setInt(2, condition);
			st.executeUpdate();
			st.close();
			conn.close();
			
		}catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		
	}
	
	
	/**
	 * given a table name , a value and column to update and a condition 
	 * column and condiction value , where updated is column is int and 
	 * condition column is String , updates row 
	 * @param table
	 * @param updateColumn
	 * @param value
	 * @param conditionCol
	 * @param condition
	 */
	public static void updateInt(String table, String updateColumn, int value, 
			String conditionCol, String condition ) {
		try {
			Connection conn = DriverManager.getConnection(URL+DBNAME,USER,PASSWORD);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("update " + table + " set " + 
			updateColumn + " = ?  where " + conditionCol + " = ? " );
			st.setInt(1, value);
			st.setString(2, condition);
			st.executeUpdate();
			st.close();
			conn.close();
			
		}catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		
	}
	
    /**
     * selects an entire column from a given table
     * @param table
     * @param columnName
     * @returns table's column as an ArrayList 
     */
	public static ArrayList<String> selectALLColumn(String table, String columnName){
		ArrayList<String> col = new ArrayList<String>();
		try {
			Connection conn = DriverManager.getConnection(URL+DBNAME,USER,PASSWORD);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select " + columnName + " from " + table); 
			ResultSet rs = st.executeQuery();
			
			int i = 1 ;
			while (rs.next()) {
			  col.add(rs.getString(i));
			}
            
			conn.close();
        
        }catch(SQLException ex) {
	       ex.printStackTrace();
        }
		
		return col;
	}
	
	
	
	
			
	public static void main(String[] args) throws SQLException {
		
		 System.out.println("Connecting...");
		 
		 /* ----- EVERYTHING BELOW WAS USED FOR MANUAL TESTING  ----  */
		 
		 
		 /*
		 ArrayList<Customer> cus = Customer.getCustomerList() ;
		 ArrayList<Handlebars> h = Handlebars.getHandlebarsList();
		 ArrayList<Frameset> f = Frameset.getFramesetList();
		 ArrayList<Wheels> w = Wheels.getWheelList();
		 Customer cus1 = cus.get(0);
		 Bike bk = new Bike(f.get(2), h.get(5), w.get(0));
		 Order ord = new Order(cus1,bk,"tester",false);
		  System.out.println( ord.placeOrder());
		 */
		 
		 /*
		 ArrayList<Handlebars> h = Handlebars.getHandlebarsList();
		 ArrayList<Frameset> f = Frameset.getFramesetList();
		 ArrayList<Wheels> w = Wheels.getWheelList();
		 
		 ArrayList<Customer> c = Customer.getCustomerList();
		 
		 c.get(1).editCustomer("LEBRON", "JAMES", new Address("7","WELLINGTON ST", "S14HL", "SHEFFIELD"));
		 */
		// System.out.println(Order.getOrderBySerialNo("0001-2022-11-30").getCustomerID());
		 //Handlebars hd = new Handlebars("8035923edf", "BRND3", "PRODHAND31", 18.5, "DROPPED", 16);
		 //System.out.println( hd.insertHandleBar());
		 
		 /**
		 ArrayList<Order> o = Order.getOrdersList();
		
		 o.get(0).setStaff(s.getUserName());
		 **/ 
		 /**
		  
		 Staff s = Staff.getStaffByUserName("staff1");
		 Bike bk = new Bike (f.get(2), h.get(2), w.get(2)); 
		 Order o = Order.getOrderBySerialNo("0001-2022-11-26");
		 Order or = new Order(cus.get(3) , bk, "Wednesday", s, true);
		 or.placeOrder();
		 **/
		 
		 /**
		 Bike bk = new Bike (f.get(0), h.get(2), w.get(0));
		 System.out.println(bk.insertBike());
		 **/ 
		 /**
		 h.get(0).incStock(10);;
		 
		 System.out.println(Frameset.getFramesetByPK(f.get(0).getSerialNo(), f.get(0).getBrandName(), f).getProductName());
		 **/ 
		 // System.out.println(Order.getLastID());
		 /*
		 Bike bk  ;
		 bk = Bike.getBikeBySerialNo(1);
		 System.out.println(bk.getBrandName());
		 */
		 /*
		 ArrayList<Bike> bk = new ArrayList<Bike>();
		 bk = Bike.getBikeList();
		 bk.get(0).setBrandName("lol");
		 System.out.println(bk.get(0).exists());
		 */
		 /* --PRINTS OUT CUSTOMER FIRST NAMES 
		 ArrayList<Customer> cus = new ArrayList<Customer>() ; 
		 cus = getCustomerList() ; 
		 
		 for (Customer c : cus) {
			 System.out.println(c.getFirstName());
		 }
		 */ 
		 
		  //--RETURNS TRUE
		 //System.out.println(customerLogin("AMAYA", "MORIS", new Address("32", "MARSHALL LANE", "n98pe", "nottingham")));
		 
		 /* --PRINTS OUT addresses
		 ArrayList<Address> add = new ArrayList<Address>() ; 
		 add = getAddressList() ; 
		 
		 for (Address a : add) {
			 System.out.println(a.getAddress());
		 }
		 */ 
	
		
	}
	
}
