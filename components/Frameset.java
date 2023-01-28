package components;

/**
 * Frameset class used to represent frameset object 
 * subclass of Components.java
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DBDriver;

public class  Frameset extends Component {
    
	private double size;
	private boolean shocks;
	private int gears ;
	private String brandName;
	private String serialNo;
	private String productName;
	private double cost;
	private int stock;
	
	/**
	 * frameset constructor to get from DB and allow staff to add to DB 
	 */
	public Frameset( String serialNo,String brandName, String productName, double cost, double size , 
			boolean shocks, int gears, int stock) {
		super(brandName,serialNo, productName,cost,stock);
		this.size = size ;
		this.shocks = shocks ;
		this.gears = gears; 
		
	}
	
	public boolean hasShocks() {
		return shocks; 		
	}
	
	public int getGears() {
		return gears; 
	}
	
	public double getSize() {
		return size ;
	}
	
	public boolean equals(Object obj) {
		boolean comp = super.equals(obj);
		Frameset ft = (Frameset)obj;
		if (comp && this.shocks == ft.hasShocks() && this.gears == ft.getGears()
			&& this.size == ft.getSize())
			return true ;
		else 
			return false; 	
	}
	
	/**
	 * given serial No and brandName, returns a frameset from DB
	 * @param serialNo
	 * @param brandName
	 * @param compList
	 * @return frameset object 
	 */
    public static Frameset getFramesetByPK(String serialNo, String brandName, ArrayList<Frameset> compList) {
    	for(Frameset x : compList) {
    		if (x.getSerialNo().equals(serialNo) && x.getBrandName().equals(brandName))
    			return x ; 
    	}
    	return null ; 
    }
	
    /**
     * gets frameset list from DB
     * @return ArrayList of frameset objects
     */
  	public static ArrayList<Frameset> getFramesetList(){
  		ArrayList<Frameset> framesets = new ArrayList<Frameset>();
  		
  		try {
  			Connection conn = DBDriver.getConn();
  			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from frameset"); 
  			ResultSet rs = st.executeQuery();
  			
  			
  			while (rs.next()) {
  			  Frameset frameset = new Frameset(rs.getString(1), rs.getString(2), rs.getString(3),rs.getDouble(4),
  			      rs.getDouble(5), DBDriver.toBoolean(rs.getInt(6)), rs.getInt(7), rs.getInt(8));
  			  framesets.add(frameset);
  			}
              
  			conn.close();
          
          }catch(SQLException ex) {
  	       ex.printStackTrace();
          }
  		
  		
  		return framesets;
  	}
  	
  	
  	/**
  	 * allows staff to insert component into database 
  	 * @returns true if successfully inserted . false otherwise 
  	 */
  	public boolean insertFrameset() {
  		try {
			Connection conn = DBDriver.getConn();
		     
			if (this.pkExists()) {
				conn.close();
				return false ; 
			}
  			
  			
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("insert into frameset " +
						"(serial_no, brand_name, product_name, cost, size, shocks,gears,no_stock) values(?,?,?,?,?,?,?,?)");
  			st.setString(1, this.getSerialNo());
  			st.setString(2, this.getBrandName());
  			st.setString(3, this.getProductName());
			st.setDouble(4, this.getCost());
			st.setDouble(5, this.getSize());
			st.setInt(6, DBDriver.toTinyInt(shocks));
			st.setInt(7, this.getGears());
			st.setInt(8, this.getStock());
			st.executeUpdate();
			
			conn.close();
			return true ; 
			
		}catch (SQLException ex) {
			 ex.printStackTrace();
		} 
  		
  		return false ;
  	}
	
	
}
