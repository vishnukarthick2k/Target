package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home_Page extends Base_Page {
	
	private By accMenuBefore = By.id("account-sign-in");
	private By accMenuAfter = By.id("account-sign-in");
	private By signin = By.xpath(".//button[@data-test='accountNav-signIn']");
	private By crrAcc = By.xpath(".//button[@data-test='accountNav-createAccount']");
	private By signout = By.xpath(".//span[text()='Sign out']");
	private By hiUser = By.xpath(".//div[@class='sc-b5d0650b-2 gSvPkw']//h2"); 
	private By manageAcc = By.xpath(".//span[text()='Manage account']");
	private WebElement sout;
    
    public Home_Page(WebDriver driver) {
    	super(driver);
    	logger.info("after hompage constructor");
    }
	public void clickAccMenuB4() throws InterruptedException {
		Thread.sleep(8000);
		WebElement sii = driver.findElement(accMenuBefore); sii.click();
		//mouse(sii).click().perform();
		//jsTouch(sii);
		Thread.sleep(4000);
	}
	public void clickAccMenuAfter() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElement(accMenuAfter).click();
		Thread.sleep(4000);
	}
	public void clickSignin() throws InterruptedException {
		driver.findElement(signin).click();
		Thread.sleep(4000);
	}
	public void clickCreateAcc() throws InterruptedException {
		driver.findElement(crrAcc).click();
		Thread.sleep(5000);
	}
	
	public void hiUser() {
		mouse(driver.findElement(hiUser)).build().perform();
	}
	public void manageAccount() throws InterruptedException {
		driver.findElement(manageAcc).click();
		Thread.sleep(10000);
	}
	public boolean clickSignout() {
		boolean result;
		jsScrollBy(0,2000);
		try{
	    sout = driver.findElement(signout); result = sout.isDisplayed();
		mouse(sout).click().perform();
		}catch(Exception e) { result = false; }
		return result;
	}
}
