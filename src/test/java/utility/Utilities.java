package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

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

			FileReader file = new FileReader(System.getProperty("user.dir") + "\\config.properties");

			propTest.load(file);

			value = propTest.getProperty(Arg);
		} catch (Exception e) {
			System.out.println("Unable to read config .properties file");
		}

		return value;
	}

	// data provider
	
	public static Object[][] TestDataPassing() throws IOException {
		String arr[][] = null;
		File file = new File(System.getProperty("user.dir") + "\\TestWorkbook.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook myWorkbook = new XSSFWorkbook(fis);

		XSSFSheet mysheet = myWorkbook.getSheet("Sheet2");
		int totalRows = mysheet.getLastRowNum() - mysheet.getFirstRowNum();
		int totalColumn = mysheet.getRow(0).getLastCellNum() - mysheet.getRow(0).getFirstCellNum();
		arr = new String[totalRows][totalColumn];

		for (int i = mysheet.getFirstRowNum(); i < mysheet.getLastRowNum() - 1; i++) {
			Row myRow = mysheet.getRow(i);

			for (int j = myRow.getFirstCellNum(); j < myRow.getLastCellNum(); j++) {
				arr[i][j] = myRow.getCell(j).getStringCellValue();
			}
		}

		return arr;

	}
	@DataProvider(name ="TravelData")
	public Object[][] TestDataProvider() throws Exception {

		Object testObjectArray[][] = TestDataPassing();

		return testObjectArray;

	}
	
	public static String getDataFromTestDataExcel(String key) throws IOException
	{
		String value="";
		File myFile =    new File(System.getProperty("user.dir")+"\\TESTWORKBOOK.xlsx");
	    FileInputStream fis = new FileInputStream(myFile);
	    XSSFWorkbook myWorkbook = new XSSFWorkbook(fis);
	    XSSFSheet mySheet=myWorkbook.getSheet("Searchflightdata");
	    int rowCount = mySheet.getLastRowNum()-mySheet.getFirstRowNum();

	    for (int i = 0; i < rowCount+1; i++) {
	    	Row myRow=mySheet.getRow(i);
			if(myRow.getCell(0).getStringCellValue().equals(key))
			{
				value=myRow.getCell(1).getStringCellValue();
			}

	    }
	    return value;
	}

}
