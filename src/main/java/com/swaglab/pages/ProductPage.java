package com.swaglab.pages;

import com.framework.library.Keywords;

public class ProductPage extends Keywords {

	/**
	 * This method verifies if the required product is displayed on the product
	 * page.
	 * 
	 * @param sProductName The name of the product to verify.
	 * @return true if the product is displayed, false otherwise.
	 */
	public boolean verifyTheRequiredProductOnProductPage(String sProductName) {
		if (elementIsVisible("cssSelector", "#back-to-products")) {
			return verifyText("cssSelector", "div[data-test='inventory-item-name']", sProductName);
		} else {
			return false;
		}
	}
}
