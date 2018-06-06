package com.util;

import java.io.File;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTestExtentRelevantCodes {
	
	 public static ExtentReports extent;
	 public static ExtentTest logger;
	 
	 
	 @BeforeTest
	 public void startTest(){
	
	 extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/TestReport.html", true);
	 extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	
	 }
	 
	 @AfterMethod
	 public void getResult(ITestResult result)
		{
			if (result.getStatus()==ITestResult.FAILURE)
			{
				logger.log(LogStatus.FAIL, "Testcase failed is "+(result.getName()));
				logger.log(LogStatus.FAIL, "Test case failed is  "+result.getThrowable());
				
			}
			else if(result.getStatus()==ITestResult.SUCCESS)
			{
				logger.log(LogStatus.PASS, "Testcase passed is "+(result.getName()));
							
			}
			else
			{
				logger.log(LogStatus.SKIP, "Testcase skipped is "+(result.getName()));
			}
			extent.endTest(logger);
		}
	 
	 @AfterTest
	 public void endReport()
	 {
		 extent.flush();
		 extent.close();
	 }

}
