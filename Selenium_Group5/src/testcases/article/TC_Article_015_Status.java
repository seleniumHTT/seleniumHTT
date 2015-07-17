package testcases.article;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import abstracts.AbstractTest;
import pages.Admin_page;
import pages.Article_add_edit_page;
import pages.Article_manager_page;
import pages.Login_page;
import utilities.Random;
import common.Selenium;
import common.config;

public class TC_Article_015_Status extends AbstractTest{
	WebDriver driver;
	Selenium sele;
	
	
  @BeforeClass
  public void beforeClass() {
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  
	  //new article data
	  title = Random.getRandomName();
	  category = "";
	  status = "";
	  access = "";
	  feature ="";
	  articleText = title + " content"; 
	  
	  msg = "Article successfully saved";
	  msgPublished = "1 article published";
	  msgUnpublished = "1 article unpublished";
  }
  
  @Test(description= "Verify user can change the status of articles using the Status column")
  public void TC14_OrderArticle() {
	  //1. Login to joomla
	  Login_page loginPage = new Login_page(driver);
	  loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  //2. Select Content > Article Manager
	  adminPage = new Admin_page(driver);
	  articleManagerPage = adminPage.clickArticleManagerMenu();
	 
	  //3. Click new icon, go to add page
	  addArticlePage = articleManagerPage.clickNewArticle();
	  addArticlePage.enterData(title, category, status, access, feature, articleText);
	  
	  //4. Click Save n close button
	  articleManagerPage = addArticlePage.clickSaveClose();
	  
	  //VP1: "Article successfully saved" message is displayed
	  check = articleManagerPage.isMessageDisplay(msg);
	  verifyTrue(check, "VP1: Article successfully saved message is displayed");
	  
	  //Search article
	  articleManagerPage.searchArticle(title);
	  
	  //VP2: Created article is displayed on the articles table
	  check = articleManagerPage.isArticleExist(title);
	  verifyTrue(check, "VP2: Created article is displayed");
	  
	  //5. Change the status icon of the selected article in the Status column
	  articleManagerPage.clickChangeStatus(title);
	  
	  //VP3: Verify the article is unpublished successfully
	  //VP3a: The icon of the selected item is showed as 'Unpublished'.
	  check = articleManagerPage.isArticlePublished(title, "Unpublished");
	  verifyTrue(check, "VP3a: The icon of the selected item is showed as 'Unpublished'");
	  
	  //VP3b: The "1 article unpublished" message is displayed
	  check = articleManagerPage.isMessageDisplay(msgUnpublished);
	  verifyTrue(check, "VP3b: The '1 article unpublished' message is displayed");
	  
	  //6. Change the status icon of the selected article in the Status column
	  articleManagerPage.clickChangeStatus(title);
	  
	  //VP3: Verify the article is unpublished successfully
	  //VP3a: The icon of the selected item is showed as 'Unpublished'.
	  check = articleManagerPage.isArticlePublished(title, "Published");
	  verifyTrue(check, "VP4a: The icon of the selected item is showed as 'Published'");
	  
	  //VP3b: The "1 article unpublished" message is displayed
	  check = articleManagerPage.isMessageDisplay(msgPublished);
	  verifyTrue(check, "VP4b: The '1 article published' message is displayed");
  } 
  
  @AfterClass
  public void afterClass() {
	  sele.close();
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
  
  String title, category, status, access, feature, articleText, msg, msgPublished, msgUnpublished;
  String title2, category2, status2, access2, feature2, articleText2;
  boolean check;
  Article_manager_page articleManagerPage;
  Article_add_edit_page editArticlePage, addArticlePage;
  Admin_page adminPage;
}
