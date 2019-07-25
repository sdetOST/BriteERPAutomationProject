package com.briteERP.tests.login;

import com.briteERP.pages.BriteERPLoginPage;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.Driver;
import com.briteERP.utilities.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase {

    @Test
    public void test1(){
        BriteERPLoginPage loginPage = new BriteERPLoginPage(Driver.getDriver());
        loginPage.userName.sendKeys(ConfigurationReader.getProperty("username"));
        loginPage.password.sendKeys(ConfigurationReader.getProperty("password"));
        loginPage.logInButton.click();
    }
}
