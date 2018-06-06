package com.DBAPITesting.Testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.APIConnection.GetEmployeeDetailsDO;
import com.DBConnection.EmpDetailsDB;
import com.DataObjects.EmpPointsDataObj;
import com.relevantcodes.extentreports.LogStatus;
import com.util.BaseTestExtentRelevantCodes;

public class CompareEmpDetailsRelCodes extends BaseTestExtentRelevantCodes{

	@Test
	public void compareEmpPoints() throws SQLException, IOException, ParseException {
		

		logger = extent.startTest("compareEmpPoints");
		logger.log(LogStatus.INFO, "Purpose: Comparing employee points from Data base and API response");

		EmpDetailsDB empDetail = new EmpDetailsDB();
		ArrayList<Long> empDataFromDB = empDetail.getEmpPoints();
		if(empDataFromDB != null && empDataFromDB.size() > 0) {
			System.out.println("Emp points from db are: "+empDataFromDB.get(0));
			logger.log(LogStatus.INFO, "Employee points from database are: " +empDataFromDB.get(0));
		}
		else{
			
			System.out.println("Given employee ID returns null values");
			logger.log(LogStatus.INFO, "Given employee ID returns null values");
		}
		
		
		GetEmployeeDetailsDO empDetailAPI = new GetEmployeeDetailsDO();
		EmpPointsDataObj empDataFromAPI = empDetailAPI.getEmpDetailsAPIDO();
		System.out.println("Emp points from API are: "+ empDataFromAPI.getPoints());
		logger.log(LogStatus.INFO, "Employee points from API are: "+empDataFromAPI.getPoints());
		
		if(empDataFromDB != null && empDataFromDB.size() > 0){
		assertEquals(empDataFromDB.get(0), empDataFromAPI.getPoints());
		logger.log(LogStatus.INFO, "Employee points from DB and API are matching");
		}
		
		ArrayList<EmpPointsDataObj> empDetails1 =  empDetail.getEmpDetails();
		
		for (EmpPointsDataObj empPointsDataObj : empDetails1) 
		{
			
			System.out.println("Emp ID and points from DB are: " +empPointsDataObj.getID() + " and " + empPointsDataObj.getPoints());
			logger.log(LogStatus.INFO, "Employee ID and points from DB are: "+empPointsDataObj.getID() + " and " + empPointsDataObj.getPoints());
		}
		
		System.out.println("Emp ID and points from API are: "+ empDataFromAPI.getID()+" and "+empDataFromAPI.getPoints());
		logger.log(LogStatus.INFO, "Employee ID and points from API are: "+empDataFromAPI.getID()+" and "+empDataFromAPI.getPoints());
		
	}

	
}
