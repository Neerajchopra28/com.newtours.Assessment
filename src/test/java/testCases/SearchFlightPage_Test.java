package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.SearchFlightPage;
import utility.Utilities;

public class SearchFlightPage_Test {
	WebDriver driver;
	LoginPage objLoginPage;
	SearchFlightPage objSearchFlightPage;

	@BeforeMethod(groups = { "Travel" })
	public void beforeMethod() throws Exception {
		driver = Utilities.startBrowser("chrome", Utilities.configProperties("url"));
		objSearchFlightPage = PageFactory.initElements(driver, SearchFlightPage.class);
		objLoginPage = PageFactory.initElements(driver, LoginPage.class);
		System.out.println("before method executed");

	}

	// this method will check departing from and arriving in
	@Test(groups = { "Travel" })
	public void searchFlightPage_test_01_selectItinerary() throws Exception {
		// Login page
		objLoginPage.userName.sendKeys(Utilities.configProperties("username"));
		objLoginPage.password.sendKeys(Utilities.configProperties("password"));
		objLoginPage.signIn_Button.click();

		// Explicit Wait
		Utilities.explicitWaiting(objSearchFlightPage.departingFrom_drpdown, driver);
		/*
		 * File dropdown_Datafile = new File(System.getProperty("user.dir") +
		 * "\\TestWorkbook.xlsx"); //XSSFWorkbook departingWorkbook = new
		 * XSSFWorkbook(dropdown_Datafile); //XSSFSheet departingsheet =
		 * departingWorkbook.getSheet("departingData");
		 * 
		 * //String deprt_drp_Value1 =
		 * departingsheet.getRow(1).getCell(0).getStringCellValue();
		 */

		// checking values of drop box by importing data from excel
		Select deprt_drpDown = new Select(objSearchFlightPage.departingFrom_drpdown);
		deprt_drpDown.selectByValue(Utilities.getDataFromTestDataExcel("fromcity"));
		/*
		 * String test_drpValue1 = deprt_drpDown.getFirstSelectedOption().getText(); //
		 * System.out.println(test_drpValue1); //
		 * Assert.assertTrue(deprt_drp_Value1.equals(test_drpValue1), " dropdown value
		 * // is not selected properly ");
		 * 
		 * Thread.sleep(5000); // checking values of arriving dropdown and imporing data
		 * from excel
		 * 
		 * // File dropdown_Datafile1 = new File(System.getProperty("user.dir") + //
		 * "\\TestWorkbook.xlsx"); // XSSFWorkbook arrivingworkbook = new
		 * XSSFWorkbook(dropdown_Datafile1); // XSSFSheet arrivingsheet =
		 * arrivingworkbook.getSheet("arrivingData");
		 * 
		 * // String arr_drp_Value1 = /
		 * arrivingsheet.getRow(1).getCell(0).getStringCellValue();
		 */
		Select arr_drpDown = new Select(objSearchFlightPage.ArrivingIn_drpdown);
		arr_drpDown.selectByValue(Utilities.getDataFromTestDataExcel("tocity"));
		/* String test_arr_drpValue1 = arr_drpDown.getFirstSelectedOption().getText();
		// System.out.println(test_arr_drpValue1);
		// Assert.assertTrue(arr_drp_Value1.equals(test_arr_drpValue1), " dropdown value
		// is not selected properly ");
		*/
		System.out.println("Flight test passed");

	}

	// below method will check radio buttons
	@Test(priority = 1, groups = { "Travel" })
	public void searchFlightPage_test_02_selectServiceClass() throws Exception {
		objLoginPage.userName.sendKeys(Utilities.configProperties("username"));
		objLoginPage.password.sendKeys(Utilities.configProperties("password"));
		objLoginPage.signIn_Button.click();

		// Explicit wait
		Utilities.explicitWaiting(objSearchFlightPage.economyClass_radioBtn, driver);
		// checking first radio button
		Assert.assertTrue(objSearchFlightPage.economyClass_radioBtn.isDisplayed(),
				"radio button must be displayed in page ");
		objSearchFlightPage.economyClass_radioBtn.click();
		Assert.assertTrue(objSearchFlightPage.economyClass_radioBtn.isSelected(),
				"radio button must be selected in page ");
		Thread.sleep(2000);
		// Explicit wait
		Utilities.explicitWaiting(objSearchFlightPage.businessClass_radioBtn, driver);
		// checking second radio button
		Assert.assertTrue(objSearchFlightPage.businessClass_radioBtn.isDisplayed(),
				"radio button must be displayed in page ");
		objSearchFlightPage.businessClass_radioBtn.click();
		Assert.assertTrue(objSearchFlightPage.businessClass_radioBtn.isSelected(),
				"radio button must be selected in page ");

		Thread.sleep(2000);
		// explicit wait
		Utilities.explicitWaiting(objSearchFlightPage.firstClass_radioBtn, driver);
		// checking third radio button
		Assert.assertTrue(objSearchFlightPage.firstClass_radioBtn.isDisplayed(),
				"radio button must be displayed in page ");
		objSearchFlightPage.firstClass_radioBtn.click();
		Assert.assertTrue(objSearchFlightPage.firstClass_radioBtn.isSelected(),
				"radio button must be selected in page ");
		System.out.println("Radio buttons are working");
	}

	@AfterMethod(groups = { "Travel" })
	public void afterMethod(ITestResult result) throws IOException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {

			System.out.println("Method Failed Check Screenshot for details");
			Thread.sleep(5000);
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());

			// Copy the screenshot on the desire location with different name using current
			// date and time
			FileUtils.copyFile(file,
					new File("C:\\Users\\Welcome\\EclipseNewWorkspace\\com.newtours.Assessment\\fail screenshots\\"
							+ "failedMethod_of_SearchFlightPage" + " " + timestamp + ".jpg"));
		}
		System.out.println("after method executed");
		driver.quit();
	}

}
