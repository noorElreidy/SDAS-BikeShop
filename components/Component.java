package components;

/**
 * Superclass components extended by Frameset, Handlebars and Wheels. 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBDriver;

public class  Component {
	private String brandName;
	private String serialNo;
	private String productName;
	private double cost;
	private int stock;
	
	
	/**
	 * constructor with common fields among components 
	 * @param brandName
	 * @param serialNo
	 * @param productName
	 * @param cost
	 * @param stock
	 */
	public Component(String brandName, String serialNo, String productName, double cost, int stock) {
		this.brandName = brandName ;
		this.serialNo = serialNo ; 
		this.productName = productName ; 
		this.cost = cost ;
		this.stock = stock ; 
	}
	
	/**
	 * checks if item is in stock
	 * @return true if in stock,otherwise  false 
	 */
	public boolean inStock() {
		if ( stock > 0 ) 
			return true ;
		else 
			return false ;
	}
	
	/**
	 * depletes stock of component by 1 
	 */
	public void decStock() {
		stock -- ; 
		String table = "";
		if(this.getClass() == Frameset.class) {
			table = "frameset"; 
		}
		else if (this.getClass() == Handlebars.class) {
			table = "handlebars" ; 
		}
		else 
			table = "wheels";
		
		DBDriver.updateInt(table, "no_stock", stock , "serial_no", this.serialNo);
	}
	
	/**
	 * increases stock of component by given amount 
	 * @param amount
	 */
	public void incStock(int amount) {
		stock += amount ; 
		String table = "" ;
		if(this.getClass() == Frameset.class) {
			table = "frameset"; 
		}
		else if (this.getClass() == Handlebars.class) {
			table = "handlebars" ; 
		}
		else 
			table = "wheels";
		
		DBDriver.updateInt(table, "no_stock", stock , "serial_no", this.serialNo);
	}
	
	public double getCost() { 
		return cost;
	}
	
	
	public boolean equals(Object obj) {
		Component comp = (Component) obj;
		if (this.brandName.equals(comp.getBrandName()) && this.serialNo.equals(comp.getSerialNo())
		   && this.productName.equals(comp.getProductName()) && this.cost == comp.getCost()
		   && this.stock == comp.getStock())
		   return true ; 
	    else 
	    	return false ; 
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getProductName() {
		return productName;
	}

	public int getStock() {
		return stock;
	}
	
	/**
	 * for all components, checks if the components brand already 
	 * uses the serial number.
	 * @return true of brand used serial number before, otherwise false 
	 */
	public boolean pkExists() {
		try {
			Connection conn = DBDriver.getConn();
			
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from handlebars"
					+ " where (brand_name=? and serial_no=?)"); 
			st.setString(1,this.brandName);
			st.setString(2, this.serialNo);
			ResultSet rs = st.executeQuery();
				
			if (rs.next()) {
				conn.close();
				return true; 
			}
				
			st = (PreparedStatement) conn.prepareStatement("Select * from frameset"
					+ " where (brand_name=? and serial_no=?)"); 
			st.setString(1,this.brandName);
			st.setString(2, this.serialNo);
				rs = st.executeQuery();
				
			if (rs.next()) {
				conn.close();
				return true; 
			}
			
			st = (PreparedStatement) conn.prepareStatement("Select * from wheels"
					+ " where (brand_name=? and serial_no=?)"); 
			st.setString(1,this.brandName);
			st.setString(2, this.serialNo);
				rs = st.executeQuery();
				
			if (rs.next()) {
				conn.close();
				return true; 
			}
			
			conn.close();
			return false ; 

			
		}catch (SQLException ex) {
			 ex.printStackTrace();
		} 
		
		return true ; 
		
		
	}

    
}
