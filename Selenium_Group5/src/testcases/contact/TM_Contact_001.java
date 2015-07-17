package testcases.contact;

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
import pages.Contact_add_edit_page;
import pages.Contact_manager_page;
import pages.Login_page;
import pages.Weblink_add_edit_page;
import utilities.Random;
import common.Selenium;
import common.config;

public class TM_Contact_001 extends AbstractTest{
	WebDriver driver;
	Selenium sele;
	
  @BeforeClass
  public void beforeClass() {
	  sele = new Selenium();
	  this.driver = sele.getDriver(config.urlLogin);
	  
	  //new Contact data
	  name = Random.getRandomName() + " name";
	  nameEdit = name + " edited";
	  name2 = name + " order";
	  name3 = name + " image";
	  category = "Sample Data-Contact";
	  stsUnpublished = "Unpublished";
	  stsPublished = "Published";
	  imageName = "powered_by.png";
	    
	  msgSave = "Contact successfully saved";
	  msgPublish = "1 contact successfully published";
	  msgUnpublish = "1 contact successfully unpublished";
	  
	  msgTrash = "1 contact successfully trashed";
	  msgArchive = "1 contact successfully archived";
  }
  
  @Test(description= "Verify user can create an Contact", priority=1)
  public void TC_JOOMLA_CONTACTS_001() {	  
	  Login_page loginPage = new Login_page(driver);
	  loginPage.login(config.usernameAdmin, config.passwordAdmin);	  
	  
	  adminPage = new Admin_page(driver);
	  contactManagerPage = adminPage.clickContactManagerMenu();
	 
	  Contact_add_edit_page addContactPage = contactManagerPage.clickNewContact();
	  
	  addContactPage.enterData(name, alias, category, stsPublished, access, feature, contactText);
	  contactManagerPage = addContactPage.clickSaveClose();	  
	  
	  check = contactManagerPage.isMessageDisplay(msgSave);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");	  
	  
	  contactManagerPage.searchContact(name);
	  	  
	  check = contactManagerPage.isContactExist(name);
	  verifyTrue(check, "VP: Created Contact is displayed");
	  
  }
  
  @Test(description= "Verify user can search for contacts using the filter text field", dependsOnMethods= "TC_JOOMLA_CONTACTS_001")
  public void TC_JOOMLA_CONTACTS_009() {	  	  
	  
	  contactManagerPage.searchContact(name);
	  	  
	  check = contactManagerPage.isContactExist(name);
	  verifyTrue(check, "VP: Created Contact is displayed");	  
	  
  }
  
  @Test(description= "User can search for contacts using the filter dropdown lists", dependsOnMethods= "TC_JOOMLA_CONTACTS_001")
  public void TC_JOOMLA_CONTACTS_010() {
	  	  
	  contactManagerPage.filterStatus(stsPublished);
	  contactManagerPage.filterCategory(category);
	  
	  check = contactManagerPage.isContactExist(name);
	  verifyTrue(check, "VP: Created Contact is displayed");	  
	  
  }
  
  @Test(description= "Verify user can edit a contact", dependsOnMethods= "TC_JOOMLA_CONTACTS_001")
  public void TC_JOOMLA_CONTACTS_002() {
	  
	  contactManagerPage.clickContactCheckbox(name);
	  editContactPage = contactManagerPage.clickEditContact();
	  	  
	  editContactPage.enterData(nameEdit, aliasEdit, categoryEdit, statusEdit, accessEdit, featureEdit, contactTextEdit);
	  contactManagerPage = editContactPage.clickSaveClose();
	  	  
	  check = contactManagerPage.isMessageDisplay(msgSave);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");	  
	  
	  contactManagerPage.searchContact(nameEdit);
	  	  
	  check = contactManagerPage.isContactExist(nameEdit);
	  verifyTrue(check, "VP: Edited contact is displayed");	  
	  
  }
  
  @Test(description= "Verify user can publish an unpublished contact", dependsOnMethods= "TC_JOOMLA_CONTACTS_002")
  public void TC_JOOMLA_CONTACTS_003() {	  
	
	  contactManagerPage.clickContactCheckbox(nameEdit);
	  contactManagerPage.clickChangeStatusToolbar("Unpublish");	  
	  	  
	  check = contactManagerPage.isMessageDisplay(msgUnpublish);
	  verifyTrue(check, "VP: '1 contact successfully unpublished' message is displayed");
  }
  
  @Test(description= "Verify user can publish an unpublished contact", dependsOnMethods="TC_JOOMLA_CONTACTS_003")
  public void TC_JOOMLA_CONTACTS_004() {
	  	  
	  contactManagerPage.clickContactCheckbox(nameEdit);
	  contactManagerPage.clickChangeStatusToolbar("Publish");	  
	  	  
	  check = contactManagerPage.isMessageDisplay(msgPublish);
	  verifyTrue(check, "VP: '1 contact successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can move a contact to the archive", dependsOnMethods= "TC_JOOMLA_CONTACTS_004")
  public void TC_JOOMLA_CONTACTS_005() {
	  
	  contactManagerPage.clickContactCheckbox(nameEdit);
	  contactManagerPage.clickArchiveContact(); 
	  
	  check = contactManagerPage.isMessageDisplay(msgArchive);
	  verifyTrue(check, "The '1 contact successfully archived' message is displayed");
	  
	  contactManagerPage.filterStatus("Archive");
	  
	  contactManagerPage.searchContact(nameEdit);
	  	  
	  check = contactManagerPage.isContactExist(nameEdit);
	  verifyTrue(check, "VP: The deleted contact is displayed on the table grid");
	  
	  contactManagerPage.filterStatus("- Select Status -");	  
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
  
  String name, alias, category, stsUnpublished, stsPublished, access, feature, contactText, imageName, name2, name3;
  String msgSave, msgPublish, msgUnpublish, msgTrash, msgArchive;
  String nameEdit, aliasEdit, categoryEdit, statusEdit, accessEdit, featureEdit, Edit, contactTextEdit;
  boolean check;
  Contact_manager_page contactManagerPage;
  Contact_add_edit_page editContactPage, addContactPage;
  Admin_page adminPage;
}
