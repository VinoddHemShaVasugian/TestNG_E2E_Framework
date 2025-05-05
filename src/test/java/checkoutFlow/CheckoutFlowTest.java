package checkoutFlow;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baseTest.SuperTestNG;
import com.framework.library.RetryAnalyzer;
import com.framework.utilities.RandomStringGenerator;
import com.swaglab.library.ProjectSpecific;
import com.swaglab.pages.checkoutFlow.CheckoutPage;
import com.swaglab.pages.checkoutFlow.OrderConfirmationPage;
import com.swaglab.pages.homepage.HomePage;

/**
 * This class contains test cases for the checkout flow of the application. It
 * verifies the functionality of adding a product to the cart, proceeding
 * through the checkout process, and confirming the order.
 */
public class CheckoutFlowTest extends SuperTestNG {
	// Instance of ProjectSpecific to handle project-specific actions like login
	ProjectSpecific projectSpecific = new ProjectSpecific();

	// Instance of CheckoutPage to interact with the checkout page
	CheckoutPage checkoutPage = new CheckoutPage();

	// Instance of HomePage to interact with the home page
	HomePage homePage = new HomePage();

	// Instance of OrderConfirmationPage to interact with the OrderConfirmationPage
	// page
	OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage();

	// Variable to store the name of the product being tested
	String sProductName;

	/**
	 * Test method to verify the complete checkout flow. Steps: 1. Log in to the
	 * application. 2. Retrieve the name of the first product on the home page. 3.
	 * Add the product to the cart. 4. Proceed to the checkout page. 5. Enter user
	 * details (first name, last name, zip code). 6. Verify the product is displayed
	 * on the final confirmation page. 7. Complete the order and verify the order
	 * confirmation messages.
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testCheckoutFlow() {
		// Log in to the application
		projectSpecific.login();

		// Get the name of the first product and add it to the cart
		sProductName = homePage.getFirstProductName();
		homePage.addProductToCart(sProductName);

		// Navigate to the cart and proceed to checkout
		homePage.clickCartIcon();
		checkoutPage.clickCheckoutButton();

		// Enter user details for checkout
		checkoutPage.enterFirstName(RandomStringGenerator.autoGenerateName());
		checkoutPage.enterLastName(RandomStringGenerator.autoGenerateName());
		checkoutPage.enterZipCode(RandomStringGenerator.generateRandomNumber(5));
		checkoutPage.clickContinueButton();

		// Verify the product is displayed on the final confirmation page
		Assert.assertTrue(checkoutPage.verifyProductOnFinalConfirmationPage(sProductName),
				"Product added on cart is not displayed on final order confirmation page");

		// Complete the order and verify the confirmation messages
		checkoutPage.clickFinishButton();
		Assert.assertTrue(orderConfirmationPage.verifyOrderConfirmation(),
				"Order confirmation details are not displayed");
	}
}
