package pages;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Random;
import common.Selenium;
import common.config;

public class TestHai {

	public static void main(String[] args) {
		URL hubUrl;
		try {
			hubUrl = new URL("http://localhost:4444/wd/hub");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			RemoteWebDriver driver = new RemoteWebDriver(hubUrl, capabilities);			
			driver.get("http://www.google.com");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		String menu = "hoanghai/a";
//		String[] arr = menu.split("/");
//		System.out.println(arr.length);
		
	}
	
	public enum status {
		Published, Unpublished, Archived, Trashed
	}

}
