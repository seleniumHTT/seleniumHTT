package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_page {

	//Mỗi page tạo ra  đầu tiên đều phải khai báo 1 WebDriver
	WebDriver driver;
	
	//Phương thức khởi tạo (constructor), khi new 1 class thì phương thức này sẽ run đầu tiên
	public Login_page(WebDriver driver) {
		this.driver = driver;		
		//Định nghĩa cho webdriver biết toàn bộ các elements phía dưới cần được khởi tạo
		PageFactory.initElements(driver, this);
	}	
		
	
	@FindBy(xpath="//input[@id='mod-login-username']")
	public WebElement txt_username;
	
	@FindBy(xpath="//input[@id='mod-login-password']")
	public WebElement txt_password;
	
	@FindBy(xpath="//a[contains(text(),'Log in')]")
	public WebElement btn_login;
}
