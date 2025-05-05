package com.swaglab.pages.addToCart;

import com.framework.library.Keywords;

public class ProductPage extends Keywords {
	private String backToProductsLink = "#back-to-products";
	private String productName = "div[data-test='inventory-item-name']";

	/**
	 * This method verifies if the required product is displayed on the product
	 * page.
	 * 
	 * @param sProductName The name of the product to verify.
	 * @return true if the product is displayed, false otherwise.
	 */
	public boolean verifyTheRequiredProductOnProductPage(String sProductName) {
		if (elementIsVisible("cssSelector", backToProductsLink)) {
			return verifyText("cssSelector", productName, sProductName);
		} else {
			return false;
		}
	}
}
