package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Baseclass {

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	
	SoftAssert soft = new SoftAssert();
	
	XSSFWorkbook wbook;
	XSSFSheet sheet;
	
	
	@BeforeTest
	public void setup() throws IOException {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");

		driver = new ChromeDriver();

		driver.get("https://www.simplilearn.com");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		report = new ExtentReports("ExtentReport.html");
		
		FileInputStream fis = new FileInputStream("data/exceldata.xlsx");
		
		wbook = new XSSFWorkbook(fis);
		sheet = wbook.getSheetAt(0);
		
		
		
		// System.out.println("finish setup()");
				
	}
	
	
	
	@AfterTest
	public void teardown() {
		System.out.println("in Teardown");
	
		report.endTest(test);
		report.flush();
		
		driver.quit();

		// at the end, throw all the assertion here
		// soft.assertAll();
	
	}

	
	
}
