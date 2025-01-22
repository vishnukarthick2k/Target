package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.CreateAcc_Page;
import pageobjects.Home_Page;
import pageobjects.Login_Page;
import pageobjects.Login_Page;
import pageobjects.ManageAcc_Page;
import utilities.DataProviders;
import utilities.XLUtils;

public class TC_002_AccountCreate extends Base_Test{
   private XLUtils exl1;
   public String myexcel = System.getProperty("user.dir")+"\\DD_output\\validlogs.xlsx";
   boolean condition = true;
   public String [][] validcreds;
   public int ROW = 0;
   private String currentUrl;
   
   @Test(priority=0,groups={"Master","Regression","Functional"})
	public void setAccCreate() throws InterruptedException, IOException {
	  String  xlfile=".\\testdata\\signup_creds3.xlsx"; String xlsheet="SHEET1";
	   DataProviders dp = new DataProviders();
	   int [] rc= dp.getRowandCol(xlfile,xlsheet);
	    hp = new Home_Page(driver);
		cracc = new CreateAcc_Page(driver);
		macc = new ManageAcc_Page(driver);
		lp = new Login_Page(driver);
		exl1 = new XLUtils(myexcel);
		validcreds = new String[rc[0]][rc[1]];
	}
	
   @Test(priority=1,dataProvider="LoginCreds",dataProviderClass=DataProviders.class,groups={"Master","Regression","Functional"})
	public void accountCreater(String fname,String lname,String email,String password) throws InterruptedException, IOException {
	   int ro = ROW;
	   if(condition) {
		hp.clickAccMenuB4();
		hp.clickCreateAcc();
		if(cracc.checkIfDisplayed()) { cracc.goToPreviousPage(); return; }
	   }
		
		logger.info("execution starts");
		Assert.assertEquals("Create your Target account", cracc.verifyPageTitle());
		logger.info("setting up credentials");
        currentUrl = cracc.grabURL();
		cracc.setEmailAdrss(email);
		cracc.setFstName(fname);
		cracc.setLstName(lname);
		//cracc.setMobile(null);
		cracc.checkWithPassword();
		cracc.setPassword(password);
		cracc.showHidePass();
		cracc.keepSignedIn();
		cracc.clickCreateacc();
		if(cracc.isVerificationCodePops()) {cracc.goToPreviousPage();cracc.getMeTo(currentUrl); condition=false;validcreds[ro][0] = email;validcreds[ro][1] = password;validcreds[ro][2] = fname; ROW++;return;}
		
		if(cracc.warningShows1()) {cracc.refreshPage();condition=false;validcreds[ro][0] = email;validcreds[ro][1] = password;validcreds[ro][2] = fname; ROW++;return;}
		if(cracc.warningShows2()) {cracc.refreshPage();condition=false; return;}
		cracc.clickMayBeLater(); 
		if(lp.recentLogin()) {lp.completeSignout();cracc.goToPreviousPage();return;}
		cracc.feedBackReply();
		hp.clickAccMenuAfter();
		hp.manageAccount();
		
		if(macc.signout()) {
			condition = true;
			validcreds[ro][0] = email;
		    validcreds[ro][1] = password;
		    validcreds[ro][2] = fname;
		    ROW++;
		}
	  logger.info("execution ends");
	}
   @Test(priority=2,groups={"Master","Regression","Functional"})
   public void record() throws IOException {
	   logger.info("excel writter starts");
	   for(String[] x : validcreds) {for(String y:x) { logger.info(y); } }
	   exl1.createCellData(validcreds,"mylogins");
	   logger.info("excel writter finish");
   }
}
  
	