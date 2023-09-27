package com.guru99.Pages;

import com.guru99.Implementation.ElementControls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class BasePage {

    WebDriver driver;
    ElementControls elementControls;

    public BasePage(WebDriver driver){
        this.driver = driver;
        elementControls = new ElementControls(driver);
    }

}
