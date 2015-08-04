package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.config;

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
			selectComboboxByXpath(_categoryValue, category);
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
		//sleep to wait dialog disappear
		sleep(config.getShortTime());
	}
	public Contact_manager_page clickSaveClose() {
		btn_saveClose.click();
		waitForPageLoaded(driver);
		return new Contact_manager_page(driver);
	}	
		
	public void clickSave() {
		btn_save.click();
		waitForPageLoaded(driver);
	}
	
	public void filterCategoryByXpath(String category) {
		getWebElement(String.format(_categoryValue, category)).click();;
	}
	//Editor
	@FindBy(xpath="//input[@id='jform_name']")
	private WebElement txt_name;

	@FindBy(xpath="//select[@id='jform_catid']")
	private WebElement cb_category;
	
	@FindBy(xpath="//select[@id='jform_published']")
	private WebElement cb_status;
	
	@FindBy(xpath="//select[@id='jform_featured']")
	private WebElement cb_feature;
	
	@FindBy(xpath="//select[@id='jform_access']")
	private WebElement cb_access;
	
	@FindBy(xpath="//input[@id='jform_alias']")
	private WebElement txt_alias;
	
	@FindBy(xpath="//textarea[@id='jform_jform_misc']")
	private WebElement txt_ContactText;
	
	@FindBy(xpath="//a[text()='Toggle editor']")
	private WebElement btn_toggleEditor;
	
	@FindBy(xpath="//a[text()='Image']")
	private WebElement btn_image;
	
	//Submit buttons
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	private WebElement btn_saveClose;

	@FindBy(xpath="//li[@id='toolbar-apply']/a")
	private WebElement btn_save;
	
	//iframe
	@FindBy(xpath="//div[@id='sbox-content']/iframe")
	private WebElement iframe_imageFrame;
	
	@FindBy(xpath="//iframe[@id='imageframe']")
	private WebElement iframe_selectImageFrame;
	
	@FindBy(xpath="//button[text()='Insert']")
	private WebElement btn_insertImageIframe;
	
	
	private String _lbl_addNewContact = "//h2[text()='Contact Manager: Add New Contact']";
	private String _categoryValue = "//select[@id='jform_catid']/option[contains(text(), '%s')]";
	
}
