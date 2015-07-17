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

public class TC_Article_002_Edit extends AbstractTest{
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
	  
	  //edited article data
	  titleEdit = title + " edited";
	  categoryEdit = "";
	  statusEdit = "";
	  accessEdit = "";
	  featureEdit ="";
	  articleTextEdit = articleText + " edited";
	  
	  msg = "Article successfully saved";
  }
  
  @Test(description= "Verify user can edit an article")
  public void TC2_EditArticle() {
	  //1. Login to joomla
	  Login_page loginPage = new Login_page(driver);
	  loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  //2. Select Content > Article Manager
	  adminPage = new Admin_page(driver);
	  articleManagerPage = adminPage.clickArticleManagerMenu();
	 
	  //3. Click new icon, go to add page
	  Article_add_edit_page addArticlePage = articleManagerPage.clickNewArticle();
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
	  
	  //5. Check on the recently added article's checkbox
	  articleManagerPage.clickArticleCheckbox(title);
	  
	  //6. Click on 'Edit' icon of the top right toolbar
	  editArticlePage = articleManagerPage.clickEditArticle();
	  
	  //7. Enter edit data
	  editArticlePage.enterData(titleEdit, categoryEdit, statusEdit, accessEdit, featureEdit, articleTextEdit);
	  
	  //8. Click save and close
	  articleManagerPage = editArticlePage.clickSaveClose();
	  
	  //VP3: "Article successfully saved" message is displayed
	  check = articleManagerPage.isMessageDisplay(msg);
	  verifyTrue(check, "VP3: Article successfully saved message is displayed");
	  
	  //Search article
	  articleManagerPage.searchArticle(titleEdit);
	  
	  //VP4: Created article is displayed on the articles table
	  check = articleManagerPage.isArticleExist(titleEdit);
	  verifyTrue(check, "VP4: Edited article is displayed");
  } 
  
  @AfterClass
  public void afterClass() {
	  //sele.close();
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
  
  String title, category, status, access, feature, articleText, msg;
  String titleEdit, categoryEdit, statusEdit, accessEdit, featureEdit, articleTextEdit;
  boolean check;
  Article_manager_page articleManagerPage;
  Article_add_edit_page editArticlePage, addArticlePage;
  Admin_page adminPage;
}
