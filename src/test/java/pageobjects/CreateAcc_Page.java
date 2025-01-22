package pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAcc_Page extends Base_Page{
   private By pageTitle = By.tagName("h1");	
   private By email = By.id("username");
   private By firstName = By.id("firstname");
   private By lastName = By.id("lastname");
   private By phone = By.id("phone");
   private By passCheckbox = By.id("password-checkbox");
   private By pass = By.id("password");
   private By viewit = By.xpath(".//button[@class='sc-e851bd29-0 dUpEFr']");
   private By keepMeIn = By.id("keepMeSignedIn");
   private By createAcc = By.id("createAccount");
   private By mayBeLater = By.xpath(".//button[text()='Maybe later']");
   private By feedbackFrame = By.id("kampyleInvite");
   private By noThanks = By.xpath(".//button[text()='No thanks']");
   private By warning1 = By.xpath(".//div[@class='sc-55a9f08c-0 gMjCue']");
   private By warning2 = By.xpath(".//span[@class='sc-7a3810b7-0 kQMtuE']");
   private By suddenlogin = By.xpath(".//span[text()='Sign into your Target account']");
   private By verifycodepops = By.xpath(".//h1[@class='sc-fe064f5c-0 lnvRvp']");
   public CreateAcc_Page(WebDriver driver) {
	   super(driver);
	   mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
   }
   
   public void setEmailAdrss(String eaddress) {
	   WebElement eid = driver.findElement(email);
	   eid.clear();
	   eid.sendKeys(eaddress);
   }
   public void setFstName(String fname) {
	   WebElement fstName = driver.findElement(firstName);
	   fstName.clear();
	   fstName.sendKeys(fname);
   }
   public void setLstName(String lname) {
	   WebElement lstName = driver.findElement(lastName);
	   lstName.clear();
	   lstName.sendKeys(lname);
   }
   public void setMobile(String mnum) {
	   WebElement mbnumber = driver.findElement(phone);
	   mbnumber.clear();
	   mbnumber.sendKeys(mnum);
   }
   
   public void checkWithPassword() {
	   driver.findElement(passCheckbox).click();
   }
   public void setPassword(String password) throws InterruptedException {
	 Thread.sleep(2000);  
	 WebElement passkey =  driver.findElement(pass);
	 passkey.clear();
	 passkey.sendKeys(password);
   }
   
   public void showHidePass() {
	   driver.findElement(viewit).click();
   }
   public void keepSignedIn() {
	   driver.findElement(keepMeIn).click();
   }
   public void clickCreateacc() throws InterruptedException {
	   driver.findElement(createAcc).click();
	   Thread.sleep(5000);
   }
   public boolean isVerificationCodePops() {
	   try {Thread.sleep(3000); return driver.findElement(verifycodepops).isDisplayed();}
	   catch(Exception E) {return false;}
   }
   public boolean warningShows1() {
	   try { Thread.sleep(1000); return driver.findElement(warning1).isDisplayed();}catch(Exception e) { return false;}
   }
   public boolean warningShows2() {
	   try { Thread.sleep(1000); return driver.findElement(warning2).isDisplayed();}catch(Exception e) { return false;}
   }
   public void clickMayBeLater() throws InterruptedException {
	   Thread.sleep(5000);
	   driver.findElement(mayBeLater).click();
   }
   public void feedBackReply() {
	   try {
	   mywait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(feedbackFrame));
	   Thread.sleep(3000);
	   driver.findElement(noThanks).click();
	   Thread.sleep(2000);
	   }catch(Exception e) {return;}
   }
   public String verifyPageTitle() {
	   return driver.findElement(pageTitle).getText();
   }
   public boolean checkIfDisplayed() throws InterruptedException {
	   Thread.sleep(5000);
	   try {
		   return driver.findElement(suddenlogin).isDisplayed();
	   }catch(Exception e) {
		   return false;
	   }
   }
   
}
