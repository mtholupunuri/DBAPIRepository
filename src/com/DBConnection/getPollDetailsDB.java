package com.DBConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.DataObjects.pollDetailsDataObj;

public class getPollDetailsDB {

	DBConnectionGeneric DBObj = new DBConnectionGeneric();
	private String QuesDesc;
	private long pollID;
	
	pollDetailsDataObj p1 = new pollDetailsDataObj(pollID, QuesDesc);
	
	public ArrayList<pollDetailsDataObj> getPollDetails() throws SQLException
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
		
//		String query = "SELECT poll_id, question_description FROM poll_questions poll join question q on poll.question_id = q.question_id";
//		String url = "jdbc:postgresql://192.168.150.82:5432/employeepollservice";
//		String username = "postgres";
//		String password = "newPassword";
		
		DBObj.jdbcConnection(prop.getProperty("pollurl"),prop.getProperty("username"),prop.getProperty("password"));
		
		try
		{
				
			ResultSet rs = DBObj.executeQuery(prop.getProperty("pollquery"));
			ArrayList<pollDetailsDataObj>  pollList= new ArrayList<>();
			while (rs.next()) 
			{
				
				pollDetailsDataObj E1 = new pollDetailsDataObj(rs.getLong(1), rs.getString(2));
			
				pollList.add(E1);
			}
			return pollList;
			
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		} finally{
			
			DBObj.closeConnection();
		}
		return null;
		
		
	}
	
	public ArrayList<Long> getpollID() throws SQLException
	{
		
		ArrayList<pollDetailsDataObj> pollID = this.getPollDetails();
		ArrayList<Long> pID = new ArrayList<Long>();
		for (pollDetailsDataObj pollIDData : pollID) {
			
			pID.add(pollIDData.getPollID());
		}
		return pID;
	}
	
	public ArrayList<String> getQuesDesc() throws SQLException
	{
		ArrayList<pollDetailsDataObj> QuesDesc = this.getPollDetails();
		ArrayList<String> Ques = new ArrayList<String>();
		for (pollDetailsDataObj quesData : QuesDesc) {
			
			Ques.add(quesData.getQuesDescrition());
			
		}
		return Ques;
		
	}

}
