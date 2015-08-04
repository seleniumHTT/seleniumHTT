package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.config;

import abstracts.AbstractPage;

public class Weblink_add_edit_page extends AbstractPage {
	WebDriver driver;
	
	public Weblink_add_edit_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}
	
	
		
	public void enterData(String title, String alias, String URL, String category, String status, String access, String WeblinkText) {
		//Enter data
		if(title !=null && title != "") {			
			txt_title.clear();
			txt_title.sendKeys(title);
		}
		
		if(alias !=null && alias != "") {			
			txt_alias.clear();
			txt_alias.sendKeys(alias);
		}
		
		if(URL != null && URL != "") {
			txt_url.clear();
			txt_url.sendKeys(URL);
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
		
		if(WeblinkText != null && WeblinkText != "") {
			//Switch editor to plain text mode
			btn_toggleEditor.click();
			txt_WeblinkText.clear();
			txt_WeblinkText.sendKeys(WeblinkText);
			
			btn_toggleEditor.click();
		}
				
	}
	
	public void insertImage(String imageName) {
		btn_image.click();
		
		driver.switchTo().frame(iframe_imageFrame);
		driver.switchTo().frame(iframe_selectImageFrame);
		
		getWebElement("//a[@title='"+ imageName +"']").click();
		
		driver.switchTo().parentFrame();		
		btn_insertImageIframe.click();
		
		driver.switchTo().parentFrame();
		sleep(config.getShortTime());
	}
	
	public Weblink_manager_page clickSaveClose() {
		btn_saveClose.click();
		waitForPageLoaded(driver);
		return new Weblink_manager_page(driver);		
	}	
		
	public void clickSave() {
		btn_save.click();
		waitForPageLoaded(driver);
	}
	
	public void clickSaveCopy() {
		btn_saveCopy.click();
		waitForPageLoaded(driver);
	}
	
	public Weblink_manager_page clickClose() {
		btn_cancel.click();
		waitForPageLoaded(driver);
		return new Weblink_manager_page(driver);
	}
	
	///////////////
	@FindBy(xpath="//input[@id='jform_title']")
	private WebElement txt_title;

	@FindBy(xpath="//input[@id='jform_alias']")
	private WebElement txt_alias;
			
	@FindBy(xpath="//select[@id='jform_catid']")
	private WebElement cb_category;
	
	@FindBy(xpath="//select[@id='jform_state']")
	private WebElement cb_status;
	
	@FindBy(xpath="//select[@id='jform_access']")
	private WebElement cb_access;
	
	@FindBy(xpath="//input[@id='jform_url']")
	private WebElement txt_url;
	
	@FindBy(xpath="//textarea[@id='jform_description']")
	private WebElement txt_WeblinkText;
	
	@FindBy(xpath="//a[text()='Toggle editor']")
	private WebElement btn_toggleEditor;
	
	@FindBy(xpath="//a[text()='Image']")
	private WebElement btn_image;
	
	//Submit buttons
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	private WebElement btn_saveClose;
	
	@FindBy(xpath="//li[@id='toolbar-apply']/a")
	private WebElement btn_save;
	
	@FindBy(xpath="//li[@id='toolbar-save-copy']/a")
	private WebElement btn_saveCopy;
	
	@FindBy(xpath="//li[@id='toolbar-cancel']/a")
	private WebElement btn_cancel;
	
	@FindBy(xpath="//img[@id='jform_publish_up_img'")
	private WebElement btn_publishDateStart;
	
	@FindBy(xpath="//img[@id='jform_publish_down_img'")
	private WebElement btn_publishDateFinish;
	
	//iframe
	@FindBy(xpath="//div[@id='sbox-content']/iframe")
	private WebElement iframe_imageFrame;
	
	@FindBy(xpath="//iframe[@id='imageframe']")
	private WebElement iframe_selectImageFrame;
	
	@FindBy(xpath="//button[text()='Insert']")
	private WebElement btn_insertImageIframe;	
	
	private String _lbl_addNewWeblink = "//h2[text()='Weblink Manager: Add New Weblink']";
	private String _categoryValue = "//select[@id='jform_catid']/option[contains(text(), '%s')]";
	
}
