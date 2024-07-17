package TDD.Testcase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import TDD.utils.AbstractClass;
import TDD.utils.BaseClass;

public class TestCase_001  extends AbstractClass{
	
	WebDriver driver;
	@Test(priority = 1)
	public void LoginValidation() throws IOException {
		driver = new ChromeDriver();
		driver.get("https://www.demo.guru99.com/v4/");
		driver.manage().window().maximize();
		
		driver.findElement(By.cssSelector("ol li a ")).click();
		driver.navigate().refresh();		
		driver.findElement(By.cssSelector("ol li a ")).click();
		WebElement emailtext = driver.findElement(By.cssSelector("td input[name='emailid']"));
		emailtext.sendKeys("tusharchaudhary@gmail.com");
		
		WebElement submitButton = driver.findElement(By.cssSelector("td input[name='btnLogin']"));
		submitButton.click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebElement userID = driver.findElement(By.cssSelector("tr:nth-child(4) td:nth-child(2)"));
		WebElement  password = driver.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2)"));
		
		String USERID = userID.getText();
		String PASSWORD = password.getText();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Test-Data");
		
		int row = sheet.getLastRowNum();		
		XSSFRow row1 = sheet.createRow(row+1);
		XSSFRow row2 = sheet.createRow(row+1+1);
		
		row1.createCell(0).setCellValue("User ID");
		row1.createCell(1).setCellValue("Passowrd");
		
		row2.createCell(0).setCellValue(USERID);
		row2.createCell(1).setCellValue(PASSWORD);
		
		
		String filepath = "C:\\Users\\HP\\eclipse-workspace\\TDD_001\\TestData\\Tets-Data.xlsx";
		FileOutputStream fos = new FileOutputStream(filepath);
		workbook.write(fos);
		workbook.close();
		
		driver.quit();
	}

}
