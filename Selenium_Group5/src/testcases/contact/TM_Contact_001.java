package testcases.contact;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class TM_Contact_001 extends AbstractTest{	
	
  @BeforeClass
  public void beforeClass() {
	  config.setup();
	  TestData.Contact.getDataTest();
	  
	  //new Contact data
	  name = TestData.Contact.getName();  
	  nameEdit = name + " edited";
	  category = TestData.Contact.getCategory();
	  stsPublished = TestData.Contact.getStsPublished();
	  
	  Login_page loginPage = PageFactory.getLoginPage();
	  adminPage = loginPage.login(AppData.getUsername(), AppData.getPassword());
  }
  
  @Test(description= "Verify user can create an Contact", priority=1)
  public void TC_JOOMLA_CONTACTS_001() {	 	  
	  
	  contactManagerPage = adminPage.clickContactManagerMenu();
	 
	  addContactPage = contactManagerPage.clickNewContact();
	  
	  addContactPage.enterData(name, alias, category, stsPublished, access, feature, contactText);
	  contactManagerPage = addContactPage.clickSaveClose();	  
	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgSave);
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
	  
	  contactManagerPage.filterStatus("- Select Status -");
	  contactManagerPage.filterCategory("Select Category");
  }
  
  @Test(description= "Verify user can edit a contact", dependsOnMethods= "TC_JOOMLA_CONTACTS_001")
  public void TC_JOOMLA_CONTACTS_002() {
	  
	  contactManagerPage.clickContactCheckbox(name);
	  editContactPage = contactManagerPage.clickEditContact();
	  	  
	  editContactPage.enterData(nameEdit, aliasEdit, categoryEdit, statusEdit, accessEdit, featureEdit, contactTextEdit);
	  contactManagerPage = editContactPage.clickSaveClose();
	  	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgSave);
	  verifyTrue(check, "VP: Contact successfully saved message is displayed");	  
	  
	  contactManagerPage.searchContact(nameEdit);
	  	  
	  check = contactManagerPage.isContactExist(nameEdit);
	  verifyTrue(check, "VP: Edited contact is displayed");	  
	  
  }
  
  @Test(description= "Verify user can publish an unpublished contact", dependsOnMethods= "TC_JOOMLA_CONTACTS_002")
  public void TC_JOOMLA_CONTACTS_003() {	  
	
	  contactManagerPage.clickContactCheckbox(nameEdit);
	  contactManagerPage.clickChangeStatusToolbar("Unpublish");	  
	  	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgUnpublish);
	  verifyTrue(check, "VP: '1 contact successfully unpublished' message is displayed");
  }
  
  @Test(description= "Verify user can publish an unpublished contact", dependsOnMethods="TC_JOOMLA_CONTACTS_003")
  public void TC_JOOMLA_CONTACTS_004() {
	  	  
	  contactManagerPage.clickContactCheckbox(nameEdit);
	  contactManagerPage.clickChangeStatusToolbar("Publish");	  
	  	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgPublish);
	  verifyTrue(check, "VP: '1 contact successfully published' message is displayed");
  }
  
  @Test(description= "Verify user can move a contact to the archive", dependsOnMethods= "TC_JOOMLA_CONTACTS_004")
  public void TC_JOOMLA_CONTACTS_005() {
	  
	  contactManagerPage.clickContactCheckbox(nameEdit);
	  contactManagerPage.clickArchiveContact(); 
	  
	  check = contactManagerPage.isMessageDisplay(AppData.Contact.msgArchive);
	  verifyTrue(check, "The '1 contact successfully archived' message is displayed");
	  
	  contactManagerPage.filterStatus("Archived");
	  
	  contactManagerPage.searchContact(nameEdit);
	  	  
	  check = contactManagerPage.isContactExist(nameEdit);
	  verifyTrue(check, "VP: The deleted contact is displayed on the table grid");
	  
	  contactManagerPage.filterStatus("- Select Status -");	  
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
  
  private String name, alias, category, stsPublished, access, feature, contactText;
  private String nameEdit, aliasEdit, categoryEdit, statusEdit, accessEdit, featureEdit, contactTextEdit;
  private boolean check;
  private Contact_manager_page contactManagerPage;
  private Contact_add_edit_page editContactPage, addContactPage;
  private Admin_page adminPage;
}
