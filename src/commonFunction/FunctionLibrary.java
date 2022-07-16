package commonFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil{
	//method for login
	public static boolean verifylogin(String username,String password)
	{
	driver.findElement(By.xpath(ajay.getProperty("objuser"))).sendKeys(username);
	driver.findElement(By.xpath(ajay.getProperty("objpass"))).sendKeys(password);
	driver.findElement(By.xpath(ajay.getProperty("objloginbtn"))).click();
	String expected= "admin";
	String actual = driver.getCurrentUrl();
	if (actual.toLowerCase().contains(expected.toLowerCase())) 
	{
		Reporter.log("Login Success",true);
		return true;
	}
	else
	{
		Reporter.log("Login fail",true);
		return false;
	}
			
	
	}
public static void clickbranches() {
	driver.findElement(By.xpath(ajay.getProperty("objbranchesbtn"))).click();
}


public static boolean verifylogout() {
	driver.findElement(By.xpath(ajay.getProperty("objlogoutbtn"))).click();
	if (driver.findElement(By.xpath(ajay.getProperty("objloginbtn"))).isDisplayed()) 
	{
	Reporter.log("logout success",true);
	return true;
	}
	else
	{
		Reporter.log("Logout fail",true);
		return false;
	}
}
public static boolean createnewbranch(String branchname,String address1,String address2,
		String address3,String area,String zipcode,String country,String state,String city) 
{
	driver.findElement(By.xpath(ajay.getProperty("objnewbranch"))).click();
	driver.findElement(By.xpath(ajay.getProperty("objbranchname"))).sendKeys(branchname);
	driver.findElement(By.xpath(ajay.getProperty("objaddress1"))).sendKeys(address1);
	driver.findElement(By.xpath(ajay.getProperty("objaddress2"))).sendKeys(address2);
	driver.findElement(By.xpath(ajay.getProperty("objaddress3"))).sendKeys(address3);
	driver.findElement(By.xpath(ajay.getProperty("objarea"))).sendKeys(area);
	driver.findElement(By.xpath(ajay.getProperty("objzipcode"))).sendKeys(zipcode);
	new Select(driver.findElement(By.xpath(ajay.getProperty("objcountry")))).selectByVisibleText(country);
	new Select(driver.findElement(By.xpath(ajay.getProperty("objstate")))).selectByVisibleText(state);
	new Select(driver.findElement(By.xpath(ajay.getProperty("objcity")))).selectByVisibleText(city);
	driver.findElement(By.xpath(ajay.getProperty("objsubmit"))).click();
	//capture alertmessage
	String expectedalert=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	String actualalert="created Sucessfully";
	if (expectedalert.toLowerCase().contains(actualalert.toLowerCase())) 
	{
	Reporter.log("Branch creation success"+expectedalert+"    "+actualalert,true);
	return true;
	}
	else {
		Reporter.log("Branch creation fail"+expectedalert+"    "+actualalert,true);
		return false;
	}
	
}
public static boolean verifybranchupdate(String branch,String address,String area)
{
	driver.findElement(By.xpath(ajay.getProperty("objedit"))).click();
	WebElement element = driver.findElement(By.xpath(ajay.getProperty("objbranchedit")));
			element.clear();
	element.sendKeys(branch);
	WebElement element1=driver.findElement(By.xpath(ajay.getProperty("objaddressedit")));
	element1.clear();
	element1.sendKeys(address);
	WebElement element2=driver.findElement(By.xpath(ajay.getProperty("objareaedit")));
	element2.clear();
	element2.sendKeys(area);
	driver.findElement(By.xpath(ajay.getProperty("objupdatebtn"))).click();
	String expectedupdate=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	String actualupdate="Branch updated";
	if (expectedupdate.toLowerCase().contains(actualupdate.toLowerCase())) 
	{
		Reporter.log("Branch updated successfully"+expectedupdate+"   "+actualupdate,true);
		return true;
	}
	else
	{
		Reporter.log("Branch updation fail"+expectedupdate+"   "+actualupdate,true);
		return false;
	}
	
	
	
}
public static void Clickroles() {
	driver.findElement(By.xpath(ajay.getProperty("objrolebtn"))).click();
}
public static boolean verifyrolecreation(String rolename,String roledes,String roletype)
{
	driver.findElement(By.xpath(ajay.getProperty("objnewrolebtn"))).click();
	driver.findElement(By.xpath(ajay.getProperty("objrolename"))).sendKeys(rolename);
	driver.findElement(By.xpath(ajay.getProperty("objroledes"))).sendKeys(roledes);
	new Select(driver.findElement(By.xpath(ajay.getProperty("objroletype")))).selectByVisibleText(roletype);
	driver.findElement(By.xpath(ajay.getProperty("objsubmitbtn"))).click();
	//capture alert message
	String expectedr=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	String actualr = "New Role with id 388";
	if (expectedr.toLowerCase().contains(actualr.toLowerCase())) 
	{
		Reporter.log("Role creation success::"+expectedr+"    "+actualr,true);
		return true;
	}
	else
	{
		Reporter.log("Role creation fail::"+expectedr+"   "+actualr,true);
		return false;
	}
	
}
}









