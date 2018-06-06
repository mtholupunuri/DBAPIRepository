package com.APIConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.testng.annotations.Test;

public class GetPollDetails {
	
	private static final String GET_URL = "http://192.168.150.82:9002/api/polldetails";
	static String responseValue;
	Long pollID;
	
	@Test
	public void pollDetails() throws IOException, ParseException
	
	{
		
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", "bearer a0e7a3c9-ce81-4e1a-8e8e-c564b1998eb6");
		con.setRequestProperty("Content-Type", "application/json");
		int responseCode = con.getResponseCode();
		//System.out.println("GET Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) 
		
		{ // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) 
			{
				response.append(inputLine);
			}
			in.close();

			// print result
			responseValue = response.toString();
			//System.out.println(response.toString());
			
						
		} else 
		{
			System.out.println("GET request not worked");
		}

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(responseValue);
		
		System.out.println(json);
		
		       
       	JSONArray jsonarr_1 = (JSONArray) json.get("pollDetailsInfoList");
       	
       	System.out.println("No. of poll ID : "+jsonarr_1.size());
        
       	for(int i=0;i<jsonarr_1.size();i++)
       	{
       		JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
       		JSONObject jsonobj_2 = (JSONObject) jsonobj_1.get("pollDetails");
       		Long pollID = (Long) jsonobj_2.get("pollId");
       		System.out.println(pollID);
       	}
		
		
	
}
	

}
