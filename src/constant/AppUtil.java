package constant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
	public static WebDriver driver;
	public static Properties ajay;
	@BeforeSuite
	public void setUp() throws Throwable {
		ajay=new Properties();
		ajay.load(new FileInputStream("D:\\Automation_practice\\Hybrid_Framework\\ProperryFile\\Environment.properties"));
		if (ajay.getProperty("Browser").equalsIgnoreCase("chrome")) 
		{
			System.setProperty("WebDriver.chrome.driver", "D://chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(ajay.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else if (ajay.getProperty("Browser").equalsIgnoreCase("firefox")) 
		{
			System.setProperty("WebDriver.gecko.driver", "D://geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get(ajay.getProperty("url"));
		}
		else {
			Reporter.log("Browser is not matching",true);
		}
	}
@AfterSuite
public void tearDown() {
	driver.close();
}
}
