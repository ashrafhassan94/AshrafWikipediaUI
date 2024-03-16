package org.Testinium.Pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // locators

    @FindBy(xpath ="//input[@placeholder='Search Wikipedia']")
    @CacheLookup
    WebElement SearchBox;

    @FindBy(xpath ="/html[1]/body[1]/div[1]/header[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/button[1]")
    @CacheLookup
    WebElement HitSearch;


    @FindBy(xpath ="//span[@id='On_this_day']")
    @CacheLookup
    WebElement OnThisDay;


    @FindBy(xpath ="//li[@id='pt-login-2']")
    @CacheLookup
    WebElement LoginIcon;

    // Methods

    public void SearchForTopic(String TopicName) {
        writeKeys(SearchBox,TopicName);
        clickOnBtn(HitSearch);
    }
    public void ClearThenSearchForTopic(String TopicName) {
        clearText(SearchBox);
        writeKeys(SearchBox,TopicName);
        clickOnBtn(HitSearch);
    }

    public void MovetoOnThisDay() {
        scrollToTheElement(OnThisDay);
    }

    public void NavigateToLoginPage() {
        clickOnBtn(LoginIcon);
    }
}
