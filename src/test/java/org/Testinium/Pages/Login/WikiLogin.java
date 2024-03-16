package org.Testinium.Pages.Login;
import base.TestBase;
import org.Testinium.Pages.HomePage;
import org.Testinium.Pages.LoginPage;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.getDriver;


public class WikiLogin extends TestBase {

    @Test
    public void Validate_SuccessfulLogin(){
        HomePage Home = new HomePage(getDriver());
        getDriver().manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("ValidUsername");
        Login.InsertPassword("ValidPass");
        //AssertValidLogin
    }

    @Test
    public void Validate_FailesLogin(){
        HomePage Home = new HomePage(getDriver());
        getDriver().manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("InValidUsername");
        Login.InsertPassword("InValidPass");
        //AssertValidLogin

    }

}
