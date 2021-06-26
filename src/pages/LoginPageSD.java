package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import shoppingTest.LoginTestSD;

public class LoginPageSD {

	
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	SoftAssert soft = new SoftAssert();	
	
	// ===== constructor ====
	public LoginPageSD () {
		
		// assign to class static obj
		driver = LoginTestSD.driver;
		report = LoginTestSD.report;
		test = LoginTestSD.test;
		
		PageFactory.initElements(driver, this);
		
	}

	
	// ==== WebElements : loginPage ===
	@FindBy(id="user-name")
	WebElement UserName;
	
	@FindBy(id="password")
	WebElement Password;
	
	@FindBy(name="login-button")
	WebElement Login;
	
	
	
	
	public void loginPage (String uname, String pass) {
		test = report.startTest("TC: Login SwagLabs");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
	
		UserName.sendKeys(uname);	
		test.log(LogStatus.PASS, "Successfully entered the user name");
		
		Password.sendKeys(pass);
		test.log(LogStatus.PASS, "Successfully entered the password");
		
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

	
	
	// ==== WebElements : addToCartPage ===
	
	@FindBy(xpath="//span[@class='title']")
	WebElement Title;

	@FindBy(xpath="//button[@class='btn btn_primary btn_small btn_inventory']")
	List<WebElement> CartList;
	
	public void addToCartPage() {
		
		System.out.println("In AddToCart()");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='inventory_container']")));

		// Can do extra check : Assert page top left title text appear
		// String ExpMsg = "PRODUCTS--wrong";
		String ExpMsg = "PRODUCTS";	// correct msg
		String ActMsg = Title.getText();
		soft.assertEquals(ActMsg, ExpMsg);
		
		
		// List<WebElement> CartList = driver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));
		
		
		

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
	

	
	
	
}
