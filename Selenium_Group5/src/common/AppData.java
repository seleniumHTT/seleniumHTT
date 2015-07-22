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
		return Random.getCurrentTime() + " " + suite; 
	}		
	
	
	
	public static class Article {		
		public static final String imageName = "powered_by.png";
		public static final String msgSave = "Article successfully saved";
		public static final String msgPublish = "1 article published";
		public static final String msgUnpublish = "1 article unpublished";
		public static final String msgTrash = "1 article trashed";
		public static final String msgArchive = "1 article archived";
		public static final String msgCheckedIn = "1 article checked in";	
	}
	
	public static class Contact {
		public static final String imageName = "powered_by.png";
		public static final String msgSave = "Contact successfully saved";
		public static final String msgPublish = "1 contact successfully published";
		public static final String msgUnpublish = "1 contact successfully unpublished";
		public static final String msgTrash = "1 contact successfully trashed";
		public static final String msgArchive = "1 contact successfully archived";
		public static final String msgCheckedIn = "1 contact successfully checked in";		
	}
	
	public static class Weblink {
		public static final String imageName = "powered_by.png";
		public static final String msgSave = "Weblink successfully saved";
		public static final String msgPublish = "1 weblink successfully published";
		public static final String msgUnpublish = "1 weblink successfully unpublished";
		public static final String msgTrash = "1 weblink successfully trashed";
		public static final String msgArchive = "1 weblink successfully archived";
		public static final String msgCheckedIn = "1 weblink successfully checked in";		
	}
	
	public static class BannerClient {
		public static final String msgSave = "Client successfully saved";
		public static final String msgPublish = "1 client successfully published";
		public static final String msgUnpublish = "1 client successfully unpublished";
		public static final String msgTrash = "1 client successfully trashed";
		public static final String msgArchive = "1 client successfully archived";
		public static final String msgCheckedIn = "1 client successfully checked in";	
	
	}
	
	private static String urlLogin, username, password;
	
	private static String filePath = "./resources/application.data.xml";
	
	private static String _loginUrl = "//loginUrl";
	private static String _username = "//username";
	private static String _password = "//password";
}
