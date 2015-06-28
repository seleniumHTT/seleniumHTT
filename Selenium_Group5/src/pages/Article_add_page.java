package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abs.AbstractPage;

public class Article_add_page extends AbstractPage {
	WebDriver driver;
	
	public Article_add_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}
	
	
	public void enterData(String title, String category, String status, String access, String feature, String articleText) {
			
		//Switch editor to plain text mode
		btn_toggleEditor.click();
		
		//Enter data
		if(title !=null && title != "") {
			txt_title.sendKeys(title);
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
		
		if(feature != null && feature != "") {
			selectCombobox(cb_feature, feature);
		}
		
		if(articleText != null && articleText != "") {
			txt_articleText.sendKeys(articleText);
		}
				
	}
	
	public Article_Manager_page clickSaveClose() {
		btn_saveClose.click();
		return new Article_Manager_page(driver);
	}	
		
	
	//Editor
	@FindBy(xpath="//input[@id='jform_title']")
	WebElement txt_title;

	@FindBy(xpath="//select[@id='jform_catid']")
	WebElement cb_category;
	
	@FindBy(xpath="//select[@id='jform_state']")
	WebElement cb_status;
	
	@FindBy(xpath="//select[@id='jform_access']")
	WebElement cb_access;
	
	@FindBy(xpath="//select[@id='jform_featured']")
	WebElement cb_feature;
	
	@FindBy(xpath="//textarea[@id='jform_articletext']")
	WebElement txt_articleText;
	
	@FindBy(xpath="//a[text()='Toggle editor']")
	WebElement btn_toggleEditor;
	
	//Submit buttons
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	WebElement btn_saveClose;
	
	
	String _lbl_addNewArticle = "//h2[text()='Article Manager: Add New Article']";
	String _lbl_articleSaved = "";
}
