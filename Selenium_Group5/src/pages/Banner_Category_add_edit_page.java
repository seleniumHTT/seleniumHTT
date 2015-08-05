package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstracts.AbstractPage;

public class Banner_Category_add_edit_page extends AbstractPage  {
	WebDriver driver;
	
	public Banner_Category_add_edit_page(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	
	//Form actions
	public void enterData(String title, String alias, String parent, String status, String access, String language, String categoryText) {
		
		//Enter data
		if(title !=null && title != "") {			
			txt_title.clear();
			txt_title.sendKeys(title);
		}
		
		if(alias !=null && alias != "") {			
			txt_alias.clear();
			txt_alias.sendKeys(alias);
		}
		
		if(parent !=null && parent != "") {
			selectCombobox(cb_parent, parent);
		}
		
		if(status != null && status != "") {
			selectCombobox(cb_status, status);
		}
		
		if(access != null && access != "") {
			selectCombobox(cb_access, access);
		}			
		
		if(language != null && language != "") {
			selectCombobox(cb_language, language);
		}
		
		if(categoryText != null && categoryText != "") {
			//Switch editor to plain text mode
			btn_toggleEditor.click();
			txt_categoryText.clear();
			txt_categoryText.sendKeys(categoryText);
			
			btn_toggleEditor.click();
		}		
		
	}
	
	public Banner_Category_manager_page clickSaveClose(){
		btn_SaveClose.click();
		waitForPageLoaded(driver);
		return new Banner_Category_manager_page(driver);
	}
	
	
	
	//Editor
	@FindBy(xpath="//input[@id='jform_title']")
	private WebElement txt_title;
	
	@FindBy(xpath="//input[@id='jform_alias']")
	private WebElement txt_alias;
	
	@FindBy(xpath="//textarea[@id='jform_description']")
	private WebElement txt_categoryText;
	
	@FindBy(xpath="//select[@id='jform_parent_id']")
	private WebElement cb_parent;
		
	@FindBy(xpath="//select[@id='jform_access']")
	private WebElement cb_access;
	
	@FindBy(xpath="//select[@id='jform_language']")
	private WebElement cb_language;
	
	@FindBy(xpath="//select[@id='jform_published']")
	private WebElement cb_status;
	
	@FindBy(xpath="//a[text()='Toggle editor']")
	private WebElement btn_toggleEditor;
	
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	private WebElement btn_SaveClose;
	
}
