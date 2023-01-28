package components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DBDriver;

public class Wheels extends Component {
	
	private enum Style { ROAD, MOUNTAIN, HYBRID};
	private enum Brake { RIM, DISK } ;
	
	private double diameter ;
	private Style style ;
	private Brake brake ; 
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
	 * @param diameter
	 * @param style
	 * @param brake
	 * @param stock
	 */
	public Wheels ( String serialNo,String brandName, String productName, double cost,
			double diameter, String style , String brake, int stock ) {
		super(brandName,serialNo, productName,cost,stock);
		this.diameter = diameter;
		this.style = fromStringStyle(style) ;
		this.brake = fromStringBrake(brake) ; 
		
	}
	
	
	public Brake getBrake() {
		return brake; 
					
	}
	
	public Style getStyle() {
		return style ; 
	}
	
	public double getDiameter() {
		return diameter ;
	}
	
	public String getStyleString() {
		switch (style) {
		case MOUNTAIN : return "MOUNTAIN" ; 
		case ROAD : return "ROAD" ; 
		default : return "HYBRID" ; 
		}
	}
	
	public String getBrakeString() {
		if (brake == Brake.DISK)
			return "DISK";
		else 
			return "RIM" ;
	}
	
	
	
	
	public static Style fromStringStyle(String styleString) {
		switch(styleString.toUpperCase()) {
		case "ROAD" :
			return Style.ROAD ; 
		case "MOUNTAIN" :
			return Style.MOUNTAIN;
		case "HYBRID" :
			return Style.HYBRID ; 
			
		}
		return Style.MOUNTAIN;
	}
	
	public static Brake fromStringBrake(String brakeString) {
		switch(brakeString.toUpperCase()) {
		case "RIM" :
			return Brake.RIM; 
		case "DISK" :
			return Brake.DISK;
			
		}
		return Brake.DISK;
	}
	
	public boolean equals(Object obj) {
		boolean comp = super.equals(obj);
		Wheels wl = (Wheels) obj;
		if (comp && this.brake == wl.getBrake() && this.style == wl.getStyle()
			&& this.diameter == wl.getDiameter())
			return true ;
		else 
			return false; 
	}
	
	
	/**
	 * gets full wheel details given primary key (brand name + serial No)
	 * @param serialNo
	 * @param brandName
	 * @param compList --list of componets 
	 * @return wheel object 
	 */
    public static Wheels getWheelsByPK(String serialNo, String brandName, ArrayList<Wheels> compList) {
    	for(Wheels x : compList) {
    		if (x.getSerialNo().equals(serialNo) && x.getBrandName().equals(brandName))
    			return x ; 
    	}
    	return null ; 
    }
    
    /**
     * gets wheels list from DB
     * @return ArrayList of wheels objects
     */
  	public static ArrayList<Wheels> getWheelList(){
  		ArrayList<Wheels> wheels = new ArrayList<Wheels>();
  		try {
  			Connection conn = DBDriver.getConn();
  			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from wheels"); 
  			ResultSet rs = st.executeQuery();
  			
  			
  			while (rs.next()) {
  			  Wheels wheel = new Wheels(rs.getString(1),rs.getString(2),rs.getString(3)
  			      ,rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getString(7), rs.getInt(8));
  			  wheels.add(wheel);
  			}
              
  			conn.close();
          
          }catch(SQLException ex) {
  	       ex.printStackTrace();
          }
  		
  		return wheels;
  	}
  	
  	/**
  	 * inserts wheel into database 
  	 * @return true if successful , otherwise false 
  	 */
  	public boolean insertWheels() {
  		try {
			Connection conn = DBDriver.getConn();
		     
			if (this.pkExists()) {
				return false ; 
			}
  			
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("insert into wheels " +
						"(serial_no, brand_name, product_name, cost, size, style, brake, no_stock) values(?,?,?,?,?,?,?,?)");
  			System.out.println(this.getSerialNo());
  			st.setString(1, this.getSerialNo());
  			st.setString(2, this.getBrandName());
  			st.setString(3, this.getProductName());
			st.setDouble(4, this.getCost());
			st.setDouble(5, this.getDiameter());
			st.setString(6, this.getStyleString());
			st.setString(7, this.getBrakeString());
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
