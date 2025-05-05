/**
 * This class represents the Login Page of the Swag Labs application.
 * It provides methods to interact with the login page elements such as
 * entering the username, entering the password, clicking the sign-in button,
 * and verifying the homepage or error messages.
 *
 * Constructors:
 * - `LoginPage(WebDriver driver)`: Initializes the class with a WebDriver instance.
 * - `LoginPage()`: Default constructor for initializing the class without a WebDriver.
 *
 * Methods:
 * - `clickSignIn()`: Clicks the sign-in button on the login page.
 * - `enterUserName(String userName)`: Enters the provided username into the username field.
 * - `enterPassword(String password)`: Enters the provided password into the password field.
 * - `verifyHomepage(String expectedResult)`: Verifies that the homepage displays the expected result.
 * - `verifyLoginErrorMessage(String loginErrorMessage)`: Verifies that the login error message matches the expected message.
 */
package com.swaglab.pages.checkoutFlow;

import com.framework.library.Keywords;

public class OrderConfirmationPage extends Keywords {
	private String completeHeader = ".complete-header";
	private String completeText = ".complete-text";
	private String title = ".title";

	/**
	 * Verifies the order confirmation details on the final confirmation page.
	 *
	 * @return True if all order confirmation details are successfully verified,
	 *         false otherwise.
	 */
	public boolean verifyOrderConfirmation() {
		boolean checkoutComplete = verifyText("cssSelector", title, "Checkout: Complete!");
		boolean completePage = verifyText("cssSelector", completeHeader, "Thank you for your order!");
		boolean orderConfirmation = verifyText("cssSelector", completeText,
				"Your order has been dispatched, and will arrive just as fast as the pony can get there!");
		return checkoutComplete && completePage && orderConfirmation;
	}
}
