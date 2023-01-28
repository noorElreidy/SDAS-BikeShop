package components;

/**
 * Bike class used to represent a bike 
 * Contains methods that run sql queries 
 * contains Frameset , Handlebars and Wheels objects .
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DBDriver;


public class Bike {
    //No product name, chosen by customer and put in order object 
    private String brandName; //combination of frameset brandname and wheel style
    private int serialNo ;
    private double cost ; 
    private Frameset frameset ;
    private Handlebars handlebars;
    private Wheels wheels ; 

    /**
     * contructoe for getting bike from database 
     * @param serialNo
     * @param brandName
     * @param frameset
     * @param handlebars
     * @param wheels
     */
    public Bike(int serialNo, String brandName, Frameset frameset, 
    		Handlebars handlebars, Wheels wheels ) {
    	this.brandName = brandName ;
		this.serialNo = serialNo ; 
		this.cost = frameset.getCost() + handlebars.getCost() + wheels.getCost();
		this.frameset = frameset ;
		this.handlebars = handlebars ;
		this.wheels = wheels ;
    }
    
    /**
     * bike constructor for creating bike 
     * @param frameset
     * @param handlebars
     * @param wheels
     */
    public Bike( Frameset frameset, 
    		Handlebars handlebars, Wheels wheels ) {
    	this.brandName = frameset.getBrandName() + wheels.getStyle() ;
		this.serialNo = -1 ; // autoinceremented in database, set later 
		this.cost = frameset.getCost() + handlebars.getCost() + wheels.getCost();
		this.frameset = frameset ;
		this.handlebars = handlebars ;
		this.wheels = wheels ;
    }
    
    public void setSerialNo(int SerialNo) {
    	this.serialNo = serialNo ; 
    }
    
    /**
     * gets the last ID in the databse. Used to set the objects ID 
     * @return
     */
  	public static int getLastID() {
  		
  		try {
  			Connection conn = DBDriver.getConn();
  			
  			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select serial_no_bike from bike " +
  				"Order By serial_no_bike Desc");
  			ResultSet rs = st.executeQuery();
  			
  			if (rs.next()) {
  				 return rs.getInt(1);
  			}
              st.close();
  			conn.close();
          
          }catch(SQLException ex) {
  	       ex.printStackTrace();
          }
  		return -1;
  	}
    
    /**
     * inserts bike in database and returns its serialNo
     * if bike already exists, it just returns serialNo
     * returns -1 if failed to insert 
     * @return returns int SerialNo
     */
    public int insertBike() {
    	int serialExists = this.exists();
    	if ( serialExists == -1) {
    		try {
    			Connection conn = DBDriver.getConn();
    			
    			int id ;
    			
    			PreparedStatement st = (PreparedStatement) conn.prepareStatement("insert into bike " +
    			  "(brand_name_bike,cost_bike,handle_bar_serial_no, handle_bar_brand_name, "
    			  + "wheel_serial_no, wheel_brand_name, frameset_serial_no, frameset_brand_name)"
    			  + " values(?,?,?,?,?,?,?,?)");
    			st.setString(1, this.brandName );
    			st.setDouble(2, this.cost);
    			st.setString(3, this.handlebars.getSerialNo() );
    			st.setString(4, this.handlebars.getBrandName());
    			st.setString(5, this.wheels.getSerialNo() );
    			st.setString(6, this.wheels.getBrandName());
    			st.setString(7, this.frameset.getSerialNo() );
    			st.setString(8, this.frameset.getBrandName());
    			st.executeUpdate();
    			st.close();
    			
    			int serial = getLastID();
    			this.setSerialNo(serial);
    			conn.close();
    			return serial ; 
    			
    			
    		}catch (SQLException ex) {
   			 ex.printStackTrace();
   		    } 
    	  return -1 ; //incase failed to insert 
   		
   		}
    	else 
    		this.setSerialNo(serialExists);
    	    
    		return serialExists; 
    		
    		
    }
    
    
    
    public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Frameset getFrameset() {
		return frameset;
	}

	public void setFrameset(Frameset frameset) {
		this.frameset = frameset;
	}

	public Handlebars getHandlebars() {
		return handlebars;
	}

	public void setHandlebars(Handlebars handlebars) {
		this.handlebars = handlebars;
	}

	public Wheels getWheels() {
		return wheels;
	}

	public void setWheels(Wheels wheels) {
		this.wheels = wheels;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public boolean equals(Object bk) {
    	Bike bike1 = (Bike) bk ;
    	if ( this.brandName.equals(bike1.getBrandName()) && this.cost == bike1.getCost()  
    		&& this.frameset.equals(bike1.getFrameset()) && this.handlebars.equals(bike1.getHandlebars())
    		&& this.wheels.equals(bike1.wheels))
    		return true ; 
    	else 
    		return false; 
    }
	
	/**
	 * gets list of bikes from database 
	 * @returns ana arrayList of bike objects 
	 */
	public static ArrayList<Bike> getBikeList(){ 
		ArrayList<Bike> bikes = new ArrayList<Bike>();
		try {
			Connection conn = DBDriver.getConn();
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from bike"); 
			ResultSet rs = st.executeQuery();

				
			while (rs.next()) {
			  Handlebars hb = Handlebars.getHandleBarByPK(rs.getString(4), rs.getString(5), Handlebars.getHandlebarsList());
			  Wheels wl = Wheels.getWheelsByPK(rs.getString(6), rs.getString(7),Wheels.getWheelList());
			  Frameset ft = Frameset.getFramesetByPK(rs.getString(8), rs.getString(9), Frameset.getFramesetList());
			  
			  Bike bike = new Bike(rs.getInt(1), rs.getString(2), ft, hb, wl );
			  
			  bikes.add(bike);
			}
	            
			conn.close();
	        
	        }catch(SQLException ex) {
		       ex.printStackTrace();
	        }
			return bikes;
	} 
	
	
	/** 
	 * gets bike from db using serial No 
	 * @param serialNo
	 * @returns Bike object with parameters serialNo
	 */
	public static Bike getBikeBySerialNo(int serialNo) {
		ArrayList<Bike> bikes = getBikeList() ;
		for(Bike x : bikes ) {
    		if (x.getSerialNo() == serialNo)
    			return x ; 
    	}
    	return null ; 
	}
	
	
	/**
	 * checks if bike exists in db 
	 * @return serialNo of bike of it exists, otherwise -1 
	 */
	public int exists() {
		ArrayList<Bike> bikes = getBikeList() ; 
		for (Bike bk : bikes) {
			if (this.equals(bk))
				return bk.getSerialNo() ; 
		}
		return -1 ; 
	}
	
	
    
}
