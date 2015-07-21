package testcases.bannerClient;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class TM_BannerClient_001 extends AbstractTest{
  
  @BeforeClass
  public void beforeClass(){
	  config.setup();
	  TestData.BannerClient.getDataTest();
	  
	  name = TestData.BannerClient.getName();
	  nameEdited = name + " edited";
	  contactName = TestData.BannerClient.getContactName();
	  contactNameEditted = contactName + " edited";
	  contactEmail = TestData.BannerClient.getContactEmail();
	  contactEmailEdited = "edited" + contactEmail;
	  stsPublished = TestData.Article.getStsPublished();
	  stsUnpublished = TestData.Article.getStsPublished();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description = "Verify that user can create a new client", priority = 1)
  public void TC_JOOMLA_BANNERS_CLIENTS_001(){
	managerBannerClientPage = adminPage.clickBannerClientManagerMenu();
	
	addBannerClientPage = managerBannerClientPage.clickNewBannerClient();
	addBannerClientPage.enterData(name, contactName, contactEmail, stsPublished);
	
	managerBannerClientPage = addBannerClientPage.clickSaveClose();
	
	check = managerBannerClientPage.isMessageDisplay(AppData.BannerClient.msgSave);
	verifyTrue(check, "VP1: Banner Client successfully saved message is displayed");
	
	managerBannerClientPage.searchBannerClient(name);
	
	check = managerBannerClientPage.isBannerClientExist(name);
	verifyTrue(check, "VP2: The created Banner Client is displayed");
			
  }
  	
    
  @Test(description ="Verify that user can edit a client", dependsOnMethods= "TC_JOOMLA_BANNERS_CLIENTS_001")
  public void TC_JOOMLA_BANNERS_CLIENTS_002(){
	  
	managerBannerClientPage.clickClientCheckbox(name);
	addBannerClientPage = managerBannerClientPage.clickEditBannerClient();
	
	addBannerClientPage.enterData(nameEdited, contactNameEditted, contactEmailEdited, stsPublished);
	
	managerBannerClientPage = addBannerClientPage.clickSaveClose();
	
	check = managerBannerClientPage.isMessageDisplay(AppData.BannerClient.msgSave);
	verifyTrue(check, "VP1: Banner Client successfully saved message is displayed");
	
	managerBannerClientPage.searchBannerClient(nameEdited);
	check = managerBannerClientPage.isBannerClientExist(nameEdited);
	verifyTrue(check, "VP2: The created Banner Client is displayed");
	
  }  
  
  @Test(description ="Verify that user can publish a client", dependsOnMethods= "TC_JOOMLA_BANNERS_CLIENTS_002")
  public void TC_JOOMLA_BANNERS_CLIENTS_003(){
	  
	managerBannerClientPage.clickClientCheckbox(nameEdited);
	managerBannerClientPage.clickPublishBannerClient();
	
	check = managerBannerClientPage.isMessageDisplay(AppData.BannerClient.msgPublish);
	verifyTrue(check, "VP1: Banner Client successfully punlished message is displayed");
	
	managerBannerClientPage.filterStatus(stsPublished);
	
	managerBannerClientPage.searchBannerClient(nameEdited);
	check = managerBannerClientPage.isBannerClientExist(nameEdited);
	verifyTrue(check, "VP2: The Banner Client is published successfully");
	
  }
  
  @Test(description ="Verify that user can unpublish a client", dependsOnMethods= "TC_JOOMLA_BANNERS_CLIENTS_003")
  public void TC_JOOMLA_BANNERS_CLIENTS_004(){
	  
	managerBannerClientPage.clickClientCheckbox(nameEdited);
	managerBannerClientPage.clickUnpublishBannerClient();
	
	check = managerBannerClientPage.isMessageDisplay(AppData.BannerClient.msgUnpublish);
	verifyTrue(check, "VP1: Banner Client successfully unpunlished message is displayed");
	
	managerBannerClientPage.filterStatus(stsUnpublished);
	
	managerBannerClientPage.searchBannerClient(nameEdited);
	check = managerBannerClientPage.isBannerClientExist(nameEdited);
	verifyTrue(check, "VP2: The Banner Client is unpublished successfully");
	
  }  
  
  @Test(description = "Verify that user can search a client  by using filter textbox", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_004")
  public void TC_JOOMLA_BANNERS_CLIENTS_008() {
	  managerBannerClientPage.filterStatus("- Select Status -"); 
	  
	  managerBannerClientPage.searchBannerClient(nameEdited);
		
	  check = managerBannerClientPage.isBannerClientExist(nameEdited);
	  verifyTrue(check, "VP2: The created Banner Client is displayed");
	
  }
  
  @Test(description = "Verify that user can search a client by using filter dropdown list", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_008")
  public void TC_JOOMLA_BANNERS_CLIENTS_009() {
	  managerBannerClientPage.filterStatus(stsUnpublished);
	  
	  managerBannerClientPage.isBannerClientExist(nameEdited);
	  verifyTrue(check, "VP2: The created Banner Client is displayed");
	  
	  managerBannerClientPage.filterStatus("- Select Status -");  
	
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
  
  String name, nameEdited, contactName, contactNameEditted, contactEmail, contactEmailEdited, stsPublished, stsUnpublished;
  boolean check;
  Login_page logInPage;
  Admin_page adminPage;
  Banner_Client_add_edit_page addBannerClientPage;
  Banner_Client_manager_page managerBannerClientPage;
  
}
