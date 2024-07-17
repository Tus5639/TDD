package TDD.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass extends AbstractClass{
	
	private WebDriver driver;
	
	
	
	@AfterTest
	public void quit() {
		driver.quit();
	}

}
