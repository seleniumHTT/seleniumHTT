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
	  
	  //new Category data
	  name = TestData.Category.getName(); 
	  name2 = name + " order";
	  name3 = name + " image";
	  nameEdit = name + " edited";
	  category = TestData.Category.getCategory();
	  stsPublished = TestData.Category.getStsPublished();
	  imageName = TestData.Category.getImageName();	    
	  categoryText = name + " category text";
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify user can check in a category", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_006() {	
	  
	  categoryManagerPage = adminPage.clickCategoryManagerMenu();
	 
	  addCategoryPage = categoryManagerPage.clickNewCategory();
	  
	  addCategoryPage.enterData(name2, category, stsPublished, access, feature, categoryText);
	  addCategoryPage.clickSave();	  
	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");	  
	  
	  config.tearDown();
	  config.setup();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
	  
	  categoryManagerPage = adminPage.clickCategoryManagerMenu();
	  categoryManagerPage.searchCategory(name);
	  
	  categoryManagerPage.checkIn(name2);
	  check = categoryManagerPage.isCategoryCheckedIn(name2);
	  verifyTrue(check, "VP: The lock icon next to the category is removed");
	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgCheckedIn);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");  
  }
  
  @Test(description= "Verify user can change the status of category using the Status column", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_006", priority=1)
  public void TC_JOOMLA_CATEGORY_MANAGER_014() {
	  
	  categoryManagerPage.clickChangeStatus(name2);
	  
	  check = categoryManagerPage.isCategoryPublished(name2, "Unpublished");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Unpublished'");
	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgUnpublish);
	  verifyTrue(check, "VP: The '1 category successfully unpublished' message is displayed");
	  
	  categoryManagerPage.clickChangeStatus(name2);	  
	  check = categoryManagerPage.isCategoryPublished(name2, "Published");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Published'");	  
	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgPublish);
	  verifyTrue(check, "VP: The '1 category successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can add image to category's information", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_006")
  public void TC_JOOMLA_CATEGORY_MANAGER_013() {  
	  
	  Category_add_edit_page addCategoryPage = categoryManagerPage.clickNewCategory();
	  addCategoryPage.enterData(name3, category, stsPublished, access, feature, categoryText);
	  addCategoryPage.insertImage(imageName);	  
	  
	  categoryManagerPage = addCategoryPage.clickSaveClose();
	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgSave);
	  verifyTrue(check, "VP: Category successfully saved message is displayed");
	  
	  categoryManagerPage.searchCategory(name);
	  
	  check = categoryManagerPage.isCategoryExist(name3);
	  verifyTrue(check, "VP: Created category is displayed");   
  }  
  
  @Test(description= "Verify user can sort the category table by ID column", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_013")
  public void TC_JOOMLA_CATEGORY_MANAGER_011() { 	  
	  	  
	  categoryManagerPage.searchCategory(name);	  
	 
	  categoryManagerPage.clickIdColumn();
	  check = categoryManagerPage.isIdSortedCorrect("asc");
	  verifyTrue(check, "VP: The categorys is sorted by ID in ascending order");
	  
	  categoryManagerPage.clickIdColumn();
	  check = categoryManagerPage.isIdSortedCorrect("dec");
	  verifyTrue(check, "VP: The categorys is sorted by ID in descending order");
  }
  
  @Test(description= "Verify user can change the order of categorys using the Ordering column", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_006")
  public void TC_JOOMLA_CATEGORY_MANAGER_015() {	  
	  categoryManagerPage.searchCategory(name);
	  	  
	  categoryManagerPage.clickOrderingColumn();	  
	 
	  int row1 = categoryManagerPage.getRowNumber(name3);
	  int row2 = categoryManagerPage.getRowNumber(name2);	  
	  categoryManagerPage.clickArrowOrdering(name3, "down");	  
	  
	  check = categoryManagerPage.isCategoryLocateAt(name3, row2);
	  verifyTrue(check, "VP: Verify the first weblink changes its position with the second weblink");
	  check = categoryManagerPage.isCategoryLocateAt(name2, row1);
	  verifyTrue(check, "VP: Verify the second weblink changes its position with the first weblink");
  } 
  
  @Test(description= "Verify user can move a category to trash section", dependsOnMethods= "TC_JOOMLA_CATEGORY_MANAGER_015")
  public void TC_JOOMLA_CATEGORY_MANAGER_007() {	  
	  
	  categoryManagerPage.clickCategoryCheckbox(name3);
	  categoryManagerPage = categoryManagerPage.clickTrashCategory();	  
	  
	  check = categoryManagerPage.isMessageDisplay(AppData.Category.msgTrash);
	  verifyTrue(check, "The '1 category trashed' message is displayed");
	  
	  categoryManagerPage.filterStatus("Trashed");
	  
	  categoryManagerPage.searchCategory(name);
	  	  
	  check = categoryManagerPage.isCategoryExist(name3);
	  verifyTrue(check, "VP: The deleted category is displayed on the table grid");
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
  
  String name, alias, category, stsPublished, access, feature, categoryText, imageName, name2, name3; 
  String nameEdit, aliasEdit, categoryEdit, statusEdit, accessEdit, featureEdit, Edit, categoryTextEdit;
  boolean check;
  Category_manager_page categoryManagerPage;
  Category_add_edit_page editCategoryPage, addCategoryPage;
  Admin_page adminPage;
  Login_page loginPage;
}
