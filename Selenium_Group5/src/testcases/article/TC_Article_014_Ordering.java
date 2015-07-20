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

public class TC_Article_014_Ordering extends AbstractTest{
	WebDriver driver;
	Selenium sele;
	
	
  @BeforeClass
  public void beforeClass() {
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  
	  //new article data
	  random = Random.getCurrentTime();
	  title =  random + " ordering1";
	  title2 = random + " ordering2"; 
	  category = "";
	  status = "";
	  access = "";
	  feature ="";
	  articleText = title + " content";  	  
	  
	  msg = "Article successfully saved";
  }
  
  @Test(description= "Verify user can change the order of articles using the Ordering column")
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
	  articleManagerPage.searchArticle(random);
	  
	  //VP2: Created article is displayed on the articles table
	  check = articleManagerPage.isArticleExist(title);
	  verifyTrue(check, "VP2: Created article is displayed");
	  
	  //5. Select Content > Article Manager	  
	  articleManagerPage.clickArticleManagerMenu();
	  
	  //6. Click new icon, go to add page
	  addArticlePage = articleManagerPage.clickNewArticle();
	  
	  //7. Enter required data
	  addArticlePage.enterData(title2, category, status, access, feature, articleText);
	  
	  //8. Click save and close
	  articleManagerPage = addArticlePage.clickSaveClose();
	  
	  //VP3: "Article successfully saved" message is displayed
	  check = articleManagerPage.isMessageDisplay(msg);
	  verifyTrue(check, "VP3: Article successfully saved message is displayed");
	  
	  //Search article
	  articleManagerPage.searchArticle(random);
	  
	  //VP4: Created article is displayed on the articles table
	  check = articleManagerPage.isArticleExist(title2);
	  verifyTrue(check, "VP4: Created article is displayed on the articles table");
	  
	  //9. Click on the Header link of Ordering column
	  articleManagerPage.clickOrderingColumn();
	  
	  //10. Check on the second created article's checkbox
	  //11. Click on down arrow in Ordering column of the selected article
	  int rowTitle1 = articleManagerPage.getRowNumber(title);
	  int rowTitle2 = articleManagerPage.getRowNumber(title2);
	  
	  articleManagerPage.clickArrowOrdering(title2, "down");
	  
	  //VP5: Verify the first article changes its position with the second article 
	  check = articleManagerPage.isArticleLocateAt(title2, rowTitle1);
	  verifyTrue(check, "VP5a: Verify the first article changes its position with the second article");
	  check = articleManagerPage.isArticleLocateAt(title, rowTitle2);
	  verifyTrue(check, "VP5b: Verify the second article changes its position with the first article");
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
  
  String title, category, status, access, feature, articleText, msg, random;
  String title2, category2, status2, access2, feature2, articleText2;
  boolean check;
  Article_manager_page articleManagerPage;
  Article_add_edit_page editArticlePage, addArticlePage;
  Admin_page adminPage;
}
