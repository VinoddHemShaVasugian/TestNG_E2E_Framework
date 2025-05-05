/**
 * This class contains project-specific methods for the Swag Labs application.
 * It extends the `Keywords` class to utilize common utility methods for interacting
 * with the application. The class provides methods for performing actions such as login.
 *
 * Constructors:
 * - `ProjectSpecific(WebDriver driver)`: Initializes the class with a WebDriver instance.
 * - `ProjectSpecific()`: Default constructor for initializing the class without a WebDriver.
 *
 * Methods:
 * - `login()`: Performs the login operation using credentials from the test data file.
 */
package com.swaglab.library;

import com.baseTest.SuperTestNG;
import com.framework.library.Keywords;
import com.swaglab.pages.login.LoginPage;

public class ProjectSpecific extends Keywords {

	/**
	 * Default constructor to initialize the class without a WebDriver instance.
	 */
	public ProjectSpecific() {
		super();
	}

	/**
	 * Performs the login operation using credentials from the test data file.
	 * Retrieves the username and password from the test data file and uses the
	 * `LoginPage` class to perform the login action.
	 */
	public void login() {
		LoginPage loginPage = new LoginPage();
		String sUserName = getData(SuperTestNG.stestDataFile, "Login", 1, 3);
		loginPage.enterUserName(sUserName);
		String sPassword = getData(SuperTestNG.stestDataFile, "Login", 1, 4);
		loginPage.enterPassword(sPassword);
		loginPage.clickSignIn();
	}
}
