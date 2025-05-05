/**
 * This class contains test cases to validate the invalid login functionality
 * of the Swag Labs application. It extends the SuperTestNG class to
 * inherit the test setup and teardown methods.
 *
 * The testInvalidLogin method:
 * - Uses the LoginPage class to perform the login operation with invalid credentials.
 * - Verifies that the appropriate error message is displayed for invalid login attempts.
 *
 * The Testing method:
 * - Provides test data for invalid login attempts using a DataProvider.
 * - Includes both static invalid credentials and dynamically generated random credentials.
 */
package login;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baseTest.SuperTestNG;
import com.framework.library.Keywords;
import com.framework.utilities.RandomStringGenerator;
import com.swaglab.pages.login.LoginPage;

public class InvalidLoginTest extends SuperTestNG {

	/**
	 * Test case to validate invalid login functionality.
	 *
	 * @param userName The user name to be used for login.
	 * @param password The password to be used for login.
	 */
	@Test(dataProvider = "invalidLogin")
	public void testInvalidLogin(String userName, String password) {
		LoginPage loginPage = new LoginPage();
		loginPage.enterUserName(userName);
		loginPage.enterPassword(password);
		loginPage.clickSignIn();
		String loginErrorMessage = Keywords.getData(stestDataFile, "Login", 2, 5);
		Assert.assertTrue(loginPage.verifyProductpage(loginErrorMessage),
				"Login error message is not displayed as expected.");
	}

	/**
	 * DataProvider method to supply invalid login credentials.
	 *
	 * @return A 2D array containing invalid username and password combinations.
	 */
	@DataProvider(name = "invalidLogin", parallel = true)
	public Object[][] Testing() {
		return new Object[][] { { "userinvalid1", "passwrg" }, { RandomStringGenerator.generateRandomStringAlphabet(6),
				RandomStringGenerator.generateRandomStringAlphaNumeric(10) } };
	}
}