package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.Home_Page;
import pageobjects.Login_Page;
import pageobjects.ManageAcc_Page;
import utilities.DataProviders;

public class TC_001_Signup extends Base_Test{
    private boolean ifYes = false; // if returned in middle.
    
	@Test(priority=0,groups={"Master","Regression"})
	public void setSignup() throws InterruptedException, IOException {
		hp = new Home_Page(driver);
		lp = new Login_Page(driver);
		hp.clickAccMenuB4();
		hp.clickSignin();
	}
		
	@Test(priority=2,dataProvider="LoginData",dataProviderClass=DataProviders.class,groups={"Master","Regression"})
	public void checkSignup(String email,String pass,String user) throws InterruptedException { 
		logger.info("Execution starts..."+email);
		 if(ifYes) {
			 hp.clickAccMenuAfter();
			 hp.clickSignin();
		   while(!lp.shownSignin()) {
			  logger.info(hp.grabURL());
			  if(hp.grabURL().equals("https://www.target.com/")) lp.forceLogin();  
		   }
		}
		if(!lp.isKeepSignedEnabled()) lp.checkKeepMeSigned();
		lp.setUsername(email);
		lp.setPassword(pass);
		lp.showPassword();
		lp.signInByPword();
		if(lp.warn0()) {lp.refreshPage();ifYes=false;return;}
		if(lp.warn3()) {lp.refreshPage();ifYes=false;return;}
		
		if(lp.warn1()==true||lp.warn2()==true) {lp.refreshPage();ifYes=false;return;}
		
		if(lp.isMobileGrpDisplayed()) lp.skipAddMobile();
		//if(lp.isIntroToPassKeyDisplayed()) lp.mayBelaterPasskeys();
		//if(lp.isBirthDateDisplayed()) lp.skipAddBirthDate();
		hp.clickAccMenuAfter();
		hp.manageAccount();
		macc = new ManageAcc_Page(driver);
		String result = macc.verifyUserName();
		macc.signout();
		if(lp.recentLogin()) {lp.completeSignout();}
		ifYes= true;
		logger.info("Execution ends...");
		Assert.assertEquals(result.substring(7),user);
	}
}
