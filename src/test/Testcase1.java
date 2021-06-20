package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Testcase1 {

	@BeforeTest
	public void BeforeTestMethod() {
		System.out.println("TC1: Inside Before Test");
		
	}
	
	
	@BeforeMethod
	public void Setup() {
		
		System.out.println("TC1: Inside Before Method");
		
	}
	
	@Test(groups={"Regression", "Sanity"})
	public void LoginTest() {
		System.out.println("TC1: Inside LoginTest {S,R}");
		
	}
	
	@Test(enabled=true)
	public void SignUpTest() {
		System.out.println("TC1: Inside SignUpTest");
		
	}
	
	@Test(groups={"Sanity"})
	public void RandomName() {
		System.out.println("TC1: Inside RandomName {S}");
	}
	
	@AfterMethod
	public void AfterMethod() {
		System.out.println("TC1: Inside After Method");
	}
	
	@AfterTest
	public void AfterTest() {
		System.out.println("TC1: Inside After Test");
		
	}
	
}
