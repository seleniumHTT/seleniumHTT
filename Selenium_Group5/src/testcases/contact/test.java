package testcases.contact;

import org.testng.annotations.*;

import abstracts.AbstractTest;
import pages.*;
import common.*;

public class test extends AbstractTest{	
	
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
	  
	  
  }
  
 
  
  @AfterClass
  public void afterClass() {
	  //config.tearDown();
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
