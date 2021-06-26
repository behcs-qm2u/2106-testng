package shoppingTest;


/*
 * - https://www.saucedemo.com
 * - login
 * - add item to card : but use @Method depends on
 * 
 */

import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.LoginPage;


public class LoginTestSD {

	// to invoke the browser
	WebDriver driver;
	

	// 20Jun21 - Extent Report
	ExtentReports report;
	ExtentTest test;
	
	SoftAssert soft = new SoftAssert();

	@BeforeTest
	public void setup() {
		
		// Tell location of chrome executable
		System.setProperty("webdriver.chrome.driver", "chromedriver");

		// to invoke the browser
		driver = new ChromeDriver();

		driver.get("https://www.saucedemo.com/");
		
		// Maximize the browser
		driver.manage().window().maximize();
		
		// set default timeout for all actions
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		// driver.close();

		report = new ExtentReports("ExtentReportSD.html");
		
		// System.out.println("finish setup()");
				
	}
	

	
	
	
	// 21-06-20 : POM model, actual login page moved
	// @Test
	@Test(enabled=false)
	@Parameters({"username", "password"})
	public void LoginTestCase(String uname, String pass) {
	
		LoginPage loginObj = new LoginPage();
		loginObj.login(uname, pass);
		
	}

	
	// Y21-06-19 using parameter, become data-driven test
	// @Test(enabled=false)
	@Test
	@Parameters({"username", "password"})
	public void LoginTest(String uname, String pass) {
	

	
		test = report.startTest("TC: Login SwagLabs");
		
		// Username Input  //input[@id='user-name']

		WebElement UserName = driver.findElement(By.id("user-name"));

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(UserName));

		UserName.sendKeys(uname);	
		test.log(LogStatus.PASS, "Successfully entered the user name");
		
		WebElement Password = driver.findElement(By.id("password"));

		Password.sendKeys(pass);
		test.log(LogStatus.PASS, "Successfully entered the password");
		
		WebElement Login = driver.findElement(By.name("login-button"));
		Login.click();	

		test.log(LogStatus.PASS, "Successfully clicked the login button");
		
		/*
		// Check if login error
		WebElement Error = driver.findElement(By.xpath("//button[@class='error-button']"));	
		String ExpMsg = "Epic sadface: Username and password do not match any user in this service";
		String ActMsg = Error.getText();
		
		soft.assertEquals(ActMsg, ExpMsg);
		*/
		
		/* -- hard way
		try {
			Assert.assertEquals(ExpMsg, ActMsg);
			test.log(LogStatus.PASS, "Expected and Actual value matches");
		} catch(Throwable e) {
			test.log(LogStatus.FAIL, "Expected and Actual value does not match");
		}
		
		*/
		
	
		
		/*
		// close after 5 seconds
		try {
			  Thread.sleep(5000);//time is in ms (1000 ms = 1 second)
			  driver.close();
		} catch (InterruptedException e) {e.printStackTrace();}			
		*/
	
	}
	
	
	@Test(dependsOnMethods="LoginTest")
	
	// @Test(dependsOnMethods="LoginTestCase")
	public void AddToCart() {
		
		System.out.println("In AddToCart()");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='inventory_container']")));

		// Can do extra check : Assert page top left title text appear
		WebElement Title = driver.findElement(By.xpath("//span[@class='title']"));	
		// String ExpMsg = "PRODUCTS--wrong";
		String ExpMsg = "PRODUCTS";	// correct msg
		String ActMsg = Title.getText();
		
		soft.assertEquals(ActMsg, ExpMsg);
		
		
		List<WebElement> CartList = driver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));
		// WebElement CartItem = driver.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));
		// CartItem.click();
		

		// Add two items to cart
		if ( CartList.size() > 2) {
			System.out.println("Adding 2 items to cart");
			CartList.get(0).click();
			CartList.get(1).click();
		} 
		else {
			System.out.println("Error!");
		}
		
		
	}
	
	
	
	@AfterTest
	public void teardown() {
		System.out.println("in Teardown");
		
	
		report.endTest(test);
		report.flush();
		
		// driver.quit();

		// close after 5 seconds
		try {
			  Thread.sleep(5000);//time is in ms (1000 ms = 1 second)
			  driver.quit();
		} catch (InterruptedException e) {e.printStackTrace();}			


		// at the end, throw all the assertion here
		soft.assertAll();
	
	
	}
	
	
	
}

