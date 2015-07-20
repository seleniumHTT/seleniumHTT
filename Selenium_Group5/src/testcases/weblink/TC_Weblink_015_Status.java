package testcases.weblink;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import abstracts.AbstractTest;
import pages.Admin_page;
import pages.Weblink_add_edit_page;
import pages.Weblink_manager_page;
import pages.Login_page;
import utilities.Random;
import common.Selenium;
import common.config;

public class TC_Weblink_015_Status extends AbstractTest{
	WebDriver driver;
	Selenium sele;
	
	
  @BeforeClass
  public void beforeClass() {
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  
	//new Weblink data
	  title = Random.getCurrentTime();
	  category = "";
	  status = "";
	  access = "";
	  URL ="http://www.joomla.org";
	  weblinkText = title + " content";
	  
	  msg = "Weblink successfully saved";
	  msgPublished = "1 weblink successfully published";
	  msgUnpublished = "1 weblink successfully unpublished";
  }
  
  @Test(description= "Verify user can change the status of weblinks using the Status column")
  public void TC14_OrderWeblink() {
	  //1. Login to joomla
	  Login_page loginPage = new Login_page(driver);
	  loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  //2. Select Content > Weblink Manager
	  adminPage = new Admin_page(driver);
	  weblinkManagerPage = adminPage.clickWeblinkManagerMenu();
	 
	  //3. Click new icon, go to add page
	  addWeblinkPage = weblinkManagerPage.clickNewWeblink();
	  addWeblinkPage.enterData(title, URL, category, status, access, weblinkText);
	  
	  //4. Click Save n close button
	  weblinkManagerPage = addWeblinkPage.clickSaveClose();
	  
	  //VP1: "Weblink successfully saved" message is displayed
	  check = weblinkManagerPage.isMessageDisplay(msg);
	  verifyTrue(check, "VP1: Weblink successfully saved message is displayed");
	  
	  //Search weblink
	  weblinkManagerPage.searchWeblink(title);
	  
	  //VP2: Created weblink is displayed on the weblinks table
	  check = weblinkManagerPage.isWeblinkExist(title);
	  verifyTrue(check, "VP2: Created weblink is displayed");
	  
	  //5. Change the status icon of the selected weblink in the Status column
	  weblinkManagerPage.clickChangeStatus(title);
	  
	  //VP3: Verify the weblink is unpublished successfully
	  //VP3a: The icon of the selected item is showed as 'Unpublished'.
	  check = weblinkManagerPage.isWeblinkPublished(title, "Unpublished");
	  verifyTrue(check, "VP3a: The icon of the selected item is showed as 'Unpublished'");
	  
	  //VP3b: The "1 weblink unpublished" message is displayed
	  check = weblinkManagerPage.isMessageDisplay(msgUnpublished);
	  verifyTrue(check, "VP3b: The '1 weblink unpublished' message is displayed");
	  
	  //6. Change the status icon of the selected weblink in the Status column
	  weblinkManagerPage.clickChangeStatus(title);
	  
	  //VP3: Verify the weblink is unpublished successfully
	  //VP3a: The icon of the selected item is showed as 'Unpublished'.
	  check = weblinkManagerPage.isWeblinkPublished(title, "Published");
	  verifyTrue(check, "VP4a: The icon of the selected item is showed as 'Published'");
	  
	  //VP3b: The "1 weblink unpublished" message is displayed
	  check = weblinkManagerPage.isMessageDisplay(msgPublished);
	  verifyTrue(check, "VP4b: The '1 weblink published' message is displayed");
  } 
  
  @AfterClass
  public void afterClass() {
	  sele.close();
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
  
  String title, category, status, access, URL, weblinkText, msg, msgPublished, msgUnpublished;
  String title2, category2, status2, access2, feature2, weblinkText2;
  boolean check;
  Weblink_manager_page weblinkManagerPage;
  Weblink_add_edit_page editWeblinkPage, addWeblinkPage;
  Admin_page adminPage;
}
