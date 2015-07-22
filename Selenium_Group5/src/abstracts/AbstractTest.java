package abstracts;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public abstract class AbstractTest {
	
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }
  
  public void verifyTrue(boolean condition, String verifyPoint) {	  
	  try {
		  Assert.assertTrue(condition);
		  Reporter.log(verifyPoint + " - PASSED");
	  } catch (AssertionError e) {		  
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
