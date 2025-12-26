package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

//LoginDDT- LoginDataDrivenTest

	/*
	 Conditions:
	 
	 Data is valid  - login is successful - test pass - logout
	 				  login is failed	  - test fail
	 
	 Data is invalid - login is successful - test fail - logout
	 				 - login is fail       - test pass
	 */

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")	// getting data provider from different class
	public void verify_loginDDT(String email, String password, String expResult ) 
	{
		logger.info("***** Starting TC003_LoginDDT *******");
		try {
		
		//homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
			
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		//MyAccount page
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExists();
			
		if(expResult.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				map.clickMyAccount();
				map.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(expResult.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				map.clickMyAccount();
				map.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** finished TC003_LoginDDT *******");
	}

	
	
	
}
