package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstracts.AbstractPage;
import common.PageFactory;
import common.config;

public class Category_add_edit_page extends AbstractPage {
	WebDriver driver;
	
	public Category_add_edit_page(WebDriver driver) {
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
			waitForPageLoaded(driver);
		}
		
		if(status != null && status != "") {
			selectCombobox(cb_status, status);
			waitForPageLoaded(driver);
		}
		
		if(access != null && access != "") {
			selectCombobox(cb_access, access);
			waitForPageLoaded(driver);
		}			
		
		if(language != null && language != "") {
			selectCombobox(cb_language, language);
			waitForPageLoaded(driver);
		}
		
		if(categoryText != null && categoryText != "") {
			//Switch editor to plain text mode
			btn_toggleEditor.click();
			txt_categoryText.clear();
			txt_categoryText.sendKeys(categoryText);
			
			btn_toggleEditor.click();
		}
				
	}
			
	//Toolbar action
	public Category_manager_page clickSaveClose() {
		btn_saveClose.click();
		waitForPageLoaded(driver);
		return new Category_manager_page(driver);
	}	
	
	public Category_manager_page clickSaveCopy(){
		btn_saveClose.click();
		waitForPageLoaded(driver);
		return new Category_manager_page(driver);
	}
		
	public Help_page clickHelpPage(){
		PageFactory.setParentWindow(driver.getWindowHandle());
		btn_help.click();
		sleep(config.getShortTime());
		switchToNextWindow();
		return new Help_page(driver);
	}
	
	public Category_add_edit_page clickSave(){
		btn_save.click();
		waitForPageLoaded(driver);
		return new Category_add_edit_page(driver);
	}
	
	public Category_manager_page clickCancel() {
		btn_cancel.click();
		waitForPageLoaded(driver);
		return new Category_manager_page(driver);
	}
	
	public void clickSaveNew(){
		btn_saveNew.click();
		waitForPageLoaded(driver);
	}	

	//Verify action
	public boolean isEditClientPageOpening(){
		return isElementExist(_txt_editClientitle);
	}
	
	public boolean isCategoryBatSuccess(String parent) {
		String new_parent = "- " + parent;
		String check_parent = driver.findElement(By.id("jform_parent_id")).findElement(By.xpath(".//option[@selected='selected']")).getAttribute("text");
		boolean check = false;
		if(new_parent.equals(check_parent)) {
			check = true;
		}
		
		return check;
	}
	
	//Editor
	@FindBy(xpath="//input[@id='jform_title']")
	private WebElement txt_title;

	@FindBy(xpath="//input[@id='jform_alias']")
	private WebElement txt_alias;
	
	@FindBy(xpath="//select[@id='jform_parent_id']")
	private WebElement cb_parent;
	
	@FindBy(xpath="//select[@id='jform_published']")
	private WebElement cb_status;
	
	@FindBy(xpath="//select[@id='jform_access']")
	private WebElement cb_access;
	
	@FindBy(xpath="//select[@id='jform_featured']")
	private WebElement cb_feature;
	
	@FindBy(xpath="//select[@id='jform_language']")
	private WebElement cb_language;	
	
	@FindBy(xpath="//textarea[@id='jform_description']")
	private WebElement txt_categoryText;
	
	@FindBy(xpath="//a[text()='Toggle editor']")
	private WebElement btn_toggleEditor;
	
	@FindBy(xpath="//a[text()='Image']")
	private WebElement btn_image;
	
	//Submit buttons
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	private WebElement btn_saveClose;
	
	@FindBy(xpath="//li[@id='toolbar-apply']/a")
	private WebElement btn_save;
	
	@FindBy(xpath="//li[@id='toolbar-save-new']/a")
	private WebElement btn_saveNew;
	
	@FindBy(xpath="//li[@id='toolbar-save-copy']")
	private WebElement brn_saveCopy;
	
	@FindBy(xpath="//li[@id='toolbar-cancel']/a")
	private WebElement btn_cancel;
	
	@FindBy(xpath="//li[@id='toolbar-help']/a")
	private WebElement btn_help;
	
	//iframe
	@FindBy(xpath="//div[@id='sbox-content']/iframe")
	private WebElement iframe_imageFrame;
	
	@FindBy(xpath="//iframe[@id='imageframe']")
	private WebElement iframe_selectImageFrame;
	
	@FindBy(xpath="//button[text()='Insert']")
	private WebElement btn_insertImageIframe;
	
	private String _txt_editClientitle = ".//*[@id='toolbar-box']/div/div[2]/h2[contains (text(),'Category Manager: Edit An Articles Category')]";
	String _categoryParent = "//select[@id='jform_catid']/option[contains(text(), '%s')]";
}
