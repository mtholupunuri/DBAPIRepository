package com.DBConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.DataObjects.EmpPointsDataObj;

public class EmpDetailsDB {	
	
	DBConnectionGeneric DBObj = new DBConnectionGeneric();
	
	private String empid;
	private Long points;
		
	EmpPointsDataObj e1 = new EmpPointsDataObj(empid,points);

	public ArrayList<EmpPointsDataObj> getEmpDetails() throws SQLException
	{	
		Properties prop = new Properties();
		InputStream input = null;
		
		try {

			input = new FileInputStream("./PropertyFiles/dbDetails.properties");

			// load a properties file
			prop.load(input);
			
		}catch (IOException ex) {
			ex.printStackTrace();
		} 
		
//		String query = "select * FROM emp_points where emp_id = '816739'";
//		String url = "jdbc:postgresql://192.168.150.82:5432/leaderboard";
//		String username = "postgres";
//		String password = "newPassword";
		
		DBObj.jdbcConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
						
		try
		{
				
			ResultSet rs = DBObj.executeQuery(prop.getProperty("query"));
			ArrayList<EmpPointsDataObj>  EmpList= new ArrayList<>();
			while (rs.next()) 
			{
				
				EmpPointsDataObj E1 = new EmpPointsDataObj(rs.getString(1), rs.getLong(2));
			
				EmpList.add(E1);
			}
			return EmpList;
			
			
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		} finally{
			
			DBObj.closeConnection();
		}
		return null;
		
		
	}
	
	public ArrayList<Long> getEmpPoints() throws SQLException
	{
		
		ArrayList<EmpPointsDataObj> empPoints = this.getEmpDetails();
		ArrayList<Long> points = new ArrayList<Long>();
		for (EmpPointsDataObj empPointsData : empPoints) {
			
			points.add(empPointsData.getPoints());
		}
		return points;
	}
	
	public ArrayList<String> getEmpID() throws SQLException
	{
		ArrayList<EmpPointsDataObj> empID = this.getEmpDetails();
		ArrayList<String> ID = new ArrayList<String>();
		for (EmpPointsDataObj empPointsData : empID) {
			
			ID.add(empPointsData.getID());
			
		}
		return ID;
		
	}
	
	
}
