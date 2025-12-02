package com.itlearn.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//a[contains(@href,'dashboard') and normalize-space()='Dashboard']")
    private WebElement dashboardclick;

    @FindBy(xpath = "//*[@id='learn-press-user-profile']/ul/li[3]")
    private WebElement offerAcademies;

    // precise text-based locator for Subscribe button
    @FindBy(xpath = "//button[normalize-space()='Subscribe Now']")
    private WebElement subscribeButton;

    // SAFE CLICK helper: waits for overlays to vanish, scrolls into view with upward nudge,
    // tries regular click, Actions click, JS click, and finally hides header and retries.
    private void safeClick(WebElement element) {
        // 1) wait briefly for common overlays/backdrops to disappear
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-backdrop")));
        } catch (TimeoutException ignored) {}

        // wait for sticky header to be not blocking if possible
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("header-sroll")));
        } catch (TimeoutException ignored) {}

        // 2) scroll into view and nudge page up so sticky header won't cover it
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            // small upward nudge (adjust px if needed)
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -90);");
            Thread.sleep(150);
        } catch (InterruptedException | WebDriverException ignored) {}

        // 3) try normal click (preferred)
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            return;
        } catch (Exception e1) {
            // continue to fallbacks
        }

        // 4) try Actions moveToElement click
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).pause(Duration.ofMillis(120)).click().build().perform();
            return;
        } catch (Exception e2) {
            // continue
        }

        // 5) try JS click
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            return;
        } catch (Exception e3) {
            // continue to last-resort
        }

        // 6) Last resort: temporarily hide sticky header (test-only) and retry JS click
        try {
            // only do this if header exists
            WebElement header = null;
            try {
                header = driver.findElement(By.id("header-sroll"));
            } catch (NoSuchElementException nee) {
                header = null;
            }

            if (header != null) {
                // hide header
                ((JavascriptExecutor) driver).executeScript("document.getElementById('header-sroll').style.display='none';");
                Thread.sleep(100);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                // restore header (best-effort)
                ((JavascriptExecutor) driver).executeScript("document.getElementById('header-sroll').style.display='block';");
                return;
            }
        } catch (Exception finalEx) {
            // rethrow a readable runtime exception
            throw new RuntimeException("safeClick: unable to click element. Last error: " + finalEx.getMessage(), finalEx);
        }

        // if we reach here we couldn't click
        throw new RuntimeException("safeClick: all click attempts failed for element: " + element);
    }

    public void dashboardClick() {
        // ensure dashboard link visible and click it
        wait.until(ExpectedConditions.visibilityOf(dashboardclick));
        safeClick(dashboardclick);

        // ensure offerAcademies visible and click it
        wait.until(ExpectedConditions.visibilityOf(offerAcademies));
        safeClick(offerAcademies);

        // ensure subscribe button visible and click using safeClick
        wait.until(ExpectedConditions.visibilityOf(subscribeButton));
        safeClick(subscribeButton);
    }
}
