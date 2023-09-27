package com.guru99.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    @CacheLookup //improves performance as the name=uid never changes
    @FindBy(name="uid")
    private WebElement userId;

    @CacheLookup
    @FindBy(name="password")
    private WebElement userPassword;

    @CacheLookup
    @FindBy(name="btnLogin")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void loginToApplication(String userName, String password) {

        elementControls.setText(userId, userName);
        elementControls.setText(userPassword, password);
        elementControls.clickElement(loginButton);

    }
}
