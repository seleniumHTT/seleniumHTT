package testcases.banners;

import org.testng.annotations.*;
import abstracts.*;
import pages.*;
import common.*;

public class TM_Banner_001 extends AbstractTest {
  
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
		bannerName = TestData.Banner.getName();
		bannerNameEdited = bannerName + " edited";		
		stsSelectStatus = TestData.Banner.getStsSelectStatus();
		stsPublished = TestData.Banner.getStsPublished();
		stsUnpublished = TestData.Banner.getStsUnpublished();
		stsArchived = TestData.Banner.getStsArchived();
		stsTrashed = TestData.Banner.getStsTrashed();
		helpPageTitle = TestData.Banner.getHelpPageTitle();
		 
		Login_page loginPage = PageFactory.getLoginPage();
		adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
	  }
	      
	  @Test(description = "Verify that user can create new banner", priority=1)
	  public void TC_JOOMLA_BANNERS_BANNERS_001(){
		
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
		addBannerPage.enterData(bannerName, bannerAlias, categoryName, stsPublished, clientName);
		
		managerBannerPage = addBannerPage.clickSaveClose();
		
		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgSave);
		verifyTrue(check, "VP: Banner successfully saved message is displayed");
		
		managerBannerPage.searchBanner(bannerName);
		
		check = managerBannerPage.isBannerExist(bannerName);
		verifyTrue(check, "VP: The created Banner is displayed");
					
	  }  	
	  
	  @Test(description= "Verify that user can browse 'Banner help' page", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_001", priority=1)
	  public void TC_JOOMLA_BANNERS_BANNERS_007() {	  
		  		   
		  helpPage = managerBannerPage.clickHelpBanner();
		  
		  check = helpPage.isHelpWindowDisplays(helpPageTitle);
		  verifyTrue(check, "VP: The Banner Help window is displayed");	  
		  helpPage.closeBackToParentPage();
	  } 
	  
	  @Test(description = "Verify that user can search a banner by using filter textbox", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_007", priority=1)
	  public void TC_JOOMLA_BANNERS_BANNERS_008() {
		  
		managerBannerPage.searchBanner(bannerName);
			
		check = managerBannerPage.isBannerExist(bannerName);
		verifyTrue(check, "VP: The created Banner is displayed");
		
	  }
	    
	  @Test(description ="Verify that user can edit a banner", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_008", priority=1)
	  public void TC_JOOMLA_BANNERS_BANNERS_002(){
		  
		managerBannerPage.clickBannerCheckbox(bannerName);
		addBannerPage = managerBannerPage.clickEditBanner();
		
		addBannerPage.enterData(bannerNameEdited, bannerAlias, bannerCategory, stsPublished, bannerClient);
		
		managerBannerPage = addBannerPage.clickSaveClose();
		
		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgSave);
		verifyTrue(check, "VP: Banner successfully saved message is displayed");
		
		managerBannerPage.searchBanner(bannerNameEdited);
		check = managerBannerPage.isBannerExist(bannerNameEdited);
		verifyTrue(check, "VP: The edited Banner is displayed");
		
	  }  
	  
	  @Test(description = "Verify that user can search a banner by using filter dropdown list", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_002", priority=1)
	  public void TC_JOOMLA_BANNERS_BANNERS_009() {
			  
		managerBannerPage.filterStatus(stsPublished);
		managerBannerPage.searchBanner(bannerNameEdited);
		  
		check = managerBannerPage.isBannerExist(bannerNameEdited);
		verifyTrue(check, "VP: The created Banner is displayed");
		
	  }
	  
	  @Test(description ="Verify that user can unpublish a banner", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_009", priority=1)
	  public void TC_JOOMLA_BANNERS_BANNERS_004(){
		  
		managerBannerPage.clickBannerCheckbox(bannerNameEdited);
		managerBannerPage.clickUnpublishBanner();
		
		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgUnpublish);
		verifyTrue(check, "VP: Banner successfully unpublished message is displayed");
		
		managerBannerPage.filterStatus(stsUnpublished);
		
		managerBannerPage.searchBanner(bannerNameEdited);
		check = managerBannerPage.isBannerExist(bannerNameEdited);
		verifyTrue(check, "VP: The Banner is unpublished successfully");
		
	  }  
	 
	  @Test(description ="Verify that user can create a new banner with 'unpublished' status", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_004", priority=1)
	  public void TC_JOOMLA_BANNERS_BANNERS_003(){
		  
		addBannerPage = managerBannerPage.clickNewBanner();
		addBannerPage.enterData(bannerName, bannerAlias, categoryName, stsUnpublished, clientName);
			
		managerBannerPage = addBannerPage.clickSaveClose();
			
		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgSave);
		verifyTrue(check, "VP: Banner successfully saved message is displayed");
			
		managerBannerPage.filterStatus(stsSelectStatus);
		managerBannerPage.searchBanner(bannerName);
		
		check = managerBannerPage.isBannerExist(bannerName);
		verifyTrue(check, "VP: The created Banner is displayed");
		
	  }	  

	  
	  @Test(description ="Verify that user can archive a Banner", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_003", priority=1)
	  public void TC_JOOMLA_BANNERS_BANNERS_005(){	 
		
		managerBannerPage.clickBannerCheckbox(bannerNameEdited);
		managerBannerPage.clickArchiveBanner();
		
		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgArchive);
		verifyTrue(check, "VP: Banner successfully archived message is displayed");
		
		managerBannerPage.filterStatus(stsArchived);
		
		managerBannerPage.searchBanner(bannerNameEdited);
		check = managerBannerPage.isBannerExist(bannerNameEdited);
		verifyTrue(check, "VP: The Banner is archived successfully");
		
	  } 
	  
	  @Test(description ="Verify that user can send a Banner to trash", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_005", priority=1)
	  public void TC_JOOMLA_BANNERS_BANNERS_006(){	 
		
		managerBannerPage.clickBannerCheckbox(bannerNameEdited);
		managerBannerPage.clickTrashBanner();
		
		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgTrash);
		verifyTrue(check, "VP: Banner successfully trashed message is displayed");
		
		managerBannerPage.filterStatus(stsTrashed);
		
		managerBannerPage.searchBanner(bannerNameEdited);
		check = managerBannerPage.isBannerExist(bannerNameEdited);
		verifyTrue(check, "VP: The Banner is trashed successfully");
		
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
	  String bannerName, bannerNameEdited, bannerAlias, bannerCategory, bannerClient, stsSelectStatus, stsPublished, stsUnpublished, stsArchived, stsTrashed, helpPageTitle;
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
