package test;

import org.testng.annotations.Test;

public class Testcase2 {

	@Test(priority=0, description = "Verify that HomePage functionality is working fine", groups= {"Regression"})
	public void HomePageTest() {
		
		System.out.println("TC2: Inside HomePageTest [P0]");
		
	}
	
	@Test(priority=1)
	public void HomePageTest1() {
		
		System.out.println("TC2: Inside HomePageTest1 [P1]");
		
	}

	@Test(priority=2)
	public void HomePageTest2() {
		
		System.out.println("TC2: Inside HomePageTest2 [P2]");
		
	}
	
	@Test(dependsOnMethods="HomePageTest")
	public void ClickAbout() {
		System.out.println("TC2: Inside Click About, depends on HomePageTest");
		
	}

	
}
