package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstracts.AbstractPage;

public class Contact_add_edit_page extends AbstractPage {
	WebDriver driver;
	
	public Contact_add_edit_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}
	
	
	public void enterData(String name, String alias, String category, String status, String access, String feature, String contactText) {
		
		//Enter data
		if(name !=null && name != "") {			
			txt_name.clear();
			txt_name.sendKeys(name);
		}
		
		if(alias != null && alias != "") {
			txt_alias.clear();
			txt_alias.sendKeys(alias);
		}
		
		if(category !=null && category != "") {
			selectCombobox(cb_category, category);
		}
		
		if(status != null && status != "") {
			selectCombobox(cb_status, status);
		}
		
		if(access != null && access != "") {
			selectCombobox(cb_access, access);
		}				
		
		if(contactText != null && contactText != "") {
			//Switch editor to plain text mode
			btn_toggleEditor.click();
			txt_ContactText.clear();
			txt_ContactText.sendKeys(contactText);
			
			btn_toggleEditor.click();
		}
				
	}
	
	public void insertImage(String imageName) {
		btn_image.click();
		
		driver.switchTo().frame(iframe_imageFrame);
		driver.switchTo().frame(iframe_selectImageFrame);
		
		getWebElement("//a[@title ='"+ imageName +"']").click();
		
		driver.switchTo().parentFrame();		
		btn_insertImageIframe.click();
		
		driver.switchTo().parentFrame();		
	}
	public Contact_manager_page clickSaveClose() {
		btn_saveClose.click();
		return new Contact_manager_page(driver);
	}	
		
	public void clickSave() {
		btn_save.click();
	}
	
	//Editor
	@FindBy(xpath="//input[@id='jform_name']")
	WebElement txt_name;

	@FindBy(xpath="//select[@id='jform_catid']")
	WebElement cb_category;
	
	@FindBy(xpath="//select[@id='jform_published']")
	WebElement cb_status;
	
	@FindBy(xpath="//select[@id='jform_featured']")
	WebElement cb_feature;
	
	@FindBy(xpath="//select[@id='jform_access']")
	WebElement cb_access;
	
	@FindBy(xpath="//input[@id='jform_alias']")
	WebElement txt_alias;
	
	@FindBy(xpath="//textarea[@id='jform_jform_misc']")
	WebElement txt_ContactText;
	
	@FindBy(xpath="//a[text()='Toggle editor']")
	WebElement btn_toggleEditor;
	
	@FindBy(xpath="//a[text()='Image']")
	WebElement btn_image;
	
	//Submit buttons
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	WebElement btn_saveClose;

	@FindBy(xpath="//li[@id='toolbar-apply']/a")
	WebElement btn_save;
	
	//iframe
	@FindBy(xpath="//div[@id='sbox-content']/iframe")
	WebElement iframe_imageFrame;
	
	@FindBy(xpath="//iframe[@id='imageframe']")
	WebElement iframe_selectImageFrame;
	
	@FindBy(xpath="//button[text()='Insert']")
	WebElement btn_insertImageIframe;
	
	
	String _lbl_addNewContact = "//h2[text()='Contact Manager: Add New Contact']";
	String _lbl_ContactSaved = "";

	
}
