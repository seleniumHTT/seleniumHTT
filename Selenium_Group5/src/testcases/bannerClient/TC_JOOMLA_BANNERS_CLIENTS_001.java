package testcases.bannerClient;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abstracts.AbstractTest;
import common.Selenium;
import common.config;
import pages.Admin_page;
import pages.Banner_Client_add_edit_page;
import pages.Banner_Client_manager_page;
import pages.Login_page;
import utilities.Random;

public class TC_JOOMLA_BANNERS_CLIENTS_001 extends AbstractTest{
  WebDriver driver;
  Selenium sele;
  
  @BeforeClass
  public void beforeClass(){
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  randomNumber = Random.getRandomName();
	  clientName = "Client Test " + randomNumber;
	  contactName = "Mr John";
	  contactEmail = "John@gmail.com";
	  editClientName = "Client Test Edited" + randomNumber;
	  editContactName = "Mr Bean";
	  editContactEmail = "Bean@gmail.com";
	  msg= "Client successfully saved";
	  msg_published = "1 client successfully published";
	  msg_unpublished = "1 client successfully unpublished";
	  status_publish = "Published";
	  status_unpublish = "Unpublished";
  }
  
  @Test(description = "Verify that user can create a new client")
  public void TC_001(){
	logInPage = new Login_page(driver);
	logInPage.login(config.usernameAdmin, config.passwordAdmin);
	
	adminPage = new Admin_page(driver);
	managerBannerClientPage = adminPage.clickBannerClientManagerMenu();
	
	addBannerClientPage = managerBannerClientPage.clickNewBannerClient();
	addBannerClientPage.enterData(clientName, contactName, contactEmail);
	
	managerBannerClientPage = addBannerClientPage.clickSaveClose();
	
	check = managerBannerClientPage.isMessageDisplay(msg);
	verifyTrue(check, "VP1: Banner Client successfully saved message is displayed");
	
	managerBannerClientPage.searchBannerClient(clientName);
	check = managerBannerClientPage.isBannerClientExist(clientName);
	verifyTrue(check, "VP2: The created Banner Client is displayed");
		
  }
  
  @Test(description ="Verify that user can edit a client")
  public void TC_002(){
	  
	managerBannerClientPage.clickClientCheckbox(clientName);
	addBannerClientPage = managerBannerClientPage.clickEditBannerClient();
	
	addBannerClientPage.enterData(editClientName, editContactName, editContactEmail);
	
	managerBannerClientPage = addBannerClientPage.clickSaveClose();
	
	check = managerBannerClientPage.isMessageDisplay(msg);
	verifyTrue(check, "VP1: Banner Client successfully saved message is displayed");
	
	managerBannerClientPage.searchBannerClient(editClientName);
	check = managerBannerClientPage.isBannerClientExist(editClientName);
	verifyTrue(check, "VP2: The created Banner Client is displayed");
  }  
  
  @Test(description ="Verify that user can publish a client")
  public void TC_003(){
	  
	managerBannerClientPage.clickClientCheckbox(editClientName);
	managerBannerClientPage.clickPublishBannerClient();
	
	check = managerBannerClientPage.isMessageDisplay(msg_published);
	verifyTrue(check, "VP1: Banner Client successfully punlished message is displayed");
	
	managerBannerClientPage.filterStatus(status_publish);
	
	managerBannerClientPage.searchBannerClient(editClientName);
	check = managerBannerClientPage.isBannerClientExist(editClientName);
	verifyTrue(check, "VP2: The Banner Client is published successfully");
  }
  
  @Test(description ="Verify that user can unpublish a client")
  public void TC_004(){
	  
	managerBannerClientPage.clickClientCheckbox(editClientName);
	managerBannerClientPage.clickUnpublishBannerClient();
	
	check = managerBannerClientPage.isMessageDisplay(msg_unpublished);
	verifyTrue(check, "VP1: Banner Client successfully unpunlished message is displayed");
	
	managerBannerClientPage.filterStatus(status_unpublish);
	
	managerBannerClientPage.searchBannerClient(editClientName);
	check = managerBannerClientPage.isBannerClientExist(editClientName);
	verifyTrue(check, "VP2: The Banner Client is unpublished successfully");
  }
  
  
  @AfterClass
  public void afterclass(){

  }
  
  
  String clientName, contactName, contactEmail, randomNumber, msg, editClientName, editContactName, editContactEmail, msg_published, msg_unpublished, status_publish, status_unpublish;
  boolean check;
  Login_page logInPage;
  Admin_page adminPage;
  Banner_Client_add_edit_page addBannerClientPage;
  Banner_Client_manager_page managerBannerClientPage;
  
}
