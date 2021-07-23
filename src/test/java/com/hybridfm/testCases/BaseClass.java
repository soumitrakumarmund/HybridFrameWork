package com.hybridfm.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.hybridfm.utilities.ReadConfig;


public class BaseClass {
	
	ReadConfig readConfig=new ReadConfig();
	
	public String URL=readConfig.getBaseURL();
	public String username=readConfig.getUsername();
	public String password=readConfig.getPassword();
	public WebDriver dr;
	
	public static Logger log;
	
	@Parameters(value="browser")
	@BeforeClass
	public void setUp(String brwsr)
	{
		log=log.getLogger("Hybrid-Ebanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(brwsr.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
		dr=new ChromeDriver();
		}else if(brwsr.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readConfig.getIEPath());
			dr=new InternetExplorerDriver();
		}else if(brwsr.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
			dr=new FirefoxDriver();
		}
		
		dr.manage().window().maximize();
		dr.get(URL);
	}

	@AfterClass
	public void setDown()
	{
		dr.quit();
	}
	
	public void captureSreenshot(WebDriver driver, String tname) 
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		try {
			FileUtils.copyFile(src, target);
			System.out.println("Screenshot Taken");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
}
