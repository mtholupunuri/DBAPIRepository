package com.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionGeneric {
	
	Connection con = null;
	
	DBConnectionGeneric()
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void jdbcConnection(String url, String username, String password) throws SQLException
	{
		
		con = DriverManager.getConnection(url,username,password);
	}
	
	
	public ResultSet executeQuery(String query)
	{
		try
		{
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public void closeConnection() throws SQLException
	{
		con.close();
	}

	

}
