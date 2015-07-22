package testcases.article;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class TM_Article_002 extends AbstractTest{

	
  @BeforeClass
  public void beforeClass() {	  
	  config.setup();
	  TestData.Article.getDataTest();
	  
	  //new Article data
	  name = TestData.Article.getName(); 
	  name2 = name + " order";
	  name3 = name + " image";
	  nameEdit = name + " edited";
	  category = TestData.Article.getCategory();
	  stsPublished = TestData.Article.getStsPublished();
	  imageName = TestData.Article.getImageName();	    
	  articleText = name + " article text";
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify user can check in a article", priority=1)
  public void TC_JOOMLA_ARTICLE_006() {	
	  
	  articleManagerPage = adminPage.clickArticleManagerMenu();
	 
	  addArticlePage = articleManagerPage.clickNewArticle();
	  
	  addArticlePage.enterData(name2, category, stsPublished, access, feature, articleText);
	  addArticlePage.clickSave();	  
	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgSave);
	  verifyTrue(check, "VP: Article successfully saved message is displayed");	  
	  
	  config.tearDown();
	  config.setup();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
	  
	  articleManagerPage = adminPage.clickArticleManagerMenu();
	  articleManagerPage.searchArticle(name);
	  
	  articleManagerPage.checkIn(name2);
	  check = articleManagerPage.isArticleCheckedIn(name2);
	  verifyTrue(check, "VP: The lock icon next to the article is removed");
	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgCheckedIn);
	  verifyTrue(check, "VP: Article successfully saved message is displayed");  
  }
  
  @Test(description= "Verify user can change the status of article using the Status column", dependsOnMethods= "TC_JOOMLA_ARTICLE_006", priority=1)
  public void TC_JOOMLA_ARTICLE_014() {
	  
	  articleManagerPage.clickChangeStatus(name2);
	  
	  check = articleManagerPage.isArticlePublished(name2, "Unpublished");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Unpublished'");
	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgUnpublish);
	  verifyTrue(check, "VP: The '1 article successfully unpublished' message is displayed");
	  
	  articleManagerPage.clickChangeStatus(name2);	  
	  check = articleManagerPage.isArticlePublished(name2, "Published");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Published'");	  
	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgPublish);
	  verifyTrue(check, "VP: The '1 article successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can add image to article's information", dependsOnMethods= "TC_JOOMLA_ARTICLE_006")
  public void TC_JOOMLA_ARTICLE_013() {  
	  
	  Article_add_edit_page addArticlePage = articleManagerPage.clickNewArticle();
	  addArticlePage.enterData(name3, category, stsPublished, access, feature, articleText);
	  addArticlePage.insertImage(imageName);	  
	  
	  articleManagerPage = addArticlePage.clickSaveClose();
	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgSave);
	  verifyTrue(check, "VP: Article successfully saved message is displayed");
	  
	  articleManagerPage.searchArticle(name);
	  
	  check = articleManagerPage.isArticleExist(name3);
	  verifyTrue(check, "VP: Created article is displayed");   
  }  
  
  @Test(description= "Verify user can sort the article table by ID column", dependsOnMethods= "TC_JOOMLA_ARTICLE_013")
  public void TC_JOOMLA_ARTICLE_011() { 	  
	  	  
	  articleManagerPage.searchArticle(name);	  
	 
	  articleManagerPage.clickIdColumn();
	  check = articleManagerPage.isIdSortedCorrect("asc");
	  verifyTrue(check, "VP: The articles is sorted by ID in ascending order");
	  
	  articleManagerPage.clickIdColumn();
	  check = articleManagerPage.isIdSortedCorrect("dec");
	  verifyTrue(check, "VP: The articles is sorted by ID in descending order");
  }
  
  @Test(description= "Verify user can change the order of articles using the Ordering column", dependsOnMethods= "TC_JOOMLA_ARTICLE_006")
  public void TC_JOOMLA_ARTICLE_015() {	  
	  articleManagerPage.searchArticle(name);
	  	  
	  articleManagerPage.clickOrderingColumn();	  
	 
	  int row1 = articleManagerPage.getRowNumber(name3);
	  int row2 = articleManagerPage.getRowNumber(name2);	  
	  articleManagerPage.clickArrowOrdering(name3, "down");	  
	  
	  check = articleManagerPage.isArticleLocateAt(name3, row2);
	  verifyTrue(check, "VP: Verify the first weblink changes its position with the second weblink");
	  check = articleManagerPage.isArticleLocateAt(name2, row1);
	  verifyTrue(check, "VP: Verify the second weblink changes its position with the first weblink");
  } 
  
  @Test(description= "Verify user can move a article to trash section", dependsOnMethods= "TC_JOOMLA_ARTICLE_015")
  public void TC_JOOMLA_ARTICLE_007() {	  
	  
	  articleManagerPage.clickArticleCheckbox(name3);
	  articleManagerPage = articleManagerPage.clickTrashArticle();	  
	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgTrash);
	  verifyTrue(check, "The '1 article trashed' message is displayed");
	  
	  articleManagerPage.filterStatus("Trashed");
	  
	  articleManagerPage.searchArticle(name);
	  	  
	  check = articleManagerPage.isArticleExist(name3);
	  verifyTrue(check, "VP: The deleted article is displayed on the table grid");
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
  
  String name, alias, category, stsPublished, access, feature, articleText, imageName, name2, name3; 
  String nameEdit, aliasEdit, categoryEdit, statusEdit, accessEdit, featureEdit, Edit, articleTextEdit;
  boolean check;
  Article_manager_page articleManagerPage;
  Article_add_edit_page editArticlePage, addArticlePage;
  Admin_page adminPage;
  Login_page loginPage;
}
