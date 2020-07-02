package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
		Assert.assertTrue(objLoginPage.date_Visibility.isDisplayed(), "date must be displayed");
		System.out.println("date object displayed");

	}

	// Verify that image of Aruba is displayed under featured destination
	@Test(groups = { "Travel" })
	public void loginPage_test_02_VerifyAruba() throws Exception {
		System.out.println("verify date  method executed");
		Utilities.explicitWaiting(objLoginPage.date_Visibility, driver);
		Assert.assertTrue(objLoginPage.Aruba_Visibility.isDisplayed(), "Aruba must be displayed");
		System.out.println("aruba object displayed");

	}

	
	// Verify that when user enters incorrect username and password then following
	// section should be displayed.
	@Test(groups= {"Travel"})
	public void LoginPage_test03_failedPassword() throws Exception {
		
		objLoginPage.userName.sendKeys(Utilities.configProperties("false_username"));
		objLoginPage.password.sendKeys(Utilities.configProperties("false_password"));
		objLoginPage.signIn_Button.click();
		
		Utilities.explicitWaiting(objLoginPage.SignOn_Visibility , driver);
		Assert.assertTrue(objLoginPage.SignOn_Visibility.isDisplayed(),"sign on section must be displayed if password fails");
		System.out.println("password failed successul");
	
	}
	
	//Successful login
	@Test(groups= {"Travel"})
	public void LoginPage_test04_successfulLogin() throws Exception {
		
		objLoginPage.userName.sendKeys(Utilities.configProperties("username"));
		objLoginPage.password.sendKeys(Utilities.configProperties("password"));
		objLoginPage.signIn_Button.click();
		
		Utilities.explicitWaiting(objLoginPage.continue_Button , driver);
		Assert.assertTrue(objLoginPage.continue_Button.isDisplayed(), "continue button must be displayed for successful login");
		
	}
	
	@AfterMethod(groups = { "Travel" })
	public void afterMethod() throws IOException, InterruptedException {
		System.out.println("after method executed");
		driver.quit();
	}
	
}
