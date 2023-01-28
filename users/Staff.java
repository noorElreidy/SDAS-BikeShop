package users;

/**
 * staff class used to represent staff
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import components.Bike;
import components.Frameset;
import components.Handlebars;
import components.Wheels;
import database.DBDriver;

public class Staff {
	
	private String username;
	private String password;
	
	/**
	 * staff constructor 
	 * @param username
	 * @param password
	 */
	public Staff(String username, String password) {
		this.username = username;
		this.password = password ; 
	}

	
	
	public String getUserName(){
		return username ;
	}
	
	public String getPassword(){
		return password ;
	}
	
	/**
	 * method that allows staff to login 
	 * @return true if staff details match, false otherwise 
	 */
	public boolean staffLogin() { 
		try {
			 Connection conn = DBDriver.getConn();
			 PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select username_staff, password_staff from"
			 		+ " staff where username_staff=? and password_staff=?");
			 st.setString(1, this.username);
             st.setString(2, this.password);
             ResultSet rs = st.executeQuery();
             if (rs.next()) {
            	 conn.close(); 
            	 return true;
             } else {
            	 conn.close(); 
            	 return false ; 
             }
			
		} catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		return false; 
	}
	
	/**
	 * gets staff object using staff username 
	 * @param staffUsername
	 * @return staff object 
	 */
	public static Staff getStaffByUserName(String staffUsername) {
		Staff staff = null;
		
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from staff where username_staff=?"); 
			st.setString(1, staffUsername);
			ResultSet rs = st.executeQuery();
	
			if (rs.next()) {
				staff = new Staff(rs.getString(1), rs.getString(2));
			  
			}
	            
			conn.close();
	        
	        }catch(SQLException ex) {
		       ex.printStackTrace();
	        }
		
		    
			return staff;
		
	}
    
}
