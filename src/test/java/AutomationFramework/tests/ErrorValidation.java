package AutomationFramework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;



import AutomationFramework.testcomponents.BaseTest;
import AutomationFramework.testcomponents.Retry;

public class ErrorValidation extends BaseTest{
	
	@Test(groups= {"errorHandling"}, retryAnalyzer=Retry.class)
	public void errorValidation() throws IOException {
		String emailId = "helloT@gmail.com";
		String password = "HelloT@123";
		
		loginPage.userLogin(emailId, password);
		Assert.assertEquals(loginPage.getLoginErrorMessage(), " Incorrect email or password.");
	}
}
