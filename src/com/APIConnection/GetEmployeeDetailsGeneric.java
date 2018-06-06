package com.APIConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class GetEmployeeDetailsGeneric {
	
	APIConnectionGeneric apiCon = new APIConnectionGeneric();
	GetAccessTokenGeneric token = new GetAccessTokenGeneric();
	
	@Test
	public void getEmpDetailsAPI() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		Long pointsAPI = null;
		
		String GET_URL = "http://192.168.150.82:9020/api/leaderBoard/empPoints/816739";
		String accessToken = token.accessToken();
		
		HttpURLConnection con = apiCon.getServiceAPI(GET_URL);
		
		con.setRequestMethod("GET");
		
		con.setRequestProperty("Authorization", "bearer "+accessToken);
		
		int respCode = con.getResponseCode();
		
		//System.out.println("GET Response Code : " + responseCode);
		
		String responseValue = apiCon.getResponseFromAPI(respCode);
		
		JSONObject json = apiCon.readJSON(responseValue);
		
		System.out.println(json);
		
		pointsAPI = (long) json.get("points");
		
		System.out.println(pointsAPI);
		
//		return pointsAPI;
		
		
	}


}
