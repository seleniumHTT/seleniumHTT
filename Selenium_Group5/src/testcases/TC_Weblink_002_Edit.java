package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import abs.AbstractTest;
import pages.Admin_page;
import pages.Weblink_add_edit_page;
import pages.Weblink_manager_page;
import pages.Login_page;
import utilities.Random;
import common.Selenium;
import common.config;

public class TC_Weblink_002_Edit extends AbstractTest{
	WebDriver driver;
	Selenium sele;
	
  @BeforeClass
  public void beforeClass() {
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  
	  //new Weblink data
	  title = Random.getRandomName();
	  category = "";
	  status = "";
	  access = "";
	  URL ="";
	  WeblinkText = title + " content";
	  
	  //edited Weblink data
	  titleEdit = title + " edited";
	  categoryEdit = "";
	  statusEdit = "";
	  accessEdit = "";
	  URLEdit ="";
	  WeblinkTextEdit = WeblinkText + " edited";
	  
	  msg = "Weblink successfully saved";
  }
  
  @Test(description= "Verify user can edit an Weblink")
  public void TC2_EditWeblink() {
	  //1. Login to joomla
	  Login_page loginPage = new Login_page(driver);
	  loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  //2. Select Components > Weblink Manager
	  adminPage = new Admin_page(driver);
	  WeblinkManagerPage = adminPage.clickWeblinkManagerMenu();
	 
	  //3. Click new icon, go to add page
	  Weblink_add_edit_page addWeblinkPage = WeblinkManagerPage.clickNewWeblink();
	  addWeblinkPage.enterData(title, URL, category, status, access, WeblinkText);
	  
	  //4. Click Save n close button
	  WeblinkManagerPage = addWeblinkPage.clickSaveClose();
	  
	  //VP1: "Weblink successfully saved" message is displayed
	  check = WeblinkManagerPage.isMessageDisplay(msg);
	  verifyTrue(check, "VP1: Weblink successfully saved message is displayed");
	  
	  //Search Weblink
	  WeblinkManagerPage.searchWeblink(title);
	  
	  //VP2: Created Weblink is displayed on the Weblinks table
	  check = WeblinkManagerPage.isWeblinkExist(title);
	  verifyTrue(check, "VP2: Created Weblink is displayed");
	  
	  //5. Check on the recently added Weblink's checkbox
	  WeblinkManagerPage.clickWeblinkCheckbox(title);
	  
	  //6. Click on 'Edit' icon of the top right toolbar
	  editWeblinkPage = WeblinkManagerPage.clickEditWeblink();
	  
	  //7. Enter edit data
	  editWeblinkPage.enterData(titleEdit, categoryEdit, statusEdit, accessEdit, URLEdit, WeblinkTextEdit);
	  
	  //8. Click save and close
	  WeblinkManagerPage = editWeblinkPage.clickSaveClose();
	  
	  //VP3: "Weblink successfully saved" message is displayed
	  check = WeblinkManagerPage.isMessageDisplay(msg);
	  verifyTrue(check, "VP3: Weblink successfully saved message is displayed");
	  
	  //Search Weblink
	  WeblinkManagerPage.searchWeblink(titleEdit);
	  
	  //VP4: Created Weblink is displayed on the Weblinks table
	  check = WeblinkManagerPage.isWeblinkExist(titleEdit);
	  verifyTrue(check, "VP4: Edited Weblink is displayed");
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
  Weblink_manager_page WeblinkManagerPage;
  Weblink_add_edit_page editWeblinkPage, addWeblinkPage;
  Admin_page adminPage;
}
