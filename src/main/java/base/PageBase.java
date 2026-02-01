package base;


import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.testng.Assert.fail;

public class PageBase {

    protected static WebDriverWait wait;
    private final long TIMEOUT = 20;
    private final long POLLING = 5000;
    private final JavascriptExecutor js;
    protected static WebDriver driver;

    public PageBase(WebDriver driver) {
        wait = new WebDriverWait(driver, ofSeconds(TIMEOUT) , ofMillis(POLLING));
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(0));
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    /**
     * A method to send a string to a text field.
     * @param element is the element to send the key to.
     * @param text is the string to be sent.
     */
    protected void writeKeys(WebElement element, String text) {
        // check the element is allocated.
        isElementLocated(element);
        try {
            element.sendKeys(text);
            // re type using js in case of the data was not sent correctly.
            if (! element.getAttribute("value").equals(text) ) {
                js.executeScript("arguments[0].setAttribute('value', '" + text + "')", element);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    protected void scrollToTheEnd() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    protected void scrollToTheElement(WebElement element) {

        // check the element is allocated.
        isElementLocated(element);
        try {
            js.executeScript("arguments[0].scrollIntoView();", element);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    protected void clearText(WebElement element) {
        element.clear();
    }


    /**
     * A method to check if the element is allocated or not.
     *
     * @param element is the web element you want to check if it is allocated or not.
     */
    protected void isElementLocated(WebElement element) {
        try {
            // wait to get the element visible
            wait.until(ExpectedConditions.visibilityOf(element));
            // scroll to the element.
            js.executeScript("arguments[0].scrollIntoView();", element);
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        }
    }
    protected boolean ifElementLocatedReturn(WebElement element) {
        try {
            // wait to get the element visible
            wait.until(ExpectedConditions.visibilityOf(element));
            // scroll to the element.
            js.executeScript("arguments[0].scrollIntoView();", element);
            //  element.isDisplayed();
            return false;
        } catch (TimeoutException toe) {
            return true;
        }
    }

    /**
     * A method which helps to click on the element using either selenium or js.
     *
     * @param element is the web element.
     */
    protected void clickOnBtn(WebElement element) {
        // make sure that the element allocated successfully.
        isElementLocated(element);
        // wait to get the element clickable.
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        // click using selenium
        try {
            element.click();
        } catch (Exception exception) {
            // we will use javascript click.
            try {
                js.executeScript("arguments[arguments.length - 1].click()", element);
            } catch (Exception root) {
                fail(root.getMessage());
            }
        }
    }

    protected void selectFromDropDown(WebElement element, String optionToBeSelected ) {
        // make sure that the dropdown element allocated successfully.
        isElementLocated(element);
        // wait to get the element clickable.
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        // Select using selenium
        // Create Select object
        Select dropdown = new Select(element);

        // Select option by different methods

        try {
            dropdown.selectByVisibleText(optionToBeSelected);
        } catch (Exception root) {
                fail(root.getMessage());
            }

    }

    protected void closeAllPages(WebDriver mainDriver){
        mainDriver.quit();
    }

    protected void closeCurrentPage(WebDriver mainDriver){
        mainDriver.close();
    }
    // available only starting from selenium4, since we upgraded we can use it
    protected void openNewTab(WebDriver mainDriver){
        mainDriver.switchTo().newWindow(WindowType.TAB);
    }

    protected void openNewWindow(WebDriver mainDriver){
        mainDriver.switchTo().newWindow(WindowType.WINDOW);
    }

    protected void addCookie(WebDriver mainDriver, Cookie cookieName){
        mainDriver.manage().addCookie(cookieName);
    }

    protected void deleteCookie(WebDriver mainDriver, String cookieName){
        mainDriver.manage().deleteCookieNamed(cookieName);
    }

    protected void deleteAllCookies(WebDriver mainDriver){
        mainDriver.manage().deleteAllCookies();
    }
    protected Set<Cookie> getALLCookies(WebDriver mainDriver){
       return mainDriver.manage().getCookies();
    }

    protected List<String> getALLCookiesNamesAsListOfStrings(WebDriver mainDriver){
        List<String> cookiesList= new ArrayList<>();
        Set<Cookie> cookieSet =new HashSet<>(mainDriver.manage().getCookies());
        for (Cookie cookie :cookieSet)
        {
            cookiesList.add(cookie.getName());
        }
        return cookiesList;
    }

    protected void setCurrentWindowPosition(int x, int y , WebDriver mainDriver){
        Point point = new Point(x,y);
        mainDriver.manage().window().setPosition(point);
    }

    protected int getCurrentWindowXPosition(WebDriver mainDriver){
        return mainDriver.manage().window().getPosition().getX();
    }
    protected int getCurrentWindowYPosition(WebDriver mainDriver){
        return mainDriver.manage().window().getPosition().getY();
    }
    protected Dimension getCurrentWindowSize(WebDriver mainDriver){
        return mainDriver.manage().window().getSize();
    }

    protected void SetCurrentWindowSize(WebDriver mainDriver,Dimension dimension){
        mainDriver.manage().window().setSize(dimension);
    }

    protected void maximizeCurrentWindow(WebDriver mainDriver){
        mainDriver.manage().window().maximize();
    }
    protected void minimizeCurrentWindow(WebDriver mainDriver){
        mainDriver.manage().window().minimize();
    }

    protected void activateFullscreen(WebDriver mainDriver){
        mainDriver.manage().window().fullscreen();
    }

}
