package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	public static WebDriver driver;
	public Logger logger;
	public Properties prt;
	
	@BeforeClass(groups={"Sanity", "Regression", "Master", "DataDriven"})
	@Parameters({"os" , "browser"})
	public void setup(String os, String br) throws IOException              
	{
		// loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		prt=new Properties();
		prt.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(prt.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capability=new DesiredCapabilities();
			
			// os
			if(os.equalsIgnoreCase("windows"))
			{
				capability.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capability.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No macthing os");
				return;
			}
			
			//browser
			switch (br.toLowerCase())
			{
			case "chrome" : capability.setBrowserName("chrome"); break;
			case "edge" : capability.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No mactching browser"); return;
			}
			
			// launch remoteWebDriver
			driver =new RemoteWebDriver(new URL("http://10.177.222.56:4444/wd/hub"), capability);
		}
		
		if(prt.getProperty("execution_env").equalsIgnoreCase("local"));
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			case "firefox" : driver=new FirefoxDriver(); break;
			default : System.out.println("invlid browser name..."); return;
			}
		
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(prt.getProperty("appURL2"));	// reading url from properties file
	}
	
	@AfterClass(groups={"Sanity", "Regression", "Master", "DataDriven"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String rendomString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	
	public String rendomNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String rendomAlphaNumeric()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(4);
		String generatedNumber=RandomStringUtils.randomNumeric(4);
		return (generatedstring+"@"+generatedNumber);
	}
	
	public String captureScreenshot(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp +".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}
	
}
