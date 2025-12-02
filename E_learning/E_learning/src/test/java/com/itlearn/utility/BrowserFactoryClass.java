package com.itlearn.utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactoryClass {
	public static WebDriver startApplication(WebDriver driver,String browserName,String appUrl)
	{
		
		   if (browserName.equalsIgnoreCase("Chrome")) {
		        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDriver\\chromedriver.exe");
		        driver = new ChromeDriver();
		    }
		else if(browserName.equals("Firefox"))
		{
			System.setProperty("webdriver.firefox.driver", "./Drivers/chromedriver.exe");
	         driver = new FirefoxDriver();
		}
		else if(browserName.equals("IE"))
		{
			System.setProperty("webdriver.edge.driver", "./Drivers/chromedriver.exe");
	         driver = new InternetExplorerDriver();
		}
		else
		{
			System.out.println("We do not support this browser ");
		}

		
		driver.manage().window().maximize();
		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		return driver;

	}
	
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}
}
