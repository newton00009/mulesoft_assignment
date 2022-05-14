package sqlitedatabase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class run {
	  
	//String to store database name and to later access it to create table in it
	String dbname;
	// String to store url of created database
	String url;
	  
	static Scanner s= new Scanner(System.in);
	  
	  
	//Function to connect to existing database 
	void connect_to_exsisting_db()
	{
       String jdbcurl="jdbc:sqlite:testdb.db";
		
		try {
			Connection conn=DriverManager.getConnection(jdbcurl);
			System.out.println("Connected to an exsisting database testdb.db");
	     	}
		catch(SQLException e)
		    {
			System.out.println("Error connecting to exsisting database");
			e.printStackTrace();
		    }
	}
	
	
	//function to create a new database where user needs to enter the name of the database as > databasename.db
	void create_new_db()
	{
		
		  
		
			System.out.println("Enter the name of the database you want to create in the format dbname.db");
			 dbname=s.next();
		  
	        url = "jdbc:sqlite:"+dbname;  
	       
	        try {  
	            Connection conn = DriverManager.getConnection(url);  
	            if (conn != null) {   
	                System.out.println("A new database has been created by the name of "+dbname );  
	            }  
	   
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	}
	
	//Function to create table Movies under the new database created
	  void createNewTable() {  
	         
		  
		  System.out.println("Createing new table under the database "+dbname);
		  
	        // SQL statement for creating a new table  
	        String sql = "CREATE TABLE IF NOT EXISTS Movies (\n"    
	                + " name TEXT NOT NULL,\n"  
	                + " actor TEXT NOT NULL,\n"  
	                + " actress TEXT NOT NULL,\n"  
	                + " director TEXT NOT NULL,\n"
	                + " year_of_release TEXT NOT NULL);";  
	          
	        try{  
	            Connection conn = DriverManager.getConnection(url);  
	            Statement stmt = conn.createStatement();  
	            stmt.execute(sql);  
	            
	            System.out.println("Movies table create under the database "+dbname);
	            
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }
	  
	  
	  
	  //function to insert values in table Movies
	  
	   public void insert() { 
		   
		   System.out.println("Inserting values in movies table"); 
	        String sql = "INSERT INTO Movies(name,actor,actress,director,year_of_release) VALUES(?,?,?,?,?)";  
	        
	   
	        try{  
	            Connection conn = DriverManager.getConnection(url);  
	            PreparedStatement pstmt = conn.prepareStatement(sql);  
	            
	            
	            
	            System.out.println("Enter the name of the movie "); 
	            pstmt.setString(1, s.next());  
	            System.out.println("Enter the actor of the movie "); 
	            pstmt.setString(2, s.next()); 
	            System.out.println("Enter the actress of the movie "); 
	            pstmt.setString(3, s.next()); 
	            System.out.println("Enter the director of the movie "); 
	            pstmt.setString(4, s.next()); 
	            System.out.println("Enter the year of release of the movie "); 
	            pstmt.setString(5, s.next()); 
	           
	            pstmt.executeUpdate();  
	            
	            System.out.println("Values inserted successfully"); 
	            
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }  
	   
	   
	  
	 //function to display all rows from table Movies  
	   public void selectAll(){  
	        String sql = "SELECT * FROM Movies";  
	          
	        try {  
	        	 Connection conn = DriverManager.getConnection(url);  
	            Statement stmt  = conn.createStatement();  
	            ResultSet rs    = stmt.executeQuery(sql);  
	              
	            System.out.println("name\t actor\t actress\t director\t year of release");  
	            // loop through the result set  
	            while (rs.next()) {  
	                System.out.println(rs.getString("name") + "\t" +  
	                                   rs.getString("actor") + "\t" +  
	                                   rs.getString("actress") + "\t" +  
	                               	   rs.getString("director") + "\t" +  
	                                   rs.getString("year_of_release"));  
	            }  
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }  
	   
	   public void selectAll_where(){  
		   System.out.println("Enter the name of the attribute you want the where clause of");
		 
	        String sql = "SELECT * FROM Movies WHERE "+ s.next()+" = ? ;";  
	          
	        try {  
	        	 Connection conn = DriverManager.getConnection(url);  
	        	 PreparedStatement pstmt = conn.prepareStatement(sql);
	        	 
	        	  
	        	  System.out.println("Enter the value of the attribute for the where clause");
	        	 pstmt.setString(1, s.next());
	            ResultSet rs    = pstmt.executeQuery();  
	              
	            // loop through the result set  
	            while (rs.next()) {  
	                System.out.println(rs.getString("name") + "\t" +  
	                                   rs.getString("actor") + "\t" +  
	                                   rs.getString("actress") + "\t" +  
	                               	   rs.getString("director") + "\t" +  
	                                   rs.getString("year_of_release"));  
	            }  
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }  
	  

	public static void main(String []args)
	{	
		run obj= new run();
		//demo1 to connect to an existing database named testdb.db stored in the directory user is running the program from 
		// user using microsoft windows 11 OS
		obj.connect_to_exsisting_db();
		
		
		//demo2 to create a new database  stored in the directory user is running the program from 
		// user using microsoft windows 11 OS
		//user will be prompted to name the database
		obj.create_new_db();
		
		
		//demo3 a new table movies will be created under new database created 
		//which will have attributes name,actor,actress,director,year of release
		obj.createNewTable();
//		
//		
		
		while(true)
		{
			 System.out.println("Press\n 1> To insert new Values in Movies Table \n 2> Display All rows of the Movies Table \n 3> To Display selected rows on basis of attribute and value provided by user \n 4> To exit the program "); 
		switch(s.nextInt()){    
		case 1:    
			obj.insert();
		 break; 
		case 2:    
			obj.selectAll();
		 break; 	 
		case 3:    
			 obj.selectAll_where();
			 break; 
		case 4: 
			System.out.println("Thank You for using SQLite Database");
			 return;
			 
		default:   System.out.println("Enter valid choice");  
		
		}  
    
		
		
		
		}
		
		
		
		
		
	}
	
}
