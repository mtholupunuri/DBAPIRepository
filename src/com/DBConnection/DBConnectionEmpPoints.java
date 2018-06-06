package com.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionEmpPoints {
	
	public long dbConnection() throws ClassNotFoundException
	{
		// TODO Auto-generated method stub

		Long pointsDB = null;
		Class.forName("org.postgresql.Driver");
		try
		{
			Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.150.82:5432/leaderboard",
					"postgres", "newPassword");
			PreparedStatement stmt = con.prepareStatement("select * FROM emp_points where emp_id = '816739'");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// System.out.println(rs.getInt(2));
				pointsDB = rs.getLong(2);

			}

		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}
		if(pointsDB == null) {
			return 0;
		}
		return pointsDB;
	}


}
