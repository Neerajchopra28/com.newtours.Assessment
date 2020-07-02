package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	//Flight price description 1
	@FindBy(how = How.XPATH, using = "(//font/b)[3]")
	public WebElement flightDepart_01;
	
	//Flight price description 2
	@FindBy(how = How.XPATH, using = "(//font/b)[5]")
	public WebElement flightDepart_02;
	
	//Flight price description 3
	@FindBy(how = How.XPATH, using = "(//font/b)[7]")
	public WebElement flightDepart_03;
	
	//Flight price description 4
	@FindBy(how = How.XPATH, using = "(//font/b)[9]")
	public WebElement flightDepart_04;
	
	//Flight name and number selected ongoing 
	@FindBy(how = How.XPATH, using = "(//font/b)[2]")
	public WebElement flightDetails_Ongoing;

	////Flight name and number selected return
	@FindBy(how = How.XPATH, using = "(//td/font/b)[7]")
	public WebElement flightDetails_Return;
	
	
	
	


}
