package base;
import configurations.Config;
import configurations.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import static base.PageBase.driver;
import static configurations.Constants.WikiURL;

public class TestBase implements _DriverManager {

    public static Config config = new Config();

    public static WebDriver driver;
    @BeforeSuite
    public void setUpHeadlessDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions ch = new ChromeOptions();
        ch.addArguments("--headless");
        driver = new ChromeDriver(ch);
        driver.get(WikiURL);

    }
    public static WebDriver getDriver() {
       return driver;
    }

}
