package common;

import javax.xml.xpath.XPathExpressionException;

import utilities.Random;
import utilities.XMLhelper;

public class TestData {
	public static class Article {
		
	}
	
	public static class Contact {
		
		public static void getDataTest() {
			try {
				XMLhelper xmlHelper = new XMLhelper(filePath);
				name = xmlHelper.getContentByXpath(_name);
				name = name + Random.getRandomName();
				
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
		
	}
	
	public static class BannerClient {
		
	}
	
	private final static String filePath = "./resources/test.data.xml";
}
