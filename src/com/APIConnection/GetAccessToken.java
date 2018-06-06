package com.APIConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetAccessToken {

	private static final String POST_URL = "http://192.168.150.82:9000/api/oauth-authenticate/idea";
	private static final String POST_PARAMS = "{\"username\" :\"818088\", \"password\" : \"Welcome@789\"}";
	static String responseValue;
	long pointsAPI;

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub


		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
		con.setDoOutput( true );
		OutputStream os = con.getOutputStream();
		byte[] postData = POST_PARAMS.getBytes();
		os.write(postData);
		os.flush();
		os.close();



		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
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
		} else {
			System.out.println("POST request not worked");
		}

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(responseValue);
		String AccessToken = (String) json.get("access_token");

		System.out.println("Access token is : "+AccessToken);
		

	}
}
