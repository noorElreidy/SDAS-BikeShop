package users;
/**
 * This is a class used to represent the customers address.
 * It contains the variables houseNumber, postcose, streetName and cityName
 * It contains methods that run sql queries .
 */

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import database.DBDriver;

public class Address {
	

	

	private String houseNumber;
	private String postcode ;
	private String streetName ;
	private String cityName;
	
	
	/**
	 * Constructor for address
	 * @param houseNumber
	 * @param streetName
	 * @param postcode
	 * @param cityName
	 */
	public Address(String houseNumber, String streetName,String postcode,  String cityName) {
		this.houseNumber = houseNumber.toUpperCase() ;
		this.postcode = postcode.toUpperCase() ;
		this.streetName = streetName.toUpperCase() ; 
		this.cityName = cityName.toUpperCase() ; 
	}
	
	//returns entire address over 3 lines as a string 
	public String getAddress() {
		return houseNumber + " " + streetName + "\n" + postcode + "\n" + cityName ; 
		 
	}
	
	/**
	 * allows editing details
	 * @param houseNumber
	 * @param postcode
	 * @param streetName
	 * @param cityName
	 */
	public void  setAddress(String houseNumber, String postcode, String streetName, String cityName) {
		this.houseNumber = houseNumber.toUpperCase() ;
		this.postcode = postcode.toUpperCase() ;
		this.streetName = streetName.toUpperCase() ; 
		this.cityName = cityName.toUpperCase() ; 
	}
	
	
	public String getHouseNumber () {
		return houseNumber;
	}
	
	public String getPostcode () {
		return postcode ; 
	}
	
	public String getStreetName () {
		return streetName ;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Address copy() {
		return new Address(this.houseNumber, this.streetName, this.postcode, this.cityName);
	}
	
	
	/**
	 * Method to get customers address from database using ID
	 * @param id - customers id 
	 * @return an address object 
	 */
	public static Address getAddressFromID(int id) {
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select address_postcode, address_house_no from customer_address " +
				"where customer_id=?");
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
			    PreparedStatement stAdd = (PreparedStatement) conn.prepareStatement("Select * from address "
			    	+ "where postcode=? and house_no=?");
			    stAdd.setString(1, rs.getString(1));
			    stAdd.setString(2, rs.getString(2));
			    ResultSet rsAdd = stAdd.executeQuery();
			    if (rsAdd.next()) {
			    	return new Address(rsAdd.getString(1), rsAdd.getString(2), rsAdd.getString(3), rsAdd.getString(4));
			    }
			}
            st.close();
			conn.close();
        
        }catch(SQLException ex) {
	       ex.printStackTrace();
        }
		
		return null;
		
	}
	
	/**
	 * Method to get full address using house number and postcode from DB 
	 * @param houseNum
	 * @param postCode
	 * @return address object (customers full address)
	 */
	public static Address getAddressByPK(String houseNum, String postCode) {
        Address add = null;
        try {
            Connection conn = DBDriver.getConn();
            PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from address"
                    + " where postcode=? and house_no=?"); 
            st.setString(1,postCode);
            st.setString(2,houseNum);
            ResultSet rs = st.executeQuery();


            if (rs.next()) {
                 add = new Address(rs.getString(2),rs.getString(3),rs.getString(1),rs.getString(4));

            }

            conn.close();

        }catch(SQLException ex) {
             ex.printStackTrace();
        }

        return add;
    }
	
	
	/**
	 * gets a list of all addresses in database 
	 * @returns Arraylist of Address objects
	 */
	public static ArrayList<Address> getAddressList(){
		ArrayList<Address> addresses = new ArrayList<Address>();
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from address"); 
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) {
			  //order of columns in data base is postcode , house no, steet and then city. reordering database
			  // too complicated . just weird getString(col) order 
			  Address address = new Address(rs.getString(2),rs.getString(3), rs.getString(1), rs.getString(4));
			  addresses.add(address);
			}
            
			conn.close();
        
        }catch(SQLException ex) {
	       ex.printStackTrace();
	       JFrame frame = new JFrame("J");
	       
	       // show a joptionpane dialog using showMessageDialog
	       JOptionPane.showMessageDialog(frame,"Database connection problem");
	     }
		return addresses;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(cityName, other.cityName) && Objects.equals(houseNumber, other.houseNumber)
				&& Objects.equals(postcode, other.postcode) && Objects.equals(streetName, other.streetName);
	}
	
	
    
}