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
import pages.Contact_add_edit_page;
import pages.Contact_manager_page;
import pages.Login_page;
import pages.Weblink_add_edit_page;
import utilities.Random;
import common.Selenium;
import common.config;

public class TC_Contact_001_Create extends AbstractTest{
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
	  status = "Unpublished";
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
	  
	  addContactPage.enterData(name, alias, category, status, access, feature, contactText);
	  contactManagerPage = addContactPage.clickSaveClose();	  
	  
	  check = contactManagerPage.isMessageDisplay(msgSave);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");	  
	  
	  contactManagerPage.searchContact(name);
	  	  
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
	  
	  status = "Unpublished";
	  
	  Contact_add_edit_page addContactPage = contactManagerPage.clickNewContact();
	  addContactPage.enterData(name2, alias, category, status, access, feature, contactText);
	  contactManagerPage = addContactPage.clickSaveClose();
	  
	  contactManagerPage.searchContact(name2);
	  
	  contactManagerPage.clickContactCheckbox(name2);
	  contactManagerPage.clickChangeStatusToolbar("Publish");	  
	  	  
	  check = contactManagerPage.isMessageDisplay(msgPublish);
	  verifyTrue(check, "VP: '1 contact successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can change the status of weblinks using the Status column", dependsOnMethods= "TC_JOOMLA_CONTACTS_004")
  public void TC_JOOMLA_CONTACTS_014() {	  
	
	  contactManagerPage.searchContact(name2);
	  
	  contactManagerPage.clickChangeStatus(name2);
	  
	  check = contactManagerPage.isContactPublished(name2, "Unpublished");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Unpublished'");
	  
	  check = contactManagerPage.isMessageDisplay(msgUnpublish);
	  verifyTrue(check, "VP: The '1 contact successfully unpublished' message is displayed");
	  
	  contactManagerPage.clickChangeStatus(name2);	  
	  check = contactManagerPage.isContactPublished(name2, "Published");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Published'");	  
	  
	  check = contactManagerPage.isMessageDisplay(msgPublish);
	  verifyTrue(check, "VP: The '1 contact successfully published' message is displayed");
  } 
  
  
  @Test(description= "Verify user can change the order of weblinks using the Ordering column", dependsOnMethods= "TC_JOOMLA_CONTACTS_014")
  public void TC_JOOMLA_CONTACTS_015() {	  
	  contactManagerPage.searchContact(name);
	  	  
	  contactManagerPage.clickOrderingColumn();	  
	 
	  int row1 = contactManagerPage.getRowNumber(nameEdit);
	  int row2 = contactManagerPage.getRowNumber(name2);	  
	  contactManagerPage.clickArrowOrdering(nameEdit, "down");	  
	  
	  check = contactManagerPage.isContactLocateAt(nameEdit, row2);
	  verifyTrue(check, "VP: Verify the first weblink changes its position with the second weblink");
	  check = contactManagerPage.isContactLocateAt(name2, row1);
	  verifyTrue(check, "VP: Verify the second weblink changes its position with the first weblink");
  } 
  
  @Test(description= "Verify user can add image to contact's information", dependsOnMethods= "TC_JOOMLA_CONTACTS_015")
  public void TC_JOOMLA_CONTACTS_013() {  
	  
	  Contact_add_edit_page addContactPage = contactManagerPage.clickNewContact();
	  addContactPage.enterData(name3, alias, category, status, access, feature, contactText);	  
	  addContactPage.insertImage(imageName);	  
	  
	  contactManagerPage = addContactPage.clickSaveClose();
	  
	  check = contactManagerPage.isMessageDisplay(msgSave);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");
	  
	  contactManagerPage.searchContact(name);
	  
	  check = contactManagerPage.isContactExist(name);
	  verifyTrue(check, "VP: Created contact is displayed");   
  }  

  @Test(description= "Verify user can move a contact to trash section", dependsOnMethods= "TC_JOOMLA_CONTACTS_013")
  public void TC_JOOMLA_CONTACTS_007() {	  
	  
	  contactManagerPage.clickContactCheckbox(name3);
	  contactManagerPage = contactManagerPage.clickTrashContact();	  
	  
	  check = contactManagerPage.isMessageDisplay(msgTrash);
	  verifyTrue(check, "The '1 contact trashed' message is displayed");
	  
	  contactManagerPage.filterStatus("Trashed");
	  
	  contactManagerPage.searchContact(name);
	  	  
	  check = contactManagerPage.isContactExist(name3);
	  verifyTrue(check, "VP: The deleted contact is displayed on the table grid");
	  
	  contactManagerPage.filterStatus("- Select Status -");
  } 
  
  @Test(description= "Verify user can move a contact to the archive", dependsOnMethods= "TC_JOOMLA_CONTACTS_013")
  public void TC_JOOMLA_CONTACTS_005() {	  
	  
	  contactManagerPage.clickContactCheckbox(nameEdit);
	  contactManagerPage.clickArchiveContact(); 
	  
	  check = contactManagerPage.isMessageDisplay(msgArchive);
	  verifyTrue(check, "The '1 contact successfully archived' message is displayed");
	  
	  contactManagerPage.filterStatus("Archive");
	  
	  contactManagerPage.searchContact(name);
	  	  
	  check = contactManagerPage.isContactExist(nameEdit);
	  verifyTrue(check, "VP: The deleted contact is displayed on the table grid");
	  
	  contactManagerPage.filterStatus("- Select Status -");	  
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
  
  String name, alias, category, status, access, feature, contactText, imageName, name2, name3;
  String msgSave, msgPublish, msgUnpublish, msgTrash, msgArchive;
  String nameEdit, aliasEdit, categoryEdit, statusEdit, accessEdit, featureEdit, Edit, contactTextEdit;
  boolean check;
  Contact_manager_page contactManagerPage;
  Contact_add_edit_page editContactPage, addContactPage;
  Admin_page adminPage;
}
