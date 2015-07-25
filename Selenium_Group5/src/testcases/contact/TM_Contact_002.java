package testcases.contact;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class TM_Contact_002 extends AbstractTest{

	
  @BeforeClass
  public void beforeClass() {
	  config.setup();
	  TestData.Contact.getDataTest();
	  
	  //new Contact data
	  name = TestData.Contact.getName(); 
	  name2 = name + " order";
	  name3 = name + " image";
	  nameEdit = name + " edited";
	  category = TestData.Contact.getCategory();
	  stsPublished = TestData.Contact.getStsPublished();
	  imageName = TestData.Contact.getImageName();	    
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify user can check in a contact", priority=1)
  public void TC_JOOMLA_CONTACTS_006() {	
	  
	  contactManagerPage = adminPage.clickContactManagerMenu();
	 
	  addContactPage = contactManagerPage.clickNewContact();
	  
	  addContactPage.enterData(name2, alias, category, stsPublished, access, feature, contactText);
	  addContactPage.clickSave();	  
	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgSave);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");	  
	  
	  config.tearDown();	  
	  config.getBrowser();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
	  
	  contactManagerPage = adminPage.clickContactManagerMenu();
	  contactManagerPage.searchContact(name);
	  
	  contactManagerPage.checkIn(name2);
	  check = contactManagerPage.isContactCheckedIn(name2);
	  verifyTrue(check, "VP: The lock icon next to the contact is removed");
	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgCheckedIn);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");  
  }
  
  @Test(description= "Verify user can change the status of contact using the Status column", dependsOnMethods= "TC_JOOMLA_CONTACTS_006")
  public void TC_JOOMLA_CONTACTS_014() {
	  
	  contactManagerPage.clickChangeStatus(name2);
	  
	  check = contactManagerPage.isContactPublished(name2, "Unpublished");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Unpublished'");
	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgUnpublish);
	  verifyTrue(check, "VP: The '1 contact successfully unpublished' message is displayed");
	  
	  contactManagerPage.clickChangeStatus(name2);	  
	  check = contactManagerPage.isContactPublished(name2, "Published");
	  verifyTrue(check, "VP: The icon of the selected item is showed as 'Published'");	  
	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgPublish);
	  verifyTrue(check, "VP: The '1 contact successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can add image to contact's information", dependsOnMethods= "TC_JOOMLA_CONTACTS_006")
  public void TC_JOOMLA_CONTACTS_013() {  
	  
	  Contact_add_edit_page addContactPage = contactManagerPage.clickNewContact();
	  addContactPage.enterData(name3, alias, category, stsPublished, access, feature, contactText);	  
	  addContactPage.insertImage(imageName);	  
	  
	  contactManagerPage = addContactPage.clickSaveClose();
	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgSave);
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
	 
	  int row1 = contactManagerPage.getRowNumber(name3);
	  int row2 = contactManagerPage.getRowNumber(name2);	  
	  contactManagerPage.clickArrowOrdering(name, "down");	  
	  
	  check = contactManagerPage.isContactLocateAt(name3, row2);
	  verifyTrue(check, "VP: Verify the first weblink changes its position with the second weblink");
	  check = contactManagerPage.isContactLocateAt(name2, row1);
	  verifyTrue(check, "VP: Verify the second weblink changes its position with the first weblink");
  } 
  
  @Test(description= "Verify user can move a contact to trash section", dependsOnMethods= "TC_JOOMLA_CONTACTS_015")
  public void TC_JOOMLA_CONTACTS_007() {	  
	  
	  contactManagerPage.clickContactCheckbox(name3);
	  contactManagerPage = contactManagerPage.clickTrashContact();	  
	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgTrash);
	  verifyTrue(check, "The '1 contact trashed' message is displayed");
	  
	  contactManagerPage.filterStatus("Trashed");
	  
	  contactManagerPage.searchContact(name);
	  	  
	  check = contactManagerPage.isContactExist(name3);
	  verifyTrue(check, "VP: The deleted contact is displayed on the table grid");
  } 
  
  @AfterClass
  public void afterClass() {
	  config.tearDown();
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
  
  String name, alias, category, stsPublished, access, feature, contactText, imageName, name2, name3; 
  String nameEdit, aliasEdit, categoryEdit, statusEdit, accessEdit, featureEdit, Edit, contactTextEdit;
  boolean check;
  Contact_manager_page contactManagerPage;
  Contact_add_edit_page editContactPage, addContactPage;
  Admin_page adminPage;
  Login_page loginPage;
}
