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
	  langUk = TestData.Category.getLanguageUK();
	  	  
	  categoryText = name + " category text";
	  helpPageTitle = TestData.Category.getHelpPageTitle();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify that user can browse 'Category help' page", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_007() {	  
	  
	  managerCategoryPage = adminPage.clickCategoryManagerMenu();
	  
	  Help_page helpPage = managerCategoryPage.clickHelpToolbar();
	  
	  check = helpPage.isHelpWindowDisplays(helpPageTitle);
	  verifyTrue(check, "VP: The Category's Help window is displayed");	  
	  
	  helpPage.closeBackToParentPage();
  } 
 
  @Test(description= "Verify that user can create a new category", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_007", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_001() {	 	  
	 
	  addCategoryPage = managerCategoryPage.clickNewCategory();
	  
	  addCategoryPage.enterData(name, alias, parent, stsPublished, access, language, categoryText);
	  managerCategoryPage = addCategoryPage.clickSaveClose();	  
	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");	  
	  
	  managerCategoryPage.searchCategory(name);
	  	  
	  check = managerCategoryPage.isCategoryExist(name);
	  verifyTrue(check, "VP: Created Category is displayed");	  
  } 
  
  @Test(description= "Verify user can search for categorys using the filter text field", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_001", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_008() {	  	  
	  
	  managerCategoryPage.searchCategory(name);
	  	  
	  check = managerCategoryPage.isCategoryExist(name);
	  verifyTrue(check, "VP: Created Category is displayed");	  
	  
  }  

  @Test(description= "Verify that user can edit a category", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_008", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_002() {
	  
	  managerCategoryPage.clickCategoryCheckbox(name);
	  editCategoryPage = managerCategoryPage.clickEditCategory();	    
	  
	  editCategoryPage.enterData(nameEdit, alias, parent, stsUnpublished, accessReg, langUk, categoryText);
	  managerCategoryPage = editCategoryPage.clickSaveClose();
	  	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");	  
	  
	  managerCategoryPage.searchCategory(nameEdit);
	  	  
	  check = managerCategoryPage.isCategoryExist(nameEdit);
	  verifyTrue(check, "VP: Edited Category is displayed");	  
	  
  }  
  
  @Test(description= "User can search for categories using the filter dropdown lists", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_002", priority=2)
  public void TC_JOOMLA_CATEGORY_MANAGER_009() {
	  	  
	  managerCategoryPage.filterStatus(stsUnpublished);
	  managerCategoryPage.filterAccess(accessReg);
	  managerCategoryPage.filterLanguage(langUk);
	  
	  check = managerCategoryPage.isCategoryExist(nameEdit);
	  verifyTrue(check, "VP: Created Category is displayed");	  
	  
	  managerCategoryPage.filterStatus("- Select Status -");
	  managerCategoryPage.filterAccess("- Select Access -");
	  managerCategoryPage.filterLanguage("- Select Language -");
  }
  
  @Test(description= "Verify user can publish an unpublished category", dependsOnMethods="TC_JOOMLA_CATEGORY_MANAGER_009", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_003() {
	  	  
	  managerCategoryPage.searchCategory(nameEdit);
	  managerCategoryPage.clickCategoryCheckbox(nameEdit);
	  managerCategoryPage.clickChangeStatusToolbar("Publish");	  
	  	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgPublish);
	  verifyTrue(check, "VP: '1 category successfully published' message is displayed");
	  
	  managerCategoryPage.filterStatus(stsPublished);
	  managerCategoryPage.searchCategory(nameEdit);
	  
	  check = managerCategoryPage.isCategoryExist(nameEdit);
	  verifyTrue(check, "VP: The selected Category is published successfully");
  }
  
  @Test(description= "Verify that user can publish a category", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_003", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_004() {	  
	
	  managerCategoryPage.clickCategoryCheckbox(nameEdit);
	  managerCategoryPage.clickChangeStatusToolbar("Unpublish");	  
	  	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgUnpublish);
	  verifyTrue(check, "VP: '1 category successfully unpublished' message is displayed");
	  
	  managerCategoryPage.filterStatus(stsUnpublished);
	  managerCategoryPage.searchCategory(nameEdit);
	  
	  check = managerCategoryPage.isCategoryExist(nameEdit);
	  verifyTrue(check, "VP: The selected Category is Unpublished successfully");
  }
  
 
  @Test(description= "Verify that user can archive a category", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_004", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_005() {
	  
	  managerCategoryPage.clickCategoryCheckbox(nameEdit);
	  managerCategoryPage.clickArchiveCategory(); 
	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgArchive);
	  verifyTrue(check, "The '1 category successfully archived' message is displayed");
	  
	  managerCategoryPage.filterStatus("Archived");
	  
	  managerCategoryPage.searchCategory(nameEdit);
	  	  
	  check = managerCategoryPage.isCategoryExist(nameEdit);
	  verifyTrue(check, "VP: The selected Category is archived successfully");	   	  
  }    
  
  @Test(description= "Verify that user can send a category to trash", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_005", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_006() {
	  
	  managerCategoryPage.clickCategoryCheckbox(nameEdit);
	  managerCategoryPage.clickTrashCategory(); 
	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgTrash);
	  verifyTrue(check, "The '1 category successfully archived' message is displayed");
	  
	  managerCategoryPage.filterStatus("Trashed");
	  
	  managerCategoryPage.searchCategory(nameEdit);
	  	  
	  check = managerCategoryPage.isCategoryExist(nameEdit);
	  verifyTrue(check, "VP: The selected Category is trashed successfully");	
  }

  
  private String name, alias, parent, stsPublished, language, access, categoryText, helpPageTitle;
  private String nameEdit, stsUnpublished, accessReg, langUk;
  private boolean check;
  private Category_manager_page managerCategoryPage;
  private Category_add_edit_page editCategoryPage, addCategoryPage;
  private Admin_page adminPage;
}
