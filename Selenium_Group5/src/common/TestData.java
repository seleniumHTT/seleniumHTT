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
				helpPage = xmlHelper.getContentByXpath(_helpPage);
				
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
		
		public static String getHelpPageTitle() {
			return helpPage;
		}
		
		private static String name, alias, category, stsPublished, stsUnpublished, feature, imageName, helpPage;
		private static final String _name = "//article/name";
		private static final String _alias = "//article/alias";
		private static final String _category = "//article/parent";
		private static final String _stsPublished = "//article//stsPublished";
		private static final String _stsUnpublished = "//article//stsUnpublished";
		private static final String _feature = "//article/feature";
		private static final String _imageName = "//article/imageName";
		private static final String _helpPage = "//article/helpPage";
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
				helpPage = xmlHelper.getContentByXpath(_helpPage);
				
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
		
		public static String getHelpPageTitle() {
			return helpPage;
		}
		
		private static String name, alias, category, stsPublished, stsUnpublished, feature, imageName, helpPage;
		private static final String _name = "//contact/name";
		private static final String _alias = "//contact/alias";
		private static final String _category = "//contact/parent";
		private static final String _stsPublished = "//contact//stsPublished";
		private static final String _stsUnpublished = "//contact//stsUnpublished";
		private static final String _feature = "//contact/feature";
		private static final String _imageName = "//contact/imageName";
		private static final String _helpPage = "//contact/helpPage";
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
				helpPage = xmlHelper.getContentByXpath(_helpPage);
				
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
		
		public static String getHelpPageTitle() {
			return helpPage;
		}
		
		private static String name, alias, url1, url2, category, stsPublished, stsUnpublished, feature, imageName, helpPage;
		private static final String _name = "//weblink/name";
		private static final String _alias = "//weblink/alias";
		private static final String _category = "//weblink/parent";
		private static final String _stsPublished = "//weblink//stsPublished";
		private static final String _stsUnpublished = "//weblink//stsUnpublished";
		private static final String _feature = "//weblink/feature";
		private static final String _imageName = "//weblink/imageName";
		private static final String _helpPage = "//weblink/helpPage";
		private static String _url1 = "//weblink/urls/url[1]";
		private static String _url2 = "//weblink/urls/url[2]";
	}
	
	public static class Banner {
		public static void getDataTest() {
			try {
				XMLhelper xmlHelper = new XMLhelper(filePath);
				name = xmlHelper.getContentByXpath(_name);
				name = name + Random.getCurrentTime();
				
				alias = xmlHelper.getContentByXpath(_alias);
				category = xmlHelper.getContentByXpath(_ctgCategory);
				unCategory = xmlHelper.getContentByXpath(_ctgUncategory);
				stsSelectStatus = xmlHelper.getContentByXpath(_stsSelectStatus);
				stsPublished = xmlHelper.getContentByXpath(_stsPublished);
				stsUnpublished = xmlHelper.getContentByXpath(_stsUnpublished);
				stsArchived = xmlHelper.getContentByXpath(_stsArchived);
				stsTrashed = xmlHelper.getContentByXpath(_stsTrashed);
				helpPage = xmlHelper.getContentByXpath(_helpPage);
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
		
		public static String getUncategory() {
			return unCategory;
		}
		
		public static String getStsSelectStatus() {
			return stsSelectStatus;
		}
		
		public static String getStsPublished() {
			return stsPublished;
		}

		public static String getStsUnpublished() {
			return stsUnpublished;
		}
		
		public static String getStsArchived() {
			return stsArchived;
		}
		
		public static String getStsTrashed() {
			return stsTrashed;
		}
		
		public static String getHelpPageTitle() {
			return helpPage;
		}
		private static String name, alias, category, unCategory, stsSelectStatus, stsPublished, stsUnpublished, stsArchived, stsTrashed, helpPage;
		private static final String _name = "//banner/name";
		private static final String _alias = "//banner/alias";
		private static final String _ctgCategory = "//banner/ctgCategory";
		private static final String _ctgUncategory = "//banner/ctgUncategory";
		private static final String _stsSelectStatus = "//banner//stsSelectStatus";
		private static final String _stsPublished = "//banner//stsPublished";
		private static final String _stsUnpublished = "//banner//stsUnpublished";
		private static final String _stsArchived = "//banner//stsArchived";
		private static final String _stsTrashed = "//banner//stsTrashed";
		private static final String _helpPage = "//banner/helpPage";
	}
	
	public static class BannerClient {
		public static void getDataTest() {
			try {
				XMLhelper xmlHelper = new XMLhelper(filePath);
				name = xmlHelper.getContentByXpath(_name);
				name = name + Random.getCurrentTime();
				
				contactname = xmlHelper.getContentByXpath(_contactname);
				contactemail = xmlHelper.getContentByXpath(_contactemail);
				stsSelectStatus = xmlHelper.getContentByXpath(_stsSelectStatus);
				stsPublished = xmlHelper.getContentByXpath(_stsPublished);
				stsUnpublished = xmlHelper.getContentByXpath(_stsUnpublished);
				stsArchived = xmlHelper.getContentByXpath(_stsArchived);
				stsTrashed = xmlHelper.getContentByXpath(_stsTrashed);
				helpPage = xmlHelper.getContentByXpath(_helpPage);
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
		
		public static String getStsSelectStatus() {
			return stsSelectStatus;
		}
		
		public static String getStsPublished() {
			return stsPublished;
		}

		public static String getStsUnpublished() {
			return stsUnpublished;
		}
		
		public static String getStsArchived() {
			return stsArchived;
		}
		
		public static String getStsTrashed() {
			return stsTrashed;
		}
		
		public static String getHelpPageTitle() {
			return helpPage;
		}
		private static String name, contactname, contactemail, stsSelectStatus, stsPublished, stsUnpublished, stsArchived, stsTrashed, helpPage;
		private static final String _name = "//bannerclient/name";
		private static final String _contactname = "//bannerclient/contactname";
		private static final String _contactemail = "//bannerclient/contactemail";
		private static final String _stsSelectStatus = "//bannerclient//stsSelectStatus";
		private static final String _stsPublished = "//bannerclient//stsPublished";
		private static final String _stsUnpublished = "//bannerclient//stsUnpublished";
		private static final String _stsArchived = "//bannerclient//stsArchived";
		private static final String _stsTrashed = "//bannerclient//stsTrashed";
		private static final String _helpPage = "//bannerclient/helpPage";
	}
	
public static class Category {
		
		public static void getDataTest() {
			try {
				XMLhelper xmlHelper = new XMLhelper(TestData.filePath);
				name = xmlHelper.getContentByXpath(_name);
				name = name + Random.getCurrentTime();
				
				alias = xmlHelper.getContentByXpath(_alias);
				parent = xmlHelper.getContentByXpath(_parent);
				stsPublished = xmlHelper.getContentByXpath(_stsPublished);
				stsUnpublished = xmlHelper.getContentByXpath(_stsUnpublished);
				accessRegistered = xmlHelper.getContentByXpath(_accessRegistered);
				accessPublic = xmlHelper.getContentByXpath(_accessPublic);
				languageUK = xmlHelper.getContentByXpath(_languageUK);
				languageAll = xmlHelper.getContentByXpath(_languageAll);
				imageName = xmlHelper.getContentByXpath(_imageName);
				helpPage = xmlHelper.getContentByXpath(_helpPage);
				
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

		public static String getParent() {
			return parent;
		}

		public static String getStsPublished() {
			return stsPublished;
		}

		public static String getStsUnpublished() {
			return stsUnpublished;
		}
		
		public static String getAccessRegistered() {
			return accessRegistered;
		}
		
		public static String getAccessPublic() {
			return accessPublic;
		}
				
		public static String getImageName() {
			return imageName;
		}
		
		public static String getHelpPageTitle() {
			return helpPage;
		}
		
		public static String getLanguageUK() {
			return languageUK;
		}
		
		public static String getLanguageAll() {
			return languageAll;
		}
		
		private static String name, alias, parent, stsPublished, stsUnpublished, 
			accessPublic, accessRegistered, imageName, helpPage, languageUK, languageAll;
		private static final String _name = "//category/name";
		private static final String _alias = "//category/alias";
		private static final String _parent = "//category/parent";
		private static final String _stsPublished = "//category//stsPublished";
		private static final String _stsUnpublished = "//category//stsUnpublished";
		private static final String _accessRegistered = "//category/registered";
		private static final String _accessPublic = "//category/public";
		private static final String _imageName = "//category/imageName";
		private static final String _helpPage = "//category/helpPage";
		private static final String _languageUK = "//category/language/UK";
		private static final String _languageAll = "//category/language/all";
		
	}
	private final static String filePath = "./resources/test.data.xml";
}
