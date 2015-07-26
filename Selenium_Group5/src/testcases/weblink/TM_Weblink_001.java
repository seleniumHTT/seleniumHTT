package testcases.weblink;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class TM_Weblink_001 extends AbstractTest{	
	
  @BeforeClass
  public void beforeClass() {
	  config.setup();
	  TestData.Weblink.getDataTest();	  
	  
	  name = TestData.Weblink.getName();  
	  nameCopied = name + " copied";
	  nameEdit = name + " edited";
	  url1 = TestData.Weblink.getUrl1();
	  url2 = TestData.Weblink.getUrl2();
	  
	  category = TestData.Weblink.getCategory();
	  stsPublished = TestData.Weblink.getStsPublished();
	  weblinkText = name + " weblink text";
	  helpPageTitle = TestData.Weblink.getHelpPageTitle();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify user can create a Weblink", priority=1)
  public void TC_JOOMLA_WEBLINKS_001() {	 	  
	  
	  weblinkManagerPage = adminPage.clickWeblinkManagerMenu();
	 
	  addWeblinkPage = weblinkManagerPage.clickNewWeblink();
	  
	  addWeblinkPage.enterData(name, alias, url1, category, stsPublished, access, weblinkText);
	  weblinkManagerPage = addWeblinkPage.clickSaveClose();	  
	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgSave);
	  verifyTrue(check, "VP: Weblink successfully saved message is displayed");	  
	  
	  weblinkManagerPage.searchWeblink(name);
	  	  
	  check = weblinkManagerPage.isWeblinkExist(name);
	  verifyTrue(check, "VP: Created Weblink is displayed");	  
  }
  
  @Test(description= "User can access weblink's help section", dependsOnMethods= "TC_JOOMLA_WEBLINKS_001")
  public void TC_JOOMLA_WEBLINKS_008() {	  
		 
	  Help_page helpPage = weblinkManagerPage.clickHelpToolbar();
	  check = helpPage.isHelpWindowDisplays(helpPageTitle);
	  verifyTrue(check, "VP: The weblink's help window is displayed");	  
	  helpPage.closeBackToParentPage();
  } 
    
  @Test(description= "Verify user can search for weblinks using the filter text field", dependsOnMethods= "TC_JOOMLA_WEBLINKS_001", priority=1)
  public void TC_JOOMLA_WEBLINKS_009() {	  	  
	  
	  weblinkManagerPage.searchWeblink(name);
	  	  
	  check = weblinkManagerPage.isWeblinkExist(name);
	  verifyTrue(check, "VP: Created Weblink is displayed");	  
	  
  }  
  
  
  @Test(description= "Verify user can create a new weblink with 'Public' Access Level property", dependsOnMethods= "TC_JOOMLA_WEBLINKS_001", priority=1)
  public void TC_JOOMLA_WEBLINKS_017() {	  	  
	  
	  weblinkManagerPage.searchWeblink(name);
	  
	  check = weblinkManagerPage.isWeblinkPublic(name, "Public");
	  verifyTrue(check, "VP: The Access Level of the weblink is displayed as 'Public'");	  
	  
  }  
 
  @Test(description= "User can search for weblinks using the filter dropdown lists", dependsOnMethods= "TC_JOOMLA_WEBLINKS_001", priority=1)
  public void TC_JOOMLA_WEBLINKS_010() {
	  	  
	  weblinkManagerPage.filterStatus(stsPublished);
	  weblinkManagerPage.filterCategory(category);
	  
	  check = weblinkManagerPage.isWeblinkExist(name);
	  verifyTrue(check, "VP: Created Weblink is displayed");	  
	  
	  weblinkManagerPage.filterStatus("- Select Status -");
	  weblinkManagerPage.filterCategory("Select Category");
  }
  
  @Test(description= "Verify user can edit a weblink", dependsOnMethods= "TC_JOOMLA_WEBLINKS_001", priority=2)
  public void TC_JOOMLA_WEBLINKS_002() {
	  
	  weblinkManagerPage.clickWeblinkCheckbox(name);
	  editWeblinkPage = weblinkManagerPage.clickEditWeblink();
	  	  
	  editWeblinkPage.enterData(nameEdit, alias, url2, category, stsPublished, access, weblinkText);
	  weblinkManagerPage = editWeblinkPage.clickSaveClose();
	  	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgSave);
	  verifyTrue(check, "VP: Weblink successfully saved message is displayed");	  
	  
	  weblinkManagerPage.searchWeblink(nameEdit);
	  	  
	  check = weblinkManagerPage.isWeblinkExist(nameEdit);
	  verifyTrue(check, "VP: Edited weblink is displayed");	  
	  
  }
  
  @Test(description= "Verify user can publish an unpublished weblink", dependsOnMethods= "TC_JOOMLA_WEBLINKS_002")
  public void TC_JOOMLA_WEBLINKS_003() {	  
	
	  weblinkManagerPage.clickWeblinkCheckbox(nameEdit);
	  weblinkManagerPage.clickChangeStatusToolbar("Unpublish");	  
	  	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgUnpublish);
	  verifyTrue(check, "VP: '1 weblink successfully unpublished' message is displayed");
  }
  
  @Test(description= "Verify user can publish an unpublished weblink", dependsOnMethods="TC_JOOMLA_WEBLINKS_003")
  public void TC_JOOMLA_WEBLINKS_004() {
	  	  
	  weblinkManagerPage.clickWeblinkCheckbox(nameEdit);
	  weblinkManagerPage.clickChangeStatusToolbar("Publish");	  
	  	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgPublish);
	  verifyTrue(check, "VP: '1 weblink successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can move a weblink to the archive", dependsOnMethods= "TC_JOOMLA_WEBLINKS_004")
  public void TC_JOOMLA_WEBLINKS_005() {
	  
	  weblinkManagerPage.clickWeblinkCheckbox(nameEdit);
	  weblinkManagerPage.clickArchiveWeblink(); 
	  
	  check = weblinkManagerPage.isMessageDisplay(AppData.Weblink.msgArchive);
	  verifyTrue(check, "VP: The '1 weblink successfully archived' message is displayed");
	  
	  weblinkManagerPage.filterStatus("Archived");
	  
	  weblinkManagerPage.searchWeblink(nameEdit);
	  	  
	  check = weblinkManagerPage.isWeblinkExist(nameEdit);
	  verifyTrue(check, "VP: The deleted weblink is displayed on the table grid");
	  
	  weblinkManagerPage.filterStatus("- Select Status -");	  
  }  
    
  @Test(description= "Verify user can create a copied version of an existed weblink", dependsOnMethods= "TC_JOOMLA_WEBLINKS_001", priority=1)
  public void TC_JOOMLA_WEBLINKS_016() {
	  weblinkManagerPage.clickWeblinkCheckbox(name);
	  editWeblinkPage = weblinkManagerPage.clickEditWeblink();
	  	  
	  editWeblinkPage.enterData(nameCopied, nameCopied, url2, category, stsPublished, access, weblinkText);
	  editWeblinkPage.clickSaveCopy();
	  	  
	  check = editWeblinkPage.isMessageDisplay(AppData.Weblink.msgSave);
	  verifyTrue(check, "VP: Weblink successfully saved message is displayed");	  
	  
	  weblinkManagerPage = editWeblinkPage.clickClose();	  
	  weblinkManagerPage.searchWeblink(name);
	  	  
	  check = weblinkManagerPage.isWeblinkExist(nameCopied);
	  verifyTrue(check, "VP: Edited weblink is displayed");	
	  	  
  } 

  
  private String name, url1, url2, alias, category, stsPublished, access, weblinkText, helpPageTitle, nameCopied;
  private String nameEdit;
  private boolean check;
  private Weblink_manager_page weblinkManagerPage;
  private Weblink_add_edit_page editWeblinkPage, addWeblinkPage;
  private Admin_page adminPage;
}
