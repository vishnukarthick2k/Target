package dummybava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HP extends BP {
	
	private By accMenuBefore = By.id("account-sign-in");
	private By accMenuAfter = By.id("account-sign-in");
	private By signin = By.xpath(".//button[@data-test='accountNav-signIn']");
	private By crrAcc = By.xpath(".//button[@data-test='accountNav-createAccount']");
	private By signout = By.xpath(".//span[text()='Sign out']");
	private By hiUser = By.xpath(".//div[@class='sc-b5d0650b-2 gSvPkw']//h2"); 
	private By manageAcc = By.xpath(".//span[text()='Manage account']");
	private WebElement sout;
    
    public HP(WebDriver driver) {
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
		Thread.sleep(8000);
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
		Thread.sleep(8000);
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
	/*
	private By keepSigned = By.id("keepMeSignedIn");
	private By usrNameBox = By.id("username");
	private WebElement unb;
	private By pwrdBox = By.id("password");
	private WebElement pdb;
	private By showPwrd = By.xpath(".//button[text()='show']");
	private By signWithPass = By.id("login"); 
	private By warn0 = By.xpath(".//div[@class='sc-55a9f08c-0 gMjCue']");
	private By skip = By.xpath(".//a[text()='Skip']");
	private By maybelater = By.xpath(".//button[text()='Maybe later']");
	private WebElement mbl;
	private By addMobilegrp = By.className("sc-f82024d1-0 JJsAp");
	private By skipMobile = By.xpath(".//a[@class='sc-e851bd29-0 kkFFLD']");
	private By addBirthDate = By.xpath(".//div[@class='sc-f86dede5-8 hDwdGT']");
	private By skipBirthDate = By.xpath(".//button[@data-test='optin-skip-button']");
	private By recentLogin = By.xpath(".//span[contains(text(),'Sign into')]");
	private By completeSgout = By.id("invalidateSession");
	private By warning1 = By.id("username--ErrorMessage");
	private By warning2 = By.id("password--ErrorMessage");
	private By warning3 = By.xpath(".//div[@alerttype='warning']");

	public boolean isKeepSignedEnabled() throws InterruptedException {
	    try {
	      Thread.sleep(9000);
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
		Thread.sleep(2000);
		try {
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
		Thread.sleep(3000);
		try {
		   return driver.findElement(addMobilegrp).isEnabled();
		}catch(Exception e) {
			return false;
		}
	}
	public void skipAddMobile() {
		driver.findElement(skipMobile).click();
	}
	
	public boolean isBirthDateDisplayed() throws InterruptedException {
		Thread.sleep(4000);
		try {
		   return driver.findElement(addBirthDate).isEnabled();
		}catch(Exception e) {
			return false;
		}
	}
	
	public void skipAddBirthDate() {
		driver.findElement(skipBirthDate).click();
	}
	
	public boolean recentLogin() throws InterruptedException {
	  Thread.sleep(9000);
		try {
		    return driver.findElement(recentLogin).isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	public void completeSignout() {
		driver.findElement(completeSgout).click();
	}
	public boolean warn1() throws InterruptedException {
		  Thread.sleep(2000);
			try {
			    return driver.findElement(warning1).isDisplayed();
			}catch(Exception e) {
				return false;
			}
	 }
	public boolean warn2() throws InterruptedException {
		  Thread.sleep(2000);
			try {
			    return driver.findElement(warning2).isDisplayed();
			}catch(Exception e) {
				return false;
		   }
	}
	
	public boolean warn3() throws InterruptedException {
	  Thread.sleep(2000);
			try {
			    return driver.findElement(warning3).isDisplayed();
			}catch(Exception e) {
				return false;
		   }
	}
  */
}
