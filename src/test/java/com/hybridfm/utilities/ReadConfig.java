package com.hybridfm.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	public static Properties pro;
	public ReadConfig() {
		
		try {
			FileInputStream fis=new FileInputStream("./Configuration/config.properties");  
			pro=new Properties();
			pro.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public String getBaseURL()
	{
		return pro.getProperty("baseURL");
	}
	
	public String getUsername() 
	{
		return pro.getProperty("username");
	}
	
	public String getPassword() 
	{
		return pro.getProperty("password");
	}
	
	public String getChromePath() 
	{
		return pro.getProperty("chromepath");
	}
	
	public String getIEPath() 
	{
		return pro.getProperty("iepath");
	}
	
	public String getFirefoxPath() 
	{
		return pro.getProperty("firefoxpath");
	}
	
}
