package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

		Utilities.explicitWaiting(objSelectFlightPage.flightDetails_Return, driver);

		String test_Value1 = objSelectFlightPage.ongoing_RaidioButtons.get(0).getAttribute("value");
		System.out.println(test_Value1);

		// getting list size
		int ongoing_RadioBtn_Size = objSelectFlightPage.ongoing_RaidioButtons.size();
		int returning_RadioBtn_Size = objSelectFlightPage.returning_RaidioButtons.size();

		// initilaizing local variable for testing
		ArrayList<Integer> radio_List1 = new ArrayList<Integer>();
		ArrayList<Integer> radio_List2 = new ArrayList<Integer>();
		int ongoing_TestInt[] = new int[ongoing_RadioBtn_Size];
		int return_TestInt[] = new int[returning_RadioBtn_Size];

		String ongoing_radio_Text[] = new String[ongoing_RadioBtn_Size];
		String Split_ongoing_radio_Text[] = new String[ongoing_RadioBtn_Size];

		String return_radio_Text[] = new String[returning_RadioBtn_Size];
		String Split_return_radio_Text[] = new String[returning_RadioBtn_Size];

		// running nested loop if any ongoing radio button is selected then split
		// selected and save data in file

		for (int i = 0; i < ongoing_RadioBtn_Size; i++) {
			ongoing_radio_Text[i] = objSelectFlightPage.ongoing_RaidioButtons.get(i).getAttribute("value");
			Boolean radio_Status = objSelectFlightPage.ongoing_RaidioButtons.get(i).isSelected();
			// System.out.println(ongoing_radio_Text[i]);
			// System.out.println(radio_Status);
			String String_radio_Status = String.valueOf(radio_Status);
			// System.out.println(String_radio_Status);
			if ((String_radio_Status.equalsIgnoreCase("true"))) {
				for (int j = 0; j < 1; j++) {

					Split_ongoing_radio_Text = ongoing_radio_Text[i].split("\\$");
					ongoing_TestInt[i] = Integer.parseInt(Split_ongoing_radio_Text[1]);
					radio_List1.add(ongoing_TestInt[i]);

					/*
					 * File radioFile = new File(System.getProperty("user.dir") +
					 * "\\TestWorkbook.xlsx"); FileInputStream fis = new FileInputStream(radioFile);
					 * XSSFWorkbook myWorkbook = new XSSFWorkbook(fis);
					 * 
					 * XSSFSheet mysheet = myWorkbook.getSheet("Sheet1"); Row newRow =
					 * mysheet.getRow(0); Cell newCell = newRow.createCell(1);
					 * newCell.setCellValue(ongoing_TestInt[i]); fis.close();
					 */
				}

			}

		}

		System.out.println(radio_List1);
		// running nested loop if any returning radio button is selected then split
		// selected and save data in file

		for (int i = 0; i < returning_RadioBtn_Size; i++) {
			return_radio_Text[i] = objSelectFlightPage.returning_RaidioButtons.get(i).getAttribute("value");
			Boolean radio_Status1 = objSelectFlightPage.returning_RaidioButtons.get(i).isSelected();
			
			String String_radio_Status1 = String.valueOf(radio_Status1);
			System.out.println(String_radio_Status1);
			if ((String_radio_Status1.equalsIgnoreCase("true"))) {
				for (int j = 0; j < 1; j++) {

					Split_return_radio_Text = return_radio_Text[i].split("\\$");
					return_TestInt[i] = Integer.parseInt(Split_return_radio_Text[1]);
					radio_List2.add(ongoing_TestInt[i]);

					/*
					 * File radioFile = new File(System.getProperty("user.dir") +
					 * "\\TestWorkbook.xlsx"); FileInputStream fis = new FileInputStream(radioFile);
					 * XSSFWorkbook myWorkbook = new XSSFWorkbook(fis);
					 * 
					 * XSSFSheet mysheet = myWorkbook.getSheet("Sheet1"); Row newRow =
					 * mysheet.getRow(0); Cell newCell = newRow.createCell(1);
					 * newCell.setCellValue(ongoing_TestInt[i]); fis.close();
					 */
				}

			}

		}

		System.out.println(radio_List2);

		// continue to book flight page
		objSelectFlightPage.continue_Button.click();

		// explicit wait
		Utilities.explicitWaiting(objBook_A_FlightPage.BookingFlight_Ongoig, driver);
		// explicit wait
		Utilities.explicitWaiting(objBook_A_FlightPage.BookingFlight_Return, driver);

		// Retrieving text and splitting it to get flight no
		String ongoingFlight_Text = objBook_A_FlightPage.BookingFlight_Ongoig.getText();
		String returningFlight_Text = objBook_A_FlightPage.BookingFlight_Return.getText();

		String[] Split_ongoingFlight_Text = ongoingFlight_Text.split(" ");
		String[] Split_returningFlight_Text = returningFlight_Text.split(" ");
		String final_ongoing_flightText = Split_ongoingFlight_Text[3];
		String final_returning_flightText = Split_returningFlight_Text[3];
		System.out.println(final_ongoing_flightText);
		System.out.println(final_returning_flightText);
		// int final_ongoing_flightNo = Integer.parseInt(final_ongoing_flightText);
		// int final_returning_flightNo = Integer.parseInt(final_returning_flightText);

		// checking if flight selected is equal to flight displayed for ongoing and return
		System.out.println(Split_return_radio_Text[1]);
		System.out.println(Split_return_radio_Text[1]);

		Assert.assertTrue(final_ongoing_flightText.equalsIgnoreCase(Split_ongoing_radio_Text[1]),
				"ongoing flight no. seleced and booked must be equal ");
		Assert.assertTrue(final_returning_flightText.equalsIgnoreCase(Split_return_radio_Text[1]),
				"returning flight no. seleced and booked must be equal ");

	}

	@AfterMethod(groups = { "Travel" })
	public void afterMethod() throws IOException, InterruptedException {
		System.out.println("after method executed");
		driver.quit();
	}

}
