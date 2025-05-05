package com.swaglab.pages.checkoutFlow;

import com.framework.library.Keywords;

/**
 * Represents the Cart Page of the Swag Labs application. Provides methods to
 * verify the presence of products in the cart.
 */
public class CheckoutPage extends Keywords {
	private String productName = ".inventory_item_name";
	private String checkoutButton = "#checkout";
	private String continueButton = "#continue";
	private String finishButton = "#finish";
	private String firstNameField = "#first-name";
	private String lastNameField = "#last-name";
	private String zipCodeField = "#postal-code";

	/**
	 * Verifies if a specific product is present in the cart.
	 *
	 * @param sproductName The name of the product to verify.
	 * @return True if the product is found in the cart, false otherwise.
	 */
	public boolean verifyProductOnCart(String sproductName) {
		return verifyText("cssSelector", productName, sproductName);
	}

	/**
	 * Clicks the checkout button on the cart page.
	 *
	 * @return True if the checkout button was successfully clicked, false
	 *         otherwise.
	 */
	public boolean clickCheckoutButton() {
		return clickElm("cssSelector", checkoutButton);

	}

	/**
	 * Enters the first name in the corresponding input field on the checkout page.
	 *
	 * @param firstName The first name to be entered.
	 * @return True if the first name was successfully entered, false otherwise.
	 */
	public boolean enterFirstName(String firstName) {
		return enterText("cssSelector", firstNameField, firstName);
	}

	/**
	 * Enters the last name in the corresponding input field on the checkout page.
	 *
	 * @param lastName The last name to be entered.
	 * @return True if the last name was successfully entered, false otherwise.
	 */
	public boolean enterLastName(String lastName) {
		return enterText("cssSelector", lastNameField, lastName);
	}

	/**
	 * Enters the zip code in the corresponding input field on the checkout page.
	 *
	 * @param zipCode The zip code to be entered.
	 * @return True if the zip code was successfully entered, false otherwise.
	 */
	public boolean enterZipCode(String zipCode) {
		return enterText("cssSelector", zipCodeField, zipCode);
	}

	/**
	 * Clicks the continue button on the checkout page.
	 *
	 * @return True if the continue button was successfully clicked, false
	 *         otherwise.
	 */
	public boolean clickContinueButton() {
		return clickElm("cssSelector", continueButton);
	}

	/**
	 * Clicks the finish button on the final checkout page.
	 *
	 * @return True if the finish button was successfully clicked, false otherwise.
	 */
	public boolean clickFinishButton() {
		return clickElm("cssSelector", finishButton);
	}

	/**
	 * Verifies if a specific product is present on the final confirmation page.
	 *
	 * @param sProductName The name of the product to verify.
	 * @return True if the product is found on the final confirmation page, false
	 *         otherwise.
	 */
	public boolean verifyProductOnFinalConfirmationPage(String sProductName) {
		return verifyText("cssSelector", productName, sProductName);
	}

}
