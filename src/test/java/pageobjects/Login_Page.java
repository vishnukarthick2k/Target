package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login_Page extends Base_Page {
	
	private By keepSigned = By.id("keepMeSignedIn");
	private By usrNameBox = By.id("username");
	private WebElement unb;
	private By pwrdBox = By.id("password");
	private WebElement pdb;
	private By showPwrd = By.xpath(".//button[text()='show']");
	private By signWithPass = By.id("login"); 
	private By intoToPassKeys = By.xpath(".//h1[@class='sc-fe064f5c-0 fJliSz']");
	private By warn0 = By.xpath(".//div[@class='sc-55a9f08c-0 gMjCue']");
	private By skip = By.xpath(".//a[text()='Skip']");
	private By maybelater = By.xpath(".//button[text()='Maybe later']");
	private WebElement mbl;
	private By addMobilegrp = By.cssSelector("sc-f82024d1-0.JJsAp");
	private By skipMobile = By.xpath(".//a[@class='sc-e851bd29-0 kkFFLD']");
	private By addBirthDate = By.xpath(".//div[@class='sc-f86dede5-8 hDwdGT']");
	private By skipBirthDate = By.xpath(".//button[@data-test='optin-skip-button']");
	//private By recentLogin = By.xpath(".//span[contains(text(),'Sign into')]");
	private By completeSgout = By.id("invalidateSession");
	private By warning1 = By.id("username--ErrorMessage");
	private By warning2 = By.id("password--ErrorMessage");
	private By warning3 = By.xpath(".//div[@alerttype='warning']");
	private By prelogindls = By.xpath("(.//strong)[1]");
	private String alterLogin = "https://www.target.com/login?client_id=ecom-web-1.0.0&ui_namespace=ui-default&back_button_action=browser&actions=create_session_signin";
	private By signinPage = By.xpath(".//span[contains(text(),'Sign into')]");
	public Login_Page(WebDriver driver) {
		super(driver);
		logger.info("after loginpage constructor");
	}

	public boolean isKeepSignedEnabled() throws InterruptedException {
	    try {
	      Thread.sleep(3000);
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
		Thread.sleep(3000);
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
			Thread.sleep(5000);
		   return driver.findElement(addMobilegrp).isEnabled();
		}catch(Exception e) {
			return false;
		}
	}
	public void skipAddMobile() {
		driver.findElement(skipMobile).click();
	}
	public boolean isIntroToPassKeyDisplayed() {
		try {
			Thread.sleep(5000);
			return driver.findElement(intoToPassKeys).isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}

	public void mayBelaterPasskeys() {
		WebElement temp = driver.findElement(maybelater);
		jsScrollView(temp);jsTouch(temp);
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
			Thread.sleep(7000);
		    return driver.findElement(prelogindls).isDisplayed();
		}catch(Exception e) {
			logger.info("recent login not displayed");
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
	
	public void forceLogin() {
		getMeTo(alterLogin);
	}
	public String LoginUrl() {
		return alterLogin;
	}
	
	public boolean shownSignin() {
	  try {
		boolean result = driver.findElement(signinPage).isDisplayed();
		if(result) {return result;}
		else {logger.info("sign in header not shown");return result;}
	  }catch(Exception e) {
			logger.info("cannot able to find signinPage");return false;
	  }
   }
}
