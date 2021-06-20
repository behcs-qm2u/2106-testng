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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTestNS {
	
	WebDriver driver;
	
	ExtentReports report;
	ExtentTest test;
	
	
	@BeforeMethod
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		driver = new ChromeDriver();
		
		driver.get("https://www.simplilearn.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		report = new ExtentReports("ExtentReport.html");
	}
	
	@Test
	@Parameters({"username","password"})
	public void Login(String uname, String pass) {
		
		
		test = report.startTest("Login Test Case");
		
		WebElement LoginLink = driver.findElement(By.linkText("Log in"));
		
		LoginLink.click();
		
		test.log(LogStatus.PASS, "Successfully clicked on the login button");
		
		
		WebElement UserName = driver.findElement(By.name("user_login"));
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		
		UserName.sendKeys(uname);
		
		test.log(LogStatus.PASS, "Successfully entered the user name");
		
		WebElement Password = driver.findElement(By.id("password"));
		
		Password.sendKeys(pass);
		
		test.log(LogStatus.PASS, "Successfully entered the password");
		
		WebElement Rememberme = driver.findElement(By.className("rememberMe"));
		
		Rememberme.click();
		
		WebElement Login = driver.findElement(By.name("btn_login"));
		
		Login.click();
		test.log(LogStatus.PASS, "Successfully clicked on login link");
		
		
		WebElement Error = driver.findElement(By.id("msg_box"));
		
		String ActMsg = Error.getText();
		String ExpMsg = "The email or password you have entered is invalid.";
				
		Assert.assertTrue(Error.isDisplayed());
		Assert.assertEquals(ActMsg, ExpMsg);
	
		
	}
	
	@AfterMethod
	public void teardown() {
		
		report.endTest(test);
		report.flush();
		
		driver.quit();
		
	}

}



