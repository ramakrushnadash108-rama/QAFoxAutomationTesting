package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		/*
		 SimpleDateFormat df=new SimpleDateFormat("yyy.MM.dd.HH.mm.ss");
		 Date dt=new Date();
		 String currentdatetimestamp=df.format(dt);
		 */
		//Combine above three line as single line
		String timeStamp=new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());	// Time Stamp
		
		repName="Test-Report-"+ timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);	// specify location of the report
				
		sparkReporter.config().setDocumentTitle("QAFOX Automation Report");	// Title of the report
		sparkReporter.config().setReportName("QAFOX Functional Testing");	// Name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "QAFOX");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups= testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());	// to display groups in report
		test.log(Status.PASS, result.getName()+"got successfully executed");

	}
	
	public void onTestFailure(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		 
		try {
			String imgPath = new BaseClass().captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext result) {
		extent.flush();
		
		// Automatic open report file
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		// Automatic send report by email
		
		try {
			
			URL url = new URL("file://"+System.getProperty("user.dir")+"\\reports\\"+repName);
			
			// create the email message
			ImageHtmlEmail email= new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("rkdash0029@gmail.com", "Rama@1234"));
			email.setSSLOnConnect(true);
			email.setFrom("rkdash0029@gmail.com");	// sender
			email.setSubject("Test Results");
			email.setMsg("Please find Attached Report...");
			email.addTo("rkdash0028@gmail.com");	// receiver
			email.attach(url, "extent report", "please check report...");
			email.send();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	*/
	
}

}
