package com.itlearn.pages;

import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
	WebDriver driver;
	
	//contsructor 
	public LoginPage(WebDriver lDriver) {
		this.driver =lDriver;
		PageFactory.initElements(driver,this );
	}
@FindBy(xpath="//*[@id=\"loginlabel\"]") WebElement loginclick;

@FindBy(name="log") WebElement unname;

@FindBy(name="pwd") WebElement pass;

@FindBy(name="wp-submit") WebElement loginButton;

@FindBy(xpath = "//*[@id=\"gk-login-toggle\"]/i")   WebElement logoutimage;

//@FindBy(xpath = "//*[@id=\"login_drop_panel\"]/div/ul/li[3]/a") WebElement logoutclick;


public void loginPortal(String username,String password) throws InterruptedException {
	  loginclick.click();
      unname.sendKeys("Demo12");   // <-- Correct
      pass.sendKeys("Test123456$"); 
      
  	Thread.sleep(3000);
      loginButton.click();
	
	
}

public void logout() throws InterruptedException {
	Thread.sleep(3000);
//    logoutimage.click();
//    logoutclick.click();
}



}
