package Case_Study;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CaseStudy.Case_Study.Drivers;

//import Selenium.ReportsNew.UtilityClass;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Reports {
	ExtentTest logger;
	WebDriver driver;
	ExtentReports extent;
	WebDriverWait wait;
	//ExtentHtmlReporter report;

	@BeforeTest
	public void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Reports.html", true);
		extent.addSystemInfo("Host Name", "TestMe");
		extent.addSystemInfo("Environment", "Selenium Testing");
		extent.addSystemInfo("User Name", "Bhavana Raj");
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	@Test(priority=1)
	  public void testRegistration() {
		  Assert.assertEquals(driver.getTitle(), "Home");
		  driver.findElement(By.linkText("SignUp")).click();
		  Assert.assertEquals(driver.getTitle(), "Sign Up");
		  driver.findElement(By.name("userName")).sendKeys("rajanna8");
		  driver.findElement(By.name("firstName")).sendKeys("bhaskaj");
		  wait= new WebDriverWait(driver, 100);
		  
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstName")));
		  String txt=driver.findElement(By.id("err")).getText();
		  System.out.println(txt);
		  if(txt.equals("Available"))
		  {
			  driver.findElement(By.name("lastName")).sendKeys("raii");
			  driver.findElement(By.name("password")).sendKeys("Rajanna8");
			  driver.findElement(By.name("confirmPassword")).sendKeys("Rajanna8");
			  driver.findElement(By.xpath("//input[@type='radio' and @value='Male']")).click();
			  driver.findElement(By.name("emailAddress")).sendKeys("bhavan@gmail.com");
			  driver.findElement(By.name("mobileNumber")).sendKeys("9176545688");
			  driver.findElement(By.name("dob")).sendKeys("21/08/1997");
			  driver.findElement(By.name("address")).sendKeys("Mangalore street");
			  Select sel=new Select(driver.findElement(By.id("securityQuestion")));
			  sel.selectByIndex(1);
			  driver.findElement(By.name("answer")).sendKeys("Mangalore street");
			  driver.findElement(By.name("Submit")).click();
			  //Assert.assertEquals(driver.getTitle(), "Login");
		  }
		  
	  }
	  @Test(priority=2)
	  public void testLogin() {
		  //Assert.assertEquals(driver.getTitle(), "SignUp");
		  //driver.findElement(By.linkText("SignIn")).click();
		  
		  Assert.assertEquals(driver.getTitle(), "Login");
		  driver.findElement(By.name("userName")).sendKeys("rajanna8");
		  driver.findElement(By.name("password")).sendKeys("Rajanna8");
		  driver.findElement(By.name("Login")).click();
		  wait= new WebDriverWait(driver, 1000);
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("SignOut")));
		  Assert.assertEquals(driver.getTitle(), "Home");
		  
	  }
		@Test(priority=3)
		   public void testCart()
			 {
			 //driver.findElement(By.linkText("All Categories")).click();
			 Actions act=new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/a/span"))).build().perform();
			 System.out.println("success");
			 driver.findElement(By.linkText("Electronics")).click();
			 WebDriverWait wait=new WebDriverWait(driver,1000);
			 wait= new WebDriverWait(driver, 100);
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Head Phone")));
			 driver.findElement(By.xpath("//*[@id=\"submenuul11290\"]/li[1]/a/span")).click();//add to cart button
			 driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();//cart1 link
			 driver.findElement(By.xpath("/html/body/header/div[1]/div/div/div[2]/div/a[2]")).click();//checkout button
			 Assert.assertEquals(driver.getTitle(), "View Cart");

			 }
		@Test(priority=4)
		public void testPayment() {
			Assert.assertEquals(driver.getTitle(), "View Cart");
			driver.findElement(By.xpath("/html/body/main/section/div[3]/table/tfoot/tr[2]/td[5]/a")).click();//proceed to pay button
			Assert.assertEquals(driver.getTitle(), "Cart Checkout");
			driver.findElement(By.name("ShippingAdd")).sendKeys("Bangalore");
			driver.findElement(By.xpath("//input[@type='submit' and @value='Proceed to Pay']")).click();
			WebDriverWait wait=new WebDriverWait(driver,100);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/h3"), "Net Banking"));
			driver.findElement(By.xpath("//*[@id=\"swit\"]/div[1]/div/label")).click();//andhrabank
			driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();//continue
			
			driver.findElement(By.name("username")).sendKeys("123456");
			driver.findElement(By.name("password")).sendKeys("Pass@456");
			driver.findElement(By.xpath("//input[@type='submit' and @value='LOGIN']")).click();//login button
			driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
			driver.findElement(By.xpath("//input[@type='submit' and @value='PayNow']")).click();
			Assert.assertEquals(driver.getTitle(), "Order Details");
			 
		}

	/*@Test
	public void passTest() {
		driver = Drivers.getDriver("Chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
		
		logger = extent.startTest("passTest");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
		//registration
			  Assert.assertEquals(driver.getTitle(), "Home");
			  driver.findElement(By.linkText("SignUp")).click();
			  Assert.assertEquals(driver.getTitle(), "Sign Up");
			  driver.findElement(By.name("userName")).sendKeys("rajann00");
			  driver.findElement(By.name("firstName")).sendKeys("bhaskaraj");
			  wait= new WebDriverWait(driver, 1000);
			  wait= new WebDriverWait(driver, 1000);
			  wait= new WebDriverWait(driver, 1000);
			  wait= new WebDriverWait(driver, 1000);
			  
			 
			  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstName")));
			  String txt=driver.findElement(By.id("err")).getText();
			  System.out.println(txt);
			  if(txt.equals("Available"))
			  {
				  driver.findElement(By.name("lastName")).sendKeys("raii");
				  driver.findElement(By.name("password")).sendKeys("Rajann10");
				  driver.findElement(By.name("confirmPassword")).sendKeys("Rajann10");
				  driver.findElement(By.xpath("//input[@type='radio' and @value='Male']")).click();
				  driver.findElement(By.name("emailAddress")).sendKeys("bhavan@gmail.com");
				  driver.findElement(By.name("mobileNumber")).sendKeys("9176545688");
				  driver.findElement(By.name("dob")).sendKeys("21/08/1997");
				  driver.findElement(By.name("address")).sendKeys("Mangalore street");
				  Select sel=new Select(driver.findElement(By.id("securityQuestion")));
				  sel.selectByIndex(1);
				  driver.findElement(By.name("answer")).sendKeys("Mangalore street");
				  driver.findElement(By.name("Submit")).click();
				  //Assert.assertEquals(driver.getTitle(), "Login");
			  }
			 
			  //Assert.assertEquals(driver.getTitle(), "SignUp");
			  //driver.findElement(By.linkText("SignIn")).click();
			  
			  //testlogin
			  Assert.assertEquals(driver.getTitle(), "Login");
			  driver.findElement(By.name("userName")).sendKeys("rajann00");
			  driver.findElement(By.name("password")).sendKeys("Rajann10");
			  driver.findElement(By.name("Login")).click();
			  wait= new WebDriverWait(driver, 1000);
			  wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("SignOut")));
			  Assert.assertEquals(driver.getTitle(), "Home");
			  
		 
				 //driver.findElement(By.linkText("All Categories")).click();
				 Actions act=new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/a/span"))).build().perform();
				 System.out.println("success");
				 driver.findElement(By.linkText("Electronics")).click();
				 WebDriverWait wait=new WebDriverWait(driver,100);
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Head Phone")));
				 driver.findElement(By.xpath("//*[@id=\"submenuul11290\"]/li[1]/a/span")).click();//add to cart button
				 driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();//cart1 link
				 driver.findElement(By.xpath("/html/body/header/div[1]/div/div/div[2]/div/a[2]")).click();//checkout button
				 Assert.assertEquals(driver.getTitle(), "View Cart");

				 
				Assert.assertEquals(driver.getTitle(), "View Cart");
				driver.findElement(By.xpath("/html/body/main/section/div[3]/table/tfoot/tr[2]/td[5]/a")).click();//proceed to pay button
				Assert.assertEquals(driver.getTitle(), "Cart Checkout");
				driver.findElement(By.name("ShippingAdd")).sendKeys("Bangalore");
				driver.findElement(By.xpath("//input[@type='submit' and @value='Proceed to Pay']")).click();
				WebDriverWait wait1=new WebDriverWait(driver,100);
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/h3"), "Net Banking"));
				driver.findElement(By.xpath("//*[@id=\"swit\"]/div[1]/div/label")).click();//andhrabank
				driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();//continue
				
				driver.findElement(By.name("username")).sendKeys("123456");
				driver.findElement(By.name("password")).sendKeys("Pass@456");
				driver.findElement(By.xpath("//input[@type='submit' and @value='LOGIN']")).click();//login button
				driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
				driver.findElement(By.xpath("//input[@type='submit' and @value='PayNow']")).click();
				Assert.assertEquals(driver.getTitle(), "Order Details");				
			  }*/
			  

	@Test
	public void failTest() {
		logger = extent.startTest("failTest");
		driver = Drivers.getDriver("Chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
		String title = driver.getTitle();
		Assert.assertEquals(title, "NoTitle");
		logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
	}

	@Test
	public void skipTest() {
		logger = extent.startTest("skipTest");
		throw new SkipException("Skipping - This is not ready for testing ");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getThrowable());
			String screenshotPath = Reports.getScreenshot(driver, result.getName());
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(logger);
	}

	@AfterTest
	public void endReport() {
		extent.close();
		driver.close();
	}

}
