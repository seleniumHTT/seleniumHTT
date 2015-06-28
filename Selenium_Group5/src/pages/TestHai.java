package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Random;
import common.Selenium;
import common.config;

public class TestHai {

	public static void main(String[] args) {
		
		String menu = "hoanghai/a";
		String[] arr = menu.split("/");
		System.out.println(arr.length);
		
	}
	
	public enum status {
		Published, Unpublished, Archived, Trashed
	}

}
