package addToCart;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baseTest.SuperTestNG;
import com.framework.library.RetryAnalyzer;
import com.swaglab.library.ProjectSpecific;
import com.swaglab.pages.checkoutFlow.CheckoutPage;
import com.swaglab.pages.homepage.HomePage;

/**
 * Represents the test class for adding a product to the cart in the Swag Labs
 * application. Contains a test method to verify the functionality of adding a
 * product to the cart and count of added item.
 */
public class AddProductToCartTest extends SuperTestNG {
	ProjectSpecific projectSpecific = new ProjectSpecific();
	CheckoutPage cartPage = new CheckoutPage();
	HomePage homePage = new HomePage();
	String sProductName;

	/**
	 * Tests the functionality of adding a product to the cart. Logs into the
	 * application, retrieves the first product name, adding the product to the cart,
	 * navigates to the cart page, and verifies that the product is present in the
	 * cart and count of added item.
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testAddToCart() {
		projectSpecific.login();
		sProductName = homePage.getFirstProductName();
		homePage.addProductToCart(sProductName);
		Assert.assertTrue(homePage.verifyItemCountInCart(1), "Item count mismatch in cart");
		homePage.clickCartIcon();
		Assert.assertTrue(cartPage.verifyProductOnCart(sProductName), "Product is not added to cart");
	}
}
