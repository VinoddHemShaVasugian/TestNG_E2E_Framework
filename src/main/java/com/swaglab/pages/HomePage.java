package com.swaglab.pages;

import org.openqa.selenium.WebElement;

import com.framework.library.Keywords;

/**
 * Represents the Home Page of the Swag Labs application. Provides methods to
 * interact with elements on the Home Page.
 */
public class HomePage extends Keywords {

	/**
	 * Retrieves the name of the first product displayed on the Home Page.
	 * 
	 * @return The name of the first product as a String.
	 */
	public String getFirstProductName() {
		WebElement productdName = getSpecificElementFromMultipleElement("cssSelector", "div.inventory_item_name", 1);
		return productdName.getText();
	}

	/**
	 * Navigates to the first product by its name and clicks the "Add to cart"
	 * button.
	 * 
	 * @param sProductName The name of the product to navigate to.
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean navigateFirstProductName(String sProductName) {
		WebElement productdName = getSpecificElementFromMultipleElement("cssSelector", "div.inventory_item_name", 1);
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
		return clickElm("cssSelector", ".shopping_cart_link");
	}
}
