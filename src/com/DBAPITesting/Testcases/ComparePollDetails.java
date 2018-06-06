package com.DBAPITesting.Testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.APIConnection.GetPollDetailsDO;
import com.DBConnection.getPollDetailsDB;
import com.DataObjects.pollDetailsDataObj;
import com.aventstack.extentreports.Status;
import com.util.BaseTest;


public class ComparePollDetails extends BaseTest{
	
//		public ExtentReports extent;
//		public ExtentTest test;
		
		@Test
		public void comparePollDetails1() throws SQLException, IOException, ParseException
		{
//			extent = new ExtentReports("C:\\Users\\mtholupunuri\\Desktop\\testreport.html", true);
//			test = extent.startTest("comparePollDetails");
//			 
//			test.log(LogStatus.INFO, "Purpose: Comparing poll details and respection question associated with poll from Data base and API response");
			
			test = extent.createTest("comparePollDetails1");
			
			String QDescFromDB = null;
			String QDescFromAPI = null;
			getPollDetailsDB pollDetail = new getPollDetailsDB();
			
			ArrayList<pollDetailsDataObj> pollDetails1 =  pollDetail.getPollDetails();
			
			System.out.println("Count of poll IDs available in DB are: " +pollDetails1.size());
			test.log(Status.INFO, "No.of poll ids available in DB are: "+pollDetails1.size());
			
			for (pollDetailsDataObj pollDetailsDataObj1 : pollDetails1) 
			{
				
				System.out.println("Poll details from DB are: " +pollDetailsDataObj1.getPollID() + " and " + pollDetailsDataObj1.getQuesDescrition());
				test.log(Status.INFO, "Poll details from DB are: "+pollDetailsDataObj1.getPollID() + " and " + pollDetailsDataObj1.getQuesDescrition());
				if(pollDetailsDataObj1.getPollID() == 733)
				{
					QDescFromDB = pollDetailsDataObj1.getQuesDescrition();
				}
				
			} 
				
			
			GetPollDetailsDO pollDetailAPI = new GetPollDetailsDO();
			ArrayList<pollDetailsDataObj> pollDetailFromAPI = pollDetailAPI.getPollDetailsAPI();
			System.out.println("Count of poll IDs available in API are: " +pollDetailFromAPI.size());
			
			for (pollDetailsDataObj pollDetailsDataObjAPI : pollDetailFromAPI) 
			{
				System.out.println("Poll details from API are: " +pollDetailsDataObjAPI.getPollID() + " and " + pollDetailsDataObjAPI.getQuesDescrition());
				
				if(pollDetailsDataObjAPI.getPollID() == 733)
				{
					QDescFromAPI = pollDetailsDataObjAPI.getQuesDescrition();
				}
				
			}
			
			assertEquals(QDescFromDB, QDescFromAPI);
			
		}

		
}
