package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistraionTest extends BaseClass {

	@Test(groups={"Regression", "Master"})
	public void verify_account_registraion()
	{
		logger.info("****** Stating T001_AccountRegistraionTest***");
		
		try {
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
				
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
//		arp.setFirstName("Rama");
//		arp.setLastName("Dash");
//		arp.setEmail("rkdash0029@gmail.com");
//		arp.setPassword("Rama@1234");
//		arp.setTelephone("69587446565");		
		// provide rendom string

		arp.setFirstName(rendomString().toUpperCase());
		arp.setLastName(rendomString().toUpperCase());
		arp.setEmail(rendomString()+"@gmail.com");
		arp.setTelephone(rendomNumber());
		String password=rendomAlphaNumeric();
		arp.setPassword(password);
		arp.setConfPassword(password);
		
		arp.clkNewsletter();
		arp.clkPrivacyPolicy();
		arp.clkContinue();
		
		String confMessage=arp.getConfirmationMsg();
		Assert.assertEquals(confMessage, "Your Account Has Been Created!", "Page title mismatch");
		
		}
		catch(Exception e)
		{
//			logger.error("Test Failed...");
//			logger.debug("Debug logs...");
			Assert.fail("Exception occurred: " + e.getMessage());
		}
		
		logger.info("****FinishT001_AccountRegistraionTest***");
	}
}
