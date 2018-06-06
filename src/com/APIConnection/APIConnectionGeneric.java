package com.APIConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APIConnectionGeneric {

	String responseValue;
	int responseCode;
	
	HttpURLConnection con = null;

	public HttpURLConnection getServiceAPI(String url) throws IOException
	{
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
				
		return con;
	}
	
	
	public int getResponseCode(HttpURLConnection con) throws IOException
	{
		responseCode = con.getResponseCode();
		return responseCode;
	}
		
	public String getResponseFromAPI(int responseCode) throws IOException
		{
		
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
			System.out.println("GET/POST request not worked");
		}
		if (responseValue!=null){
		return responseValue;
		}
		else {
			
			return null;
		}
	}
		
	public JSONObject readJSON(String respValue) throws ParseException{
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(respValue);
		return json;
		}
		
}
	
	

