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
	  helpPageTitle = TestData.Article.getHelpPageTitle();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify user can create an Article", priority=1)
  public void TC_JOOMLA_ARTICLE_001() {	 	  
	 
	  addArticlePage = adminPage.clickAddNewArticle();
	  
	  addArticlePage.enterData(name, category, stsPublished, access, feature, articleText);
	  articleManagerPage = addArticlePage.clickSaveClose();	  
	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgSave);
	  verifyTrue(check, "VP: Article successfully saved message is displayed");	  
	  
	  articleManagerPage.searchArticle(name);
	  	  
	  check = articleManagerPage.isArticleExist(name);
	  verifyTrue(check, "VP: Created Article is displayed");	  
  }
  
  @Test(description= "User can access article's help section", dependsOnMethods= "TC_JOOMLA_ARTICLE_001")
  public void TC_JOOMLA_ARTICLE_008() {	  
		 
	  Help_page helpPage = articleManagerPage.clickHelpToolbar();
	  check = helpPage.isHelpWindowDisplays(helpPageTitle);
	  verifyTrue(check, "VP: The article's help window is displayed");	  
	  helpPage.closeBackToParentPage();
  }  
  
  @Test(description= "Verify user can search for articles using the filter text field", dependsOnMethods= "TC_JOOMLA_ARTICLE_001", priority=1)
  public void TC_JOOMLA_ARTICLE_009() {	  	  
	  
	  articleManagerPage.searchArticle(name);
	  	  
	  check = articleManagerPage.isArticleExist(name);
	  verifyTrue(check, "VP: Created Article is displayed");	  
	  
  }
  
  @Test(description= "Verify user can change the feature property of articles using the Featured column", dependsOnMethods= "TC_JOOMLA_ARTICLE_001", priority=1)
  public void TC_JOOMLA_ARTICLE_016() {	  	  
	  
	  articleManagerPage.searchArticle(name);
	  	  
	  articleManagerPage.clickChangeFeature(name);	  
	  check = articleManagerPage.isArticleFeatured(name, "Featured");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Featured'");	  
	  
	  articleManagerPage.clickChangeFeature(name);	  
	  check = articleManagerPage.isArticleFeatured(name, "Unfeatured");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Unfeatured'");
	  
  }
  
  @Test(description= "Verify user can create a new article with 'Public' Access Level property", dependsOnMethods= "TC_JOOMLA_ARTICLE_001", priority=1)
  public void TC_JOOMLA_ARTICLE_017() {	  	  
	  
	  articleManagerPage.searchArticle(name);
	  
	  check = articleManagerPage.isArticlePublic(name, "Public");
	  verifyTrue(check, "VP: The Access Level of the article is displayed as 'Public'");	  
	  
  }  
  
  @Test(description= "User can search for articles using the filter dropdown lists", dependsOnMethods= "TC_JOOMLA_ARTICLE_001", priority=2)
  public void TC_JOOMLA_ARTICLE_010() {
	  	  
	  articleManagerPage.filterStatus(stsPublished);
	  articleManagerPage.filterCategory(category);
	  
	  check = articleManagerPage.isArticleExist(name);
	  verifyTrue(check, "VP: Created Article is displayed");	  
	  
	  articleManagerPage.filterStatus("- Select Status -");
	  articleManagerPage.filterCategory("Select Category");
  }
  
  @Test(description= "Verify user can edit a article", dependsOnMethods= "TC_JOOMLA_ARTICLE_001", priority=2)
  public void TC_JOOMLA_ARTICLE_002() {
	  
	  articleManagerPage.clickArticleCheckbox(name);
	  editArticlePage = articleManagerPage.clickEditArticle();
	  	  
	  editArticlePage.enterData(nameEdit, category, stsPublished, access, feature, articleText);
	  articleManagerPage = editArticlePage.clickSaveClose();
	  	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgSave);
	  verifyTrue(check, "VP: Article successfully saved message is displayed");	  
	  
	  articleManagerPage.searchArticle(nameEdit);
	  	  
	  check = articleManagerPage.isArticleExist(nameEdit);
	  verifyTrue(check, "VP: Edited article is displayed");	  
	  
  }
  
  @Test(description= "Verify user can publish an unpublished article", dependsOnMethods= "TC_JOOMLA_ARTICLE_002")
  public void TC_JOOMLA_ARTICLE_003() {	  
	
	  articleManagerPage.clickArticleCheckbox(nameEdit);
	  articleManagerPage.clickChangeStatusToolbar("Unpublish");	  
	  	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgUnpublish);
	  verifyTrue(check, "VP: '1 article successfully unpublished' message is displayed");
  }
  
  @Test(description= "Verify user can publish an unpublished article", dependsOnMethods="TC_JOOMLA_ARTICLE_003")
  public void TC_JOOMLA_ARTICLE_004() {
	  	  
	  articleManagerPage.clickArticleCheckbox(nameEdit);
	  articleManagerPage.clickChangeStatusToolbar("Publish");	  
	  	  
	  check = articleManagerPage.isMessageDisplay(AppData.Article.msgPublish);
	  verifyTrue(check, "VP: '1 article successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can move a article to the archive", dependsOnMethods= "TC_JOOMLA_ARTICLE_004")
  public void TC_JOOMLA_ARTICLE_005() {
	  
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

  
  private String name, category, stsPublished, access, feature, articleText, helpPageTitle;
  private String nameEdit;
  private boolean check;
  private Article_manager_page articleManagerPage;
  private Article_add_edit_page editArticlePage, addArticlePage;
  private Admin_page adminPage;
}
