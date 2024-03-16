package base;
import configurations.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import static base.PageBase.driver;

public class TestBase implements _DriverManager {

    public static Config config = new Config();


    public static WebDriver getDriver() {
        return driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

}
