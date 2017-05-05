package com.itc.util;

/*
* Copyright @ QA All rights Reserved.
* @version 1.0, April, 2017
* @author Akshaya Kumar Panigrahi
*
* Description: This class contains all the commonly used methods which will be used by browsers
* Common methods related to opening Browser, Closing browser, Closing Connections,pop ups..
* Basically these methods will be used for start and termination time for browser level operations..
*
*
*/
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseTestObject {

	protected static WebDriver driver;
	public static String propertyFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testData.properties";
	public static String chromeDriverPath = System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
	//public static String firefoxDriverPath = System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\geckodriver.exe";

	FileInputStream fileInput =null;
	//Properties ObjProperty = getPropertyContents();
	public Properties ObjProperty=getPropertyContents();
	
	public String browser = ObjProperty.getProperty("browser");
	public String url = ObjProperty.getProperty("url");

	 VideoRecord test=new VideoRecord();
	
	/**
	 
     * This function will execute before each Test tag in testng.xml
 
     * @param browser
 
     * @throws Exception
 
     */
	private static final Properties prop = new Properties();

	private static void loadPropertiesFile() 
	{
		InputStream input = null;

		try
		{
			input = new FileInputStream(propertyFilePath);
			// load a properties file
			prop.load(input);
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			if (input != null) 
			{
				try
				{
					input.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static Properties getPropertyContents() {
		loadPropertiesFile();
		return prop;
	}
	
	
	@Parameters({"browserType"})
	//@BeforeSuite(alwaysRun = true)
	@BeforeClass(alwaysRun = true)
    public void setup(String browser) throws Exception
	{
        if(browser.equalsIgnoreCase("FF"))
        {
            System.setProperty("webdriver.gecko.driver","D:\\MyWorkspace\\GecosAutomation\\src\\test\\resources\\drivers\\geckodriver.exe");

            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("GC"))
        {
            System.setProperty("webdriver.chrome.driver",chromeDriverPath);
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver","C:\\IEdriver.exe");
            driver = new InternetExplorerDriver();
        }
        else
        {
        	throw new Exception("Browser is not correct");
        }
       
       test.startRecording();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
        driver.manage().window().maximize();
        
}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws Exception{
		driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception{
		//ReportSending.execute("testaynaxdemo@gmail.com","testaynaxdemo@gmail.com");
		//driver.quit();
       test.stopRecording();
	}
	
	public void closePopUp() throws InterruptedException{
		String parent = driver.getWindowHandle();
		Set<String>pops=driver.getWindowHandles();
        {
        Iterator<String>it = pops.iterator();
        while (it.hasNext()) {
            String popupHandle=it.next().toString();
            if(!popupHandle.contains(parent))
            {
            driver.switchTo().window(popupHandle);
            System.out.println("Pop Up Title: "+ driver.switchTo().window(popupHandle).getTitle());
            driver.close();
            Thread.sleep(5000);
            }
        }
	}
	}
}
