package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	static WebDriver driver;

	// function use to start browser
	public static WebDriver startBrowser(String browserName, String url) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}

	// Funcltion for Explicit wait
	public static boolean explicitWaiting(WebElement element, WebDriver driver) throws Exception {
		try {
			WebDriverWait myWait = new WebDriverWait(driver, 20);
			myWait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			System.out.println("explicit wait error  " + e);
			return false;
		}
		return true;
	}

	public static String configProperties(String Arg) throws Exception {
		
		String value = "";
		try {
			Properties propTest = new Properties();

			FileReader file = new FileReader(System.getProperty("user.dir")+ "\\config.properties");

			propTest.load(file);

			 value = propTest.getProperty(Arg);
		} catch (Exception e) {
			System.out.println("Unable to read config .properties file");
		}
				
		return value;
	}

}