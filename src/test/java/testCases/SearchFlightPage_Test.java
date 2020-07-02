package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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
	@Test(priority = 1, groups = { "Travel" })
	public void searchFlightPage_test_01_selectItinerary() throws Exception {
		//Login page
		objLoginPage.userName.sendKeys(Utilities.configProperties("username"));
		objLoginPage.password.sendKeys(Utilities.configProperties("password"));
		objLoginPage.signIn_Button.click();
		
		//Explicit Wait
		Utilities.explicitWaiting(objSearchFlightPage.departingFrom_drpdown, driver);
		
		Select drpDown1 = new Select(objSearchFlightPage.departingFrom_drpdown);
		drpDown1.selectByIndex(5);
		
		Assert.assertTrue(objSearchFlightPage.departingFrom_drpdown.isDisplayed(),
				"drparting from dropdowm must be present");
		Select drpDown2 = new Select(objSearchFlightPage.ArrivingIn_drpdown);
		drpDown2.selectByIndex(5);
		Assert.assertTrue(objSearchFlightPage.ArrivingIn_drpdown.isDisplayed(),
				"  arriving in dropdowm must be present");
		System.out.println("Flight test passed");

	}

	// below method will check radio buttons
	@Test(priority = 2, groups = { "Travel" })
	public void searchFlightPage_test_02_selectServiceClass() throws Exception {
		objLoginPage.userName.sendKeys("mercury");
		objLoginPage.password.sendKeys("mercury");
		objLoginPage.signIn_Button.click();

		Utilities.explicitWaiting(objSearchFlightPage.economyClass_radioBtn, driver);
		Assert.assertTrue(objSearchFlightPage.economyClass_radioBtn.isDisplayed(),
				"radio button must be displayed in page ");
		objSearchFlightPage.economyClass_radioBtn.click();
		Utilities.explicitWaiting(objSearchFlightPage.businessClass_radioBtn, driver);
		Assert.assertTrue(objSearchFlightPage.businessClass_radioBtn.isDisplayed(),
				"radio button must be displayed in page ");
		objSearchFlightPage.businessClass_radioBtn.click();
		Utilities.explicitWaiting(objSearchFlightPage.firstClass_radioBtn, driver);
		Assert.assertTrue(objSearchFlightPage.firstClass_radioBtn.isDisplayed(),
				"radio button must be displayed in page ");
		objSearchFlightPage.firstClass_radioBtn.click();
		System.out.println("Radio buttons are wrking");
	}

	@AfterMethod(groups = { "Travel" })
	public void afterMethod() throws IOException, InterruptedException {
		System.out.println("after method executed");
		driver.quit();
	}

}
