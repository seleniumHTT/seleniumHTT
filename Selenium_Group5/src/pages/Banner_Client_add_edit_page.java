package pages;

import abs.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Banner_Client_add_edit_page extends AbstractPage{
	WebDriver driver;	

	public Banner_Client_add_edit_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}
	
	public void enterData(String clientName, String contactName, String contactEmail){
		
		if(clientName != null && clientName!="")
			txt_clientName.clear();
			txt_clientName.sendKeys(clientName);
			
		if(contactName!=null && contactName!="")
			txt_contactName.clear();
			txt_contactName.sendKeys(contactName);

		if(contactEmail!=null && contactEmail!="")
			txt_contactEmail.clear();
			txt_contactEmail.sendKeys(contactEmail);
			
	}
	
	public Banner_Client_manager_page clickSaveClose(){
		btn_saveClose.click();
		return new Banner_Client_manager_page(driver);
	}
	
	
	//Editor		
	@FindBy(xpath="//input[@id = 'jform_name']")
	WebElement txt_clientName;
	
	@FindBy(xpath="//input[@id='jform_contact']")
	WebElement txt_contactName;
	
	@FindBy(xpath="//input[@id='jform_email']")
	WebElement txt_contactEmail;
	
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	WebElement btn_saveClose;
	
	
}
