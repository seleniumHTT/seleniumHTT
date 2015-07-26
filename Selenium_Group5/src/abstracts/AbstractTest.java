package abstracts;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import common.config;

public abstract class AbstractTest {
	
  WebDriver driver;  

  @AfterClass
  public void afterClass() {
	  config.tearDown();
  }
  
  public void verifyTrue(boolean condition, String verifyPoint) {	  
	  try {
		  
		  Assert.assertTrue(condition);
		  System.out.println("--------------" + verifyPoint + " - PASSED");
		  Reporter.log(verifyPoint + " - PASSED");
	  } catch (AssertionError e) {		  
		  System.out.println("--------------" + verifyPoint + " - FAILED");
		  Reporter.log(verifyPoint + " - FAILED");
		  Assert.fail("Test Failed", e.getCause());
		  
	  }	 
  }

  public void verifyFalse(boolean condition, String verifyPoint) {
	  if(condition == false) {
		  Reporter.log(verifyPoint + " - PASSED");  
	  } else {
		  Reporter.log(verifyPoint + " - FAILED");
	  }	  
	  Assert.assertFalse(condition);	  
  }
  
  @BeforeMethod
  public void beforeTest(Method method) {
	  System.out.println("---------Begin " + method.getName());
	  Reporter.log("---------Begin " + method.getName());
  }
  
  @AfterMethod
  public void afterTest(Method method) {
	  System.out.println("---------End " + method.getName());
	  Reporter.log("---------End " + method.getName());
  }
}
