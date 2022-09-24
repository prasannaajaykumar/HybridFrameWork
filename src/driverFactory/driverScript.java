package driverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import commonFunctions.FunctionalLibrary;
import utilities.ExcelFileUtil;
public class driverScript {
WebDriver driver;
String inputpath="C:\\PROJECT TESTING\\Hybrid_Framework\\TestInput\\dataengine1.xlsx";
String outputpath="C:\\PROJECT TESTING\\Hybrid_Framework\\TestOutput\\ash.xlsx";
@Test
public void starttest() throws Throwable {
	//create object to access all excel methods
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);	
	for (int i = 1; i <=xl.rowCount("MasterTestCases"); i++) {
	 String modulestatus="";
	 if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
	 {
		//read test cases cell
		 String TCmodule=xl.getCellData("MasterTestCases", i, 1 );
		 //iterate all rows in tc module
		 for (int j =1 ; j <= xl.rowCount(TCmodule); j++) {
			//read cells in tc module
			 String Description=xl.getCellData(TCmodule,j, 0);
			 String ObjectType=xl.getCellData(TCmodule, j, 1);
			 String Locatorname=xl.getCellData(TCmodule, j, 2);
			 String Locatorvalue=xl.getCellData(TCmodule, j, 3);
			 String Testdata=xl.getCellData(TCmodule, j, 4);
			
			 try {
				 if(ObjectType.equalsIgnoreCase("startBrowser"))
				 {
					driver= FunctionalLibrary.startBrowser();
				 }else if(ObjectType.equalsIgnoreCase("appUrl"))
				 {
					 FunctionalLibrary.appUrl(driver);
				 }else if(ObjectType.equalsIgnoreCase("waitForElement"))
				 {
					 FunctionalLibrary.waitForElement(driver, Locatorname, Locatorvalue, Testdata);
				 }else if(ObjectType.equalsIgnoreCase("typeAction"))
				 {
					 FunctionalLibrary.typeAction(driver, Locatorname, Locatorvalue, Testdata);
				 }else if(ObjectType.equalsIgnoreCase("clickAction"))
				 {
					 FunctionalLibrary.clickAction(driver, Locatorname, Locatorvalue);
				 }else if(ObjectType.equalsIgnoreCase("validateTitle"))
				 {
					 FunctionalLibrary.validateTitle(driver, Testdata);
				 }else if(ObjectType.equalsIgnoreCase("closeBrowser"))
				 {
					 FunctionalLibrary.closeBrowser(driver);
				 }else if(ObjectType.equalsIgnoreCase("supplierTable"))
				 {
					FunctionalLibrary.supplierTable(driver, Testdata);
				 }else if(ObjectType.equalsIgnoreCase("captureData"))
				 {
					 FunctionalLibrary.captureData(driver, Locatorname, Locatorvalue);
				 }else if(ObjectType.equalsIgnoreCase("captureData1"))
				 {
					 FunctionalLibrary.captureData1(driver, Locatorname, Locatorvalue);
				 }else if(ObjectType.equalsIgnoreCase("customerTable"))
				 {
					 FunctionalLibrary.customerrTable(driver, Testdata);
				 }else if(ObjectType.equalsIgnoreCase("tableValidation"))
				 {
					 FunctionalLibrary.tableValidation(driver, Testdata);
				 }else if(ObjectType.equalsIgnoreCase("mouseAction"))
				 {
					 FunctionalLibrary.mouseAction(driver, Locatorname, Locatorvalue);
				 }
				 
				 //write as pass in status column when testcase pass
				 xl.setCellData(TCmodule, j, 5, "PASS", outputpath);
				modulestatus="True";
			} catch (Exception e) 
			 {
				//write as fail in status when any testcase got fail
				xl.setCellData(TCmodule, j, 5, "FAIL", outputpath);
				modulestatus="False";
			}
			 if(modulestatus.equalsIgnoreCase("True"))
			 {//write as pass in mastertestcase sheet
				 xl.setCellData("MasterTestCases", i, 3, "PASS", outputpath);
			 }
			 if(modulestatus.equalsIgnoreCase("False"))
			 {//write as fail in mastertestcase sheet
				 xl.setCellData("MasterTestCases", i, 3, "FAIL", outputpath);
			 }
		}
	 }else
	 {//write a blocked which are flag to n
		 xl.setCellData("MasterTestCases", i, 3, "BLOCKED", outputpath);
	 }
	}
}
}
