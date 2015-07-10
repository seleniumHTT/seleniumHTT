package testcases;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abs.AbstractTest;
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
	 // status = "";
	  //purchaseType = "";
	  //trackImpression = "";
	  //trackClick = "";
	  msg= "Client successfully saved";
  }
  
  @Test (description = "Verify that user can create a new client")
  public void TC_JOOMLA_BANNERS_CLIENTS_001(){
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
  
  	
  String clientName, contactName, contactEmail, randomNumber, msg;
  boolean check;
  Login_page logInPage;
  Admin_page adminPage;
  Banner_Client_add_edit_page addBannerClientPage;
  Banner_Client_manager_page managerBannerClientPage;
  
}
