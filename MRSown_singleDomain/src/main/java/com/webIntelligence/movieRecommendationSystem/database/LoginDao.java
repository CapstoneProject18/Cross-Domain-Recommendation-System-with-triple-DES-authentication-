package com.webIntelligence.movieRecommendationSystem.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {

	public Connection createConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/login","root", "");

		return conn;

	}
	
	 public Boolean authenticateUser(String email, String password) throws ClassNotFoundException
	 {
	 
	 Connection con = null;
	 Statement statement = null;
	 ResultSet resultSet = null;
	 
	 String emailDB = "";
	 String passwordDB = "";
	 
	try
	 {
	 con = createConnection(); //establishing connection
	 statement = con.createStatement(); //Statement is used to write queries. Read more about it.
	 resultSet = statement.executeQuery("select email,password from users"); //Here table name is users and userName,password are columns. fetching all the records and storing in a resultSet.
	 
	while(resultSet.next()) // Until next row is present otherwise it return false
	{
	  emailDB = resultSet.getString("email"); //fetch the values present in database
	  passwordDB = resultSet.getString("password");
	 
	   if(email.equals(emailDB) && password.equals(passwordDB))
	   {
	      return true; ////If the user entered values are already present in database, which means user has already registered so I will return SUCCESS message.
	   }
   
	 }}
	 catch(SQLException e)
	 {
	 e.printStackTrace();
	 }
	 return false; // Just returning appropriate message otherwise
	
	
	 }
	 
	 
	 public  Boolean insertIntoTbl(String FirstName, String LastName, String Gender, String UserName, String Email, String Password) throws ClassNotFoundException
		{
			//1. Establish the connection with DB
			 Connection con = null;
			 Statement statement = null;
			 ResultSet resultSet = null;
			
			
			//2. Specify Operation
			try {
				con = createConnection(); //establishing connection
				PreparedStatement stmt = con.prepareStatement("insert into users values(?,?,?,?,?,?)");
				//3. Pass parameters if any
				stmt.setString(1,FirstName);
				stmt.setString(2,LastName);
				stmt.setString(3,Gender);
				stmt.setString(4,UserName);
				stmt.setString(5,Email);
				stmt.setString(6,Password);
				
				//4. Execute query
				stmt.executeUpdate();
				System.out.println("Data inserted");
				//5. Close your connection
				con.createStatement();stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			return true;
			
		}
	 
	 
	 public  Boolean insertIntoTbl2(String movieName, String MID, String Year) throws ClassNotFoundException
		{
			//1. Establish the connection with DB
			 Connection con = null;
			 Statement statement = null;
			 ResultSet resultSet = null;
			
			
			//2. Specify Operation
			try {
				con = createConnection(); //establishing connection
				
				PreparedStatement stmt = con.prepareStatement("insert into movie values(?,?,?)");
				//3. Pass parameters if any
				stmt.setString(1,movieName);
				stmt.setString(2,MID);
				stmt.setString(3,Year);
			
				
				//4. Execute query
				stmt.executeUpdate();
				System.out.println("Data inserted");
				//5. Close your connection
				con.createStatement();stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			return true;
			
		}
	 
	 public  Boolean insertIntoTbl3(String movieName,String username, int rating) throws ClassNotFoundException
		{
			//1. Establish the connection with DB
			 Connection con = null;
			 Statement statement = null;
			 ResultSet resultSet = null;
			
			
			//2. Specify Operation
			try {
				con = createConnection(); //establishing connection
				PreparedStatement stmt = con.prepareStatement("insert into addrating values(?,?,?)");
				//3. Pass parameters if any
				stmt.setString(1,movieName);
				stmt.setString(2,username);
				stmt.setInt(3,rating);
				
				//4. Execute query
				stmt.executeUpdate();
				System.out.println("Data inserted");
				//5. Close your connection
				con.createStatement();stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			return true;
			
		}
	 
	 public String getRandMovieName() throws ClassNotFoundException {
		 String movieName = "a";
		//1. Establish the connection with DB
		 Connection con = null;
		 Statement statement = null;
		 ResultSet resultSet = null;
		
		
		//2. Specify Operation
		try {
			con = createConnection(); //establishing connection
			  System.out.println("Creating statement...");
			  Statement stmt = con.createStatement();

		      String sql = "SELECT movieName FROM movie ORDER BY RAND() LIMIT 1";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         movieName = rs.getString("movieName");

		         //Display values
		         System.out.println("movieName: " + movieName);
		      }
		      rs.close();
		      con.close();
		      return movieName;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return movieName;
		}
	 }
	 
	 
	
	
}
