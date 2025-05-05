package com.swaglab.pages.homepage;

import org.openqa.selenium.WebElement;

import com.framework.library.Keywords;

/**
 * Represents the Home Page of the Swag Labs application. Provides methods to
 * interact with elements on the Home Page.
 */
public class HomePage extends Keywords {
	private String productNames = "div.inventory_item_name";
	private String shoppingCartIcon = ".shopping_cart_link";
	private String shoppingCartItemCount = ".shopping_cart_badge";

	/**
	 * Retrieves the name of the first product displayed on the Home Page.
	 * 
	 * @return The name of the first product as a String.
	 */
	public String getFirstProductName() {
		WebElement productName = getSpecificElementFromMultipleElement("cssSelector", productNames, 1);
		return productName.getText();
	}

	/**
	 * Navigates to the first product by its name and clicks the "Add to cart"
	 * button.
	 * 
	 * @param sProductName The name of the product to navigate to.
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean navigateFirstProductName(String sProductName) {
		WebElement productdName = getSpecificElementFromMultipleElement("cssSelector", productNames, 1);
		productdName.click();
		return true;
	}

	/**
	 * Adds a product to the cart by its name.
	 * 
	 * @param sProductName The name of the product to add to the cart.
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean addProductToCart(String sProductName) {
		return clickElm("xpath", "//*[text()='" + sProductName
				+ "']/parent::a/parent::div/following-sibling::*/button[text()='Add to cart']");
	}

	/**
	 * Clicks on the cart icon to navigate to the cart page.
	 * 
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean clickCartIcon() {
		return clickElm("cssSelector", shoppingCartIcon);
	}

	/**
	 * Verifies if the item count in the shopping cart matches the specified value.
	 *
	 * @param itemCount The expected item count in the shopping cart.
	 * @return True if the item count matches the specified value, false otherwise.
	 */
	public boolean verifyItemCountInCart(int itemCount) {
		return verifyText("cssSelector", shoppingCartItemCount, String.valueOf(itemCount));
	}

}
