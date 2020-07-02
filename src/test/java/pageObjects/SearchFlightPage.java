package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchFlightPage {
	WebDriver driver;

	// class constructor
	public SearchFlightPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Departing from dropdown button
	@FindBy(how = How.XPATH, using = "//select[@name='fromPort']")
	public WebElement departingFrom_drpdown;

	// Arriving in dropdown button
	@FindBy(how = How.XPATH, using = "//select[@name='toPort']")
	public WebElement ArrivingIn_drpdown;

	// service class radio button economy class
	@FindBy(how = How.XPATH, using = "//input[@name='servClass' and @Value ='Coach']")
	public WebElement economyClass_radioBtn;

	// service class radio button Business class
	@FindBy(how = How.XPATH, using = "//input[@name='servClass' and @Value ='Business']")
	public WebElement businessClass_radioBtn;

	// service class radio button first class
	@FindBy(how = How.XPATH, using = "//input[@name='servClass' and @Value ='First']")
	public WebElement firstClass_radioBtn;

}
