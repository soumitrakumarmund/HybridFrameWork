package com.hybridfm.testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.hybridfm.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{

	
	@Test
	public void loginTest() throws InterruptedException
	{		
		log.info("URL is opened");
		LoginPage lp=new LoginPage(dr);
		log.info("we Landed in Login Homepage and giving username and password");
		lp.setUsername(username);
		lp.setPassword(password);
		lp.clickSubmit();
		
		log.info("We are in Main HomePage");
		String acttitle=dr.getTitle();
		
		if(acttitle.equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true, "Title found");
			log.info("Test passed");
		}
		else
		{
			captureSreenshot(dr, "loginTest()");
			Assert.assertTrue(false, "Title Not found");
			log.info("Test failed");
		}
	}
}
