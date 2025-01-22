package dummybava;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pageobjects.CreateAcc_Page;
import pageobjects.Home_Page;
import pageobjects.Login_Page;
import pageobjects.ManageAcc_Page;
import utilities.ReadConfigProps;

public class BT {
    
	public WebDriver driver;
	protected Logger logger;
	protected HP hp;
	protected LP lp;
	protected ReadConfigProps pptfile;
	protected CreateAcc_Page cracc;
	protected ManageAcc_Page macc;
	
	@BeforeClass
	void setup() throws InterruptedException {
		pptfile = new ReadConfigProps(".\\src\\main\\resources\\config.properties");
		pptfile.loadfile();
		logger = LogManager.getLogger(this.getClass());
		 
		switch("CHROME".toLowerCase()) {
		    case "chrome" : driver = new ChromeDriver();break;
		    case "firefox" : driver = new FirefoxDriver();break;
		    case "edge" : driver = new EdgeDriver();break;
		    default : System.out.println("Enter valid browser");return;
	    }
		 
		driver.get(pptfile.url());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 }
	
	//@AfterClass
	void teardown() {
		driver.quit();
	}
}	
