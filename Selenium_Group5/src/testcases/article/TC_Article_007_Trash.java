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

public class TC_Article_007_Trash extends AbstractTest{
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
	  msgTrash = "1 article trashed";
  }
  
  @Test(description= "Verify user can move an article to trash section")
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
	  
	  //6. Click on 'Trash' icon of the top right toolbar
	  articleManagerPage = articleManagerPage.clickTrashArticle();
	  
	  //VP3: "1 article trashed" message is displayed
	  check = articleManagerPage.isMessageDisplay(msgTrash);
	  verifyTrue(check, "VP3: The '1 article trashed' message is displayed");
	  
	  //7. Select 'Trashed' item of 'Status' dropdown list
	  articleManagerPage.filterStatus("Trashed");
	  
	  //Search article
	  articleManagerPage.searchArticle(title);
	  
	  //VP4: Verify the deleted article is displayed on the table grid	  
	  check = articleManagerPage.isArticleExist(title);
	  verifyTrue(check, "VP4: The deleted article is displayed on the table grid");
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
  
  String title, category, status, access, feature, articleText, msg, msgTrash;
  String titleEdit, categoryEdit, statusEdit, accessEdit, featureEdit, articleTextEdit;
  boolean check;
  Article_manager_page articleManagerPage;
  Article_add_edit_page editArticlePage, addArticlePage;
  Admin_page adminPage;
}
