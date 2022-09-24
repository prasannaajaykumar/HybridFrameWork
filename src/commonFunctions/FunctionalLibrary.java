package commonFunctions;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.PropertyUtilFile;
public class FunctionalLibrary {
	public static WebDriver driver;
	//method for launching browser
	public static WebDriver startBrowser() throws Throwable 
	{
		if(PropertyUtilFile.getValueforKey("Browser").equalsIgnoreCase("chrome"))
		{ 

			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}else if(PropertyUtilFile.getValueforKey("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			driver.manage().deleteAllCookies();
		}else
		{
			System.out.println("browser value is not matching");
		}
		return driver;
	}

	//launch url
	public static void appUrl(WebDriver driver) throws Throwable {

		driver.get(PropertyUtilFile.getValueforKey("Url"));
	}

	//wait for element
	public static void waitForElement(WebDriver driver,String locatorname,String locatorvalue,String testdata) {
		WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(testdata));
		if(locatorname.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}else if(locatorname.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		}else if(locatorname.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		}else {
			System.out.println("unable to execute wait for element method");
		}
	}
	//method for type action
	public static void typeAction(WebDriver driver,String locatorname,String locatorvalue,String testdata) {
		if(locatorname.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}else if(locatorname.equalsIgnoreCase("id")) 
		{
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}else if(locatorname.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}else
		{
			System.out.println("unable to perform typeaction method");
		}
	}

	//method for clicking buttons
	public static void clickAction(WebDriver driver,String locatorname,String locatorvalue) {
		if(locatorname.equalsIgnoreCase("id")) 
		{
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}else if(locatorname.equalsIgnoreCase("xpath")) 
		{
			driver.findElement(By.xpath(locatorvalue)).click();
		}else if(locatorname.equalsIgnoreCase("xpath")) 
		{
			driver.findElement(By.xpath(locatorvalue)).click();
		}else
		{
			System.out.println("unable to perform click action method");
		}
	}

	//method for validate pagetitle
	public static void validateTitle(WebDriver driver,String testdata) {
		String actualtitle=driver.getTitle();
		try {
			Assert.assertEquals(testdata, actualtitle,"Title is not matching");
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	//close browser
	public static void closeBrowser(WebDriver driver) {
		driver.close();
	}

	//method for capturing supplier number
	public static void captureData(WebDriver driver,String locatorname,String locatorvalue) throws Throwable {
		String  befnumber="";
		if(locatorname.equalsIgnoreCase("id")) {
			befnumber=driver.findElement(By.id(locatorvalue)).getAttribute("Value");
		}else if(locatorname.equalsIgnoreCase("xpath")) {
			befnumber=driver.findElement(By.xpath(locatorvalue)).getAttribute("Value");
		}
		//create notepad
		File f=new File("C:\\PROJECT TESTING\\Hybrid_Framework\\CapatureData\\supi1.txt");
		FileWriter fw=new FileWriter(f);
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(befnumber);
		bw.flush();
		bw.close();
	}

	//method for supplier table validation
	public static void supplierTable(WebDriver driver,String testdata) throws Throwable {
		//read supplier number from notepad

		FileReader fr=new FileReader("C:\\PROJECT TESTING\\Hybrid_Framework\\CapatureData\\supi1.txt");
		BufferedReader br=new BufferedReader(fr);
		String ExpSnumber=br.readLine();

		//convert test cell data into integer type
		int columnNum=Integer.parseInt(testdata);
		if(!driver.findElement(By.name(PropertyUtilFile.getValueforKey("searchtextbox"))).isDisplayed())
			//click search panel
			driver.findElement(By.xpath(PropertyUtilFile.getValueforKey("searchpanel"))).click();
		Thread.sleep(4000);
		driver.findElement(By.name(PropertyUtilFile.getValueforKey("searchtextbox"))).clear();
		Thread.sleep(4000);
		driver.findElement(By.name(PropertyUtilFile.getValueforKey("searchtextbox"))).sendKeys(ExpSnumber);
		Thread.sleep(4000);
		driver.findElement(By.xpath(PropertyUtilFile.getValueforKey("searchbutton"))).click();
		//capture supplier number from table
		String ActualSnumber=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
		System.out.println(ExpSnumber+"   "+ActualSnumber);	
		Assert.assertEquals(ActualSnumber, ExpSnumber,"Supplier number not matching");

	}
	//method for capturing customer number
		public static void captureData1(WebDriver driver,String locatorname,String locatorvalue) throws Throwable {
			String  befnumber="";
			if(locatorname.equalsIgnoreCase("id")) {
				befnumber=driver.findElement(By.id(locatorvalue)).getAttribute("Value");
			}else if(locatorname.equalsIgnoreCase("xpath")) {
				befnumber=driver.findElement(By.xpath(locatorvalue)).getAttribute("Value");
			}
			//create notepad
			File f=new File("C:\\PROJECT TESTING\\Hybrid_Framework\\CapatureData\\cust.txt");
			FileWriter fw=new FileWriter(f);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(befnumber);
			bw.flush();
			bw.close();
		}
		
		public static void customerrTable(WebDriver driver,String testdata) throws Throwable {
			//read scustomer number from notepad

			FileReader fr=new FileReader("C:\\PROJECT TESTING\\Hybrid_Framework\\CapatureData\\cust.txt");
			BufferedReader br=new BufferedReader(fr);
			String ExpSnumber=br.readLine();

			//convert test cell data into integer type
			int columnNum=Integer.parseInt(testdata);
			if(!driver.findElement(By.name(PropertyUtilFile.getValueforKey("searchtextbox"))).isDisplayed())
				//click search panel
				driver.findElement(By.xpath(PropertyUtilFile.getValueforKey("searchpanel"))).click();
			Thread.sleep(4000);
			driver.findElement(By.name(PropertyUtilFile.getValueforKey("searchtextbox"))).clear();
			Thread.sleep(4000);
			driver.findElement(By.name(PropertyUtilFile.getValueforKey("searchtextbox"))).sendKeys(ExpSnumber);
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyUtilFile.getValueforKey("searchbutton"))).click();
			//capture supplier number from table
			String ActualSnumber=driver.findElement(By.xpath("//table[@id='tbl_a_customerslist']/tbody/tr[1]/td[5]/div/span/span")).getText();
			System.out.println(ExpSnumber+"   "+ActualSnumber);	
			Assert.assertEquals(ActualSnumber, ExpSnumber,"Supplier number not matching");

		}
		
		//method for mouseAction
		public static void mouseAction(WebDriver driver,String locatorname,String locatorvalue) {
			Actions ac=new Actions(driver);
			ac.moveToElement(driver.findElement(By.xpath(locatorvalue))).perform();
		}
		
		//method for tableValidation
		public static void tableValidation(WebDriver driver,String testdata ) throws Throwable {
			if(!driver.findElement(By.name(PropertyUtilFile.getValueforKey("searchtextbox"))).isDisplayed())
{
	driver.findElement(By.xpath(PropertyUtilFile.getValueforKey("searchpanel"))).click();
	Thread.sleep(4000);
	driver.findElement(By.name(PropertyUtilFile.getValueforKey("searchtextbox"))).sendKeys(testdata);
	Thread.sleep(4000);
	driver.findElement(By.xpath(PropertyUtilFile.getValueforKey("searchbutton"))).click();
	Thread.sleep(4000);
	
	String Actualnumber=driver.findElement(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr[1]/td[4]/div/span/span")).getText();
	Assert.assertEquals(Actualnumber, testdata ,"Both are not matching");
	System.out.println(Actualnumber+"   " +testdata);
}
		}



	//method for generate reports
	public static String generateDate() {
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("YYYY_MM_DD_HH_mm_SS");
		return df.format(date);

	}

}

