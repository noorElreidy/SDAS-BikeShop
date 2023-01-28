package components;

/**
 * Class used to represent a Handlebar 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DBDriver;

public class Handlebars extends Component {

	private enum Style{STRAIGHT, HIGH, DROPPED};
	
	private Style style ;
	
	
	private String brandName;
	private String serialNo;
	private String productName;
	private double cost;
	private int stock;
	
	/**
	 * constructor 
	 * @param serialNo
	 * @param brandName
	 * @param productName
	 * @param cost
	 * @param style
	 * @param stock
	 */
	public Handlebars(String serialNo, String brandName,  String productName, double cost, String style, int stock ) {
		super(brandName,serialNo, productName,cost,stock);
		this.style = fromStringStyle(style) ;
	}
	
	/**
	 * converts string into enum
	 * @param st
	 * @return enum Style
	 */
	public static Style fromStringStyle(String st) {
		switch (st.toUpperCase()) {
		case "STRAIGHT" : 
			return Style.STRAIGHT ;
		case "HIGH" : 
			return Style.HIGH ; 
		case "DROPPED" : 
			return Style.DROPPED ;
		}
		return Style.DROPPED;
	}
	
	/**
	 * converts enum to string
	 * @return string
	 */
	public String styleToString() {
		switch (style) {
		case STRAIGHT : return "STRAIGHT";
		case HIGH : return "HIGH" ;
		default : return "DROPPED";
		}
	}
	
	
	
	public Style getStyle() {
	    return style;
	}
	
	public boolean equals(Object obj) {
		boolean comp = super.equals(obj);
		Handlebars hndl = (Handlebars)obj;
		if (comp && this.style == hndl.getStyle())
			return true ;
		else 
			return false; 
		
	}
	
	/**
	 * gets handlebar from database given primary key (brandname and serial No)
	 * @param serialNo
	 * @param brandName
	 * @param compList
	 * @return handlebar object 
	 */
    public static Handlebars getHandleBarByPK(String serialNo, String brandName, ArrayList<Handlebars> compList) {
    	for(Handlebars x : compList) {
    		if (x.getSerialNo().equals(serialNo) && x.getBrandName().equals(brandName))
    			return x ; 	    		
    	}
    	return null ; 
    }
    
    /**
     * gets list of handle bars from database 
     * @returns an arrayList of handlebars 
     */
  	public static ArrayList<Handlebars> getHandlebarsList(){
  		ArrayList<Handlebars> handlebars = new ArrayList<Handlebars>();
  		
  		try {
  			Connection conn = DBDriver.getConn();
  			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from handlebars"); 
  			ResultSet rs = st.executeQuery();
  			
  			while (rs.next()) {
  			  Handlebars handlebar = new Handlebars(rs.getString(1), rs.getString(2), rs.getString(3),rs.getDouble(4),
  					rs.getString(5), rs.getInt(6));
  			  handlebars.add(handlebar);
  			}
              
  			conn.close();
          
          }catch(SQLException ex) {
  	       ex.printStackTrace();
          }
  		return handlebars;
  	}
  	
  	/**
  	 * inserts handle bar into databse
  	 * @returns true if successully inserted , false otherwise 
  	 */
  	public boolean insertHandleBar() {
  		try {
			Connection conn = DBDriver.getConn();
		     
			if (this.pkExists()) {
				return false ; 
			}
  			
  			
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("insert into handlebars " +
						"(serial_no, brand_name, product_name, cost, style, no_stock) values(?,?,?,?,?,?)");
  			System.out.println(this.getSerialNo());
  			st.setString(1, this.getSerialNo());
  			st.setString(2, this.getBrandName());
  			st.setString(3, this.getProductName());
			st.setDouble(4, this.getCost());
			st.setString(5, this.styleToString());
			st.setInt(6, this.getStock());
			st.executeUpdate();
			conn.close();
			return true ; 
			
		}catch (SQLException ex) {
			 ex.printStackTrace();
		} 
 	
  		return false ;
  	}
	
	
}
