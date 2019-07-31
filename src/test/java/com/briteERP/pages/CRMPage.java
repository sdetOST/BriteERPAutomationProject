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

    public CRMPage(){

        PageFactory.initElements(Driver.getDriver(), this);

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

//    @FindBy(xpath = "//ol[@class='breadcrumb']//a[contains(text(),'Pipeline')]")
//    public WebElement pageNameLocator;
//
//    @FindBy(xpath = "//li[@class='active']//span[@class='oe_menu_text'][contains(text(),'Pipeline')]")
//    public WebElement pipelinePage;

    @FindBy(css = "button[aria-label='pivot']")
    public WebElement pivotIconLocator;

    @FindBy(css = "td[class='o_pivot_header_cell_opened']:nth-of-type(1)")
    public WebElement totalBoxLocator1;

    @FindBy(css = "td[class='o_pivot_header_cell_closed']:nth-of-type(1)")
    public WebElement totalBoxLocator2;

    @FindBy(css = "body>nav+div>div+div> div+div>div>div>div>ul>li[data-field='name']>a")//"//div[@class='o_field_selection']//ul//li[25]//a")
    public WebElement opportunitySelectionLocator;

    @FindBy(css ="table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)")//"//table[@class='table-hover table-condensed table-bordered']//tbody//tr[3]//td[2]")
    public WebElement valueFromPivot;

    @FindBy(css = "table>tbody>tr:nth-of-type(2)>td:nth-of-type(9)")//"//table[@class='o_list_view table table-condensed table-striped o_list_view_ungrouped']//tbody//tr[2]//td[9]")
    public  WebElement valueFromList;

    @FindBy(css = "button[accesskey='c']")
    public WebElement createButtonLocator;

    @FindBy(css = "input[name='name']")
    public WebElement opportunityNameLocator;

    @FindBy(css = "input[name='planned_revenue']")
    public WebElement expectedRevenueLocator;

    @FindBy(css = "button[name='close_dialog']")
    public WebElement createButton2Locator;

    @FindBy(css = "table>tbody>tr:first-of-type>td:nth-of-type(2)")
    public WebElement totalValueLocator;

    @FindBy(css = "table>tbody>tr>td:nth-of-type(2)")
    public List<WebElement> expectedValueLocatorFromTable;

    //this method will select the action for a specific opportunity from the action's dropdown menu
    public void selectAction(String actionName){
        String optionLocator = "//a[contains(@data-section,'other') and contains(text(),'"+actionName+"')]";
        BriteUtils.waitForVisibility(Driver.getDriver().findElement(By.xpath(optionLocator)),5);
        Driver.getDriver().findElement(By.xpath(optionLocator)).click();
    }

    //this method verifies whether the opportunity is deleted
    public void verifyThatOpportunityDeleted(String opportunity){
        String locator = "//td[text()='"+opportunity+"']";
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath(locator));
        Assert.assertTrue(elements.isEmpty());
    }

    //this method creates 3 opportunity as a first pre-condition of the test.
    public void createOptionPreCondition(){
        for(int i=0;i<3;i++){
            createButtonLocator.click();
            opportunityNameLocator.sendKeys(i+"Person"+ ++i);
            expectedRevenueLocator.clear();
            double num = 1000.00+i*100;
            expectedRevenueLocator.sendKeys(num+"");
            //BriteUtils.clickWithWait(Driver.getDriver(),createButton2Locator,5);
            BriteUtils.waitFor(2);
            --i;
        }
    }

    //this method selects Opportunity from total's menu as a second pre-condition of the test
    public void selectOpportunity(){
        totalBoxLocator1.click();
        totalBoxLocator2.click();
        opportunitySelectionLocator.click();
    }

    //this method finds the sum of Web Elements in the String list
    public double sumOfElements(List<String> list){
        double sum=0;
        for(int i=1; i<list.size();i++){
            String nums=list.get(i).replaceAll("\\D+","");
            sum = sum+Math.round(Double.parseDouble(nums)*100.0)/100.0;
        }

        return sum;
    }
}
