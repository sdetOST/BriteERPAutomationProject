package com.briteERP.pages;

import com.briteERP.utilities.BriteUtils;
import com.briteERP.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class CRMPage {

    public CRMPage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }
    @FindBy(css="div[class='btn-group btn-group-sm o_cp_switch_buttons']>button[accesskey='l']")
    public WebElement listIconLocator;

    @FindBy(css = "table[class^='o_list_view table table-condensed']")
    public WebElement listTable;

    @FindBy(css = "table[class^='o_list_view table table-condensed']>tbody>tr")
    public WebElement firstOptionOfTable;

    @FindBy(xpath="//button[contains(text(),'Action')]")
    public WebElement actionButtonLOcator;

    @FindBy(css = "div[class='modal-footer']>button>span")
    public WebElement okButtonLocator;

    @FindBy(xpath = "//ol[@class='breadcrumb']//a[contains(text(),'Pipeline')]")
    public WebElement pageNameLocator;

    @FindBy(xpath = "//li[@class='active']//span[@class='oe_menu_text'][contains(text(),'Pipeline')]")
    public WebElement pipelinePage;

    public void selectAction(String actionName){
        String optionLocator = "//a[contains(@data-section,'other') and contains(text(),'"+actionName+"')]";
        BriteUtils.waitForVisibility(Driver.getDriver().findElement(By.xpath(optionLocator)),5);
        Driver.getDriver().findElement(By.xpath(optionLocator)).click();
    }
    public void verifyThatOpportunityDeleted(String opportunity){
        String locator = "//td[text()='"+opportunity+"']";
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath(locator));
        Assert.assertTrue(elements.isEmpty());
    }
}
