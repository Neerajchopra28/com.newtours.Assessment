package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SelectFlightPage {
	WebDriver driver;

	// class Constructor
	public SelectFlightPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Find Elements - price of flight List
	@FindAll(@FindBy(how = How.XPATH, using = "//td[@class ='data_verb_xcols']/font//b"))
	public List<WebElement> Flight_Prices;

	// Flight price description 1
	@FindBy(how = How.XPATH, using = "(//td[@class ='data_verb_xcols']/font//b)[1]")
	public WebElement flightDepart_01;

	// Flight price description 2
	@FindBy(how = How.XPATH, using = "(//td[@class ='data_verb_xcols']/font//b)[2]")
	public WebElement flightDepart_02;

	// Flight price description 3
	@FindBy(how = How.XPATH, using = "(//td[@class ='data_verb_xcols']/font//b)[3]")
	public WebElement flightDepart_03;

	// Flight price description 4
	@FindBy(how = How.XPATH, using = "(//td[@class ='data_verb_xcols']/font//b)[4]")
	public WebElement flightDepart_04;

	// Flight name and number selected ongoing
	@FindBy(how = How.XPATH, using = "(//td[@class ='data_left']/font//b)[1]")
	public WebElement flightDetails_Ongoing;

	//// Flight name and number selected return
	@FindBy(how = How.XPATH, using = "(//td[@class ='data_left']/font//b)[5]")
	public WebElement flightDetails_Return;

	// Find Elements - radio button ongoing flight List
	@FindAll(@FindBy(how = How.XPATH, using = "//input[@type='radio' and @name='outFlight']"))
	public List<WebElement> ongoing_RaidioButtons;

	// Find Elements - radio button returning flight List
	@FindAll(@FindBy(how = How.XPATH, using = "//input[@type='radio' and @name='inFlight']"))
	public List<WebElement> returning_RaidioButtons;

	// continue button
	@FindBy(how = How.XPATH, using = "//input[@name='reserveFlights']")
	public WebElement continue_Button;
}
