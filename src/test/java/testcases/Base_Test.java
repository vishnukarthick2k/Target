package testcases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

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

import pageobjects.CreateAcc_Page;
import pageobjects.Home_Page;
import pageobjects.Login_Page;
import pageobjects.ManageAcc_Page;
import utilities.ReadConfigProps;

public class Base_Test {
    
	public static WebDriver driver;
	public static Logger logger;
	protected Home_Page hp;
	protected Login_Page lp;
	protected ReadConfigProps pptfile;
	protected CreateAcc_Page cracc;
	protected ManageAcc_Page macc;
	
	@BeforeClass(groups={"Master","Sanity","Regression"})
	@Parameters({"os","browser"})
	void setup(String os,String br) throws InterruptedException, MalformedURLException {
		pptfile = new ReadConfigProps(".\\src\\main\\resources\\config.properties");
		pptfile.loadfile();
		logger = LogManager.getLogger(this.getClass());
		 
	   if(pptfile.executeOn().equalsIgnoreCase("remote")) {
			  DesiredCapabilities capabilities = new DesiredCapabilities();
		   // switch os	 
		    switch(os.toLowerCase()) {
			     case "windows" :capabilities.setPlatform(Platform.WIN10);break;
			     case "mac" :capabilities.setPlatform(Platform.MAC);break;
			     case "linux" :capabilities.setPlatform(Platform.LINUX);break;
			     default : logger.info("set valid platform!");return;
		   }
		   
		   // switch browser
		   switch(br.toLowerCase()) {
		        case "chrome" : capabilities.setBrowserName("chrome");break;
		        case "edge" : capabilities.setBrowserName("MicrosoftEdge");break;
		        case "firefox" : capabilities.setBrowserName("firefox");break;
		        default : logger.info("set valid browser!");return;
		   }
		     driver = new RemoteWebDriver(new URL("http://192.168.1.106:4444/wd/hub"),capabilities); 
	   }
	   
       if(pptfile.executeOn().equalsIgnoreCase("local")) {
		      switch(br.toLowerCase()) {
		          case "chrome" : driver = new ChromeDriver();break;
		          case "firefox" : driver = new FirefoxDriver();break;
		          case "edge" : driver = new EdgeDriver();break;
		          default : System.out.println("Enter valid browser");return;
	         }
		}
         driver.manage().deleteAllCookies();
	     driver.get(pptfile.url());
	     logger.info(driver.getTitle());
	     driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	//@AfterClass (groups={"Master","Sanity","Regression"})
	void teardown() {
		driver.quit();
	}
	
	public String captureScreenshot(String testName) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshot\\"+ testName +"_"+timeStamp +".png"; 
	    File targetFile = new File(targetFilePath);
	    
	    sourceFile.renameTo(targetFile);
	    
	    return targetFilePath;
	}
	
	public String randomText(int size) {
         return RandomStringUtils.randomAlphabetic(size);
	}
	
	public String randomNumber(int size) {
		return RandomStringUtils.randomNumeric(size);
	}
	
	public String randomAlphaNumbers(int size) {
		return RandomStringUtils.randomAlphanumeric(size);
	}
}	
