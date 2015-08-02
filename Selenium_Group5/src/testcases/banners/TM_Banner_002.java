package testcases.banners;

import org.testng.annotations.*;
import abstracts.*;
import pages.*;
import common.*;

public class TM_Banner_002 extends AbstractTest {
	
	@BeforeClass
	public void beforeClass(){
		
		config.setup();
		TestData.BannerCategory.getDataTest();
		TestData.BannerClient.getDataTest();
		TestData.Banner.getDataTest();
		
		categoryName = TestData.BannerCategory.getName();
		clientName = TestData.BannerClient.getName();
		clientContact = TestData.BannerClient.getContactName();
		clientEmail = TestData.BannerClient.getContactEmail();
		bannerNameCI = TestData.Banner.getName();
		bannerNameSN = bannerNameCI + " SaveNew";
		bannerNameSasN = bannerNameCI + " SaveAsNew";
		bannerNameSC = bannerNameCI + " SaveCopy";
		bannerNameSasC = bannerNameCI + " SaveAsCopy";
		stsSelectStatus = TestData.Banner.getStsSelectStatus();
		stsPublished = TestData.Banner.getStsPublished();
		stsUnpublished = TestData.Banner.getStsUnpublished();
		stsArchived = TestData.Banner.getStsArchived();
		stsTrashed = TestData.Banner.getStsTrashed();
		helpPageTitle = TestData.Banner.getHelpPageTitle();
		 
		Login_page loginPage = PageFactory.getLoginPage();
		adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
	  }
	
	@Test(description="Verify that user can check in a banner", priority=1)
	public void TC_JOOMLA_BANNERS_BANNERS_010(){
		
		managerBannerClientPage = adminPage.clickBannerClientManagerMenu();
		
		addBannerClientPage = managerBannerClientPage.clickNewBannerClient();
		addBannerClientPage.enterData(clientName, clientContact, clientEmail, clientStatus);
		managerBannerClientPage = addBannerClientPage.clickSaveClose();
		
		check = managerBannerClientPage.isMessageDisplay(AppData.BannerClient.msgSave);
		verifyTrue(check, "VP: Banner Client successfully saved message is displayed");
		
		managerBannerClientPage.searchBannerClient(clientName);
		
		check = managerBannerClientPage.isBannerClientExist(clientName);
		verifyTrue(check, "VP: Banner Client is created successfully");
		
		managerBannerCategoryPage = managerBannerClientPage.clickBannerCategoryManagerMenu();
		
		addBannerCategoryPage = managerBannerCategoryPage.clickNewCategories();
		addBannerCategoryPage.enterData(categoryName, categoryAlias, categoryParent, catagoryStatus, categoryAccess, categoryLanguage, categoryText);
		managerBannerCategoryPage = addBannerCategoryPage.clickSaveClose();
		
		check = managerBannerCategoryPage.isMessageDisplay(AppData.BannerCategory.msgSave);
		verifyTrue(check, "VP: Banner Category successfully saved message is displayed");
		
		managerBannerCategoryPage.searchBannerCategory(categoryName);
		
		check = managerBannerCategoryPage.isBannerCategoryExist(categoryName);
		verifyTrue(check, "VP: Banner Client is created successfully");
		
		managerBannerPage = managerBannerCategoryPage.clickBannerManagerMenu();
		
		addBannerPage = managerBannerPage.clickNewBanner();
		addBannerPage.enterData(bannerNameCI, bannerAlias, categoryName, stsPublished, clientName);
		addBannerPage.clickSave();
		
		check = addBannerPage.isMessageDisplay(AppData.Banner.msgSave);
		verifyTrue(check, "VP: Banner successfully saved message is displayed");
		
		config.tearDown();
		config.setup();
		Login_page loginPage = PageFactory.getLoginPage();
		adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
		
		managerBannerPage = adminPage.clickBannerManagerMenu();
		managerBannerPage.searchBanner(bannerNameCI);
		
		check = managerBannerPage.isBannerCheckedIn(bannerNameCI);
		verifyFalse(check, "VP: The created Banner is checked in successfully");
		
		managerBannerPage.clickBannerCheckbox(bannerNameCI);
		managerBannerPage.clickCheckInBanner();
		
		check = managerBannerPage.isBannerCheckedIn(bannerNameCI);
		verifyTrue(check, "VP: Check In icon is removed");
		
		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgCheckedIn);
		verifyTrue(check, "VP: Banner successfully checked in message is displayed");
		
	}
	
	@Test(description="Verify that user can browse 'New Banner help' page in 'New banner' page", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_010", priority=1)
	public void TC_JOOMLA_BANNERS_BANNERS_012(){
				
		addBannerPage = managerBannerPage.clickNewBanner();
		helpPage = addBannerPage.clickHelpPage();
		
		check = helpPage.isHelpWindowDisplays(helpPageTitle);
		verifyTrue(check, "VP: The Banner Client Help window is displayed");
		
		helpPage.closeBackToParentPage();
	}
	
	@Test(description="Verify that user cannot create a new banner without entering the name of the banner", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_012", priority=1)
	public void TC_JOOMLA_BANNERS_BANNERS_014(){
		
		addBannerPage.clickSave();
		
		check = addBannerPage.isBannerNameHighlighed();
		verifyTrue(check, "VP: Banner Name is highlighted in red");
	}
	
	@Test(description="Verify that user can create many banners by using 'Save & New' button", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_014", priority=1)
	public void TC_JOOMLA_BANNERS_BANNERS_011(){
				
		addBannerPage.enterData(bannerNameSN, bannerAlias, categoryName, stsPublished, clientName);
		addBannerPage.clickSaveNew();
		
		check = addBannerPage.isMessageDisplay(AppData.Banner.msgSave);
		verifyTrue(check, "VP: First Banner successfully saved message is displayed");
		
		check = addBannerPage.isNewBannerPageOpening();
		verifyTrue(check, "VP: New Banner page is still opened");
		
		addBannerPage.enterData(bannerNameSasN, bannerAlias, categoryName, stsPublished, clientName);
		managerBannerPage = addBannerPage.clickSaveClose();
		
		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgSave);
		verifyTrue(check, "VP: Second Banner successfully saved message is displayed");
		
		managerBannerPage.searchBanner(bannerNameSN);
		check = managerBannerPage.isBannerExist(bannerNameSN);
		verifyTrue(check, "VP: First Banner is created successfully");
		
		managerBannerPage.searchBanner(bannerNameSasN);
		check = managerBannerPage.isBannerExist(bannerNameSasN);
		verifyTrue(check, "VP: Second Banner is created successfully");
				
	} 
	
	@Test(description="Verify that user can create a new banner by using 'Save as Copy' button", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_011", priority=1)
	public void TC_JOOMLA_BANNERS_BANNERS_013(){
		addBannerPage = managerBannerPage.clickNewBanner();
		addBannerPage.enterData(bannerNameSC, bannerAlias, categoryName, stsPublished, clientName);
		addBannerPage.clickSave();
		
		check = addBannerPage.isMessageDisplay(AppData.Banner.msgSave);
		verifyTrue(check, "VP: Banner successfully saved message is displayed");
		
		check = addBannerPage.isEditBannerPageOpening();
		verifyTrue(check, "VP: Edit Banner page is opened");
		
		addBannerPage.enterData(bannerNameSasC, bannerAlias, categoryName, stsPublished, clientName);
		addBannerPage.clickSaveCopy();
		
		check = addBannerPage.isMessageDisplay(AppData.Banner.msgSave);
		verifyTrue(check, "VP: Copied Banner successfully saved message is displayed");
		
		managerBannerPage = addBannerPage.clickCancel();
		managerBannerPage.searchBanner(bannerNameSC);
		
		check = managerBannerPage.isBannerExist(bannerNameSC);
		verifyTrue(check, "VP: First Banner is created successfully");
		
		managerBannerPage.searchBanner(bannerNameSasC);
		check = managerBannerPage.isBannerExist(bannerNameSasC);
		verifyTrue(check, "VP: The copied Banner is created successfully");
				
	}
	
	@Test(description="Verify that user can access 'Banner clients' page while browsing 'Banner' page", dependsOnMethods="TC_JOOMLA_BANNERS_BANNERS_013", priority=1)
	public void TC_JOOMLA_BANNERS_BANNERS_017(){
		managerBannerClientPage = managerBannerPage.switchToBannerClient();
		
		check = managerBannerClientPage.isBannerClientManagerTitleCorrect();
		verifyTrue(check, "VP: User naviagted to 'Banner clients' page from 'Banner' page successfully");
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
	  
	String categoryName, categoryAlias, categoryParent, catagoryStatus, categoryAccess, categoryLanguage, categoryText;
	String clientName, clientContact, clientEmail, clientStatus;
	String bannerNameCI, bannerNameSC, bannerNameSasC, bannerNameSN, bannerNameSasN, bannerAlias, bannerCategory, bannerClient, stsSelectStatus, stsPublished, stsUnpublished, stsArchived, stsTrashed, helpPageTitle;
	boolean check;
	Login_page logInPage;
	Admin_page adminPage;
	Banner_add_edit_page addBannerPage, editBannerPage;
	Banner_manager_page managerBannerPage;
	Banner_Client_add_edit_page addBannerClientPage, editBannerClientPage;
	Banner_Client_manager_page managerBannerClientPage;
	Banner_Category_add_edit_page addBannerCategoryPage, editBannerCategoryPage;
	Banner_Category_manager_page managerBannerCategoryPage;
	Help_page helpPage;
	  
}
