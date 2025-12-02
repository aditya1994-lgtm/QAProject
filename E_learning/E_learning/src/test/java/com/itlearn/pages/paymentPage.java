package com.itlearn.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class paymentPage {

    WebDriver driver;

    public paymentPage(WebDriver lDriver) {
        this.driver = lDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='learn-press-payment']/ul/li/label")
    WebElement paystripeclick;

    @FindBy(xpath = "//input[@value='Place order']\r\n"
    		+ "")
    WebElement placeorderbtn;

    @FindBy(xpath = "//*[@id='card-element']/div/iframe")
    WebElement frameelement;

    @FindBy(name = "cardnumber")
    WebElement cardnum;

    @FindBy(name = "exp-date")
    WebElement expdate;

    @FindBy(name = "cvc")
    WebElement cvc;

    @FindBy(id = "payment-button")
    WebElement paybtn;

    private void scrollPage(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ");");
    }

    public void paymentOption(String cardnumber, String exp, String cvcnum) throws InterruptedException {

        paystripeclick.click();
        scrollPage(500);   // <-- SCROLL AFTER STRIPE CLICK

        placeorderbtn.click();

        driver.switchTo().frame(frameelement);
        cardnum.sendKeys("5085460031878112");
        expdate.sendKeys("11/36");
        cvc.sendKeys("786");

        driver.switchTo().defaultContent();

        paybtn.click();
        Thread.sleep(5000);
    }
}
