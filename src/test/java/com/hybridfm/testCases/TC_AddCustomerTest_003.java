package com.hybridfm.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hybridfm.pageObjects.AddCustomerPage;
import com.hybridfm.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(dr);
		lp.setUsername(username);
		log.info("User name is provided");
		lp.setPassword(password);
		log.info("Passsword is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(dr);
		
		addcust.clickAddNewCustomer();
		
		log.info("providing customer details....");
		
		
		addcust.custName("Pavan");
		addcust.custgender("male");
		addcust.custdob("10","15","1985");
		Thread.sleep(5000);
		addcust.custaddress("INDIA");
		addcust.custcity("HYD");
		addcust.custstate("AP");
		addcust.custpinno("5000074");
		addcust.custtelephoneno("987890091");
		
		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		log.info("validation started....");
		
		boolean res=dr.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			log.info("test case passed....");
			
		}
		else
		{
			log.info("test case failed....");
			captureSreenshot(dr,"addNewCustomer");
			Assert.assertTrue(false);
		}
			
	}
}
