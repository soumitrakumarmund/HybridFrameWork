package com.hybridfm.testCases;


import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridfm.pageObjects.LoginPage;
import com.hybridfm.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{
	
	public WebDriver driver;
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(user);
		log.info("UserName Provided");
		lp.setPassword(pwd);
		log.info("Password Provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			log.info("Login Failed");
		}else
		{
			Assert.assertTrue(true);
			log.info("Login Passes");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		captureSreenshot(driver, "loginDDT()");
	}

	@DataProvider(name="LoginData")
	String[][] getData()
	{
		
		String path=System.getProperty("user.dir")+"/src/test/java/com/hybridfm/testData/logindata.xlsx";
		
		int rowcount=XLUtils.getRowCount(path, "userdata");
		int cellcount=XLUtils.getCellCount(path, "userdata", 1);
		
		String logindate[][]=new String[rowcount][cellcount];
		
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<cellcount;j++)
			{
				logindate[i-1][j]=XLUtils.getCellData(path, "userdata", i, j);
			}
		}
		
		return logindate;
	}
	
	public boolean isAlertPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e) {
			return false;
		}
	}
}
