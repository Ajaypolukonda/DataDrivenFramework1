package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.AddempPage;
import applicationLayer.LoginPage;
import applicationLayer.LogoutPage;
import utilities.ExcelFileUtil;

public class POMDriverScript {
	WebDriver driver;
	String inputpath="D:\\Automation_practice\\Hybrid_Framework\\TestInput\\EmpData.xlsx";
	String outputpath="D:\\Automation_practice\\Hybrid_Framework\\TestOutput\\reportpom.xlsx";
	ExtentReports report;
	ExtentTest test;
	@BeforeTest
	public void Adminlogin() throws Throwable {
		report=new ExtentReports("./reports/Pomdatsdriver.html");
		System.setProperty("WebDriver.chrome.driver", "D://chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		login.verifylogin("Admin", "Qedge123!@#");
	}
	@Test
	public void empcreation() throws Throwable {
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int rc=xl.rowcount("AddEmp");
		Reporter.log("no of rows are::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			test=report.startTest("Validateemp");
			String para1=xl.getcelldata("AddEmp", i, 0);
			String para2=xl.getcelldata("AddEmp", i, 1);
			String para3=xl.getcelldata("AddEmp", i, 2);
			AddempPage emp=PageFactory.initElements(driver, AddempPage.class);
			boolean res=emp.verifyemp(para1, para2, para3);
			if (res) 
			{
			xl.setcelldata("AddEmp", i, 3, "pass", outputpath);
			test.log(LogStatus.PASS, "EmpCreation success");
			
			}
			else
			{
				xl.setcelldata("AddEmp", i, 3, "Fail", outputpath);
				test.log(LogStatus.FAIL, "Empcreation failure");
			}
		}
	}
	@AfterTest
	public void logoutapp() {
		report.endTest(test);
		report.flush();
		LogoutPage logout = PageFactory.initElements(driver, LogoutPage.class);
		logout.verifylogout();
		driver.close();
	}

}






