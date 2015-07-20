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

public class TC_Weblink_007_Trash extends AbstractTest{
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
	  msgTrash = "1 weblink successfully trashed";
  }
  
  @Test(description= "Verify user can move an weblink to trash section")
  public void TC3_MoveWeblinkToTrash() {
	  //1. Login to joomla
	  Login_page loginPage = new Login_page(driver);
	  loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  //2. Select Components > Weblink Manager
	  adminPage = new Admin_page(driver);
	  weblinkManagerPage = adminPage.clickWeblinkManagerMenu();
	 
	  //3. Click new icon, go to add page
	  Weblink_add_edit_page addWeblinkPage = weblinkManagerPage.clickNewWeblink();
	  addWeblinkPage.enterData(title, URL, category, status, access, weblinkText);
	  
	  //4. Click Save n close button
	  weblinkManagerPage = addWeblinkPage.clickSaveClose();
	  
	  //VP1: "Weblink successfully saved" message is displayed
	  check = weblinkManagerPage.isMessageDisplay(msg);
	  verifyTrue(check, "VP1: Weblink successfully saved message is displayed");
	  
	  //Search Weblink
	  weblinkManagerPage.searchWeblink(title);
	  
	  //VP2: Created Weblink is displayed on the Weblinks table
	  check = weblinkManagerPage.isWeblinkExist(title);
	  verifyTrue(check, "VP2: Created Weblink is displayed");
	  
	  //5. Check on the recently added weblink's checkbox
	  weblinkManagerPage.clickWeblinkCheckbox(title);
	  
	  //6. Click on 'Trash' icon of the top right toolbar
	  weblinkManagerPage = weblinkManagerPage.clickTrashWeblink();
	  
	  //VP3: "1 weblink trashed" message is displayed
	  check = weblinkManagerPage.isMessageDisplay(msgTrash);
	  verifyTrue(check, "VP3: The '1 weblink successfully trashed' message is displayed");
	  
	  //7. Select 'Trashed' item of 'Status' dropdown list
	  weblinkManagerPage.filterStatus("Trashed");
	  
	  //Search weblink
	  weblinkManagerPage.searchWeblink(title);
	  
	  //VP4: Verify the deleted weblink is displayed on the table grid	  
	  check = weblinkManagerPage.isWeblinkExist(title);
	  verifyTrue(check, "VP4: The deleted weblink is displayed on the table grid");
  } 
  
  @AfterClass
  public void afterClass() {
	  //sele.close();
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
  
  String title, category, status, access, URL, weblinkText, msg, msgTrash;
  boolean check;
  Weblink_manager_page weblinkManagerPage;
  Weblink_add_edit_page editWeblinkPage, addWeblinkPage;
  Admin_page adminPage;
}
