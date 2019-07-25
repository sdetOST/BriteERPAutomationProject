package com.briteERP.tests.crm;

import com.briteERP.pages.BriteERPLoginPage;
import com.briteERP.pages.CRMPage;
import com.briteERP.utilities.BriteUtils;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.Driver;
import com.briteERP.utilities.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class opportunityTests extends TestBase {

    @Test
    public void crmDeleteTest() {
        //Step1: login to the page
        BriteERPLoginPage loginPage = new BriteERPLoginPage(Driver.getDriver());
        loginPage.userName.sendKeys(ConfigurationReader.getProperty("username"));
        loginPage.password.sendKeys(ConfigurationReader.getProperty("password"));
        loginPage.logInButton.click();

        //Step2: navigate to CRM module
        BriteUtils.waitPlease(5);
        BriteUtils.navigateToModuleBriteERP(Driver.getDriver(),"CRM");

        //Step3: verify the page Title as expected so we are on the correct page.
        BriteUtils.waitPlease(5);
        BriteUtils.pageNameVerifyBriteERP(Driver.getDriver(),"Pipeline");

        //Step4: verify the user can see the list view.
        CRMPage crmPage = new CRMPage(Driver.getDriver());
        crmPage.listIconLocator.click();
        BriteUtils.waitForVisibility(crmPage.listTable,3);
        Assert.assertTrue(crmPage.listTable.isDisplayed());

        //Step5: verify that user can delete an opportunity from action drop down list .
        //there is a pre-conditon, so we always have a couple of opportunities in the page.
        String opportunityName= crmPage.firstOptionOfTable.getText();//I stored it as a String so I will verify that opportunity is already deleted
        crmPage.firstOptionOfTable.click();//I select the first option always.
        BriteUtils.waitForClickAbility(crmPage.actionButtonLOcator,5);
        crmPage.actionButtonLOcator.click();
        BriteUtils.waitPlease(2);
        crmPage.selectAction("Delete");
        BriteUtils.waitPlease(2);
        crmPage.okButtonLocator.click();
        crmPage.verifyThatOpportunityDeleted(opportunityName);
    }
}
