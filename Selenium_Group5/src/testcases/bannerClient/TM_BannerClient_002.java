package testcases.bannerClient;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import common.AppData;
import common.PageFactory;
import common.TestData;
import common.config;
import pages.Admin_page;
import pages.Banner_Client_add_edit_page;
import pages.Banner_Client_manager_page;
import pages.Help_page;
import pages.Login_page;

public class TM_BannerClient_002 extends AbstractTest {
	@BeforeClass
	public void beforeClass(){
		config.setup();
		TestData.BannerClient.getDataTest();
		  
		name = TestData.BannerClient.getName();
		clientName1 = "Client 1 " + name;
		clientName2 = "Client 2 " + name;
		clientName3 = "Client 3 " + name;
		clientName3Copy = "Client Copy " + name;
		contactName = TestData.BannerClient.getContactName();
		contactNameEmpty = "";
		contactName1 = "Clien 1 " + contactName;
		contactName2 = "Clien 2 " + contactName;
		contactName3 = "Clien 3 " + contactName;
		contactName3Copy = "Clien Copy " + contactName;
		contactEmail = TestData.BannerClient.getContactEmail();
		contactEmailEmpty = "";
		contactEmail1 = "Clien1" + contactEmail;
		contactEmail2 = "Clien2" + contactEmail;
		contactEmail3 = "Clien3" + contactEmail;
		contactEmail3Copy = "ClientCopy" + contactEmail;
		stsSelectStatus = TestData.BannerClient.getStsSelectStatus();
		stsPublished = TestData.BannerClient.getStsPublished();
		stsUnpublished = TestData.BannerClient.getStsUnpublished();
		helpPageTitle = TestData.BannerClient.getHelpPageTitle();
		  
		Login_page loginPage = PageFactory.getLoginPage();
		adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
	}

	
	@Test(description="Verify that user can check in a client", priority=1 )
	public void TC_JOOMLA_BANNERS_CLIENTS_010(){
		managerBannerClientPage = adminPage.clickBannerClientManagerMenu();
		
		addBannerClientPage = managerBannerClientPage.clickNewBannerClient();
		addBannerClientPage.enterData(name, contactName, contactEmail, stsPublished);
		addBannerClientPage.clickSave();
		
		check = addBannerClientPage.isMessageDisplay(AppData.BannerClient.msgSave);
		verifyTrue(check, "VP: Banner Client successfully saved message is displayed");
		
		config.tearDown();
		config.setup();
		  
		Login_page loginPage = PageFactory.getLoginPage();
		adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
		
		managerBannerClientPage = adminPage.clickBannerClientManagerMenu();
		managerBannerClientPage.searchBannerClient(name);
		
		check = managerBannerClientPage.isArticleCheckedIn(name);
		verifyFalse(check , "VP: Check In icon is displayed");
				
		managerBannerClientPage.clickClientCheckbox(name);
		managerBannerClientPage.clickCheckInBannerClient();
		
		check = managerBannerClientPage.isArticleCheckedIn(name);
		verifyTrue(check, "VP: Check In icon is removed");
		
		check = managerBannerClientPage.isMessageDisplay(AppData.BannerClient.msgCheckedIn);
		verifyTrue(check, "VP: Banner Client successfully checked in message is displayed");		
	}
	
	@Test(description="Verify that user can browse New Client Help page", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_010", priority=1)
	public void TC_JOOMLA_BANNERS_CLIENTS_012(){
		addBannerClientPage = managerBannerClientPage.clickNewBannerClient();
		help_page = addBannerClientPage.clickHelpPage();
		
		check = help_page.isHelpWindowDisplays(helpPageTitle);
		verifyTrue(check, "VP: The Banner Client Help window is displayed");
		
		help_page.closeBackToParentPage();
	}
	
	@Test(description="Verify that user cannot create a new client without entering the name of the client", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_012", priority=1)
	public void TC_JOOMLA_BANNERS_CLIENTS_014(){
		
		addBannerClientPage.enterData(clientName1, contactNameEmpty, contactEmail, stsPublished);
		addBannerClientPage.clickSave();
		
		check = addBannerClientPage.isContactNameHighlighed();
		verifyTrue(check, "VP: Contact Name is highlighted in red");
	}
	
	@Test(description="Verify that user cannot create a new client without entering the email of the client", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_014", priority=1)
	public void TC_JOOMLA_BANNERS_CLIENTS_015(){
		
		addBannerClientPage.refreshPage();
		addBannerClientPage.enterData(clientName2, contactName, contactEmailEmpty, stsPublished);
		addBannerClientPage.clickSave();
		
		check = addBannerClientPage.isContactEmailHighlighed();
		verifyTrue(check, "VP: Contact Email is highlighted in red");
		
		managerBannerClientPage = addBannerClientPage.clickCancel();
	}
	
	@Test(description="Verify that user can create many clients by using 'Save & New' button", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_015", priority=1)
	public void TC_JOOMLA_BANNERS_CLIENTS_011(){
		addBannerClientPage = managerBannerClientPage.clickNewBannerClient();
		
		addBannerClientPage.enterData(clientName1, contactName1, contactEmail1, stsPublished);
		addBannerClientPage.clickSaveNew();
		
		check = addBannerClientPage.isMessageDisplay(AppData.BannerClient.msgSave);
		verifyTrue(check, "VP: First Banner Client successfully saved message is displayed");
		
		check = addBannerClientPage.isNewClientPageOpening();
		verifyTrue(check, "VP: New Client page is still opened");
		
		addBannerClientPage.enterData(clientName2, contactName2, contactEmail2, stsPublished);
		managerBannerClientPage = addBannerClientPage.clickSaveClose();
		
		check = managerBannerClientPage.isMessageDisplay(AppData.BannerClient.msgSave);
		verifyTrue(check, "VP: Second Banner Client successfully saved message is displayed");
		
		managerBannerClientPage.searchBannerClient(clientName1);
		check = managerBannerClientPage.isBannerClientExist(clientName1);
		verifyTrue(check, "VP: First Banner Client is created successfully");
		
		managerBannerClientPage.searchBannerClient(clientName2);
		check = managerBannerClientPage.isBannerClientExist(clientName2);
		verifyTrue(check, "VP: Second Banner Client is created successfully");
				
	} 
	
	@Test(description="Verify that user can creat a new client by using 'Save as Copy' button", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_011", priority=1)
	public void TC_JOOMLA_BANNERS_CLIENTS_013(){
		addBannerClientPage = managerBannerClientPage.clickNewBannerClient();
		addBannerClientPage.enterData(clientName3, contactName3, contactEmail3, stsPublished);
		addBannerClientPage.clickSave();
		
		check = addBannerClientPage.isMessageDisplay(AppData.BannerClient.msgSave);
		verifyTrue(check, "VP: Banner Client successfully saved message is displayed");
		
		check = addBannerClientPage.isEditClientPageOpening();
		verifyTrue(check, "VP: Edit Client page is opened");
		
		addBannerClientPage.enterData(clientName3Copy, contactName3Copy, contactEmail3Copy, stsPublished);
		addBannerClientPage.clickSaveCopy();
		
		check = addBannerClientPage.isMessageDisplay(AppData.BannerClient.msgSave);
		verifyTrue(check, "VP: Banner Client successfully saved message is displayed");
		
		managerBannerClientPage = addBannerClientPage.clickCancel();
		managerBannerClientPage.searchBannerClient(clientName3);
		
		check = managerBannerClientPage.isBannerClientExist(clientName3);
		verifyTrue(check, "VP: First Banner Client is created successfully");
		
		managerBannerClientPage.searchBannerClient(clientName3Copy);
		check = managerBannerClientPage.isBannerClientExist(clientName3Copy);
		verifyTrue(check, "VP: Banner Client Copied is created successfully");
				
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
  
  String name, clientName1, clientName2, clientName3, clientName3Copy, contactName, contactNameEmpty, contactName1, contactName2, contactName3, contactName3Copy, contactEmail, contactEmailEmpty, contactEmail1, contactEmail2, contactEmail3, contactEmail3Copy, stsSelectStatus, stsPublished, stsUnpublished, stsArchived, stsTrashed, helpPageTitle;
  boolean check;
  Login_page logInPage;
  Admin_page adminPage;
  Banner_Client_add_edit_page addBannerClientPage;
  Banner_Client_manager_page managerBannerClientPage;
  Help_page help_page;
}
