package dbconnector;

import java.sql.*;

public class DBConnect {

	//A common method to connect to the DB
	public Connection connect() throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Doctors?serverTimezone=UTC", "root", "");
		
		return con;	
	}
}
