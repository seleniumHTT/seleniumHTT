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

public class TM_Contact_002 extends AbstractTest{
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
	  category = "";
	  status = "Published";
	  imageName = "powered_by.png";
	    
	  msgSave = "Contact successfully saved";
	  msgCheckedIn = "1 contact successfully checked in";
	  msgPublish = "1 contact successfully published";
	  msgUnpublish = "1 contact successfully unpublished";
	  
	  msgTrash = "1 contact successfully trashed";
	  msgArchive = "1 contact successfully archived";
  }
  
  @Test(description= "Verify user can check in a contact", priority=1)
  public void TC_JOOMLA_CONTACTS_006() {	  
	  loginPage = new Login_page(driver);
	  adminPage = loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  contactManagerPage = adminPage.clickContactManagerMenu();
	 
	  addContactPage = contactManagerPage.clickNewContact();
	  
	  addContactPage.enterData(name, alias, category, status, access, feature, contactText);
	  addContactPage.clickSave();	  
	  
	  check = contactManagerPage.isMessageDisplay(msgSave);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");	  
	  
	  sele.close();
	  
	  driver = sele.getDriver(config.urlLogin);
	  loginPage = new Login_page(driver);
	  adminPage = loginPage.login(config.usernameAdmin, config.passwordAdmin);
	  
	  contactManagerPage = adminPage.clickContactManagerMenu();
	  contactManagerPage.searchContact(name);
	  
	  contactManagerPage.checkIn(name);
	  check = contactManagerPage.isContactCheckedIn(name);
	  verifyTrue(check, "VP: The lock icon next to the contact is removed");
	  
	  check = contactManagerPage.isMessageDisplay(msgCheckedIn);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");  
  }
  
  @Test(description= "Verify user can change the status of contact using the Status column", dependsOnMethods= "TC_JOOMLA_CONTACTS_006")
  public void TC_JOOMLA_CONTACTS_014() {
	  
	  contactManagerPage.clickChangeStatus(name);
	  
	  check = contactManagerPage.isContactPublished(name, "Unpublished");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Unpublished'");
	  
	  check = contactManagerPage.isMessageDisplay(msgUnpublish);
	  verifyTrue(check, "VP: The '1 contact successfully unpublished' message is displayed");
	  
	  contactManagerPage.clickChangeStatus(name);	  
	  check = contactManagerPage.isContactPublished(name, "Published");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Published'");	  
	  
	  check = contactManagerPage.isMessageDisplay(msgPublish);
	  verifyTrue(check, "VP: The '1 contact successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can add image to contact's information", dependsOnMethods= "TC_JOOMLA_CONTACTS_006")
  public void TC_JOOMLA_CONTACTS_013() {  
	  
	  Contact_add_edit_page addContactPage = contactManagerPage.clickNewContact();
	  addContactPage.enterData(name2, alias, category, status, access, feature, contactText);	  
	  addContactPage.insertImage(imageName);	  
	  
	  contactManagerPage = addContactPage.clickSaveClose();
	  
	  check = contactManagerPage.isMessageDisplay(msgSave);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");
	  
	  contactManagerPage.searchContact(name);
	  
	  check = contactManagerPage.isContactExist(name);
	  verifyTrue(check, "VP: Created contact is displayed");   
  }  
  
  @Test(description= "Verify user can sort the contact table by ID column", dependsOnMethods= "TC_JOOMLA_CONTACTS_013")
  public void TC_JOOMLA_CONTACTS_011() { 	  
	  	  
	  contactManagerPage.searchContact(name);	  
	 
	  contactManagerPage.clickIdColumn();
	  check = contactManagerPage.isIdSortedCorrect("asc");
	  verifyTrue(check, "VP: The contacts is sorted by ID in ascending order");
	  
	  contactManagerPage.clickIdColumn();
	  check = contactManagerPage.isIdSortedCorrect("dec");
	  verifyTrue(check, "VP: The contacts is sorted by ID in descending order");
  }
  
  @Test(description= "Verify user can change the order of contacts using the Ordering column", dependsOnMethods= "TC_JOOMLA_CONTACTS_006")
  public void TC_JOOMLA_CONTACTS_015() {	  
	  contactManagerPage.searchContact(name);
	  	  
	  contactManagerPage.clickOrderingColumn();	  
	 
	  int row1 = contactManagerPage.getRowNumber(name);
	  int row2 = contactManagerPage.getRowNumber(name2);	  
	  contactManagerPage.clickArrowOrdering(name, "down");	  
	  
	  check = contactManagerPage.isContactLocateAt(name, row2);
	  verifyTrue(check, "VP: Verify the first weblink changes its position with the second weblink");
	  check = contactManagerPage.isContactLocateAt(name2, row1);
	  verifyTrue(check, "VP: Verify the second weblink changes its position with the first weblink");
  } 
  
  @Test(description= "Verify user can move a contact to trash section", dependsOnMethods= "TC_JOOMLA_CONTACTS_015")
  public void TC_JOOMLA_CONTACTS_007() {	  
	  
	  contactManagerPage.clickContactCheckbox(name2);
	  contactManagerPage = contactManagerPage.clickTrashContact();	  
	  
	  check = contactManagerPage.isMessageDisplay(msgTrash);
	  verifyTrue(check, "The '1 contact trashed' message is displayed");
	  
	  contactManagerPage.filterStatus("Trashed");
	  
	  contactManagerPage.searchContact(name);
	  	  
	  check = contactManagerPage.isContactExist(name2);
	  verifyTrue(check, "VP: The deleted contact is displayed on the table grid");
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
  
  String name, alias, category, status, access, feature, contactText, imageName, name2, name3;
  String msgSave, msgPublish, msgUnpublish, msgTrash, msgArchive, msgCheckedIn;
  String nameEdit, aliasEdit, categoryEdit, statusEdit, accessEdit, featureEdit, Edit, contactTextEdit;
  boolean check;
  Contact_manager_page contactManagerPage;
  Contact_add_edit_page editContactPage, addContactPage;
  Admin_page adminPage;
  Login_page loginPage;
}
