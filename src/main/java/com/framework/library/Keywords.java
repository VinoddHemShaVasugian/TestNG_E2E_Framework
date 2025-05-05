package com.framework.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class provides a library of reusable methods for interacting with web
 * elements, handling browser actions, and performing validations using Selenium
 * WebDriver.
 */
public class Keywords {
	private static ThreadLocal<WebDriver> WD = new ThreadLocal<>();
	private static final Logger LOGGER = Logger.getLogger(Keywords.class.getName());

	public static void setDriver(WebDriver driver) {
		WD.set(driver);
	}

	public WebDriver getDriver() {
		return WD.get();
	}

	/**
	 * Constructor to initialize the WebDriver based on the specified browser.
	 * 
	 * @param browser The name of the browser to initialize (e.g., "Mozilla
	 *                Firefox", "Google Chrome").
	 */
	public Keywords(String browser) {
		WebDriver driver = null;
		if (browser.equals("Mozilla Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("Google Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--ignore-certificate-errors");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		setDriver(driver);
	}

	/**
	 * Default constructor.
	 */
	public Keywords() {
	}

	/**
	 * Finds a web element based on the specified locator type and value.
	 * 
	 * @param locname  The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue The value of the locator.
	 * @return The located WebElement, or null if not found.
	 */
	public WebElement getElement(String locname, String locvalue) {
		WebElement elm = null;
		try {
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			if (locname.equalsIgnoreCase("id"))
				elm = getDriver().findElement(By.id(locvalue));
			else if (locname.equalsIgnoreCase("name"))
				elm = getDriver().findElement(By.name(locvalue));
			else if (locname.equalsIgnoreCase("className"))
				elm = getDriver().findElement(By.className(locvalue));
			else if (locname.equalsIgnoreCase("linkText"))
				elm = getDriver().findElement(By.linkText(locvalue));
			else if (locname.equalsIgnoreCase("partialLinkText"))
				elm = getDriver().findElement(By.partialLinkText(locvalue));
			else if (locname.equalsIgnoreCase("xpath"))
				elm = getDriver().findElement(By.xpath(locvalue));
			else if (locname.equalsIgnoreCase("cssSelector"))
				elm = getDriver().findElement(By.cssSelector(locvalue));
			else if (locname.equalsIgnoreCase("tagname"))
				elm = getDriver().findElement(By.tagName(locvalue));
			else
				LOGGER.warning("Invalid locator name: " + locname);
		} catch (Exception e) {
			LOGGER.warning("Error finding element with locator: " + locname + " and value: " + locvalue);
		}
		return (elm);
	}

	public WebElement getSpecificElementFromMultipleElement(String locname, String locvalue, int index) {
		index = index - 1; // Adjust index to be zero-based
		WebElement elm = null;
		try {
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			if (locname.equalsIgnoreCase("id"))
				elm = getDriver().findElements(By.id(locvalue)).get(index);
			else if (locname.equalsIgnoreCase("name"))
				elm = getDriver().findElements(By.name(locvalue)).get(index);
			else if (locname.equalsIgnoreCase("className"))
				elm = getDriver().findElements(By.className(locvalue)).get(index);
			else if (locname.equalsIgnoreCase("linkText"))
				elm = getDriver().findElements(By.linkText(locvalue)).get(index);
			else if (locname.equalsIgnoreCase("partialLinkText"))
				elm = getDriver().findElements(By.partialLinkText(locvalue)).get(index);
			else if (locname.equalsIgnoreCase("xpath"))
				elm = getDriver().findElements(By.xpath(locvalue)).get(index);
			else if (locname.equalsIgnoreCase("cssSelector"))
				elm = getDriver().findElements(By.cssSelector(locvalue)).get(index);
			else if (locname.equalsIgnoreCase("tagname"))
				elm = getDriver().findElements(By.tagName(locvalue)).get(index);
			else
				LOGGER.warning("Invalid locator name: " + locname);
		} catch (Exception e) {
			LOGGER.warning("Error finding element with locator: " + locname + " and value: " + locvalue);
		}
		return (elm);
	}

	/**
	 * Clicks on a web element identified by the specified locator type and value.
	 * 
	 * @param locaname The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue The value of the locator.
	 * @return True if the click action was successful, false otherwise.
	 */
	public boolean clickElm(String locaname, String locvalue) {
		boolean bStatus = false;
		try {
			WebElement elm = getElement(locaname, locvalue);
			elm.click();
			bStatus = true;
			LOGGER.info("Clicked element with locator: " + locaname + " and value: " + locvalue);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error clicking element with locator: " + locaname + " and value: " + locvalue, e);
		}
		return (bStatus);
	}

	/**
	 * Verifies if a web element identified by the specified locator type and value
	 * is visible on the page.
	 *
	 * @param locaname The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue The value of the locator.
	 * @return True if the element is visible, false otherwise.
	 */
	public boolean elementIsVisible(String locaname, String locvalue) {
		boolean bStatus = false;
		try {
			WebElement elm = getElement(locaname, locvalue);
			elm.isDisplayed();
			bStatus = true;
			LOGGER.info("Element displayed with locator: " + locaname + " and value: " + locvalue);
		} catch (Exception e) {
			LOGGER.warning("Element is not displayed with locator: " + locaname + " and value: " + locvalue);
		}
		return (bStatus);
	}

	/**
	 * Enters the specified text into a text field identified by the locator type
	 * and value.
	 * 
	 * @param locaname The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue The value of the locator.
	 * @param txt      The text to enter into the text field.
	 * @return True if the text was entered successfully, false otherwise.
	 */
	public boolean enterText(String locaname, String locvalue, String txt) {
		boolean bStatus = false;
		try {
			WebElement elm = getElement(locaname, locvalue);
			elm.clear();
			elm.sendKeys(txt);
			bStatus = true;
			LOGGER.info("Entered text: " + txt + " into element with locator: " + locaname + " and value: " + locvalue);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Error entering text into element with locator: " + locaname + " and value: " + locvalue, e);
		}
		return (bStatus);
	}

	/**
	 * Verifies if the text of a web element matches the expected result.
	 *
	 * @param locaname       The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue       The value of the locator.
	 * @param expectedResult The expected text of the web element.
	 * @return True if the text matches the expected result, false otherwise.
	 */
	public boolean verifyText(String locaname, String locvalue, String expectedResult) {
		try {
			WebElement elm = getElement(locaname, locvalue);
			String sActResult = elm.getText();
			Assert.assertEquals(sActResult, expectedResult);
			LOGGER.info("Verified text with locator: " + locaname + " and value: " + locvalue
					+ " with expected result: " + expectedResult);
			return true;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error verifying text with locator: " + locaname + " and value: " + locvalue, e);
			return false;
		}
	}

	/**
	 * Verifies the presence of an element based on a dynamic XPath and value.
	 * 
	 * @param xpathTemplate The XPath template with a placeholder for the dynamic
	 *                      value.
	 * @param value         The dynamic value to be inserted into the XPath.
	 * @return True if the element is found, false otherwise.
	 */
	public boolean verifyElementWithDynamicValue(String xpathTemplate, String value) {
		String dynamicXPath = xpathTemplate.replace("{value}", value);
		return verifyElement("xpath", dynamicXPath);
	}

	/**
	 * Verifies if a web element identified by the specified locator type and value
	 * is displayed on the page.
	 *
	 * @param locaname The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue The value of the locator.
	 * @return True if the element is displayed, false otherwise.
	 */
	private boolean verifyElement(String locaname, String locvalue) {
		try {
			return getElement(locaname, locvalue).isDisplayed();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error verifying element with locator: " + locaname + " and value: " + locvalue,
					e);
			return false;
		}
	}

	/**
	 * Captures a screenshot of the current browser window and saves it to the
	 * specified file.
	 *
	 * @param fileName The name of the file to save the screenshot.
	 */
	public void screenShot(String fileName) {
		TakesScreenshot t = (TakesScreenshot) WD;
		File capture = t.getScreenshotAs(OutputType.FILE);
		File save = new File("./ScreenShots/" + fileName + ".jpg");
		try {
			FileUtils.copyFile(capture, save);
			LOGGER.info("Screenshot captured and saved as: " + fileName + ".jpg");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error capturing screenshot: " + e.getMessage(), e);
		}
	}

	/**
	 * Reads data from an Excel sheet using the Apache POI library.
	 *
	 * @param path  The file path of the Excel sheet.
	 * @param Sheet The name of the sheet to read from.
	 * @param r     The row number of the cell.
	 * @param c     The column number of the cell.
	 * @return The data from the specified cell as a String.
	 */
	public static String getData(String path, String Sheet, int r, int c) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "File not found: " + path, e);
		}
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(fis);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error creating workbook from file: " + path, e);
		}
		String data = null;
		try {
			data = wb.getSheet(Sheet).getRow(r).getCell(c).getStringCellValue();
		} catch (NullPointerException e) {
			LOGGER.log(Level.SEVERE, "Cell data is null. Please check the sheet, row, or column." + e.getMessage());
		} catch (IllegalStateException e) {
			LOGGER.log(Level.SEVERE, "Cell data is not of type String. Please verify the cell type." + e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An unexpected error occurred while reading cell data: " + e.getMessage(), e);
		}
		return data;
	}
}