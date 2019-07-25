package com.briteERP.pages;

import com.briteERP.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BriteERPLoginPage {

    public BriteERPLoginPage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }

    @FindBy(id="login")
    public WebElement userName;

    @FindBy(id="password")
    public WebElement password;

    @FindBy(css = "button[class='btn btn-primary']")
    public WebElement logInButton;


}
