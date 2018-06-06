package com.APIConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.DataObjects.EmpPointsDataObj;

public class GetEmployeeDetailsDO {
	
	APIConnectionGeneric apiCon = new APIConnectionGeneric();
	GetAccessTokenGeneric token = new GetAccessTokenGeneric();

	
	public  EmpPointsDataObj getEmpDetailsAPIDO() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		String GET_URL = "http://192.168.150.82:9020/api/leaderBoard/empPoints/126251";
		String accessToken = token.accessToken();
		
		HttpURLConnection con = apiCon.getServiceAPI(GET_URL);
		
		con.setRequestMethod("GET");
		
		con.setRequestProperty("Authorization", "bearer "+accessToken);
		
		int respCode = con.getResponseCode();
		
		//System.out.println("GET Response Code : " + responseCode);
		
		String responseValue = apiCon.getResponseFromAPI(respCode);
		
		JSONObject json = apiCon.readJSON(responseValue);
		
		//System.out.println(json);
		
	//	ArrayList<EmpPointsDataObj>  EmpList= new ArrayList<>();
		EmpPointsDataObj empPointsDataObj = null;
		if (json!=null)
		{
				
			empPointsDataObj = new EmpPointsDataObj((String) json.get("empId"), (Long) json.get("points"));
		
		}
	return empPointsDataObj;
		
}
	
}
