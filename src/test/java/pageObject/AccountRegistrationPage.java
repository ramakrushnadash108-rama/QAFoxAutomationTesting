package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver)
	{
		super (driver);
	}

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfPassword;
	
	@FindBy(xpath="//input[@name='newsletter' and @value='1']")
	WebElement chkNewsLetter;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tele)
	{
		txtTelephone.sendKeys(tele);
	}
	
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void setConfPassword(String confPass)
	{
		txtConfPassword.sendKeys(confPass);
	}
	
	public void clkNewsletter()
	{
		chkNewsLetter.click();
	}
	
	public void clkPrivacyPolicy()
	{
		chkPrivacyPolicy.click();
	}
	
	public void clkContinue()
	{
		btnContinue.click();
		
		// if click action is not working
		/*
		// slo1
		btnContinue.submit();
		
		//sol2
		btnContinue.sendKeys(Keys.RETURN);
		
		// sol3
		Actions action=new Actions(driver);
		action.moveToElement(btnContinue).click().perform();
		
		// sol4
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", btnContinue);
		
		//sol5
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		*/
	}
	
	public String getConfirmationMsg()
	{
		try {
			return (msgConfirmation.getText());
		} catch (Exception e){
			return (e.getMessage());
		}  
	}
}
