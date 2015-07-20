package pages;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.xpath.XPathExpressionException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Random;
import utilities.XMLhelper;
import common.config;

public class TestHai {

	public static void main(String[] args) {
		
		XMLhelper xh = null;
		try {
			xh = new XMLhelper("./resources/webconfig.xml");
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(xh.getContentByXpath("//username"));
		
		
		
//		String menu = "hoanghai/a";
//		String[] arr = menu.split("/");
//		System.out.println(arr.length);
		
	}
	
	public enum status {
		Published, Unpublished, Archived, Trashed
	}

}
