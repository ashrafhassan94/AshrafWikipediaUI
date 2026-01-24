package org.Testinium.Pages.Login;
import base.TestBase;
import org.Testinium.Pages.HomePage;
import org.Testinium.Pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.getDriver;


public class WikiLogin extends TestBase {

    @Test
    public void Validate_SuccessfulLogin(){
        HomePage Home = new HomePage(getDriver());
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("omar barakat91");
        Login.InsertPassword("omar@2212");
        //AssertValidLogin
    }
    @Test
    public void Validate_FailLogin_invalid_username_and_password(){
        HomePage Home = new HomePage(getDriver());
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("InValidUsername");
        Login.InsertPassword("InValidPass");
        //Assert inValid Login

    }
    @Test
    public void Validate_FailLogin_invalid_password(){
        HomePage Home = new HomePage(getDriver());
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("omar barakat91");
        Login.InsertPassword("InValidpass");
        //Assert inValid Login

    }
    @Test
    public void Validate_FailLogin_empty_both_fields(){
        HomePage Home = new HomePage(getDriver());
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("");
        Login.InsertPassword("");
        //Assert inValid Login

    }
    @Test
    public void Validate_FailLogin_empty_password(){
        HomePage Home = new HomePage(getDriver());
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("InValidUsername");
        Login.InsertPassword("");
        //Assert inValid Login

    }
    @Test
    public void Validate_FailLogin_empty_username(){
        HomePage Home = new HomePage(getDriver());
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("");
        Login.InsertPassword("omar@2212");
        //Assert inValid Login

    }
    @Test
    public void Validate_session_handling_when_using_the_browser_back_button(){
        HomePage Home = new HomePage(getDriver());
        Home.NavigateToLoginPage();
        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("omar barakat91");
        Login.InsertPassword("omar@2212");
        driver.navigate().back();
        //Validate_session_handling_when_using_the_browser_back_button
        //i cant see if the browser go back or not, i want to add an assertion for this test case
    }
    @Test
    public void Validate_remmember_me_checkbox(){
        HomePage Home = new HomePage(getDriver());
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("omar barakat91");
        Login.InsertPassword("omar@2212");
        driver.findElement(By.id("wpRemember")).click();
        driver.close();
        
        Home.NavigateToLoginPage();

        //i want to Assert remember me checkbox
    }
    @Test
    public void Validate_Brute_force_lockout() {
        HomePage Home = new HomePage(getDriver());
        Home.NavigateToLoginPage();

        LoginPage Login = new LoginPage(getDriver());
        Login.InsertUsername("omar barakat91");
        Login.InsertPassword("InValidpass");
        for (int i = 1; i <= 5; i++) {
            Login.InsertUsername("omar barakat91");
            Login.InsertPassword("InValidpass");
            //Assert Brute-force lockout

        }
    }
}
