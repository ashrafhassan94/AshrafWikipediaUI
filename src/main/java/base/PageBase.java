package base;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.testng.Assert.fail;

public class PageBase {

    protected static WebDriverWait wait;
    private final long TIMEOUT = 20;
    private final long POLLING = 5000;
    private final JavascriptExecutor js;
    protected static WebDriver driver;

    public PageBase(WebDriver driver) {
        wait = new WebDriverWait(driver, ofSeconds(TIMEOUT) , ofSeconds(POLLING));
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }
    public PageBase() {
        wait = new WebDriverWait(driver, ofSeconds(TIMEOUT) , ofSeconds(POLLING));
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
        js.executeScript("arguments[0].scrollIntoView();", element);

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
            return true;
        } catch (TimeoutException toe) {
            return false;
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

}
