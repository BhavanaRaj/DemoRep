package CaseStudy.Case_Study;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Drivers {
	static WebDriver driver;
	public static WebDriver getDriver(String browser)
	{
		if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("Ie"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\drivers\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
		}
		else
			System.out.println("Invalid browser !!");
		return driver;
	}
	

}
