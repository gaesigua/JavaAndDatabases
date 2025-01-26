package pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePage {
	
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	String columnValue;
	
	public String getDataFromDB(String columnName) {
		
		
		try {

//1. To retrieve data from database, we need to first set the properties for MySQL

			Class.forName("com.mysql.cj.jdbc.Driver");
			String sqlUrl = "jdbc:mysql://localhost:3306/techfios";
			String sqlUsername = "root";
			String sqlPassword = "password";
			String sqlQuery = "select * from users;";
			
//2. Now Let's create a connection to our local database, using a class called DriverManager, that implements an interface called Connection
			
			connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);
			
//3. Now let's create a Statement Object using our Connection; this statement is to empower the connection reference variable to execute queries
			
			statement = connection.createStatement();
			
//4. Now let's the execute/get our query by creating a resultset reference variable
			
			resultSet = statement.executeQuery(sqlQuery);
			
            //Now let's create a while loop to keep going through the data, and print the results: in other words, as long as the execution is returning something (next), then we have to keep executing the query
			
			while(resultSet.next()) {
				
				//Here we are getting the data from the table, using the ColumnLabel, it is preferred than using the ColumnIndex, because indexes might change anytime
				
				columnValue = resultSet.getString(columnName);
				return columnValue;
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
			//Now let's close both the resultSet and the connection since they are still open
			
		}finally {
			
			//closing the resultSet
			
			if(resultSet!=null) {
				try {
					resultSet.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			//closing the connection
			
			if(connection!=null) {
				
				try {
					connection.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
			
		}
		
	return columnValue;
		
	}

}
