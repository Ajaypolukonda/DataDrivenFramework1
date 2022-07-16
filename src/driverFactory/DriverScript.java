package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
	String inputpath="D:\\Automation_practice\\Hybrid_Framework\\ProperryFile\\Environment.properties";
	String outputpath="D:\\Automation_practice\\Hybrid_Framework\\TestOutput\\hybridresults.xlsx";
	ExtentReports report;
	ExtentTest test;
	String Tcsheet="TestCase";
	String Tssheet="TestData";
	@Test
	public void statTest() throws Throwable
	{ 
		report=new ExtentReports("./Repots/Hydrid.html");
		boolean res=false;
		String tcres="";
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		//count no of rows in both sheet
		int Tccount=xl.rowcount(Tcsheet);
		int Tscount=xl.rowcount(Tssheet);
		//count no of cell in both sheet
		int Tccell=xl.cellcount(Tcsheet);
		int Tscell=xl.cellcount(Tssheet);
		Reporter.log(Tccount+"  "+Tscount+"   "+Tccell+"    "+Tscell,true);
		//itaretion all rows in  Tcsheet
		for(int i=1;i<=Tccount;i++)
		{ 
			
			//read modul cell
			String modulcell=xl.getcelldata(Tcsheet, i, 1);
			test=report.startTest(modulcell);
			//read module status cell
			String modulstatus=xl.getcelldata(Tcsheet, i, 2);
			if(modulstatus.equalsIgnoreCase("Y"))
			{
				String tcid=xl.getcelldata(Tcsheet, i, 0);
				//iteration all row  in Tssheet
				for(int j=1;j<=Tscount;j++)
				{
					// read description cell into tssheet
					String description =xl.getcelldata(Tssheet, j, 2);
					//read tsid cell in tssheet
					String tsid=xl.getcelldata(Tssheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						//read keyword cell in tssheet
						String keyword=xl.getcelldata(Tssheet, j, 4);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							String par1=xl.getcelldata(Tssheet, j, 5);
							String par2=xl.getcelldata(Tssheet, j, 6);
							res=FunctionLibrary.verifylogin(par1, par2);
							test.log(LogStatus.INFO, description);
						}
						else if(keyword.equalsIgnoreCase("BranchCreation"))
						{
							String par1=xl.getcelldata(Tssheet, j, 5);
							String par2=xl.getcelldata(Tssheet, j, 6);
							String par3=xl.getcelldata(Tssheet, j, 7);
							String par4=xl.getcelldata(Tssheet, j, 8);
							String par5=xl.getcelldata(Tssheet, j, 9);
							String par6=xl.getcelldata(Tssheet, j, 10);
							String par7=xl.getcelldata(Tssheet, j, 11);
							String par8=xl.getcelldata(Tssheet, j, 12);
							String par9=xl.getcelldata(Tssheet, j, 13);
							FunctionLibrary.clickbranches();
							res=FunctionLibrary.createnewbranch(par1, par2,par3, par4, par5, par6, par7, par8, par9);
							test.log(LogStatus.INFO, description);
						}
						else if(keyword.equalsIgnoreCase("BranchUpdate"))
						{
							String par2=xl.getcelldata(Tssheet, j, 6);
							String par4=xl.getcelldata(Tssheet, j, 8);
							String par5=xl.getcelldata(Tssheet, j, 9);
							FunctionLibrary.clickbranches();
							res=FunctionLibrary.verifybranchupdate(par2, par4, par5);
							test.log(LogStatus.INFO, description);
						}
						else if (keyword.equalsIgnoreCase("AdminLogout"))
						{
							res=FunctionLibrary.verifylogout();
							test.log(LogStatus.INFO, description);
						}
						else if (keyword.equalsIgnoreCase("RoleCreation"))
						{
							String par1=xl.getcelldata(Tssheet, j, 5);
							String par2=xl.getcelldata(Tssheet, j, 6);
							String par3=xl.getcelldata(Tssheet, j, 7);
							FunctionLibrary.Clickroles();
							res=FunctionLibrary.verifyrolecreation(par1, par2, par3);
							test.log(LogStatus.INFO, description);
						}
						String tsres="";
						if(res)
						{
							//write as  pass into Tssheet
							tsres="pass";
							xl.setcelldata(Tssheet, j, 3, tsres, outputpath);
							test.log(LogStatus.PASS, description);
						}
						else
						{
							//write as  fail into Tssheet
							tsres="fail";
							xl.setcelldata(Tssheet, j, 3, tsres, outputpath);
							test.log(LogStatus.FAIL, description);
						}
						tcres=tsres;
					}
				}
				//write as tcres into Tcsheet
				xl.setcelldata(Tcsheet, i, 3, tcres, outputpath);
				report.endTest(test);
				report.flush();
			}
			else
			{
				//write as blocked into status cell which tc is flag N
				xl.setcelldata(Tcsheet, i, 3, "Blocked", outputpath);
			}

		}

	}
}

			


