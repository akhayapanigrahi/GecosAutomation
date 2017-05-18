package com.Gecos.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GecosUtil {

	static WebDriver driver;
	
	public static void implicitWait(int seconds){
		
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	public static void explicitWait(int miliSeconds) throws InterruptedException{
		
		Thread.sleep(miliSeconds);
	}
	
	public static void waitForElementLoad(WebElement element) throws InterruptedException {
		try {
			int sec = 0;
			for (sec = 0; sec <= 30; sec++) {
				try {
					Thread.sleep(1000);
					sec++;
					if (element.isEnabled()) {
						//System.out.println(" : ELEMENT IS ENABLED : " + element.getAttribute("name"));
						break;
					}
				} catch (Exception e) {
				}
				//System.out.println(" : ELEMENT NOT YET ENABLED : " + element.getAttribute("name"));
			}
		} catch (Exception e) {
		}
	}
}
