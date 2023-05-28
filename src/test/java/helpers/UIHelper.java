package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.UiTests;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


public class UIHelper extends UiTests {
    @Step("Прокрутка до элемента на странице")
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step("Видимость элемента на странице")
    public static boolean checkElementVisible(WebElement element) {
        return element.isDisplayed();
    }

    @Step("Неявное ожидание с таймаутом")
    public static WebDriverWait getWaiter(long timeOutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        webDriverWait
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);
        return webDriverWait;
    }

    @Step("Явное ожидание на видимость элемента")
    public static WebElement waitForElementVisible(WebElement findStrategy, long timeoutInSeconds) {
        getWaiter(timeoutInSeconds)
                .until(ExpectedConditions.visibilityOf(findStrategy));
        return findStrategy;
    }

    @Step("Явное ожидание на видимость элементов")
    public static List<WebElement> waitForElementsVisible(List<WebElement> findStrategy, long timeoutInSeconds) {
        getWaiter(timeoutInSeconds)
                .until(ExpectedConditions.visibilityOfAllElements(findStrategy));
        return findStrategy;
    }

    @Step("Явное ожидание на появление Alert")
    public static void waitForAlertPresented(long timeoutInSeconds) {
        getWaiter(timeoutInSeconds)
                .until(ExpectedConditions.alertIsPresent());
    }

    @Step("Переключение на frame")
    public static WebDriver frameElement(WebElement frame) {
        return driver.switchTo().frame(frame);
    }

    @Step("Нажимаем на кнопку OK в alert")
    public static void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    @Step("Получение Alert-текста")
    public static String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    @Step("Представлен ли Alert")
    public static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    @Step("URL-переадресация")
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

    @Step("Получение атрибута элемента")
    public static String getAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

}
