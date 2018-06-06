package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtility {
	
	
	
	Properties prop = new Properties();
	InputStream input = null;
	
	try {

		input = new FileInputStream("./PropertyFiles/dbDetails.properties");

		// load a properties file
		prop.load(input);
		
	}catch (IOException ex) {
		ex.printStackTrace();
	} 

}
