package common;

import javax.xml.xpath.XPathExpressionException;

import utilities.Random;
import utilities.XMLhelper;

public class TestData {
	public static class Article {
		
		public static void getDataTest() {
			try {
				XMLhelper xmlHelper = new XMLhelper(TestData.filePath);
				name = xmlHelper.getContentByXpath(_name);
				name = name + Random.getCurrentTime();
				
				alias = xmlHelper.getContentByXpath(_alias);
				category = xmlHelper.getContentByXpath(_category);
				stsPublished = xmlHelper.getContentByXpath(_stsPublished);
				stsUnpublished = xmlHelper.getContentByXpath(_stsUnpublished);
				feature = xmlHelper.getContentByXpath(_feature);
				imageName = xmlHelper.getContentByXpath(_imageName);
				
			} catch (XPathExpressionException e) {			
				e.printStackTrace();
			}	
		}

		public static String getName() {
			return name;
		}

		public static String getAlias() {
			return alias;
		}

		public static String getCategory() {
			return category;
		}

		public static String getStsPublished() {
			return stsPublished;
		}

		public static String getStsUnpublished() {
			return stsUnpublished;
		}

		public static String getFeature() {
			return feature;
		}
		
		public static String getImageName() {
			return imageName;
		}
		
		private static String name, alias, category, stsPublished, stsUnpublished, feature, imageName;
		private static final String _name = "//article/name";
		private static final String _alias = "//article/alias";
		private static final String _category = "//article/category";
		private static final String _stsPublished = "//article//stsPublished";
		private static final String _stsUnpublished = "//article//stsUnpublished";
		private static final String _feature = "//article/feature";
		private static final String _imageName = "//article/imageName";
	}
	
	public static class Contact {
		
		public static void getDataTest() {
			try {
				XMLhelper xmlHelper = new XMLhelper(TestData.filePath);
				name = xmlHelper.getContentByXpath(_name);
				name = name + Random.getCurrentTime();
				
				alias = xmlHelper.getContentByXpath(_alias);
				category = xmlHelper.getContentByXpath(_category);
				stsPublished = xmlHelper.getContentByXpath(_stsPublished);
				stsUnpublished = xmlHelper.getContentByXpath(_stsUnpublished);
				feature = xmlHelper.getContentByXpath(_feature);
				imageName = xmlHelper.getContentByXpath(_imageName);
				
			} catch (XPathExpressionException e) {			
				e.printStackTrace();
			}	
		}

		public static String getName() {
			return name;
		}

		public static String getAlias() {
			return alias;
		}

		public static String getCategory() {
			return category;
		}

		public static String getStsPublished() {
			return stsPublished;
		}

		public static String getStsUnpublished() {
			return stsUnpublished;
		}

		public static String getFeature() {
			return feature;
		}
		
		public static String getImageName() {
			return imageName;
		}
		
		private static String name, alias, category, stsPublished, stsUnpublished, feature, imageName;
		private static final String _name = "//contact/name";
		private static final String _alias = "//contact/alias";
		private static final String _category = "//contact/category";
		private static final String _stsPublished = "//contact//stsPublished";
		private static final String _stsUnpublished = "//contact//stsUnpublished";
		private static final String _feature = "//contact/feature";
		private static final String _imageName = "//contact/imageName";
	}
	
	public static class Weblink {
		public static void getDataTest() {
			try {
				XMLhelper xmlHelper = new XMLhelper(filePath);
				name = xmlHelper.getContentByXpath(_name);
				name = name + Random.getCurrentTime();
				
				alias = xmlHelper.getContentByXpath(_alias);
				url1 = xmlHelper.getContentByXpath(_url1);
				url2 = xmlHelper.getContentByXpath(_url2);
				category = xmlHelper.getContentByXpath(_category);
				stsPublished = xmlHelper.getContentByXpath(_stsPublished);
				stsUnpublished = xmlHelper.getContentByXpath(_stsUnpublished);
				feature = xmlHelper.getContentByXpath(_feature);
				imageName = xmlHelper.getContentByXpath(_imageName);
				
			} catch (XPathExpressionException e) {			
				e.printStackTrace();
			}	
		}

		public static String getName() {
			return name;
		}

		public static String getAlias() {
			return alias;
		}

		public static String getUrl1() {
			return url1;
		}
		
		public static String getUrl2() {
			return url2;
		}
		
		public static String getCategory() {
			return category;
		}

		public static String getStsPublished() {
			return stsPublished;
		}

		public static String getStsUnpublished() {
			return stsUnpublished;
		}

		public static String getFeature() {
			return feature;
		}
		
		public static String getImageName() {
			return imageName;
		}
		
		private static String name, alias, url1, url2, category, stsPublished, stsUnpublished, feature, imageName;
		private static final String _name = "//weblink/name";
		private static final String _alias = "//weblink/alias";
		private static final String _category = "//weblink/category";
		private static final String _stsPublished = "//weblink//stsPublished";
		private static final String _stsUnpublished = "//weblink//stsUnpublished";
		private static final String _feature = "//weblink/feature";
		private static final String _imageName = "//weblink/imageName";
		private static String _url1 = "//weblink/urls/url[1]";
		private static String _url2 = "//weblink/urls/url[2]";
	}
	
	public static class BannerClient {
		public static void getDataTest() {
			try {
				XMLhelper xmlHelper = new XMLhelper(filePath);
				name = xmlHelper.getContentByXpath(_name);
				name = name + Random.getCurrentTime();
				
				contactname = xmlHelper.getContentByXpath(_contactname);
				contactemail = xmlHelper.getContentByXpath(_contactemail);
				stsPublished = xmlHelper.getContentByXpath(_stsPublished);
				stsUnpublished = xmlHelper.getContentByXpath(_stsUnpublished);
				} catch (XPathExpressionException e) {			
					e.printStackTrace();
				}
			
		}
		
		public static String getName() {
			return name;
		}
		
		public static String getContactName() {
			return contactname;
		}
		
		public static String getContactEmail() {
			return contactemail;
		}
		
		public static String getStsPublished() {
			return stsPublished;
		}

		public static String getStsUnpublished() {
			return stsUnpublished;
		}
		
		private static String name, contactname, contactemail, stsPublished, stsUnpublished; 
		private static final String _name = "//bannerclient/name";
		private static final String _contactname = "//bannerclient/contactname";
		private static final String _contactemail = "//bannerclient/contactemail";
		private static final String _stsPublished = "//bannerclient//stsPublished";
		private static final String _stsUnpublished = "//bannerclient//stsUnpublished";
		
	}
	
	private final static String filePath = "./resources/test.data.xml";
}
