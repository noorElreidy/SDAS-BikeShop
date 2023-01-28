# com2008-com3008

Private Repo used by Noor Elreidy, Noor Sharif, Zakariyah Yunis and Vernon Yankson for Systems Design and Security module.

DBDriver guide cause it got out of hand: 

**staffLogin(String username, String password)**: return true if login details match details in database

**CustomerLogin(String firstName, String lastName, Address address)**: returns true if customer details match those in database

**getLastID()** : return int of last id in customer table 


**getAddressFromID(int id)** : returns address object of customer given id

**newCustomer(Customer customer)** : inserts a new customer into database. customer object contains an address object which is used to populate address and linker tables 

**update(String table, String updateColumn, String value, String conditionCol, String condition)** : updates a tables record. All type string  . goes like : 
update table set updateColumn = value where conditionCol = condition . 

**selectALLColumn(String table, String columnName)** : returns ArrayList of values ni clumnname in table 

**getAddressList()** : returns an ArrayList of address objects from database 

**getCustomerlist()** : return an ArrayList of customer objects from database 

**getWheelList()** : return an ArrayList of wheel objects from database 

**getFramesetList()** : returns an ArrayList of Framesets from database 

**getHandlebarsList()** : returns an ArrayList of handlebars from database 

**getBikeList()** : returns an ArrayList of bikes from database 

**getHandleBarByPK(string serialNo, String brandName, ArrayList<Handlebars>)** : returns an handlebar object from list given serial no and brandname 

**getFramesetByPK(string serialNo, String brandName, ArrayList<Frameset>)** : returns a Frameset object from list given serial no and brandname

**getWheelsByPK(string serialNo, String brandName, ArrayList<Wheels>)** : returns a Wheels object from list given serial no and brandname  
 