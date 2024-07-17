package TDD.Testcase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Negative_TestCase {
	
WebDriver driver;
	
	@Test(priority = 3)
	public void TestValidation() throws IOException {
		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/V1/index.php");
		driver.manage().window().maximize();
		
		String filepath = "C:\\Users\\HP\\eclipse-workspace\\TDD_001\\TestData\\Tets-Data.xlsx";
		
		FileInputStream fis = new FileInputStream(filepath);
		XSSFWorkbook workbook =new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet("Test-Data");
		
		int rows = sheet.getLastRowNum();
		XSSFRow row = sheet.createRow(rows+1);
		row.createCell(0).setCellValue("Tushar1234");
		row.createCell(1).setCellValue("num12345");
		
		
		row = sheet.getRow(rows+1);

		String username = row.getCell(0).getStringCellValue();
		String password = row.getCell(1).getStringCellValue();
		
		driver.findElement(By.cssSelector("[name='uid']")).sendKeys(username);
		driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("[name='btnLogin']")).click();
		
		String result = driver.switchTo().alert().getText();
		
		Assert.assertEquals(result, "User is not valid");
		driver.switchTo().alert().accept();
		
		FileOutputStream fos = new FileOutputStream(filepath);
		workbook.write(fos);
		
		fos.close();
		fis.close();
		driver.quit();
	}

}
