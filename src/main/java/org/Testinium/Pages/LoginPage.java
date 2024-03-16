package org.Testinium.Pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath ="//input[@id='wpName1']")
    @CacheLookup
    WebElement UserNameBox;

    public void InsertUsername(String Username) {
        writeKeys(UserNameBox,Username);
    }


    @FindBy(xpath ="//input[@id='wpPassword1']")
    @CacheLookup
    WebElement PasswordBox;

    public void InsertPassword(String Password) {
        writeKeys(PasswordBox,Password);
    }

    @FindBy(xpath ="//button[@id='wpLoginAttempt']")
    @CacheLookup
    WebElement HitLogoin;

    public void NavigateToLoginPage() {
        clickOnBtn(HitLogoin);
    }


}
