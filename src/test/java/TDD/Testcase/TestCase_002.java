package TDD.Testcase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestCase_002 {
	
	WebDriver driver;
	
	@Test
	public void TestValidation() throws IOException {
		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/V1/index.php");
		driver.manage().window().maximize();
		
		String filepath = "C:\\Users\\HP\\eclipse-workspace\\TDD_001\\TestData\\Tets-Data.xlsx";
		
		FileInputStream fis = new FileInputStream(filepath);
		XSSFWorkbook workbook =new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet("Test-Data");
		
		//int rows = sheet.getLastRowNum();
		
		XSSFRow row = sheet.getRow(1);
		String username = row.getCell(0).getStringCellValue();
		String password = row.getCell(1).getStringCellValue();
		
		driver.findElement(By.cssSelector("[name='uid']")).sendKeys(username);
		driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("[name='btnLogin']")).click();
		
		String title = driver.getTitle();
		Assert.assertEquals(title, "GTPL Bank Manager HomePage");
		
		fis.close();
		driver.quit();
	}

}
