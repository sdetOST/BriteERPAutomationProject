package com.briteERP.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BriteUtils {
//this method stops the operation and waits until the amount of time
    public static void waitPlease(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//this method verifies if the opened page TITLE as expected
    public static void pageTitleVerifyBriteERP(WebDriver driver, String expectedTitle){
        String actualTitle = driver.getTitle();
        waitPlease(5);
        Assert.assertEquals(expectedTitle,actualTitle);
    }
//this method verifies the opened page NAME as expected
    public static void pageNameVerifyBriteERP(WebDriver driver, String expectedName){
        String pageNameLocator ="//div//div//ol//li[@class='active']";
        waitForVisibility(By.xpath(pageNameLocator),5);
        String actualName = driver.findElement(By.xpath(pageNameLocator)).getText();
        Assert.assertEquals(expectedName,actualName);
    }
//this method opens the module
    public static void navigateToModuleBriteERP(WebDriver driver, String module){
        String moduleLocator = "//a[@class='oe_menu_toggler']//span[@class='oe_menu_text'][contains(text(),'"+module+"')]";
        Driver.getDriver().findElement(By.xpath(moduleLocator)).click();
        waitForVisibility(Driver.getDriver().findElement(By.xpath(moduleLocator)),5);
    }
//this method Waits for the provided element to be visible on the page
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

//this method Waits for element matching the locator to be visible on the page
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

//this method Waits for provided element to be clickable
    public static WebElement waitForClickAbility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

//this method waits for backgrounds processes on the browser to complete
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
    try {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
        error.printStackTrace();
            }
    }


}
