package testcases.article;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class TM_Article_001 extends AbstractTest{	
	
  @BeforeClass
  public void beforeClass() {
	  config.setup();
	  TestData.Article.getDataTest();	  
	  
	  name = TestData.Article.getName();  
	  nameEdit = name + " edited";
	  category = TestData.Article.getCategory();
	  stsPublished = TestData.Article.getStsPublished();
	  articleText = name + " article text";
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify user can create an Article", priority=1)
  public void TC_JOOMLA_CONTACTS_001() {	 	  
	  
	  articleManagerPage = adminPage.clickArticleManagerMenu();
	 
	  addArticlePage = articleManagerPage.clickNewArticle();
	  
	  addArticlePage.enterData(name, category, stsPublished, access, feature, articleText);
	  articleManagerPage = addArticlePage.clickSaveClose();	  
	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgSave);
	  verifyTrue(check, "VP: Article successfully saved message is displayed");	  
	  
	  articleManagerPage.searchArticle(name);
	  	  
	  check = articleManagerPage.isArticleExist(name);
	  verifyTrue(check, "VP: Created Article is displayed");	  
  }
  
  @Test(description= "Verify user can search for articles using the filter text field", dependsOnMethods= "TC_JOOMLA_CONTACTS_001")
  public void TC_JOOMLA_CONTACTS_009() {	  	  
	  
	  articleManagerPage.searchArticle(name);
	  	  
	  check = articleManagerPage.isArticleExist(name);
	  verifyTrue(check, "VP: Created Article is displayed");	  
	  
  }
  
  @Test(description= "User can search for articles using the filter dropdown lists", dependsOnMethods= "TC_JOOMLA_CONTACTS_001")
  public void TC_JOOMLA_CONTACTS_010() {
	  	  
	  articleManagerPage.filterStatus(stsPublished);
	  articleManagerPage.filterCategory(category);
	  
	  check = articleManagerPage.isArticleExist(name);
	  verifyTrue(check, "VP: Created Article is displayed");	  
	  
	  articleManagerPage.filterStatus("- Select Status -");
	  articleManagerPage.filterCategory("Select Category");
  }
  
  @Test(description= "Verify user can edit a article", dependsOnMethods= "TC_JOOMLA_CONTACTS_001")
  public void TC_JOOMLA_CONTACTS_002() {
	  
	  articleManagerPage.clickArticleCheckbox(name);
	  editArticlePage = articleManagerPage.clickEditArticle();
	  	  
	  editArticlePage.enterData(name, category, stsPublished, access, feature, articleText);
	  articleManagerPage = editArticlePage.clickSaveClose();
	  	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgSave);
	  verifyTrue(check, "VP: Article successfully saved message is displayed");	  
	  
	  articleManagerPage.searchArticle(nameEdit);
	  	  
	  check = articleManagerPage.isArticleExist(nameEdit);
	  verifyTrue(check, "VP: Edited article is displayed");	  
	  
  }
  
  @Test(description= "Verify user can publish an unpublished article", dependsOnMethods= "TC_JOOMLA_CONTACTS_002")
  public void TC_JOOMLA_CONTACTS_003() {	  
	
	  articleManagerPage.clickArticleCheckbox(nameEdit);
	  articleManagerPage.clickChangeStatusToolbar("Unpublish");	  
	  	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgUnpublish);
	  verifyTrue(check, "VP: '1 article successfully unpublished' message is displayed");
  }
  
  @Test(description= "Verify user can publish an unpublished article", dependsOnMethods="TC_JOOMLA_CONTACTS_003")
  public void TC_JOOMLA_CONTACTS_004() {
	  	  
	  articleManagerPage.clickArticleCheckbox(nameEdit);
	  articleManagerPage.clickChangeStatusToolbar("Publish");	  
	  	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgPublish);
	  verifyTrue(check, "VP: '1 article successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can move a article to the archive", dependsOnMethods= "TC_JOOMLA_CONTACTS_004")
  public void TC_JOOMLA_CONTACTS_005() {
	  
	  articleManagerPage.clickArticleCheckbox(nameEdit);
	  articleManagerPage.clickArchiveArticle(); 
	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgArchive);
	  verifyTrue(check, "The '1 article successfully archived' message is displayed");
	  
	  articleManagerPage.filterStatus("Archived");
	  
	  articleManagerPage.searchArticle(nameEdit);
	  	  
	  check = articleManagerPage.isArticleExist(nameEdit);
	  verifyTrue(check, "VP: The deleted article is displayed on the table grid");
	  
	  articleManagerPage.filterStatus("- Select Status -");	  
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
  
  private String name, category, stsPublished, access, feature, articleText;
  private String nameEdit;
  private boolean check;
  private Article_manager_page articleManagerPage;
  private Article_add_edit_page editArticlePage, addArticlePage;
  private Admin_page adminPage;
}
