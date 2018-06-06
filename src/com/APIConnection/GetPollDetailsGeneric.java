package com.APIConnection;


import java.io.IOException;
import java.net.HttpURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class GetPollDetailsGeneric {

	APIConnectionGeneric apiCon = new APIConnectionGeneric();
	GetAccessTokenGeneric token = new GetAccessTokenGeneric();
	
	@Test
	public void getPollDetailsAPI() throws IOException, ParseException
	{
		String GET_URL = "http://192.168.150.82:9002/api/polldetails";
		String responseValue;
		Long pollID;
		String QDesc;
		String accessToken = token.accessToken();
		
		HttpURLConnection con = apiCon.getServiceAPI(GET_URL);
		
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", "bearer "+accessToken);
		con.setRequestProperty("Content-Type", "application/json");
		
		int responseCode = con.getResponseCode();
		//System.out.println("GET Response Code :: " + responseCode);
		
		responseValue = apiCon.getResponseFromAPI(responseCode);
		
		JSONObject json = apiCon.readJSON(responseValue);		
		System.out.println(json);
		
		       
       	JSONArray jsonarr_1 = (JSONArray) json.get("pollDetailsInfoList");
       	
       	System.out.println("No. of poll ID : "+jsonarr_1.size());
        
       	for(int i=0;i<jsonarr_1.size();i++)
       	{
       		JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
       		JSONObject jsonobj_2 = (JSONObject) jsonobj_1.get("pollDetails");
       		pollID = (Long) jsonobj_2.get("pollId");
       		QDesc = (String) jsonobj_2.get("questionDesc");
       		System.out.println(pollID + " and " + QDesc );
       	}
		
		
	
}

	
}
