package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity", "Master"})
	public void Verify_login()
	{
		logger.info("**** Starting TC_002_LoinTest***");
		
		try {
		//homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(prt.getProperty("email"));
		lp.setPassword(prt.getProperty("password"));
		lp.clickLogin();
		logger.info("**** Login successful***");
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExists();
		if(targetPage==true)
		{
		map.clickMyAccount();
		map.clickLogout();
		}
		Assert.assertEquals(targetPage, true);
		//Assert.assertTrue(targetPage);
		
		logger.info("****My Account page is displayed***");
		}
		catch(Exception e)
		{
			Assert.fail();
			logger.info("****My Account page is not displayed***");
		}

		logger.info("*******Finished TC_002_LoginTest*****");	
		
	}
	
}
