package com.swaglab.pages;

import com.framework.library.Keywords;

/**
 * Represents the Cart Page of the Swag Labs application. Provides methods to
 * verify the presence of products in the cart.
 */
public class CartPage extends Keywords {

	/**
	 * Verifies if a specific product is present in the cart.
	 *
	 * @param sproductName The name of the product to verify.
	 * @return True if the product is found in the cart, false otherwise.
	 */
	public boolean verifyProductOnCart(String sproductName) {
		return verifyText("cssSelector", ".inventory_item_name", sproductName);
	}
}
