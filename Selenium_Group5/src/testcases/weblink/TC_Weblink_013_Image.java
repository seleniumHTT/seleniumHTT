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

public class TC_Weblink_013_Image extends AbstractTest{
	WebDriver driver;
	Selenium sele;
	
  @BeforeClass
  public void beforeClass() {
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  
	  //new weblink data
	  title = Random.getRandomName();
	  category = "";
	  status = "";
	  access = "";
	  URL ="http://www.joomla.org";
	  weblinkText = title + " content";
	    
	  msg = "Weblink successfully saved";
	  imageName = "powered_by.png";
  }
  
  @Test(description= "Verify user can add image to weblink's content")
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
	  
	  //4. Insert image
	  addWeblinkPage.insertImage(imageName);
	  
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
  
  String title, category, status, access, feature, weblinkText, msg, msgTrash, imageName, URL;
  boolean check;
  Weblink_manager_page weblinkManagerPage;
  Weblink_add_edit_page editWeblinkPage, addWeblinkPage;
  Admin_page adminPage;
}
