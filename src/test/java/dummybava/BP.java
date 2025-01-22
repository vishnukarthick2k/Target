package dummybava;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BP {
    
	public WebDriver driver;
	protected JavascriptExecutor js;
	protected Logger logger;
	protected Actions mouseaction;
	protected WebDriverWait mywait;
	
	public BP(WebDriver Driver) {
		//pilot(Driver);
		logger = LogManager.getLogger(this.getClass());
	    driver = Driver;
		//mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	public void jsScrollView(WebElement obj) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", obj);
	}
	public void jsTouch(WebElement obj) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", obj);
	}
	public void refreshPage() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(4000);
	}
	public void jsScrollBy(int from,int to) {
		js.executeScript("window.scrollBy("+from+","+to+");");
	}
	
	public Actions mouse(WebElement obj) {
		mouseaction = new Actions(driver);
		return mouseaction.moveToElement(obj); 
	}
	public void goToPreviousPage() throws InterruptedException {
	  Thread.sleep(2000);
	  driver.navigate().back();
	  Thread.sleep(8000);
	}
	public void goToNextPage() throws InterruptedException {
	   Thread.sleep(2000);
	   driver.navigate().forward();
	   Thread.sleep(8000);
	}
	
	private synchronized void pilot(WebDriver driver) {
		this.driver = driver;
	}
}
