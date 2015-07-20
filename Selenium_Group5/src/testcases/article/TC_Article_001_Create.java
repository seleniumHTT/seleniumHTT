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

public class TC_Article_001_Create extends AbstractTest{
	WebDriver driver;
	Selenium sele;
	
  @BeforeClass
  public void beforeClass() {
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  //Test data
	  title = Random.getCurrentTime();
	  category = "";
	  status = "";
	  access = "";
	  feature ="";
	  articleText = title + " content";
	  msg = "Article successfully saved";
  }
  
  @Test(description= "Verify user can create new article with valid information")
  public void TC1_CreateArticle() {
	  //1. Login to joomla
	  Login_page loginPage = new Login_page(driver);
	  loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  //2. Go to add new article page
	  Admin_page adminPage = new Admin_page(driver);
	  Article_add_edit_page addArticlePage = adminPage.clickAddNewArticle();
	 
	  //3. Enter data	  
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
  boolean check;
  Article_manager_page articleManagerPage;
}
