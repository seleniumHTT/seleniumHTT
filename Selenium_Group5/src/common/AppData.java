package common;

import javax.xml.xpath.*;

import utilities.Random;
import utilities.XMLhelper;

public class AppData {
	public static String getUrlLogin() {
		return urlLogin;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}	
	
	public static void getData() {
		try {
			XMLhelper xmlHelper = new XMLhelper(filePath);
			urlLogin = xmlHelper.getContentByXpath(_loginUrl);
			username = xmlHelper.getContentByXpath(_username);
			password = xmlHelper.getContentByXpath(_password);
			
		} catch (XPathExpressionException e) {			
			e.printStackTrace();
		}		
	}
		
	public static String getRandomString(String suite) {
		return Random.getRandomName() + " " + suite; 
	}		
	
	private static String urlLogin, username, password;
	
	public static final String imageName = "powered_by.png";
	public static final String msgSaveContact = "Contact successfully saved";
	public static final String msgPublish = "1 contact successfully published";
	public static final String msgUnpublish = "1 contact successfully unpublished";
	public static final String msgTrash = "1 contact successfully trashed";
	public static final String msgArchive = "1 contact successfully archived";
	public static final String msgCheckedIn = "1 contact successfully checked in";
	
	private static String filePath = "./resources/application.data.xml";
	
	private static String _loginUrl = "//loginUrl";
	private static String _username = "//username";
	private static String _password = "//password";
}
