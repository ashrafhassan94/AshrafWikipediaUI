package org.Testinium.Pages;
import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends PageBase{
    public SearchPage() {
        super(driver);
    }
    @FindBy(xpath ="//span[contains(text(),'Article')]")
    @CacheLookup
    WebElement SuccessfulSearchAssertion;

    @FindBy(xpath ="//p[contains(text(),'There were no results matching the query.')]")
    @CacheLookup
    WebElement FailedSearchAssertion;


    public boolean CheckIfSearchReturnsArticle() throws InterruptedException {
        return ifElementLocatedReturn(FailedSearchAssertion);
    }
}
