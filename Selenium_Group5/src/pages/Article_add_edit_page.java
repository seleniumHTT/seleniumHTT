package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abs.AbstractPage;

public class Article_add_edit_page extends AbstractPage {
	WebDriver driver;
	
	public Article_add_edit_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}
	
	
	public void enterData(String title, String category, String status, String access, String feature, String articleText) {
		
		//Enter data
		if(title !=null && title != "") {			
			txt_title.clear();
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
			//Switch editor to plain text mode
			btn_toggleEditor.click();
			txt_articleText.clear();
			txt_articleText.sendKeys(articleText);
			
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
	}
	public Article_manager_page clickSaveClose() {
		btn_saveClose.click();
		return new Article_manager_page(driver);
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
	
	@FindBy(xpath="//a[text()='Image']")
	WebElement btn_image;
	
	//Submit buttons
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	WebElement btn_saveClose;
	
	//iframe
	@FindBy(xpath="//div[@id='sbox-content']/iframe")
	WebElement iframe_imageFrame;
	
	@FindBy(xpath="//iframe[@id='imageframe']")
	WebElement iframe_selectImageFrame;
	
	@FindBy(xpath="//button[text()='Insert']")
	WebElement btn_insertImageIframe;
	
	
	String _lbl_addNewArticle = "//h2[text()='Article Manager: Add New Article']";
	String _lbl_articleSaved = "";
}