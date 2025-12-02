package com.itlearn.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.itlearn.utility.BrowserFactoryClass;
import com.itlearn.utility.ConfigDataProvider;

public class BaseTest {
	public WebDriver driver;
	public ConfigDataProvider config =new ConfigDataProvider();

	@BeforeClass
	public void setUp() {
		driver=BrowserFactoryClass.startApplication(driver,config.getBrowser(),config.getStagingUrl());
	}
	
	@AfterClass
	public void tearDown() {
		BrowserFactoryClass.quitBrowser(driver);
	}
	
	
	public void captureScreenShot(WebDriver driver ,String testName)throws IOException{
		//convert into take screen shot web
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		//for caturing screenshot
		File src =screenshot.getScreenshotAs(OutputType.FILE);
		File srcpath=new File("."+ "//Screenshots//"+testName+".png");
		System.out.println("This is screenshot");
//		File srcpath=new File ("C:\\Users\\DELL\\eclipse-workspace\\E_learning\\Screenshots\\verifylogin.png");
		//copy image file to destination 
		FileUtils.copyFile(src,srcpath);
		
		
		
	}
	
	
	
	
	
	
	
}
