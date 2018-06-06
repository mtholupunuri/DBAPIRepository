package com.APIConnection;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


public class GetAccessTokenGeneric {
	
	APIConnectionGeneric apiCon = new APIConnectionGeneric();
	
	public String accessToken() throws IOException, ParseException
	{
		String POST_URL = "http://192.168.150.82:9000/api/oauth-authenticate/idea";
		String POST_PARAMS = "{\"username\" :\"818088\", \"password\" : \"Welcome@789\"}";
				
		HttpURLConnection con = apiCon.getServiceAPI(POST_URL);
	
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
		con.setDoOutput( true );
		OutputStream os = con.getOutputStream();
		byte[] postData = POST_PARAMS.getBytes();
		os.write(postData);
		os.flush();
		os.close();

		int responseCode = con.getResponseCode();
		//System.out.println("POST Response Code :: " + responseCode);

		String responseValue1 = apiCon.getResponseFromAPI(responseCode);
		
		JSONObject json = apiCon.readJSON(responseValue1);
		
		//System.out.println(json);
		
		String AccessToken = (String) json.get("access_token");

		//System.out.println("Access token is : "+AccessToken);
		
		return AccessToken;

	}

}
