package com.briteERP.tests.crm;

import com.briteERP.pages.LoginPage;
import com.briteERP.pages.CRMPage;
import com.briteERP.utilities.BriteUtils;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.Driver;
import com.briteERP.utilities.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class opportunityTests extends TestBase {

    @Test
    public void crmDeleteTest() {
        //Step1: login to the page
        LoginPage loginPage = new LoginPage();
        loginPage.loginToBriteERP(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        //Step2: navigate to CRM module
        BriteUtils.navigateToModuleBriteERP(Driver.getDriver(),"CRM");

        //Step3: verify the page Title as expected so we are on the correct page.
        BriteUtils.waitFor(5);
        BriteUtils.pageNameVerifyBriteERP(Driver.getDriver(),"Pipeline");

        //Step4: verify the user can see the list view.
        CRMPage crmPage = new CRMPage();
        crmPage.listIconLocator.click();
        BriteUtils.waitForVisibility(crmPage.listTable,3);
        Assert.assertTrue(crmPage.listTable.isDisplayed());

        //Step5: verify that user can delete an opportunity from action drop down list .
        //there is a pre-conditon, so we always have a couple of opportunities in the page.
        //I stored it as a String so I will verify that opportunity is already deleted
        String opportunityName= crmPage.firstOptionOfTable.getText();
        crmPage.firstOptionOfTable.click();//I select the first option always.
        BriteUtils.waitForClickAbility(crmPage.actionButtonLOcator,5);
        crmPage.actionButtonLOcator.click();
        crmPage.selectAction("Delete");
        crmPage.okButtonLocator.click();
        crmPage.verifyThatOpportunityDeleted(opportunityName);
    }
    //Verify that second opportunity’ Expected Revenue value on the Pivot board should be the same
    // as the Expected revenue column value on the list board.

    @Test
    public void pivotListExpectedValueCompareTest(){
        LoginPage loginPage = new LoginPage();
        CRMPage crmPage = new CRMPage();
        //step1: login page
        loginPage.loginToBriteERP(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        //step2: navigate to module
        BriteUtils.navigateToModuleBriteERP(Driver.getDriver(),"CRM");

        //step3: apply the first pre-condition(create 3 opportunities)
        //crmPage.createOptionPreCondition();//in case of need I will run this condition
        //and click on pivot icon then apply the second pre-condition(select the opportunity from total
        crmPage.pivotIconLocator.click();
        crmPage.selectOpportunity();

        //step4: click on total twice and choose opportunity
        crmPage.selectOpportunity();

        //step5: compare the expected value of second opportunity from pivot
        // with list view's second opportunity expected value.
        String value1=crmPage.valueFromPivot.getText();
        crmPage.listIconLocator.click();
        String value2=crmPage.valueFromList.getText();
        Assert.assertEquals(value1,value2);
    }

    @Test
    public void pivotTotalValueAndSumOfValuesCompareTest(){
        LoginPage loginPage =new LoginPage();
        CRMPage crmPage = new CRMPage();
        //Step1: Login to the page
        loginPage.loginToBriteERP(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        //Step2: navigate to module
        BriteUtils.navigateToModuleBriteERP(Driver.getDriver(),"CRM");

        //step3: apply the first pre-condition(create 3 opportunities)
        //crmPage.createOptionPreCondition();
        //and click on pivot icon then apply the second pre-condition(select the opportunity from total
        crmPage.pivotIconLocator.click();
        crmPage.selectOpportunity();

        //step4: store the value from total box
        String totalValue = crmPage.totalValueLocator.getText().replaceAll("\\D+","");
        double value1 = Double.parseDouble(totalValue);
        System.out.println(value1);
        //step5: store all the expected value's from the table and find the sum
        BriteUtils.waitFor(2);
        List<String> list = BriteUtils.getElementsText(crmPage.expectedValueLocatorFromTable);//By.cssSelector("table>tbody>tr>td:nth-of-type(2)"));
        double value2 = crmPage.sumOfElements(list);
        System.out.println(value2);
        //Step6: verify that total value is equal to sum of all values in the table
        Assert.assertEquals(value1,value2);
    }
}
