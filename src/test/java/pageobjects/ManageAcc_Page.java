package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageAcc_Page extends Base_Page {
   private By sgout = By.xpath(".//button[text()='Sign out']");
   private WebElement sigout;
   private By verifyUsername = By.tagName("h1");
   
  public ManageAcc_Page(WebDriver driver) {
	  super(driver);
  }
  public boolean signout() {
	  try {
	   sigout = driver.findElement(sgout);
	   jsScrollView(sigout);
	   sigout.click();
	    return true;
	  }catch(Exception e) {
		  return false;
	  }
  }
  public String verifyUserName() {
	  return driver.findElement(verifyUsername).getText();
  }

}
