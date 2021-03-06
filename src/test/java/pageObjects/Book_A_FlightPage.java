package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Book_A_FlightPage {
	WebDriver driver;
	// Class Constructor

	public Book_A_FlightPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//ongoing fight name & Number
	@FindBy(how = How.XPATH, using = "//td[@class ='data_left']/font[@face='ARIAL']/b")
	public WebElement BookingFlight_Ongoig;
	
	//Return Flight name & Number
	@FindBy(how = How.XPATH, using = "//td[@class ='data_left']/font/font/font/b")
	public WebElement BookingFlight_Return;


}
