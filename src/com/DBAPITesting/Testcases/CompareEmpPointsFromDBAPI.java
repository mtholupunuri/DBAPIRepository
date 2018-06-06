package com.DBAPITesting.Testcases;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import com.APIConnection.*;
import com.DBConnection.*;


public class CompareEmpPointsFromDBAPI {

	public static void main(String[] args) throws ClassNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		
		DBConnectionEmpPoints empPointsDB = new DBConnectionEmpPoints();
		long empPoints1 = empPointsDB.dbConnection();
		System.out.println("Employee points from DB are: "+ empPoints1);
		
		GetEmployeeDetails empPointsAPI = new GetEmployeeDetails();
		long empPoints2 = empPointsAPI.APIConnection();
		System.out.println("Employee points from API are: "+ empPoints2);
		
		if (empPoints1 == empPoints2)
		{
			System.out.println("Employee points from DB and API are matching");
			
		}
		else
		{
			System.out.println("Employee points from DB and API are NOT matching");
		}
		
	}

}
