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

public class TC_Weblink_014_Ordering extends AbstractTest{
	WebDriver driver;
	Selenium sele;
	
	
  @BeforeClass
  public void beforeClass() {
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  
	  //new weblink data
	  random = Random.getRandomName();
	  title =  random + " ordering1";
	  title2 = random + " ordering2"; 
	  category = "";
	  status = "";
	  access = "";
	  URL ="http://www.joomla.org";
	  weblinkText = title + " content";	  
	  
	  msg = "Weblink successfully saved";
  }
  
  @Test(description= "Verify user can change the order of weblinks using the Ordering column")
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
	  weblinkManagerPage.searchWeblink(random);
	  
	  //VP2: Created weblink is displayed on the weblinks table
	  check = weblinkManagerPage.isWeblinkExist(title);
	  verifyTrue(check, "VP2: Created weblink is displayed");
	  
	  //5. Select Content > Weblink Manager	  
	  weblinkManagerPage.clickWeblinkManagerMenu();
	  
	  //6. Click new icon, go to add page
	  addWeblinkPage = weblinkManagerPage.clickNewWeblink();
	  
	  //7. Enter required data
	  addWeblinkPage.enterData(title2, URL, category, status, access, weblinkText);
	  
	  //8. Click save and close
	  weblinkManagerPage = addWeblinkPage.clickSaveClose();
	  
	  //VP3: "Weblink successfully saved" message is displayed
	  check = weblinkManagerPage.isMessageDisplay(msg);
	  verifyTrue(check, "VP3: Weblink successfully saved message is displayed");
	  
	  //Search weblink
	  weblinkManagerPage.searchWeblink(random);
	  
	  //VP4: Created weblink is displayed on the weblinks table
	  check = weblinkManagerPage.isWeblinkExist(title2);
	  verifyTrue(check, "VP4: Created weblink is displayed on the weblinks table");
	  
	  //9. Click on the Header link of Ordering column
	  weblinkManagerPage.clickOrderingColumn();
	  
	  //10. Check on the second created weblink's checkbox
	  //11. Click on down arrow in Ordering column of the selected weblink
	  int rowTitle1 = weblinkManagerPage.getRowNumber(title);
	  int rowTitle2 = weblinkManagerPage.getRowNumber(title2);
	  
	  weblinkManagerPage.clickArrowOrdering(title, "down");
	  
	  //VP5: Verify the first weblink changes its position with the second weblink
	  check = weblinkManagerPage.isWeblinkLocateAt(title2, rowTitle1);
	  verifyTrue(check, "VP5a: Verify the first weblink changes its position with the second weblink");
	  check = weblinkManagerPage.isWeblinkLocateAt(title, rowTitle2);
	  verifyTrue(check, "VP5b: Verify the second weblink changes its position with the first weblink");
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
  
  String title, category, status, access, feature, weblinkText, msg, URL, random;
  String title2, category2, status2, access2, feature2, weblinkText2;
  boolean check;
  Weblink_manager_page weblinkManagerPage;
  Weblink_add_edit_page editWeblinkPage, addWeblinkPage;
  Admin_page adminPage;
}
