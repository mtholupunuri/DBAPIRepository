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
import com.relevantcodes.extentreports.LogStatus;
import com.util.BaseTestExtentRelevantCodes;

public class ComparePollDetailsRelCodes extends BaseTestExtentRelevantCodes{

	@Test
	public void comparePollDetails1() throws SQLException, IOException, ParseException
	{

		logger = extent.startTest("comparePollDetails");
		 
		logger.log(LogStatus.INFO, "Purpose: Comparing poll details and respective question associated with poll from Data base and API response");
		
		String QDescFromDB = null;
		String QDescFromAPI = null;
		getPollDetailsDB pollDetail = new getPollDetailsDB();
		
		ArrayList<pollDetailsDataObj> pollDetails1 =  pollDetail.getPollDetails();
		
		System.out.println("Count of poll IDs available in DB are: " +pollDetails1.size());
		logger.log(LogStatus.INFO, "No.of poll ids available in DB are: "+pollDetails1.size());
		
		for (pollDetailsDataObj pollDetailsDataObj1 : pollDetails1) 
		{
			
			//System.out.println("Poll details from DB are: " +pollDetailsDataObj1.getPollID() + " and " + pollDetailsDataObj1.getQuesDescrition());
			//logger.log(LogStatus.INFO, "Poll details from DB are: "+pollDetailsDataObj1.getPollID() + " and " + pollDetailsDataObj1.getQuesDescrition());
			if(pollDetailsDataObj1.getPollID() == 733)
			{
				QDescFromDB = pollDetailsDataObj1.getQuesDescrition();
				logger.log(LogStatus.INFO, "Question for the poll id 733 from DB is: "+QDescFromDB);
			}
			
		} 
			
		
		GetPollDetailsDO pollDetailAPI = new GetPollDetailsDO();
		ArrayList<pollDetailsDataObj> pollDetailFromAPI = pollDetailAPI.getPollDetailsAPI();
		System.out.println("Count of poll IDs available in API are: " +pollDetailFromAPI.size());
		logger.log(LogStatus.INFO, "No.of poll ids available in API are: "+pollDetailFromAPI.size());
		
		for (pollDetailsDataObj pollDetailsDataObjAPI : pollDetailFromAPI) 
		{
			//System.out.println("Poll details from API are: " +pollDetailsDataObjAPI.getPollID() + " and " + pollDetailsDataObjAPI.getQuesDescrition());
			//logger.log(LogStatus.INFO, "Poll details from API are: "+pollDetailsDataObjAPI.getPollID() + " and " + pollDetailsDataObjAPI.getQuesDescrition());
			if(pollDetailsDataObjAPI.getPollID() == 733)
			{
				QDescFromAPI = pollDetailsDataObjAPI.getQuesDescrition();
				logger.log(LogStatus.INFO, "Question for the poll id 733 from API is: "+QDescFromAPI);
			}
			
		}
		
		assertEquals(QDescFromDB, QDescFromAPI);
		logger.log(LogStatus.INFO, "Questions for the poll id mentioned are matching from DB and API");
		
	}

	
}
