package com.briteERP.pages;

import com.briteERP.utilities.BriteUtils;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id="login")
    public WebElement userNameLocator;

    @FindBy(id="password")
    public WebElement passwordLocator;

    @FindBy(css = "button[class='btn btn-primary']")
    public WebElement logInButton;

    public void loginToBriteERP(String username, String password){
        userNameLocator.sendKeys(username);
        passwordLocator.sendKeys(password);
        logInButton.click();
        BriteUtils.waitForPageToLoad(5);
    }

}
