package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.Book_A_FlightPage;
import pageObjects.LoginPage;
import pageObjects.SearchFlightPage;
import pageObjects.SelectFlightPage;
import utility.Utilities;

public class Book_A_FlightPage_Test {
	WebDriver driver;
	LoginPage objLoginPage;
	SearchFlightPage objSearchFlightPage;
	SelectFlightPage objSelectFlightPage;
	Book_A_FlightPage objBook_A_FlightPage;

	@BeforeTest(groups = { "Travel" })
	public void beforeMethod() {
		driver = Utilities.startBrowser("chrome", "http://newtours.demoaut.com/");
		objLoginPage = PageFactory.initElements(driver, LoginPage.class);
		objSearchFlightPage = PageFactory.initElements(driver, SearchFlightPage.class);
		objSelectFlightPage = PageFactory.initElements(driver, SelectFlightPage.class);
		objBook_A_FlightPage = PageFactory.initElements(driver, Book_A_FlightPage.class);

		System.out.println("Before method Executed");

	}

	@Test(groups = { "Travel" })
	public void verifyFlightDescription() throws Exception {

		// Login website
		objLoginPage.userName.sendKeys("mercury");
		objLoginPage.password.sendKeys("mercury");
		objLoginPage.signIn_Button.click();

		// press continue in search flight page
		driver.findElement(By.xpath("//input[@name='findFlights']")).click();

		// continue to book flight page
		Utilities.explicitWaiting(objSelectFlightPage.flightDetails_Return, driver);
		Utilities.explicitWaiting(objSelectFlightPage.flightDetails_Ongoing, driver);
		String TempVariable2 = objSelectFlightPage.flightDetails_Ongoing.getText();
		String TempVariable4 = objSelectFlightPage.flightDetails_Return.getText();
		driver.findElement(By.xpath("//input[@name='reserveFlights']")).click();

		// explicit wait
		Utilities.explicitWaiting(objBook_A_FlightPage.BookingFlight_Ongoig, driver);
		// explicit wait
		Utilities.explicitWaiting(objBook_A_FlightPage.BookingFlight_Return, driver);
		// checking if flight selected is equal to flight displayed for ongoing flight
		String TempVariable1 = objBook_A_FlightPage.BookingFlight_Ongoig.getText();
		
		Assert.assertTrue(TempVariable1.equals(TempVariable2),
				" ongoing Flight selected and ongoing Flight displayed must be equal ");

		// checking if flight selected is equal to flight displayed for Return flight
		// System.out.println(objBook_A_FlightPage.BookingFlight_Return.getText());
		System.out.println("Ongoing flight selected =" + TempVariable2);
		System.out.println("Ongoing flight booked =" + TempVariable1);

		

		String TempVariable3 = objBook_A_FlightPage.BookingFlight_Return.getText();
		
		
		System.out.println("Return flight selected =" + TempVariable4);
		System.out.println("Return flight booked =" + TempVariable3);
		
		Assert.assertTrue(TempVariable3.equals(TempVariable4),
				"return flight selected and return fight displayed must be equal");

		System.out.println("Displayed flight detaild verified");
	}

	@AfterMethod(groups = { "Travel" })
	public void afterMethod() throws IOException, InterruptedException {
		System.out.println("after method executed");
		driver.quit();
	}

}
