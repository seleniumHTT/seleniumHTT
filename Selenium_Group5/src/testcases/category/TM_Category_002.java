package testcases.category;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class TM_Category_002 extends AbstractTest{

	
  @BeforeClass
  public void beforeClass() {	  
	  config.setup();
	  TestData.Category.getDataTest();
	  
	  name = TestData.Category.getName();
	  name2 = "Another " + name;
	  nameSaveAsNew = name + " New";
	  name2SaveAsCopy = name2 + " Copy";
	  parent = TestData.Category.getParent();
	  stsPublished = TestData.Category.getStsPublished();
	  stsUnpublished = TestData.Category.getStsUnpublished();
	  accessReg = TestData.Category.getAccessRegistered();
	  langUk = TestData.Category.getLanguageUK();
	  categoryText = name + " category text";
	  helpPageTitle = TestData.Category.getHelpPageTitle();
	  defaultAccessLevel = "- Keep original Access Levels -";
	  defaultLanguage = "- Keep original Language -";
	  batchActionMove = "Move";
	  batchActionCopy = "Copy";
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
   
  @Test(description= "Verify that user can browse 'New Category help' page", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_011() {
	  
	  managerCategoryPage = adminPage.clickCategoryManagerMenu();
	  
	  addCategoryPage = managerCategoryPage.clickNewCategory();
	  helpPage = addCategoryPage.clickHelpPage();
	  
	  check = helpPage.isHelpWindowDisplays(helpPageTitle);
	  verifyTrue(check, "VP: The Category's Help window is displayed");
	  
	  helpPage.closeBackToParentPage();
  }
  
  @Test(description="Verify that user can cancel adding action while adding a new create", dependsOnMethods="TC_JOOMLA_CATEGORY_MANAGER_011", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_012(){
	  
	  addCategoryPage.enterData(name, alias, parent, stsPublished, accessReg, langUk, categoryText);
	  managerCategoryPage = addCategoryPage.clickCancel();
	  
	  managerCategoryPage.searchCategory(name);
	  
	  check = managerCategoryPage.isCategoryExist(name);
	  verifyFalse(check, "The new Category is not created");
	  
  }
  
  @Test(description="Verify that user can create many categories by using 'Save & New' button", dependsOnMethods="TC_JOOMLA_CATEGORY_MANAGER_012", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_010(){
	  
	  addCategoryPage = managerCategoryPage.clickNewCategory();
	  
	  addCategoryPage.enterData(name, alias, parent, stsPublished, accessReg, langUk, categoryText);
	  addCategoryPage.clickSaveNew();
	  
	  check = addCategoryPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");
	  
	  addCategoryPage.enterData(nameSaveAsNew, alias, parent, stsPublished, accessReg, langUk, categoryText);
	  managerCategoryPage = addCategoryPage.clickSaveClose();
	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");
	  
	  managerCategoryPage.filterStatus("- Select Status -");
	  managerCategoryPage.filterAccess("- Select Access -");
	  managerCategoryPage.filterLanguage("- Select Language -");
	  
	  managerCategoryPage.searchCategory(name);
	  
	  check = managerCategoryPage.isCategoryExist(name);
	  verifyTrue(check, "VP: The first Category is created");
	  
	  managerCategoryPage.searchCategory(name);
	  
	  check = managerCategoryPage.isCategoryExist(name);
	  verifyTrue(check, "VP: The first Category is created");
  }
  
  @Test(description="Verify that user can creat a new category by using 'Save as Copy' button", dependsOnMethods="TC_JOOMLA_CATEGORY_MANAGER_010", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_013(){
	  
	  addCategoryPage = managerCategoryPage.clickNewCategory();
	  
	  addCategoryPage.enterData(name2, alias, parent, stsPublished, accessReg, langUk, categoryText);
	  editCategoryPage = addCategoryPage.clickSave();
	  
	  check = editCategoryPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");
	  
	  check = editCategoryPage.isEditClientPageOpening();
	  verifyTrue(check, "VP: Edit Category page is openning");
	  
	  editCategoryPage.enterData(name2SaveAsCopy, alias, parent, stsPublished, accessReg, langUk, categoryText);
	  managerCategoryPage = editCategoryPage.clickSaveCopy();
	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");
	  
	  managerCategoryPage.filterStatus("- Select Status -");
	  managerCategoryPage.filterAccess("- Select Access -");
	  managerCategoryPage.filterLanguage("- Select Language -");
	  
	  managerCategoryPage.searchCategory(name2);
	  
	  check = managerCategoryPage.isCategoryExist(name2);
	  verifyTrue(check, "VP: The first Category is created");
	  
	  managerCategoryPage.searchCategory(name2SaveAsCopy);
	  
	  check = managerCategoryPage.isCategoryExist(name2SaveAsCopy);
	  verifyTrue(check, "VP: The first Category is created");
	  
  }
  
  @Test(description="Verify that user can move may articles to another category", dependsOnMethods="TC_JOOMLA_CATEGORY_MANAGER_013", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_014() {
	  
	  managerCategoryPage.searchCategory(nameSaveAsNew);
	  managerCategoryPage.clickCategoryCheckbox(nameSaveAsNew);
	  managerCategoryPage.batchProcess(defaultAccessLevel, defaultLanguage, name, batchActionMove);
	  managerCategoryPage.clickProcess();
	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgBatch);
	  verifyTrue(check, "VP: Batch Process completed message displayed");
	  
	  managerCategoryPage.clickCategoryCheckbox(nameSaveAsNew);
	  editCategoryPage = managerCategoryPage.clickEditCategory();
	  
	  check = editCategoryPage.isCategoryDataCorrect(nameSaveAsNew, alias, name, stsPublished, accessReg, langUk, categoryText);
	  verifyTrue(check, "VP: Category Artical is moved successfully");
	  
	  managerCategoryPage = addCategoryPage.clickCancel();
  }
  
  @Test(description="Verify that user can copy may articles to another category", dependsOnMethods="TC_JOOMLA_CATEGORY_MANAGER_014", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_015(){
	  
	  managerCategoryPage.searchCategory(name2SaveAsCopy);
	  managerCategoryPage.clickCategoryCheckbox(name2SaveAsCopy);
	  managerCategoryPage.batchProcess(defaultAccessLevel, defaultLanguage, name2, batchActionCopy);
	  managerCategoryPage.clickProcess();
	  
	  check = managerCategoryPage.isMessageDisplay(AppData.Category.msgBatch);
	  verifyTrue(check, "VP: Batch Process completed message displayed");
	  
	  managerCategoryPage.clickCategoryCheckbox(name2SaveAsCopy);
	  editCategoryPage = managerCategoryPage.clickEditCategory();
	  
	  check = editCategoryPage.isCategoryDataCorrect(name2SaveAsCopy, alias, name2, stsPublished, accessReg, langUk, categoryText);
	  verifyTrue(check, "VP: Category Artical is moved successfully");
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
  
  String name, name2, nameSaveAsNew, name2SaveAsCopy, alias, parent, stsPublished, stsUnpublished, accessReg, categoryText, langUk;
  String defaultAccessLevel, defaultLanguage, batchActionMove, batchActionCopy; 
  String helpPageTitle;
  boolean check;
  Category_manager_page managerCategoryPage;
  Category_add_edit_page editCategoryPage, addCategoryPage;
  Help_page helpPage;
  Admin_page adminPage;
  Login_page loginPage;
}
