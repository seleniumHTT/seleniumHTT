package utilities;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import java.io.*;

public class XMLhelper {
	
	public XMLhelper(String filePath) throws XPathExpressionException {
		factory = DocumentBuilderFactory.newInstance();
		
		
		try {
			builder = factory.newDocumentBuilder();			
			document = builder.parse(new File(filePath));
			
			document.getDocumentElement().normalize();	
			
			xPathfactory = XPathFactory.newInstance();
			xpath = xPathfactory.newXPath();			
			
		} catch (IOException e) {
			e.printStackTrace();		
		
		} catch (SAXException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		
	}	
	
	public String getContentByXpath(String xpathString){
		XPathExpression expr = null;
		
		try {
			expr = xpath.compile(xpathString);
			return expr.evaluate(document);
		} catch (XPathExpressionException e) {			
			e.printStackTrace();			
		}
		
		return expr.toString();
		
	}
	
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document document;
	XPathFactory xPathfactory;
	XPath xpath;
}
