package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class LoginTest {

	// to invoke the browser
	WebDriver driver;
	
	// 20Jun21 - Extent Report
	ExtentReports report;
	ExtentTest test;
	
	SoftAssert soft = new SoftAssert();

	@BeforeMethod
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
	
	
	// Y21-06-19 using parameter, become data-driven test
	
	@Test
	@Parameters({"username", "password"})
	public void Login(String uname, String pass) {
	
		
		test = report.startTest("Login Test Case [Our TC Name]");
		
		WebElement LoginLink = driver.findElement(By.linkText("Log in"));
		LoginLink.click();
		// Note: advantage of create a webelement is for reuse later. eg. click login again
		
		// Can direct do this.
		// driver.findElement(By.linkText("Log in")).click();
		
		test.log(LogStatus.PASS, "Successfully clicked on the login button");

		WebElement UserName = driver.findElement(By.name("user_login"));

		// 6Jun21
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
	
		

		// UserName.sendKeys("nikhunj204@gmail.com");
		UserName.sendKeys(uname);	
		
		test.log(LogStatus.PASS, "Successfully entered the user name");
		
		WebElement Password = driver.findElement(By.id("password"));
		// Password.sendKeys("unknown");
		Password.sendKeys(pass);
		
		test.log(LogStatus.PASS, "Successfully entered the password");
		
		WebElement Rememberme = driver.findElement(By.className("rememberMe"));
		Rememberme.click();
		
		WebElement Login = driver.findElement(By.name("btn_login"));
		Login.click();	

		test.log(LogStatus.PASS, "Successfully clicked the login link");
		
		// 20Jun21 : check error 
		
		WebElement Error = driver.findElement(By.id("msg_box"));	
		String ExpMsg = "The email or password you have entered is invalid.MEH";
		String ActMsg = Error.getText();
		
		Assert.assertTrue(Error.isDisplayed());
		
		soft.assertEquals(ActMsg, ExpMsg);
		
		/* -- hard way
		try {
			Assert.assertEquals(ExpMsg, ActMsg);
			test.log(LogStatus.PASS, "Expected and Actual value matches");
		} catch(Throwable e) {
			test.log(LogStatus.FAIL, "Expected and Actual value does not match");
		}
		
		*/
		
		if (ActMsg.equals(ExpMsg)) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}
		
		
		/*
		// close after 5 seconds
		try {
			  Thread.sleep(5000);//time is in ms (1000 ms = 1 second)
			  driver.close();
		} catch (InterruptedException e) {e.printStackTrace();}			
		*/
	
	}


	
	
	@AfterMethod
	public void teardown() {
		System.out.println("in Teardown");
		
	
		report.endTest(test);
		report.flush();
		
		driver.quit();

		// at the end, throw all the assertion here
		soft.assertAll();
	
	
	}
	
	
	
}
