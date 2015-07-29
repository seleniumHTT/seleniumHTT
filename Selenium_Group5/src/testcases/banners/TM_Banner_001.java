//package testcases.banners;
//
//import org.testng.annotations.*;
//import abstracts.*;
//import pages.*;
//import common.*;
//
//public class TM_Banner_001 extends AbstractTest {
//  
//	@BeforeClass
//	public void beforeClass(){
//		config.setup();
//		
//		  name = TestData.Banner.getName();
//		  nameEdited = name + " edited";
//		  alias = TestData.Banner.getAlias();
//		  category = TestData.Banner.getCategory();
//		  unCategory = TestData.Banner.getUncategory();
//		  stsSelectStatus = TestData.Banner.getStsSelectStatus();
//		  stsPublished = TestData.Banner.getStsPublished();
//		  stsUnpublished = TestData.Banner.getStsUnpublished();
//		  stsArchived = TestData.Banner.getStsArchived();
//		  stsTrashed = TestData.Banner.getStsTrashed();
//		  helpPageTitle = TestData.Banner.getHelpPageTitle();
//		  
//		  Login_page loginPage = PageFactory.getLoginPage();
//		  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
//	  }
//	    
//	  @Test(description= "Verify that user can browse 'Banner help' page", priority=1)
//	  public void TC_JOOMLA_BANNERS_BANNERS_007() {	  
//		  managerBannerPage = adminPage.clickBannerManagerMenu();
//		  
//		  helpPage = managerBannerPage.clickHelpBanner();
//		  check = helpPage.isHelpWindowDisplays(helpPageTitle);
//		  verifyTrue(check, "VP: The Banner Help window is displayed");	  
//		  helpPage.closeBackToParentPage();
//	  } 
//	  
//	  @Test(description = "Verify that user can create new banner", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_007", priority=1)
//	  public void TC_JOOMLA_BANNERS_BANNERS_001(){
//		
//		
//		addBannerPage = managerBannerPage.clickNewBanner();
//		addBannerPage.enterData(name, alias, category, stsPublished);
//		
//		managerBannerPage = addBannerPage.clickSaveClose();
//		
//		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgSave);
//		verifyTrue(check, "VP: Banner successfully saved message is displayed");
//		
//		managerBannerPage.searchBanner(name);
//		
//		check = managerBannerPage.isBannerExist(name);
//		verifyTrue(check, "VP: The created Banner is displayed");
//					
//	  }  	
//	  
//	  @Test(description = "Verify that user can search a banner by using filter textbox", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_007", priority=1)
//	  public void TC_JOOMLA_BANNERS_BANNERS_008() {
//		  
//		managerBannerPage.searchBanner(name);
//			
//		check = managerBannerPage.isBannerExist(name);
//		verifyTrue(check, "VP: The created Banner is displayed");
//		
//	  }
//	    
//	  @Test(description ="Verify that user can edit a banner", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_008", priority=1)
//	  public void TC_JOOMLA_BANNERS_BANNERS_002(){
//		  
//		managerBannerPage.clickBannerCheckbox(name);
//		addBannerPage = managerBannerPage.clickEditBanner();
//		
//		addBannerPage.enterData(nameEdited, alias, unCategory, stsPublished);
//		
//		managerBannerPage = addBannerPage.clickSaveClose();
//		
//		check = managerBannerPage.isMessageDisplay(AppData.Banner.msgSave);
//		verifyTrue(check, "VP: Banner successfully saved message is displayed");
//		
//		managerBannerPage.searchBanner(nameEdited);
//		check = managerBannerPage.isBannerExist(nameEdited);
//		verifyTrue(check, "VP: The created Banner is displayed");
//		
//	  }  
//	  
//	  @Test(description = "Verify that user can search a client by using filter dropdown list", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_002", priority=1)
//	  public void TC_JOOMLA_BANNERS_BANNERS_009() {
//			  
//		managerBannerPage.filterStatus(stsPublished);
//		  
//		managerBannerPage.isBannerExist(nameEdited);
//		verifyTrue(check, "VP: The created Banner is displayed");
//		
//	  }
//	  
//	  @Test(description ="Verify that user can publish a client", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_009", priority=1)
//	  public void TC_JOOMLA_BANNERS_BANNERS_003(){
//		  
//		managerBannerPage.clickClientCheckbox(nameEdited);
//		managerBannerPage.clickPublishBannerClient();
//		
//		check = managerBannerPage.isMessageDisplay(AppData.BannerClient.msgPublish);
//		verifyTrue(check, "VP: Banner Client successfully punlished message is displayed");
//		
//		managerBannerPage.filterStatus(stsPublished);
//		
//		managerBannerPage.searchBannerClient(nameEdited);
//		check = managerBannerPage.isBannerClientExist(nameEdited);
//		verifyTrue(check, "VP: The Banner Client is published successfully");
//		
//	  }
//	  
//	  @Test(description ="Verify that user can unpublish a client", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_003", priority=1)
//	  public void TC_JOOMLA_BANNERS_BANNERS_004(){
//		  
//		
//		managerBannerPage.clickClientCheckbox(nameEdited);
//		managerBannerPage.clickUnpublishBannerClient();
//		
//		check = managerBannerPage.isMessageDisplay(AppData.BannerClient.msgUnpublish);
//		verifyTrue(check, "VP: Banner Client successfully unpunlished message is displayed");
//		
//		managerBannerPage.filterStatus(stsUnpublished);
//		
//		managerBannerPage.searchBannerClient(nameEdited);
//		check = managerBannerPage.isBannerClientExist(nameEdited);
//		verifyTrue(check, "VP: The Banner Client is unpublished successfully");
//		
//	  }  
//	  
//	  @Test(description ="Verify that user can archive a client", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_004", priority=1)
//	  public void TC_JOOMLA_BANNERS_BANNERS_005(){	 
//		
//		managerBannerPage.clickClientCheckbox(nameEdited);
//		managerBannerPage.clickArchiveBannerClient();
//		
//		check = managerBannerPage.isMessageDisplay(AppData.BannerClient.msgArchive);
//		verifyTrue(check, "VP: Banner Client successfully archived message is displayed");
//		
//		managerBannerPage.filterStatus(stsArchived);
//		
//		managerBannerPage.searchBannerClient(nameEdited);
//		check = managerBannerPage.isBannerClientExist(nameEdited);
//		verifyTrue(check, "VP: The Banner Client is archived successfully");
//		
//	  } 
//	  
//	  @Test(description ="Verify that user can send a client to trash", dependsOnMethods= "TC_JOOMLA_BANNERS_BANNERS_005", priority=1)
//	  public void TC_JOOMLA_BANNERS_BANNERS_006(){	 
//		
//		managerBannerPage.clickClientCheckbox(nameEdited);
//		managerBannerPage.clickTrashBannerClient();
//		
//		check = managerBannerPage.isMessageDisplay(AppData.BannerClient.msgTrash);
//		verifyTrue(check, "VP: Banner Client successfully trashed message is displayed");
//		
//		managerBannerPage.filterStatus(stsTrashed);
//		
//		managerBannerPage.searchBannerClient(nameEdited);
//		check = managerBannerPage.isBannerClientExist(nameEdited);
//		verifyTrue(check, "VP: The Banner Client is trashed successfully");
//		
//	  }
//	  
//	  
//	  
//	  @AfterClass
//	  public void afterClass() {
//		config.tearDown();
//	  }
//
//	  @BeforeTest
//	  public void beforeTest() {
//	  }
//
//	  @AfterTest
//	  public void afterTest() {
//	  }
//	  
//	  String name, nameEdited, alias, category, unCategory, stsSelectStatus, stsPublished, stsUnpublished, stsArchived, stsTrashed, helpPageTitle;
//	  boolean check;
//	  Login_page logInPage;
//	  Admin_page adminPage;
//	  Banner_add_edit_page addBannerPage;
//	  Banner_manager_page managerBannerPage;
//	  Help_page helpPage;
//	  
//	}
