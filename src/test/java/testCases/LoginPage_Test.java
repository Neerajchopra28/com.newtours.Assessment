package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utility.Utilities;

public class LoginPage_Test {
	WebDriver driver;
	LoginPage objLoginPage;

	@BeforeMethod(groups = { "Travel" })
	public void beforeMethod() throws Exception {
		System.out.println(Utilities.configProperties("url"));
		driver = Utilities.startBrowser("chrome", Utilities.configProperties("url"));

		objLoginPage = PageFactory.initElements(driver, LoginPage.class);
		System.out.println("Before method executed");
	}

	// verify that current date is displayed on top section.
	@Test(groups = { "Travel" })
	public void loginPage_test_01_VerifyDate() throws Exception {
		System.out.println("verify date  method executed");
		Utilities.explicitWaiting(objLoginPage.date_Visibility, driver);

		// taking date as string from website page
		String PageDate = objLoginPage.date_Visibility.getText();
		System.out.println("Date is = " + PageDate);
		// using Date method to find date and format it
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMM d, YYYY");

		// converting date to string using format() method
		String String_SystemDate = formatter.format(date);

		System.out.println("Date is = " + String_SystemDate);

		Assert.assertTrue(PageDate.equalsIgnoreCase(String_SystemDate), "Page date and string date must be equal");

	}

	// Verify that image of Aruba is displayed under featured destination
	@Test(groups = { "Travel" })
	public void loginPage_test_02_VerifyAruba() throws Exception {
		System.out.println("verify date  method executed");
		Utilities.explicitWaiting(objLoginPage.Aruba_Visibility, driver);
		Assert.assertTrue(objLoginPage.Aruba_Visibility.isDisplayed(), "Aruba must be displayed");
		System.out.println("aruba object displayed");

	}

	// Verify that when user enters incorrect username and password then following
	// section should be displayed.
	@Test(dataProvider = "TravelData", groups = { "Travel" })
	public void LoginPage_test03_failedPassword(String userName, String password) throws Exception {
		System.out.println(userName);
		System.out.println(password);
		objLoginPage.userName.sendKeys(userName);
		objLoginPage.password.sendKeys(password);
		objLoginPage.signIn_Button.click();
		Thread.sleep(5000);
		Utilities.explicitWaiting(objLoginPage.SignOn_Visibility, driver);
		Assert.assertTrue(objLoginPage.SignOn_Visibility.isDisplayed(),
				"sign on section must be displayed if password fails");
		System.out.println("password failed successul");

	}

	// Successful login
	@Test(groups = { "Travel" })
	public void LoginPage_test04_successfulLogin() throws Exception {

		objLoginPage.userName.sendKeys(Utilities.configProperties("username"));
		objLoginPage.password.sendKeys(Utilities.configProperties("password"));
		objLoginPage.signIn_Button.click();

		Utilities.explicitWaiting(objLoginPage.continue_Button, driver);
		Assert.assertTrue(objLoginPage.continue_Button.isDisplayed(),
				"continue button must be displayed for successful login");

	}

	@AfterMethod(groups = { "Travel" })
	public void afterMethod() throws IOException, InterruptedException {
		System.out.println("after method executed");
		driver.quit();
	}

	// data provider of login page
	public static Object[][] TestDataPassing() throws IOException {
		String arr[][] = null;
		File file = new File(System.getProperty("user.dir") + "\\TestWorkbook.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook myWorkbook = new XSSFWorkbook(fis);

		XSSFSheet mysheet = myWorkbook.getSheet("Sheet3");
		int totalRows = mysheet.getLastRowNum() - mysheet.getFirstRowNum();
		totalRows++;
		int totalColumn = mysheet.getRow(0).getLastCellNum() - mysheet.getRow(0).getFirstCellNum();
		arr = new String[totalRows][totalColumn];

		for (int i = mysheet.getFirstRowNum(); i <= mysheet.getLastRowNum(); i++) {
			Row myRow = mysheet.getRow(i);

			for (int j = myRow.getFirstCellNum(); j < myRow.getLastCellNum(); j++) {
				arr[i][j] = myRow.getCell(j).getStringCellValue();
			}
		}
		System.out.println("before returning" + arr);
		return arr;

	}

	@DataProvider(name = "TravelData")
	public Object[][] TestDataProvider() throws Exception {

		Object testObjectArray[][] = TestDataPassing();

		return testObjectArray;
	}

}
