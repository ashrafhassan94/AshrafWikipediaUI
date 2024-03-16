package org.Testinium.Pages.Login;
import base.TestBase;
import org.Testinium.Pages.HomePage;
import org.Testinium.Pages.LoginPage;
import org.Testinium.Pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static base.TestBase.getDriver;

public class WikiSearch extends TestBase{

    @Test
    public void Validate_Search_Journey() throws InterruptedException {
        HomePage Home = new HomePage(getDriver());
        getDriver().manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        Home.SearchForTopic("fifa WC 2002");


        SearchPage Search = new SearchPage();
        Assert.assertTrue(Search.CheckIfSearchReturnsArticle());

    }

    @Test
    public void Validate_Search_Journey_forDummyWords() throws InterruptedException {
        HomePage Home = new HomePage(getDriver());
        getDriver().manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        Home.SearchForTopic("hhghgg");
        //AssertNewPageTitle or element located
        SearchPage Search = new SearchPage();
        Assert.assertFalse(Search.CheckIfSearchReturnsArticle());

    }
}
