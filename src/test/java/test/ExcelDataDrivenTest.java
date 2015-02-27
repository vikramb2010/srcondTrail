package test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ExcelDataDrivenTest {
	
	public WebDriver driver;

	 @Test (testName="data driven test")
 	  public void testSeachCountry() throws Exception {
    try {
        // Open the Excel file
        FileInputStream fis = new FileInputStream("C:\\MyDocument\\mydata.xlsx");
        // Access the required test data sheet
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("testdata");
        // Loop through all rows in the sheet
        // Start at row 1 as row 0 is our header row
        for(int count = 1;count<=sheet.getLastRowNum();count++){
            XSSFRow row = sheet.getRow(count);
            System.out.println("Running test case " + row.getCell(0).toString());
            // Run the test for the current test data row
            runTest(row.getCell(1).toString(),row.getCell(2).toString());
        }
        fis.close();
    } catch (IOException e) {
        System.out.println("Test data file not found");
    }   
}


public  void runTest(String strSearchString, String validateString) {
         
        // Start a browser driver and navigate to Google
	 driver = new FirefoxDriver();
        driver.get("http://www.bing.com");
        driver.manage().window().maximize();
 
        // Enter the search string and send it
        WebElement element = driver.findElement(By.id("sb_form_q"));
        element.sendKeys(strSearchString);
        element.submit();
       
        String searchStr = driver.findElement(By.xpath("//*[@id='b_context']/li[1]/h2")).getText();
        System.out.println("seachStr :"+searchStr);
         
        // Check the title of the page
        if (searchStr.equals(validateString)) {
            System.out.println("Validate String is " + validateString + ", as expected");
        } else {
            System.out.println("Expected String was " + validateString + ", but was " + searchStr + " instead");
        }
         
        //Close the browser
        driver.quit();
}


public void waitForPageLoad() throws Exception {
	
	driver.wait();
	
}

}
