package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.LoginTest;


public class LoginPage {
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	SoftAssert soft = new SoftAssert();	
	
	
	// ===== WebElements =====
	@FindBy(linkText="Log in")
	WebElement LoginLink;
	
	@FindBy(name="user_login")
	WebElement UserName;
	
	@FindBy(id="password")
	WebElement Password;
	
	@FindBy(className="rememberMe")
	WebElement Rememberme;
	
	@FindBy(name="btn_login")
	WebElement Login;
	
	@FindBy(id="msg_box")
	WebElement Error;
	
	// ===== constructor ====
	public LoginPage () {
		
		// assign to class static obj
		driver = LoginTest.driver;
		report = LoginTest.report;
		test = LoginTest.test;
		
		PageFactory.initElements(driver, this);
		
	}
	
	// ==== methods =====
	public void login(String uname, String pass) {
		
		test = report.startTest("Login Test Case [Our TC Name]");
		
		LoginLink.click();
		test.log(LogStatus.PASS, "Successfully clicked on the login button");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
	
		UserName.sendKeys(uname);	
		test.log(LogStatus.PASS, "Successfully entered the user name");
		
		Password.sendKeys(pass);
		test.log(LogStatus.PASS, "Successfully entered the password");
		
		Rememberme.click();
		test.log(LogStatus.PASS, "Successfully ticked remember me");
		
		Login.click();	
		test.log(LogStatus.PASS, "Successfully clicked the login link");
		
		String ExpMsg = "The email or password you have entered is invalid.";
		String ActMsg = Error.getText();
		
		
		Assert.assertTrue(Error.isDisplayed());
		Assert.assertEquals(ActMsg, ExpMsg);
		
		// soft.assertEquals(ActMsg, ExpMsg);
		/* -- hard way
		try {
			Assert.assertEquals(ExpMsg, ActMsg);
			test.log(LogStatus.PASS, "Expected and Actual value matches");
		} catch(Throwable e) {
			test.log(LogStatus.FAIL, "Expected and Actual value does not match");
		}
		
		*/
		

	}

}
