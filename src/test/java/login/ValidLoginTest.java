package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baseTest.SuperTestNG;
import com.framework.library.RetryAnalyzer;
import com.swaglab.library.ProjectSpecific;
import com.swaglab.pages.HomePage;
import com.swaglab.pages.ProductPage;

/**
 * Represents the test class for validating a successful login to the Swag Labs
 * application. Contains a test method to verify the login functionality and
 * product navigation.
 */
public class ValidLoginTest extends SuperTestNG {
	ProjectSpecific projectSpecific = new ProjectSpecific();
	HomePage homePage = new HomePage();
	ProductPage productPage = new ProductPage();
	String sProductName;

	/**
	 * Tests the valid login functionality. Logs into the application, retrieves the
	 * first product name, navigates to the product, and verifies that the correct
	 * product is displayed on the product page.
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testValidLogin() {
		projectSpecific.login();
		sProductName = homePage.getFirstProductName();
		homePage.navigateFirstProductName(sProductName);
		Assert.assertEquals(productPage.verifyTheRequiredProductOnProductPage(sProductName), true);
	}
}
