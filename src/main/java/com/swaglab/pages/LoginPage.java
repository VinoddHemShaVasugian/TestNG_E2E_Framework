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
package com.swaglab.pages;

import com.framework.library.Keywords;

public class LoginPage extends Keywords {

	/**
	 * Clicks the sign-in button on the login page.
	 */
	public void clickSignIn() {
		clickElm("cssSelector", ".submit-button");
	}

	/**
	 * Enters the provided username into the username field.
	 *
	 * @param userName The username to be entered.
	 */
	public void enterUserName(String userName) {
		enterText("cssSelector", "#user-name", userName);
	}

	/**
	 * Enters the provided password into the password field.
	 *
	 * @param password The password to be entered.
	 */
	public void enterPassword(String password) {
		enterText("cssSelector", "#password", password);
	}

	/**
	 * Verifies that the homepage displays the expected result.
	 *
	 * @param expectedResult The expected text to verify on the homepage.
	 * @return True if the expected text matches, otherwise false.
	 */
	public boolean verifyHomepage(String expectedResult) {
//		acceptAlert();
		return verifyText("cssSelector", "div.app_logo", expectedResult);
	}

	/**
	 * Verifies that the login error message matches the expected message.
	 *
	 * @param loginErrorMessage The expected error message to verify.
	 */
	public void verifyLoginErrorMessage(String loginErrorMessage) {
		verifyText("cssSelector", "div.error-message-container h3", loginErrorMessage);
	}
}
