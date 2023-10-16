package com.virtusa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']") public WebElement cookieAccept;

    @FindBy(xpath = "//img[@class='coh-image coh-image-responsive-xl' and contains(@alt,'Automation Anywhere')]") public WebElement logoElement;
    @FindBy(xpath = "//a[@title='Request Demo' and contains(@class,'coh-link utility-nav-link coh-style-solid-orange-button')]") public WebElement btnRequestDemo;

    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

}
