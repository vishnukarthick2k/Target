package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcases.Base_Test;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public String reportName;
    public URL url;
    
    public void onStart(ITestContext testContext){
    	
    	//lengthy
    	/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    	Date dt = new Date();
    	String currentDateTimeStamp = df.format(dt); */
    	
    	//simple
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	reportName = "Test-Report-" + timeStamp + ".html";
    	sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName); // specify local 
    	
    	sparkReporter.config().setDocumentTitle("Target e-commerce Automation Report"); // report Title
    	sparkReporter.config().setReportName("Target Functional Testing"); // report name
    	sparkReporter.config().setTheme(Theme.DARK);
    	
    	extent = new ExtentReports();
    	extent.attachReporter(sparkReporter);
    	extent.setSystemInfo("Application","Target Online Shopping");
    	extent.setSystemInfo("Module","Admin");
    	extent.setSystemInfo("Sub Module","Customers");
    	extent.setSystemInfo("User Name",System.getProperty("user.name"));
    	extent.setSystemInfo("Environment","QA");
    	
    	String os = testContext.getCurrentXmlTest().getParameter("os");
    	extent.setSystemInfo("Operating System",os);
    	
    	String browser = testContext.getCurrentXmlTest().getParameter("browser");
    	extent.setSystemInfo("Browser",browser);
    	
    	List <String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
    	if(!includedGroups.isEmpty()) {extent.setSystemInfo("Groups", includedGroups.toString());}
  
    }
    public void  onTestSuccess(ITestResult result) {
    	test = extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups()); // to display groups in report
    	test.log(Status.PASS,result.getName()+ " got successfully executed");
    }
    
    public void onTestFailure(ITestResult result) {
    	test = extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.log(Status.FAIL,result.getName()+ " got failed executed");
    	test.log(Status.INFO,result.getThrowable().getMessage());
    	
    	try {
    		String imgPath = new Base_Test().captureScreenshot(result.getName());
    		test.addScreenCaptureFromPath(imgPath);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void onTestSkipped(ITestResult result) {
    	test = extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.log(Status.SKIP,result.getName()+ " got skipped");
    	test.log(Status.INFO,result.getThrowable().getMessage());
    }
    
    public void onFinish(ITestContext testContext){
    	extent.flush();
       
    	// open the report automatically on the browser after execution
    	String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
        File extentReport = new File(pathOfExtentReport);
        
        try {
        	Desktop.getDesktop().browse(extentReport.toURI());
        }catch(Exception e) {
        	e.printStackTrace();
        }
        // Method 1 - using Apache POI Commons
        // After execution the generated report will be sent through email 
        // don't try multiple times because gmail will get blocked
        /*try{
        	URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName);
        	// Create the email message
        	ImageHtmlEmail email = new ImageHtmlEmail();
        	email.setDataSourceResolver(new DataSourceUrlResolver(url));
        	email.setHostName("smtp.gmail.com");
        	email.setSmtpPort(587);  //465
        	email.setAuthenticator(new DefaultAuthenticator("vishnukarthickmatsq@gmail.com","$Vishnu24k"));
        	email.setSSLOnConnect(true);
        	email.setFrom("vishnukarthickmatsq@gmail.com");  // Sender
        	email.setSubject("Test Results");
        	email.setMsg("Please find the attached report");
        	email.addTo("iamvishnukarthick082@gmail.com");  // receiver
        	email.attach(url,"extent report","please check report...");
        	email.send();  // send the email
        }catch(Exception e) {
        	e.printStackTrace();
        } 
     */
        //  Method 2 - using javamail.jar
        try {
           MyEmail email = new MyEmail();
           email.setSession("vishnukarthickmatsq@gmail.com","$Vishnu24k");
           email.draftMail("Email from TestNg report", "iamvishnukarthick082@gmail.com", "2018pmc033vishnukarthick.m@gmail.com");
           email.draftEmailBody("<h1>Hi Team, Here, the executed test report reference.</h1>", "text/html", url.toString(), ".\\DD_output\\validlogs.xlsx");
           email.sendIt();
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
