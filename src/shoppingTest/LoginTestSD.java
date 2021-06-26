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


import pages.LoginPageSD;

public class LoginTestSD extends BaseclassSD {

	
	// @Test
	@Test(enabled=false)
	@Parameters({"username", "password"})
	public void LoginTestCase(String uname, String pass) {
	
		LoginPageSD loginObj = new LoginPageSD();
		loginObj.loginPage(uname, pass);
		
	}

	@Test
	public void LoginTestCase2() {
		
		LoginPageSD obj = new LoginPageSD();
		
		String uname = sheet.getRow(1).getCell(0).getStringCellValue();
		String pass = sheet.getRow(1).getCell(1).getStringCellValue();
		
		System.out.println("LoginTestcase2: uname from xlsx is ["+uname+"]");
		System.out.println("LoginTestcase2: uname from xlsx is ["+pass+"]");
		
		obj.loginPage(uname, pass);
		
	}
	
	
	// @Test(dependsOnMethods="LoginTestCase")
	@Test(dependsOnMethods="LoginTestCase2")
	public void AddToCartTest() {

		LoginPageSD loginObj = new LoginPageSD();
		loginObj.addToCartPage();
		
	}
	
	
	
	
}

