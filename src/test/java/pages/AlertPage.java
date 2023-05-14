package pages;

import helpers.UIHelper;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertPage {

    private WebDriver driver;
    private final String REDIRECT_URL="https://demoqa.com/sample";
    @FindBy(xpath="//span[text()='Browser Windows']")
    private WebElement browserWindows;
    @FindBy(xpath="//span[text()='Alerts']")
    private WebElement alerts;
    @FindBy(xpath="//span[text()='Frames']")
    private WebElement frames;
    @FindBy(xpath="//span[text()='Nested Frames']")
    private WebElement nestedFrames;
    @FindBy(xpath="//span[text()='Modal Dialogs']")
    private WebElement modalDialogs;
    @FindBy(xpath="//*[text()='New Tab']")
    private WebElement newTabButton;
    @FindBy(xpath="//*[text()='This is a sample page']")
    private WebElement redirectPageText;

    public AlertPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Клик по кнопке Browser Windows")
    public AlertPage browserWindowsClick(){
        browserWindows.click();
        return this;
    }

    @Step("Клик по кнопке Alerts")
    public AlertPage alertsClick(){
        alerts.click();
        return this;
    }

    @Step("Клик по кнопке Frames")
    public AlertPage framesClick(){
        frames.click();
        return this;
    }

    @Step("Клик по кнопке Nested Frames")
    public AlertPage nestedFramesClick(){
        nestedFrames.click();
        return this;
    }

    @Step("Клик по кнопке Modal Dialogs")
    public AlertPage modalDialogsClick(){
        modalDialogs.click();
        return this;
    }

    @Step("Клик по кнопке New Tab")
    public AlertPage newTabClick(){
        newTabButton.click();
        Assert.assertEquals(REDIRECT_URL,UIHelper.redirectUrl());
        Assert.assertTrue(UIHelper.checkElementVisible(redirectPageText));
        return this;
    }
}
