/**
 * This class contains a test case to validate the login functionality
 * of the Swag Labs application. It extends the SuperTestNG class to
 * inherit the test setup and teardown methods.
 * 
 * The testValidLogin method:
 * - Uses the ProjectSpecific class to perform the login operation.
 * - Verifies that the homepage title matches the expected result.
 * - Utilizes TestNG's RetryAnalyzer for retrying the test in case of failure.
 */
package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baseTest.SuperTestNG;
import com.framework.library.RetryAnalyzer;
import com.swaglab.library.ProjectSpecific;
import com.swaglab.pages.LoginPage;

public class ValidLogin extends SuperTestNG {
	LoginPage loginPage= new LoginPage();
	ProjectSpecific projectSpecific =new ProjectSpecific();
	String sExpectedResult = "Swag Labs";

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testValidLogin() {
		projectSpecific.login();
		Assert.assertEquals(loginPage.verifyHomepage(sExpectedResult), true);
	}
}
