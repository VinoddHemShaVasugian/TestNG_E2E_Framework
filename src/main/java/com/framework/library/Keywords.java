package com.framework.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
			LOGGER.log(Level.SEVERE, "Error finding element with locator: " + locname + " and value: " + locvalue, e);
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
	 * Selects an item from a drop down menu by visible text.
	 * 
	 * @param locaname The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue The value of the locator.
	 * @param itm      The visible text of the item to select.
	 * @return True if the item was selected successfully, false otherwise.
	 */
	public boolean selectItem(String locaname, String locvalue, String itm) {
		boolean bStatus = false;
		Select itms = null;
		try {
			WebElement elm = getElement(locaname, locvalue);
			itms = new Select(elm);
			itms.selectByVisibleText(itm);
			bStatus = true;
			LOGGER.info(
					"Selected item: " + itm + " from dropdown with locator: " + locaname + " and value: " + locvalue);
		} catch (Exception e) {
			itms.selectByValue(itm);
		}
		return (bStatus);
	}

	/**
	 * Checks or unchecks a checkbox based on the specified value.
	 * 
	 * @param locaname The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue The value of the locator.
	 * @param val      1 to check the checkbox, 0 to uncheck it.
	 * @return True if the action was successful, false otherwise.
	 */
	public boolean setElm(String locaname, String locvalue, int val) {
		boolean bStatus = false;
		try {
			WebElement elm = getElement(locaname, locvalue);
			if (val == 1) {
				if (!elm.isSelected())
					elm.click();
				bStatus = true;
				LOGGER.info("Checked element with locator: " + locaname + " and value: " + locvalue);
			} else if (val == 0) {
				if (elm.isSelected())
					elm.click();
				bStatus = true;
				LOGGER.info("Unchecked element with locator: " + locaname + " and value: " + locvalue);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Error checking/unchecking element with locator: " + locaname + " and value: " + locvalue, e);
		}
		return (bStatus);
	}

	/**
	 * Verifies if a checkbox is selected and matches the expected result.
	 *
	 * @param locaname       The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue       The value of the locator.
	 * @param expectedResult The expected text of the selected checkbox.
	 * @return True if the checkbox is selected and matches the expected result,
	 *         false otherwise.
	 */
	public boolean chkElmSelected(String locaname, String locvalue, String expectedResult) {
		boolean bStatus = false;
		try {
			WebElement elm = getElement(locaname, locvalue);
			String sActResult = elm.getText();
			if (elm.isSelected())
				Assert.assertEquals(sActResult, expectedResult);
			LOGGER.info("Checkbox with locator: " + locaname + " and value: " + locvalue
					+ " is selected and verified with expected result: " + expectedResult);
			bStatus = true;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Error verifying checkbox selection with locator: " + locaname + " and value: " + locvalue, e);
		}
		return (bStatus);
	}

	/**
	 * Switches the WebDriver context to a frame identified by the specified
	 * locator.
	 *
	 * @param locaname The type of locator (e.g., "id", "name", "xpath").
	 * @param locvalue The value of the locator.
	 */
	public void switchToFrame(String locaname, String locvalue) {
		WebElement frm = getElement(locaname, locvalue);
		getDriver().switchTo().frame(frm);
		LOGGER.info("Switched to frame with locator: " + locaname + " and value: " + locvalue);
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
	 * Handles and accepts a JavaScript alert if present.
	 *
	 * @return True if the alert was successfully accepted, false otherwise.
	 */
	public boolean acceptAlert() {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		try {
			Alert alt = wait.until(ExpectedConditions.alertIsPresent());
			alt = getDriver().switchTo().alert();
			alt.accept();
			LOGGER.info("Alert accepted successfully.");
			return true;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error accepting alert: " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Verifies if the current page title matches the expected result.
	 *
	 * @param expectedResult The expected page title.
	 */
	public void verifyPageTitle(String expectedResult) {
		String pageTitle = getDriver().getTitle();
		Assert.assertEquals(pageTitle, expectedResult);
		LOGGER.info("Page title verified: " + pageTitle + " matches expected result: " + expectedResult);
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