package testcases.category;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class TM_Category_001 extends AbstractTest{	
	
  @BeforeClass
  public void beforeClass() {
	  config.setup();
	  TestData.Category.getDataTest();	  
	  
	  name = TestData.Category.getName();  
	  nameEdit = name + " edited";
	  parent = TestData.Category.getParent();
	  stsPublished = TestData.Category.getStsPublished();
	  stsUnpublished = TestData.Category.getStsUnpublished();
	  accessReg = TestData.Category.getAccessRegistered();
	  accessPub = TestData.Category.getAccessPublic();
	  langUk = TestData.Category.getLanguageUK();
	  langAll = TestData.Category.getLanguageAll();
	  
	  categoryText = name + " category text";
	  helpPageTitle = TestData.Category.getHelpPageTitle();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify user can create an Category", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_001() {	 	  
	 
	  categoryManagerPage = adminPage.clickCategoryManagerMenu();
		 
	  addCategoryPage = categoryManagerPage.clickNewCategory();
	  
	  addCategoryPage.enterData(name, alias, parent, stsPublished, access, language, categoryText);
	  categoryManagerPage = addCategoryPage.clickSaveClose();	  
	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");	  
	  
	  categoryManagerPage.searchCategory(name);
	  	  
	  check = categoryManagerPage.isCategoryExist(name);
	  verifyTrue(check, "VP: Created Category is displayed");	  
  }
  
  @Test(description= "User can access category's help section", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_001")
  public void TC_JOOMLA_CATEGORY_MANAGER_008() {	  
		 
	  Help_page helpPage = categoryManagerPage.clickHelpToolbar();
	  check = helpPage.isHelpWindowDisplays(helpPageTitle);
	  verifyTrue(check, "VP: The category's help window is displayed");	  
	  helpPage.closeBackToParentPage();
  }  
  
  @Test(description= "Verify user can search for categorys using the filter text field", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_001", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_009() {	  	  
	  
	  categoryManagerPage.searchCategory(name);
	  	  
	  check = categoryManagerPage.isCategoryExist(name);
	  verifyTrue(check, "VP: Created Category is displayed");	  
	  
  }  

  @Test(description= "Verify user can create a new category with 'Public' Access Level property", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_001", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_017() {	  	  
	  
	  categoryManagerPage.searchCategory(name);
	  
	  check = categoryManagerPage.isCategoryPublic(name, "Public");
	  verifyTrue(check, "VP: The Access Level of the category is displayed as 'Public'");	  
	  
  }  
  
  @Test(description= "User can search for categories using the filter dropdown lists", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_001", priority=2)
  public void TC_JOOMLA_CATEGORY_MANAGER_010() {
	  	  
	  categoryManagerPage.filterStatus(stsUnpublished);
	  categoryManagerPage.filterAccess(accessReg);
	  categoryManagerPage.filterLanguage(langUk);
	  
	  check = categoryManagerPage.isCategoryExist(name);
	  verifyTrue(check, "VP: Created Category is displayed");	  
	  
	  categoryManagerPage.filterStatus("- Select Status -");
	  categoryManagerPage.filterAccess("- Select Access -");
	  categoryManagerPage.filterLanguage("- Select Languague -");
  }
  
  @Test(description= "Verify user can edit a category", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_001", priority=2)
  public void TC_JOOMLA_CATEGORY_MANAGER_002() {
	  
	  categoryManagerPage.clickCategoryCheckbox(name);
	  editCategoryPage = categoryManagerPage.clickEditCategory();	    
	  
	  editCategoryPage.enterData(nameEdit, alias, parent, stsPublished, access, categoryText);
	  categoryManagerPage = editCategoryPage.clickSaveClose();
	  	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");	  
	  
	  categoryManagerPage.searchCategory(nameEdit);
	  	  
	  check = categoryManagerPage.isCategoryExist(nameEdit);
	  verifyTrue(check, "VP: Edited category is displayed");	  
	  
  }
  
  @Test(description= "Verify user can publish an unpublished category", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_002")
  public void TC_JOOMLA_CATEGORY_MANAGER_003() {	  
	
	  categoryManagerPage.clickCategoryCheckbox(nameEdit);
	  categoryManagerPage.clickChangeStatusToolbar("Unpublish");	  
	  	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgUnpublish);
	  verifyTrue(check, "VP: '1 category successfully unpublished' message is displayed");
  }
  
  @Test(description= "Verify user can publish an unpublished category", dependsOnMethods="TC_JOOMLA_CATEGORY_MANAGER_003")
  public void TC_JOOMLA_CATEGORY_MANAGER_004() {
	  	  
	  categoryManagerPage.clickCategoryCheckbox(nameEdit);
	  categoryManagerPage.clickChangeStatusToolbar("Publish");	  
	  	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgPublish);
	  verifyTrue(check, "VP: '1 category successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can move a category to the archive", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_004")
  public void TC_JOOMLA_CATEGORY_MANAGER_005() {
	  
	  categoryManagerPage.clickCategoryCheckbox(nameEdit);
	  categoryManagerPage.clickArchiveCategory(); 
	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgArchive);
	  verifyTrue(check, "The '1 category successfully archived' message is displayed");
	  
	  categoryManagerPage.filterStatus("Archived");
	  
	  categoryManagerPage.searchCategory(nameEdit);
	  	  
	  check = categoryManagerPage.isCategoryExist(nameEdit);
	  verifyTrue(check, "VP: The deleted category is displayed on the table grid");
	  
	  categoryManagerPage.filterStatus("- Select Status -");	  
  }    

  
  private String name, alias, parent, stsPublished, language, access, categoryText, helpPageTitle;
  private String nameEdit, stsUnpublished, accessReg, accessPub, langUk, langAll;
  private boolean check;
  private Category_manager_page categoryManagerPage;
  private Category_add_edit_page editCategoryPage, addCategoryPage;
  private Admin_page adminPage;
}
