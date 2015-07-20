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

public class TC_Weblink_001_Create extends AbstractTest{
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
	  WeblinkText = title + " content";
	    
	  msg = "Weblink successfully saved";
  }
  
  @Test(description= "Verify user can create an Weblink")
  public void TC2_EditWeblink() {
	  //1. Login to joomla
	  Login_page loginPage = new Login_page(driver);
	  loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  //2. Select Components > Weblink Manager
	  adminPage = new Admin_page(driver);
	  weblinkManagerPage = adminPage.clickWeblinkManagerMenu();
	 
	  //3. Click new icon, go to add page
	  Weblink_add_edit_page addWeblinkPage = weblinkManagerPage.clickNewWeblink();
	  addWeblinkPage.enterData(title, URL, category, status, access, WeblinkText);
	  
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
  
  String title, category, status, access, URL, WeblinkText, msg;
  String titleEdit, categoryEdit, statusEdit, accessEdit, URLEdit, WeblinkTextEdit;
  boolean check;
  Weblink_manager_page weblinkManagerPage;
  Weblink_add_edit_page editWeblinkPage, addWeblinkPage;
  Admin_page adminPage;
}
