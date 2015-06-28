package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import abs.AbstractTest;
import pages.Admin_page;
import pages.Article_manager_page;
import pages.Article_add_edit_page;
import pages.Login_page;
import utilities.Random;
import common.Selenium;
import common.config;

public class TC013_Article_Image extends AbstractTest{
	WebDriver driver;
	Selenium sele;
	
  @BeforeClass
  public void beforeClass() {
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  
	  //new article data
	  title = Random.getArticleName();
	  category = "";
	  status = "";
	  access = "";
	  feature ="";
	  articleText = title + " content";
	  imageName = "powered_by.png";
	  
	  msg = "Article successfully saved";
  }
  
  @Test(description= "Verify user can add image to article's content")
  public void TC3_MoveArticleToTrash() {
	  //1. Login to joomla
	  Login_page loginPage = new Login_page(driver);
	  loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  //2. Select Content > Article Manager
	  adminPage = new Admin_page(driver);
	  articleManagerPage = adminPage.clickArticleManagerMenu();
	 
	  //3. Click new icon, go to add page
	  Article_add_edit_page addArticlePage = articleManagerPage.clickNewArticle();
	  addArticlePage.enterData(title, category, status, access, feature, articleText);
	  
	  //4. Insert image
	  addArticlePage.insertImage(imageName);
	  
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
  
  String title, category, status, access, feature, articleText, msg, msgTrash, imageName;
  boolean check;
  Article_manager_page articleManagerPage;
  Article_add_edit_page editArticlePage, addArticlePage;
  Admin_page adminPage;
}
