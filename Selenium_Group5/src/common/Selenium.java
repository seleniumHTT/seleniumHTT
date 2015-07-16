package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Selenium {
	private WebDriver driver;

	public WebDriver getDriver(String url) {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(config.timeWaitLong, TimeUnit.SECONDS);
		driver.get(url);
		//driver.manage().window().maximize();
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void close() {
		driver.quit();
	}
	
}
