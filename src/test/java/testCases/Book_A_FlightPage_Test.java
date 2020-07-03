package testCases;

import java.io.IOException;
import java.util.ArrayList;

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
		objSearchFlightPage.continue_Button.click();

		// continue to book flight page
		Utilities.explicitWaiting(objSelectFlightPage.flightDetails_Return, driver);
		String TempVariable2 = objSelectFlightPage.flightDetails_Ongoing.getText();
		String TempVariable4 = objSelectFlightPage.flightDetails_Return.getText();

		String test_Value1 = objSelectFlightPage.ongoing_RaidioButtons.get(0).getAttribute("value");
		System.out.println(test_Value1);


		// getting list size
		int ongoing_RadioBtn_Size = objSelectFlightPage.ongoing_RaidioButtons.size();
		int returning_RadioBtn_Size = objSelectFlightPage.returning_RaidioButtons.size();

	
		// initilaizing local variable for testing
		ArrayList<Integer> radio_List1 = new ArrayList<Integer>();
		ArrayList<Integer> Sorted_radio_List2 = new ArrayList<Integer>();
		int ongoing_TestInt[] = new int[ongoing_RadioBtn_Size];
		int return_TestInt[] = new int[returning_RadioBtn_Size];
		
		
		String ongoing_radio_Text[] = new String[ongoing_RadioBtn_Size];
		String Split_ongoing_radio_Text[] = new String[ongoing_RadioBtn_Size];

		// running nested loop if any radio button is selected then split selected and
		// compare with flight name of another
		for (int i = 0; i < ongoing_RadioBtn_Size; i++) {
			ongoing_radio_Text[i] = objSelectFlightPage.Flight_Prices.get(i).getText();
			for (int j = 0; j < 1; j++) {

				Split_ongoing_radio_Text = ongoing_radio_Text[i].split("\\$");
				// System.out.println(flight_Text[i]);
				// System.out.println(Split_flight_Text[i]);
				ongoing_TestInt[i] = Integer.parseInt(Split_ongoing_radio_Text[1]);
				Test_List1.add(ongoing_TestInt[i]);
				Sorted_Test_List2.add(TestInt[i]);
			}

		}

		/*
		 * objSelectFlightPage.continue_Button.click();
		 * 
		 * 
		 * 
		 * // explicit wait
		 * Utilities.explicitWaiting(objBook_A_FlightPage.BookingFlight_Ongoig, driver);
		 * // explicit wait
		 * Utilities.explicitWaiting(objBook_A_FlightPage.BookingFlight_Return, driver);
		 * 
		 * // checking if flight selected is equal to flight displayed for ongoing
		 * flight String TempVariable1 =
		 * objBook_A_FlightPage.BookingFlight_Ongoig.getText();
		 * Assert.assertTrue(TempVariable1.equals(TempVariable2),
		 * " ongoing Flight selected and ongoing Flight displayed must be equal ");
		 * 
		 * // checking if flight selected is equal to flight displayed for Return flight
		 * String TempVariable3 = objBook_A_FlightPage.BookingFlight_Return.getText();
		 * Assert.assertTrue(TempVariable3.equals(TempVariable4),
		 * "return flight selected and return fight displayed must be equal");
		 * 
		 * System.out.println("Displayed flight detaild verified");
		 */}

	@AfterMethod(groups = { "Travel" })
	public void afterMethod() throws IOException, InterruptedException {
		System.out.println("after method executed");
		driver.quit();
	}

}
