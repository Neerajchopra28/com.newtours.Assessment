// all Login page objects are below 
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	//class constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Date visibility
	@FindBy(how = How.XPATH, using = "//form[@name=\"home\"]/table//font[@face=\"Arial, Helvetica, sans-serif, Verdana\"]//b")
	public WebElement date_Visibility;

	// Aruba image
	@FindBy(how = How.XPATH, using = "//img[@src='/images/banner2.gif']")
	public WebElement Aruba_Visibility;

	// Sign on page visibilty for unvalid login test
	@FindBy(how = How.XPATH, using = "//img[@src='/images/masts/mast_signon.gif']")
	public WebElement SignOn_Visibility;

	// Valid login test visibility
	@FindBy(how = How.XPATH, using = "//input[@name ='findFlights']")
	public WebElement continue_Button;
	
	//username textbox
	@FindBy(how = How.XPATH, using = "//input[@name='userName']")
	public WebElement userName;
	
	//password textbox
	@FindBy(how = How.XPATH, using = "//input[@name='password']")
	public WebElement password;
	
	//signin button
	@FindBy(how = How.XPATH, using = "//input[@name='login']")
	public WebElement signIn_Button;
}

