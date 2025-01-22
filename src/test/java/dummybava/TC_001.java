package dummybava;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.Home_Page;
import pageobjects.Login_Page;
import pageobjects.ManageAcc_Page;
import utilities.DataProviders;

public class TC_001 extends BT{
    
	@Test(priority=0)
	public void setSignup() throws InterruptedException, IOException {
		hp = new HP(driver);
		hp.clickAccMenuB4();
		hp.clickSignin();
		lp = new LP(driver);
	}
	
	@Test(priority=1,dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void checkSignup(String email,String pass,String user) throws InterruptedException { 
		logger.info("Execution starts..."+email+" "+pass);
		
		if(!lp.isKeepSignedEnabled()) lp.checkKeepMeSigned();
		lp.setUsername(email);
		lp.setPassword(pass);
		lp.showPassword();
		lp.signInByPword();
		if(lp.warn0()) {lp.refreshPage();return;}
		if(lp.warn3()) {lp.refreshPage();return;}
		
		if(lp.warn1()==true||lp.warn2()==true) {lp.refreshPage();return;}
		
		//if(lp.isMobileGrpDisplayed()) lp.skipAddMobile();
		if(lp.isBirthDateDisplayed()) lp.skipAddBirthDate();
		hp.clickAccMenuAfter();
		hp.manageAccount();
		macc = new ManageAcc_Page(driver);
		String result = macc.verifyUserName();
		macc.signout();
		hp.clickAccMenuAfter();
		hp.manageAccount();
		if(lp.recentLogin()) {lp.completeSignout();}
		logger.info("Execution ends...");
		Assert.assertEquals(result.substring(7),user);
	}
}
