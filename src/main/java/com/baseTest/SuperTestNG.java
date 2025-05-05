/**
 * Base test class for TestNG test cases.
 * This class provides setup and teardown methods for initializing and closing
 * the WebDriver instance. It also includes configuration for browser type,
 * test data, and application URL.
 *
 * Fields:
 * - `driver`: The WebDriver instance used for browser automation.
 * - `step`: An instance of the `Keywords` class for performing actions.
 * - `sConfigFile`: Path to the configuration file containing setup details.
 * - `stestDataFile`: Path to the test data file used in test cases.
 *
 * Methods:
 * - `setUp()`: Initializes the WebDriver, configures browser settings, and navigates to the application URL.
 * - `tearDown()`: Closes the WebDriver instance after test execution.
 */
package com.baseTest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.framework.library.Keywords;

public class SuperTestNG {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static String sConfigFile = "./com/test/resources/files/Setup.xlsx";
	public static String stestDataFile = "./com/test/resources/testData/TestData.xlsx";

	public static WebDriver getDriver() {
		return driver.get();
	}

	/**
	 * Sets up the WebDriver instance and browser configuration before each test.
	 * Reads browser type and application URL from the configuration file.
	 */
	@BeforeMethod
	public void setUp() {
		String browserType = Keywords.getData(sConfigFile, "LAUNCH", 0, 2);
		Keywords keywords = new Keywords(browserType);
		driver.set(keywords.getDriver());
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		String url = Keywords.getData(sConfigFile, "LAUNCH", 1, 2);
		getDriver().get(url);
	}

	/**
	 * Tears down the WebDriver instance after each test. Ensures the browser is
	 * closed to release resources.
	 */
	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
		}
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("taskkill", "/f", "/im", "chromedriver.exe");
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			process.waitFor(); // Wait for the process to complete
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
