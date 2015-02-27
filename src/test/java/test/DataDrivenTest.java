package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {
	 private WebDriver driver;
	  private String baseUrl;
	  
	  @DataProvider(name = "myTest")
	  public Object[][] createData1() {
	          return new Object[][] {
	            { "India"},
	            { "Brazil"},
	            { "Canada"},
	            { "Sri Lanka"},
	            { "England"},
	            { "United Kingdom"},
	            { "United States"},     
	              
	          };
	  }
	  
	  @BeforeMethod
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://www.wikipedia.org/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	 
	  @Test(dataProvider = "myTest")
	  public void testSearchCountry(String country) throws Exception {
	    driver.get(baseUrl + "/wiki/Main_Page");
	    driver.findElement(By.id("searchInput")).clear();
	    driver.findElement(By.id("searchInput")).sendKeys(country);
	    driver.findElement(By.id("searchButton")).click();
	    String str = driver.findElement(By.xpath("//*[@id='firstHeading']")).getText();
	    System.out.println("String value:" +str);
	    Assert.assertTrue(country.equals(str.trim()));
	  }
	 
	  @AfterMethod
	  public void tearDown() throws Exception {
	    driver.quit();   
	  } 
	}


