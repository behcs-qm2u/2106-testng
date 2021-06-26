package test;

import java.util.concurrent.TimeUnit;

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
	
	
	@BeforeTest
	public void setup() {
		
		// Tell location of chrome executable
		System.setProperty("webdriver.chrome.driver", "chromedriver");

		// to invoke the browser
		driver = new ChromeDriver();

		driver.get("https://www.simplilearn.com");
		
		// Maximize the browser
		driver.manage().window().maximize();
		
		// set default timeout for all actions
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		// driver.close();

		report = new ExtentReports("ExtentReport.html");
		
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
