package addToCart;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baseTest.SuperTestNG;
import com.swaglab.library.ProjectSpecific;
import com.swaglab.pages.CartPage;
import com.swaglab.pages.HomePage;

/**
 * Represents the test class for adding a product to the cart in the Swag Labs
 * application. Contains a test method to verify the functionality of adding a
 * product to the cart.
 */
public class AddProductToCartTest extends SuperTestNG {
	ProjectSpecific projectSpecific = new ProjectSpecific();
	CartPage cartPage = new CartPage();
	HomePage homePage = new HomePage();
	String sProductName;

	/**
	 * Tests the functionality of adding a product to the cart. Logs into the
	 * application, retrieves the first product name, adds the product to the cart,
	 * navigates to the cart page, and verifies that the product is present in the
	 * cart.
	 */
	@Test()
	public void testAddToCart() {
		projectSpecific.login();
		sProductName = homePage.getFirstProductName();
		homePage.addProductToCart(sProductName);
		homePage.clickCartIcon();
		boolean status = cartPage.verifyProductOnCart(sProductName);
		Assert.assertEquals(status, true, "Product is not added to cart");
	}
}
