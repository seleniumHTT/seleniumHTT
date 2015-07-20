package testcases.weblink;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class TM_Weblink_002 extends AbstractTest{

	
  @BeforeClass
  public void beforeClass() {	  
	  config.setup();
	  TestData.Weblink.getDataTest();
	  
	  //new Weblink data
	  name = TestData.Weblink.getName(); 
	  name2 = name + " order";
	  name3 = name + " image";
	  
	  url1 = TestData.Weblink.getUrl1();
	  url2 = TestData.Weblink.getUrl2();	  
	  
	  category = TestData.Weblink.getCategory();
	  stsPublished = TestData.Weblink.getStsPublished();
	  imageName = TestData.Weblink.getImageName();	    
	  weblinkText = name + " weblink text";
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify user can check in a weblink", priority=1)
  public void TC_JOOMLA_WEBLINK_006() {	
	  
	  weblinkManagerPage = adminPage.clickWeblinkManagerMenu();
	 
	  addWeblinkPage = weblinkManagerPage.clickNewWeblink();
	  
	  addWeblinkPage.enterData(name, alias, url1, category, stsPublished, access, weblinkText);
	  addWeblinkPage.clickSave();	  
	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgSave);
	  verifyTrue(check, "VP: Weblink successfully saved message is displayed");	  
	  
	  config.tearDown();
	  config.setup();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
	  
	  weblinkManagerPage = adminPage.clickWeblinkManagerMenu();
	  weblinkManagerPage.searchWeblink(name);
	  
	  weblinkManagerPage.checkIn(name);
	  check = weblinkManagerPage.isWeblinkCheckedIn(name);
	  verifyTrue(check, "VP: The lock icon next to the weblink is removed");
	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgCheckedIn);
	  verifyTrue(check, "VP: Weblink successfully saved message is displayed");  
  }
  
  @Test(description= "Verify user can change the status of weblink using the Status column", dependsOnMethods= "TC_JOOMLA_WEBLINK_006", priority=1)
  public void TC_JOOMLA_WEBLINK_014() {
	  
	  weblinkManagerPage.clickChangeStatus(name);
	  
	  check = weblinkManagerPage.isWeblinkPublished(name, "Unpublished");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Unpublished'");
	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgUnpublish);
	  verifyTrue(check, "VP: The '1 weblink successfully unpublished' message is displayed");
	  
	  weblinkManagerPage.clickChangeStatus(name);	  
	  check = weblinkManagerPage.isWeblinkPublished(name, "Published");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Published'");	  
	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgPublish);
	  verifyTrue(check, "VP: The '1 weblink successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can add image to weblink's information", dependsOnMethods= "TC_JOOMLA_WEBLINK_006")
  public void TC_JOOMLA_WEBLINK_013() {  
	  
	  Weblink_add_edit_page addWeblinkPage = weblinkManagerPage.clickNewWeblink();
	  addWeblinkPage.enterData(name3, alias, url1, category, stsPublished, access, weblinkText);
	  addWeblinkPage.insertImage(imageName);	  
	  
	  weblinkManagerPage = addWeblinkPage.clickSaveClose();
	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgSave);
	  verifyTrue(check, "VP: Weblink successfully saved message is displayed");
	  
	  weblinkManagerPage.searchWeblink(name);
	  
	  check = weblinkManagerPage.isWeblinkExist(name3);
	  verifyTrue(check, "VP: Created weblink is displayed");   
  }  
  
  @Test(description= "Verify user can sort the weblink table by ID column", dependsOnMethods= "TC_JOOMLA_WEBLINK_013")
  public void TC_JOOMLA_WEBLINK_011() { 	  
	  	  
	  weblinkManagerPage.searchWeblink(name);	  
	 
	  weblinkManagerPage.clickIdColumn();
	  check = weblinkManagerPage.isIdSortedCorrect("asc");
	  verifyTrue(check, "VP: The weblinks is sorted by ID in ascending order");
	  
	  weblinkManagerPage.clickIdColumn();
	  check = weblinkManagerPage.isIdSortedCorrect("dec");
	  verifyTrue(check, "VP: The weblinks is sorted by ID in descending order");
  }
  
  @Test(description= "Verify user can change the order of weblinks using the Ordering column", dependsOnMethods= "TC_JOOMLA_WEBLINK_006")
  public void TC_JOOMLA_WEBLINK_015() {	  
	  weblinkManagerPage.searchWeblink(name);
	  	  
	  weblinkManagerPage.clickOrderingColumn();	  
	 
	  int row1 = weblinkManagerPage.getRowNumber(name);
	  int row2 = weblinkManagerPage.getRowNumber(name3);	  
	  weblinkManagerPage.clickArrowOrdering(name, "down");	  
	  
	  check = weblinkManagerPage.isWeblinkLocateAt(name, row2);
	  verifyTrue(check, "VP: Verify the first weblink changes its position with the second weblink");
	  check = weblinkManagerPage.isWeblinkLocateAt(name3, row1);
	  verifyTrue(check, "VP: Verify the second weblink changes its position with the first weblink");
  } 
  
  @Test(description= "Verify user can move a weblink to trash section", dependsOnMethods= "TC_JOOMLA_WEBLINK_015")
  public void TC_JOOMLA_WEBLINK_007() {	  
	  
	  weblinkManagerPage.clickWeblinkCheckbox(name3);
	  weblinkManagerPage = weblinkManagerPage.clickTrashWeblink();	  
	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgTrash);
	  verifyTrue(check, "The '1 weblink trashed' message is displayed");
	  
	  weblinkManagerPage.filterStatus("Trashed");
	  
	  weblinkManagerPage.searchWeblink(name);
	  	  
	  check = weblinkManagerPage.isWeblinkExist(name3);
	  verifyTrue(check, "VP: The deleted weblink is displayed on the table grid");
  } 
  
  @AfterClass
  public void afterClass() {
	  config.tearDown();
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
  
  String name, url1, url2, alias, category, stsPublished, access, feature, weblinkText, imageName, name2, name3; 
  boolean check;
  Weblink_manager_page weblinkManagerPage;
  Weblink_add_edit_page editWeblinkPage, addWeblinkPage;
  Admin_page adminPage;
  Login_page loginPage;
}
