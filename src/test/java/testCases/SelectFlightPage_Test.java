package testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.SearchFlightPage;
import pageObjects.SelectFlightPage;
import utility.Utilities;

public class SelectFlightPage_Test {

	WebDriver driver;
	LoginPage objLoginPage;
	SearchFlightPage objSearchFlightPage;
	SelectFlightPage objSelectFlightPage;

	@BeforeTest(groups = { "Travel" })
	public void BeforeMethod() throws Exception {
		driver = Utilities.startBrowser("chrome", Utilities.configProperties("url"));

		objSearchFlightPage = PageFactory.initElements(driver, SearchFlightPage.class);
		objLoginPage = PageFactory.initElements(driver, LoginPage.class);
		objSelectFlightPage = PageFactory.initElements(driver, SelectFlightPage.class);

		System.out.println("Before method Executed");
	}

	@Test(groups = { "Travel" })
	public void Flight_SortCheck() throws Exception {

		// Login website
		objLoginPage.userName.sendKeys("mercury");
		objLoginPage.password.sendKeys("mercury");
		objLoginPage.signIn_Button.click();

		// press continue in search flight page
		objSearchFlightPage.continue_Button.click();

		// initilaizing local variable for testing
		ArrayList<Integer> Test_List1 = new ArrayList<Integer>();
		ArrayList<Integer> Sorted_Test_List2 = new ArrayList<Integer>();
		int TestInt[] = new int[4];

		// Eplicit wait
		Utilities.explicitWaiting(objSelectFlightPage.flightDepart_01, driver);

		// getting list size
		int listSize = objSelectFlightPage.Flight_Prices.size();
		String flight_Text[] = new String[listSize];
		String Split_flight_Text[] = new String[listSize];
		// running nested loop for splitting each element coverig into integer and
		// adding into list's
		for (int i = 0; i < listSize - 4; i++) {
			flight_Text[i] = objSelectFlightPage.Flight_Prices.get(i).getText();
			for (int j = 0; j < 1; j++) {

				Split_flight_Text = flight_Text[i].split("\\$");
				// System.out.println(flight_Text[i]);
				// System.out.println(Split_flight_Text[i]);
				TestInt[i] = Integer.parseInt(Split_flight_Text[1]);
				Test_List1.add(TestInt[i]);
				Sorted_Test_List2.add(TestInt[i]);
			}

		}
		// sorting one list
		System.out.println(Test_List1);
		System.out.println(Sorted_Test_List2);
		Collections.sort(Sorted_Test_List2);
		// condition for testing
		Assert.assertTrue(Test_List1.equals(Sorted_Test_List2), "price of flights must be in sorted order");

		/*
		 * String S1 = objSelectFlightPage.flightDepart_01.getText();
		 * System.out.println(S1); String S2 =
		 * objSelectFlightPage.flightDepart_02.getText(); System.out.println(S2); String
		 * S3 = objSelectFlightPage.flightDepart_03.getText(); System.out.println(S3);
		 * String S4 = objSelectFlightPage.flightDepart_04.getText();
		 * System.out.println(S4);
		 * 
		 * System.out.println(); System.out.println(); // splitting text String[]
		 * SplitS1 = S1.split("\\$"); System.out.println(SplitS1[1]); String[] SplitS2 =
		 * S2.split("\\$"); System.out.println(SplitS2[1]); String[] SplitS3 =
		 * S3.split("\\$"); System.out.println(SplitS3[1]); String[] SplitS4 =
		 * S4.split("\\$"); System.out.println(SplitS4[1]);
		 * 
		 * // converting String to Integer int arrInt[] = new int[4]; arrInt[0] =
		 * Integer.parseInt(SplitS1[1]); arrInt[1] = Integer.parseInt(SplitS2[1]);
		 * arrInt[2] = Integer.parseInt(SplitS3[1]); arrInt[3] =
		 * Integer.parseInt(SplitS4[1]);
		 * 
		 * // Adding Integer array elements in List1 and List 2 for (int i = 0; i <
		 * 4;i++) { List1.add(arrInt[i]); List2.add(arrInt[i]);
		 * 
		 * } System.out.println(List1); System.out.println(List2); // Sorting list
		 * Collections.sort(List1); // checking if both are same or not ?
		 * 
		 * if (List1.equals(List2)) {
		 * System.out.println("fight in sorted order function successful"); } else {
		 * System.out.println("fight in in unsorted order function failed");
		 * 
		 * } Assert.assertTrue(List1.equals(List2), "flight must be sorted");
		 */
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
			FileUtils.copyFile(file, new File("C:\\Users\\Welcome\\EclipseNewWorkspace\\com.newtours.Assessment\\fail screenshots\\" + "failedMethod_of_SelectFlightPage" + " " + timestamp + ".jpg"));
		}
		System.out.println("after method executed");
		driver.quit();
	}
}
