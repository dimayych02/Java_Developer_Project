package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.UiTests;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


public class UIHelper extends UiTests {
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }



    public static boolean checkElementVisible(WebElement element) {
        if (element.isDisplayed()) {
            return true;
        }
        return false;
    }

    public static WebDriverWait getWaiter(long timeOutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        webDriverWait
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);
        return webDriverWait;
    }

    public static WebElement waitForElementVisible(WebElement findStrategy, long timeoutInSeconds) {
        getWaiter(timeoutInSeconds).until(ExpectedConditions.visibilityOf(findStrategy));
        return findStrategy;
    }

    public static List<WebElement> waitForElementsVisible(List<WebElement> findStrategy, long timeoutInSeconds) {
        getWaiter(timeoutInSeconds).until(ExpectedConditions.visibilityOfAllElements(findStrategy));
        return findStrategy;
    }

    public static WebDriver frameElement(WebElement frame) {
        return driver.switchTo().frame(frame);
    }

    public static void closeAlert() {
        driver.switchTo().alert().accept();
    }

    public static String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public static String redirectUrl() {
        String window1 = driver.getWindowHandle();
        Set<String> currentWindows = driver.getWindowHandles();
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                String window2 = window;
                driver.switchTo().window(window2);
            }
        }
        return driver.getCurrentUrl();
    }

    public static String getAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

}
