package users;

/**
 * Customer class used to represent customer . 
 * It contains methods that run sql queries. 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import database.DBDriver;
import components.Bike;
import components.Order;

public class Customer {
	
	private int customerID ;
	private String firstName ;
	private String lastName;
	private Address address ;
	
	
	/**
	 * Customer constructor without address object 
	 * @param customerID
	 * @param firstName
	 * @param lastName
	 * @param HouseNumber
	 * @param postcode
	 * @param streetName
	 * @param cityName
	 */
	public Customer(int customerID, String firstName, String lastName,
			String HouseNumber, String postcode, String streetName, String cityName ) {
		this.customerID = customerID ;
		this.firstName = firstName.toUpperCase() ;
		this.lastName = lastName.toUpperCase() ;
		this.address = new Address(HouseNumber,streetName, postcode,cityName);
		
	}
	
	/**
	 * customer constructor using an address object and an ID . used to get 
	 * customers from Database
	 * @param customerID
	 * @param firstName
	 * @param lastName
	 * @param address
	 */
	public Customer(int customerID, String firstName, String lastName,
			Address address) {
		this.customerID = customerID ;
		this.firstName = firstName.toUpperCase() ;
		this.lastName = lastName.toUpperCase() ;
		if (address != null)
		    this.address = address.copy();
		else 
			this.address = null ; 
		
	}
	
	/**
	 * Constructor for logging in and signing up. 
	 * Customer ID not known at that point yet so no customerID. 
	 * Customer ID set later
	 * @param firstName
	 * @param lastName
	 * @param address
	 */
	public Customer(String firstName, String lastName, Address address) {
		this.customerID = -1 ;
		this.firstName = firstName.toUpperCase() ;
		this.lastName = lastName.toUpperCase() ;
		if (address != null)
		    this.address = address.copy();
		else 
			this.address = null ; 
		
	}
	
	
	
	public int getCustomerID () {
		return customerID ;
	}
	
	public String getFirstName(){
		return firstName ;
		
	}
    
	public String getLastName() {
		return lastName ;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(String houseNumber, String postcode, String streetName, String cityName) {
		address.setAddress(houseNumber, postcode, streetName, cityName);
		
	}

	
	/**
	 * Gets List of customers from database
	 * @return an ArrayList of customer objects
	 */
	public static ArrayList<Customer> getCustomerList(){
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from customer"); 
			ResultSet rs = st.executeQuery();
				
				
			while (rs.next()) {
				 Address address = Address.getAddressFromID(rs.getInt(1));
				 Customer cus = new Customer(rs.getInt(1),rs.getString(2).toUpperCase(), rs.getString(3).toUpperCase(), address); 
				 customers.add(cus);
			}
	            
			conn.close();
	        
	    }catch(SQLException ex) {
		     ex.printStackTrace();
	    }
			
		return customers;
			
	 }
	
	
	/**
	 * gets customer from database using an ID
	 * @param id
	 * @return a customer object
	 */
	public static Customer getCustomerByID(int id) {
		Customer customer = null ;
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from customer "
					+ "where id_customer= ?"); 
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
				
				
			if (rs.next()) {
				 Address address = Address.getAddressFromID(id);
				 customer = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), address); 
				 
			}
	            
			conn.close();
	        
	    }catch(SQLException ex) {
		     ex.printStackTrace();
	    }
			
		return customer;
		
	}
	
	/**
	 * @return int last ID in database , as in last customer added
	 */
	public static int getLastID() {
		
		try {
			Connection conn = DBDriver.getConn();
			
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select id_customer from customer " +
				"Order By id_customer Desc");
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				 int id = rs.getInt(1);
				 conn.close();
				 return id;
			}
            st.close();
			conn.close();
        
        }catch(SQLException ex) {
	       ex.printStackTrace();
        }
		
		return -1;
		
	}
	
	
	/**
	 * checks if customer details match those in Database 
	 * @return true if customer details correct, false otherwise 
	 */
	public boolean customerLogin() {
		try {
			
			 if (address == null ) {
				 return false ; 
			 }
			 Connection conn = DBDriver.getConn();
			 
			 PreparedStatement stCus = (PreparedStatement) conn.prepareStatement("Select id_customer,first_name,last_name from"
			 		+ " customer where first_name=? and last_name=?");
			 stCus.setString(1, firstName.toUpperCase());
             stCus.setString(2, lastName.toUpperCase());
             
             ResultSet rsCus = stCus.executeQuery();
             
             
             
             PreparedStatement stAdd = (PreparedStatement) conn.prepareStatement("Select * from"
  			 		+ " customer_address where address_postcode=? and address_house_no=?");
              
              stAdd.setString(1, address.getPostcode());
              stAdd.setString(2, address.getHouseNumber());
              ResultSet rsAdd = stAdd.executeQuery(); 
             
             while (rsCus.next() && rsAdd.next()) {
            	 if (rsCus.getInt(1) == rsAdd.getInt(1)) {
            		 this.customerID = rsCus.getInt(1);
            		 conn.close() ; 
            		 return true ; 
            		 
            	 }
            	 
            	 
             }
             conn.close(); 
             return false;

		} catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		return false; 
	}
	
	
	/**
	 * Adds customer to DB 
	 * @return true if customer successfully added, false if they already exist 
	 */
	public boolean newCustomer() {	
		try {
			Connection conn = DBDriver.getConn();
			
			//check if customer exists already 
			if (this.customerLogin()) {
				conn.close();
				return false ;
			}else {
				int id ;
				
				PreparedStatement stCus = (PreparedStatement) conn.prepareStatement("insert into customer " +
				  "(first_name, last_name) values(?,?)");
				stCus.setString(1, firstName );
				stCus.setString(2, lastName);
				stCus.executeUpdate();
				stCus.close();
				
				//gets if address already in database. This is for when 2 customers live in same house.
				PreparedStatement stAdd = (PreparedStatement) conn.prepareStatement("Select * from address "
						+ "where postcode=? and house_no=? and street_name=? and city=?");
				stAdd.setString(1, address.getPostcode());
				stAdd.setString(2, address.getHouseNumber());
				stAdd.setString(3, address.getStreetName());
				stAdd.setString(4, address.getCityName());
				ResultSet rs = stAdd.executeQuery();
				
				//only adds address if not already in db 
				if (!(rs.next())) {
					stAdd = (PreparedStatement) conn.prepareStatement("insert into address " +
							"(postcode, house_no, street_name,city) values(?,?,?,?)");
						stAdd.setString(1, address.getPostcode());
						stAdd.setString(2, address.getHouseNumber());
						stAdd.setString(3, address.getStreetName());
						stAdd.setString(4, address.getCityName());
						stAdd.executeUpdate();
						stAdd.close();
				}
				

				
				PreparedStatement stCusAdd = (PreparedStatement) conn.prepareStatement("insert into customer_address " +
					"(customer_id, address_postcode, address_house_no) values(?,?,?)");
				stCusAdd.setInt(1, getLastID());
				stCusAdd.setString(2, address.getPostcode() );
				stCusAdd.setString(3, address.getHouseNumber());
				stCusAdd.executeUpdate();
				stCusAdd.close();
				
				this.customerID = getLastID() ;
				
				conn.close();
				return true;
			}
			
		}catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		this.customerID = -1 ;
		return false ;
	}
	
    /**
     * edits customers details in DB 
     * @param firstNameEdit
     * @param lastNameEdit
     * @param addressEdit
     * @return true if successfully editted, otherwise false 
     */
    public boolean editCustomer(String firstNameEdit, String lastNameEdit, Address addressEdit ) {
		try {
			Connection conn = DBDriver.getConn();
			
			if (!(firstNameEdit.equals(this.firstName))) {
				this.firstName = (firstNameEdit);
				DBDriver.updateCondInt("customer", "first_name", firstNameEdit , "id_customer", this.customerID);
			}
			
			if (!(lastNameEdit.equals(this.lastName))) {
				this.lastName = (lastNameEdit);
				DBDriver.updateCondInt("customer", "last_name", lastName , "id_customer", this.customerID);			
			}
			
			System.out.println(!(addressEdit.equals(this.address)));
			
			if (!(addressEdit.equals(this.address))) {
				
				//gets if address alrready in database. This is for when 2 customers live in same house.
				PreparedStatement stAdd = (PreparedStatement) conn.prepareStatement("Select * from address "
						+ "where postcode=? and house_no=? and street_name=? and city=?");
				stAdd.setString(1, addressEdit.getPostcode());
				stAdd.setString(2, addressEdit.getHouseNumber());
				stAdd.setString(3, addressEdit.getStreetName());
				stAdd.setString(4, addressEdit.getCityName());
				ResultSet rs = stAdd.executeQuery();
				
				//only adds address if not already in db 
				if (!(rs.next())) {
					stAdd = (PreparedStatement) conn.prepareStatement("insert into address " +
							"(postcode, house_no, street_name,city) values(?,?,?,?)");
						stAdd.setString(1, addressEdit.getPostcode());
						stAdd.setString(2, addressEdit.getHouseNumber());
						stAdd.setString(3, addressEdit.getStreetName());
						stAdd.setString(4, addressEdit.getCityName());
						stAdd.executeUpdate();
						stAdd.close();
				}

				this.address = addressEdit.copy();

				PreparedStatement stCusAdd = (PreparedStatement) conn.prepareStatement("delete from customer_address where "+
						"customer_id=?");
				stCusAdd.setInt(1, customerID);
				stCusAdd.executeUpdate();
				
				
				
			    stCusAdd = (PreparedStatement) conn.prepareStatement("insert into customer_address " +
					"(customer_id, address_postcode, address_house_no) values(?,?,?)");
				stCusAdd.setInt(1, this.customerID);
				stCusAdd.setString(2, address.getPostcode() );
				stCusAdd.setString(3, address.getHouseNumber());
				stCusAdd.executeUpdate();
				stCusAdd.close();

				
			}
			
			
			
			conn.close();
			
			return true;
			
			
		
		
		}catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		
		return false ;	
     }
	
	
	/**
	 * gets customers orders according to status 
	 * empty string returns all and PREVIOUS returns confirmed and fulfilled 
	 * singular statuses return that staus 
	 * @param status
	 * @return arraylist of customers orders 
	 */
	public ArrayList<Order> getCustomerOrders(String status) {
		ArrayList<Order> orders = new ArrayList<Order>() ; 
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st;
			
			st = (PreparedStatement) conn.prepareStatement("Select * from orders " +
				      "where customer_id=? and status=?");
			
			
			if (status.equals("PENDING")) {
				st.setString(2, "PENDING");
			}
			else if (status.equals("CONFIRMED")) {
				st.setString(2, "CONFIRMED");
				
			}else if (status.equals("FULFILLED")){
				st.setString(2, "FULFILLED");

			}else if(status.equals("PREVIOUS")) {
				st = (PreparedStatement) conn.prepareStatement("Select * from orders " +
					      "where customer_id=? and (status=? OR status=?) ");
				st.setString(2, "FULFILLED");
				st.setString(3, "CONFIRMED");
				
			}else {
				st = (PreparedStatement) conn.prepareStatement("Select * from orders " +
					      "where customer_id=? ");
			}
			
			st.setInt(1, this.customerID);
			
		
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Order order = new Order(rs.getString(1), rs.getString(2), this , Bike.getBikeBySerialNo(rs.getInt(3)),
					Staff.getStaffByUserName(rs.getString(6)),rs.getString(4), rs.getDouble(5), DBDriver.toBoolean(rs.getInt(8)));
				orders.add(order);
			}
            st.close();
			conn.close();
        
        }catch(SQLException ex) {
	       ex.printStackTrace();
        }
		
		return orders;
	}

}