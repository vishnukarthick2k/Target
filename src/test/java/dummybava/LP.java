package dummybava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LP extends BP {
	
	private By keepSigned = By.xpath(".//input[@id='keepMeSignedIn']");
	private By usrNameBox = By.xpath(".//input[@id='username']");
	private WebElement unb;
	private By pwrdBox = By.id(".//input[@id='password']");
	private WebElement pdb;
	private By showPwrd = By.xpath(".//button[text()='show']");
	private By signWithPass = By.xpath(".//button[@id='login']"); 
	private By warn0 = By.xpath(".//div[@class='sc-55a9f08c-0 gMjCue']");
	private By skip = By.xpath(".//a[text()='Skip']");
	private By maybelater = By.xpath(".//button[text()='Maybe later']");
	private WebElement mbl;
	private By addMobilegrp = By.cssSelector("sc-f82024d1-0.JJsAp");
	private By skipMobile = By.xpath(".//a[@class='sc-e851bd29-0 kkFFLD']");
	private By addBirthDate = By.xpath(".//div[@class='sc-f86dede5-8 hDwdGT']");
	private By skipBirthDate = By.xpath(".//button[@data-test='optin-skip-button']");
	private By recentLogin = By.xpath(".//span[contains(text(),'Sign into')]");
	private By completeSgout = By.id("invalidateSession");
	private By warning1 = By.id("username--ErrorMessage");
	private By warning2 = By.id("password--ErrorMessage");
	private By warning3 = By.xpath(".//div[@alerttype='warning']");
	
	public LP(WebDriver driver) {
		super(driver);
		logger.info("after loginpage constructor");
	}

	public boolean isKeepSignedEnabled() throws InterruptedException {
	    try {
	      Thread.sleep(5000);
		  return driver.findElement(keepSigned).isSelected();
	    }catch(Exception e) {return false;}
	 
	}
	public void checkKeepMeSigned() {
		jsTouch(driver.findElement(keepSigned));
	}
	public void setUsername(String uname) throws InterruptedException {
		unb = driver.findElement(usrNameBox);
		unb.clear();
		unb.sendKeys(uname);
	}
	public void setPassword(String passKey) throws InterruptedException {
		pdb = driver.findElement(pwrdBox);
		unb.clear();
		pdb.sendKeys(passKey);
	}
	public void inputClear() {
		unb.clear();pdb.clear();
	}
	
	public void showPassword() {
		driver.findElement(showPwrd).click();
	}
	public void signInByPword() {
		driver.findElement(signWithPass).click();
	}
	public boolean warn0() throws InterruptedException {
		try {
		   Thread.sleep(2000);
		   return driver.findElement(warn0).isEnabled();
		}catch(Exception e) {
			return false;
	    }
	}
	
	public void skip() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(skip).click();
	}
	public void mayBeLater() throws InterruptedException {
		Thread.sleep(2000);
		mbl = driver.findElement(maybelater);
		jsScrollView(mbl);
		mbl.click();
	}
	public boolean isMayBeDisplayed() {
		try{
		  return mbl.isDisplayed();
		}catch(Exception e){
		   return false;
		}
	}
	public boolean isMobileGrpDisplayed() throws InterruptedException {
		try {
		   Thread.sleep(3000);
		   return driver.findElement(addMobilegrp).isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	public void skipAddMobile() {
		driver.findElement(skipMobile).click();
	}
	
	public boolean isBirthDateDisplayed() throws InterruptedException {
		try {
		   Thread.sleep(4000);	
		   return driver.findElement(addBirthDate).isEnabled();
		}catch(Exception e) {
			return false;
		}
	}
	
	public void skipAddBirthDate() {
		driver.findElement(skipBirthDate).click();
	}
	
	public boolean recentLogin() throws InterruptedException {
		try {
			Thread.sleep(5000);
		    return driver.findElement(recentLogin).isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	public void completeSignout() {
		driver.findElement(completeSgout).click();
	}
	public boolean warn1() throws InterruptedException {
			try {
				Thread.sleep(2000);
			    return driver.findElement(warning1).isDisplayed();
			}catch(Exception e) {
				return false;
			}
	 }
	public boolean warn2() throws InterruptedException {
			try {
				Thread.sleep(2000);
			    return driver.findElement(warning2).isDisplayed();
			}catch(Exception e) {
				return false;
		   }
	}
	
	public boolean warn3() throws InterruptedException {
			try {
				Thread.sleep(2000);
			    return driver.findElement(warning3).isDisplayed();
			}catch(Exception e) {
				return false;
		   }
	}
}
