package abstracts;

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
  
  
  
}
