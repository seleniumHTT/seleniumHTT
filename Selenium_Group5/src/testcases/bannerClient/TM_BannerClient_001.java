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
	  stsSelectStatus = TestData.BannerClient.getStsSelectStatus();
	  stsPublished = TestData.BannerClient.getStsPublished();
	  stsUnpublished = TestData.BannerClient.getStsUnpublished();
	  stsArchived = TestData.BannerClient.getStsArchived();
	  stsTrashed = TestData.BannerClient.getStsTrashed();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description = "Verify that user can create a new client")
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
  	
    
  @Test(description ="Verify that user can edit a client")
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
  
  @Test(description ="Verify that user can publish a client")
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
  
  @Test(description ="Verify that user can unpublish a client")
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
  
  @Test(description ="Verify that user can archive a client")
  public void TC_JOOMLA_BANNERS_CLIENTS_005(){	 
	
	managerBannerClientPage.clickClientCheckbox(nameEdited);
	managerBannerClientPage.clickArchiveBannerClient();
	
	check = managerBannerClientPage.isMessageDisplay(AppData.BannerClient.msgArchive);
	verifyTrue(check, "VP1: Banner Client successfully archived message is displayed");
	
	managerBannerClientPage.filterStatus(stsArchived);
	
	managerBannerClientPage.searchBannerClient(nameEdited);
	check = managerBannerClientPage.isBannerClientExist(nameEdited);
	verifyTrue(check, "VP2: The Banner Client is archived successfully");
	
  } 
  
  @Test(description ="Verify that user can send a client to trash")
  public void TC_JOOMLA_BANNERS_CLIENTS_006(){	 
	
	managerBannerClientPage.clickClientCheckbox(nameEdited);
	managerBannerClientPage.clickTrashBannerClient();
	
	check = managerBannerClientPage.isMessageDisplay(AppData.BannerClient.msgTrash);
	verifyTrue(check, "VP1: Banner Client successfully trashed message is displayed");
	
	managerBannerClientPage.filterStatus(stsTrashed);
	
	managerBannerClientPage.searchBannerClient(nameEdited);
	check = managerBannerClientPage.isBannerClientExist(nameEdited);
	verifyTrue(check, "VP2: The Banner Client is trashed successfully");
	
  }   
  
  @Test(description = "Verify that user can search a client  by using filter textbox")
  public void TC_JOOMLA_BANNERS_CLIENTS_008() {
	  
	managerBannerClientPage.clickClientCheckbox(nameEdited);
	managerBannerClientPage.clickPublishBannerClient();
	  
	managerBannerClientPage.filterStatus(stsSelectStatus); 
	  
	managerBannerClientPage.searchBannerClient(nameEdited);
	
	check = managerBannerClientPage.isBannerClientExist(nameEdited);
	verifyTrue(check, "VP2: The created Banner Client is displayed");
	
  }
  
  @Test(description = "Verify that user can search a client by using filter dropdown list")
  public void TC_JOOMLA_BANNERS_CLIENTS_009() {
	
	managerBannerClientPage.clickClientCheckbox(nameEdited);
	
	addBannerClientPage = managerBannerClientPage.clickEditBannerClient();
	addBannerClientPage.enterData(nameEdited, contactNameEditted, contactEmailEdited, stsUnpublished);
	
	managerBannerClientPage = addBannerClientPage.clickSaveClose();  
	  
	managerBannerClientPage.filterStatus(stsUnpublished);
	  
	managerBannerClientPage.isBannerClientExist(nameEdited);
	verifyTrue(check, "VP2: The created Banner Client is displayed");
	
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
  
  String name, nameEdited, contactName, contactNameEditted, contactEmail, contactEmailEdited, stsSelectStatus, stsPublished, stsUnpublished, stsArchived, stsTrashed;
  boolean check;
  Login_page logInPage;
  Admin_page adminPage;
  Banner_Client_add_edit_page addBannerClientPage;
  Banner_Client_manager_page managerBannerClientPage;
  
}
