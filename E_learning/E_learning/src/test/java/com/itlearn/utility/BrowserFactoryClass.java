package com.itlearn.utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactoryClass {

    public static WebDriver startApplication(WebDriver driver, String browserName, String appUrl) {

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();   // Auto matches Chrome version
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
        else {
            throw new RuntimeException("Browser not supported: " + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(appUrl);

        return driver;
    }
}

	
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}
}

