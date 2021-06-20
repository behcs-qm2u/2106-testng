package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class LoginPage {
	
	WebDriver driver;
	
	// constructor
	public LoginPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public void Login(String uname, String pass) {
		
		WebElement UserName = driver.findElement(By.id("user-name"));

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(UserName));

		UserName.sendKeys(uname);	
		
		WebElement Password = driver.findElement(By.id("password"));

		Password.sendKeys(pass);
		
		WebElement Login = driver.findElement(By.name("login-button"));
		Login.click();	


	}

}
