package com.DBAPITesting.Testcases;


import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.APIConnection.GetEmployeeDetailsDO;
import com.DBConnection.EmpDetailsDB;
import com.DataObjects.*;
import com.aventstack.extentreports.Status;
import com.util.BaseTest;

public class CompareEmpDetails extends BaseTest{
	
//	public ExtentReports extent;
//	public ExtentTest test;
	
	@Test
	public void compareEmpPoints() throws SQLException, IOException, ParseException {
		
//		extent = new ExtentReports("C:\\Users\\mtholupunuri\\Desktop\\testreport.html", true);
//		test = extent.startTest("compareEmpPoints");
//		 
//		test.log(LogStatus.INFO, "Purpose: Comparing employee points from Data base and API response");
		
		test =extent.createTest("compareEmpPoints");

		
		EmpDetailsDB empDetail = new EmpDetailsDB();
		ArrayList<Long> empDataFromDB = empDetail.getEmpPoints();
		System.out.println("Emp points from db are: "+empDataFromDB.get(0));
		test.log(Status.INFO, "Employee points from database are: " +empDataFromDB.get(0));
		
		
		GetEmployeeDetailsDO empDetailAPI = new GetEmployeeDetailsDO();
		EmpPointsDataObj empDataFromAPI = empDetailAPI.getEmpDetailsAPIDO();
		System.out.println("Emp points from API are: "+ empDataFromAPI.getPoints());
		test.log(Status.INFO, "Employee points from API are: "+empDataFromAPI.getPoints());
		
		assertEquals(empDataFromDB.get(0), empDataFromAPI.getPoints());
		
		ArrayList<EmpPointsDataObj> empDetails1 =  empDetail.getEmpDetails();
		for (EmpPointsDataObj empPointsDataObj : empDetails1) 
		{
			
			System.out.println("Emp ID and points from DB are: " +empPointsDataObj.getID() + " and " + empPointsDataObj.getPoints());
			test.log(Status.INFO, "Employee ID and points from DB are: "+empPointsDataObj.getID() + " and " + empPointsDataObj.getPoints());
		}
		
		System.out.println("Emp ID and points from API are: "+ empDataFromAPI.getID()+" and "+empDataFromAPI.getPoints());
		test.log(Status.INFO, "Employee ID and points from API are: "+empDataFromAPI.getID()+" and "+empDataFromAPI.getPoints());
		
//		extent.endTest(test);
//	    extent.flush();
		
	}
	
}
