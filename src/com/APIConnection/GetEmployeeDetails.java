package com.APIConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class GetEmployeeDetails {
	
	private static final String GET_URL = "http://192.168.150.82:9020/api/leaderBoard/empPoints/816739";
	static String responseValue;


	public long APIConnection() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		Long pointsAPI = null;
		
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", "bearer ca8eb9bb-f86b-4669-9cab-93276a475493");
		int responseCode = con.getResponseCode();
		//System.out.println("GET Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			responseValue = response.toString();
			//System.out.println(response.toString());
			
						
		} else {
			System.out.println("GET request not worked");
		}

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(responseValue);
		pointsAPI = (long) json.get("points");
		
		return pointsAPI;
		
		
	}
}
