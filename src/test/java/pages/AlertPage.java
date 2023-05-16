package pages;

import helpers.UIHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AlertPage {
    private WebDriver driver;
    private final String REDIRECT_URL="https://demoqa.com/sample";
    private final String FIRST_ALERT_TEXT="You clicked a button";
    private final String SECOND_ALERT_TEXT="This alert appeared after 5 seconds";
    private final String THIRD_ALERT_TEXT="Do you confirm action?";
    private final String TEXT_AFTER_ALERT_CLICK="Ok";
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

    @FindBy(id="alertButton")
    private WebElement clickButtonToSeeAlert;

    @FindBy(id="timerAlertButton")
    private WebElement timeAlert;

    @FindBy(id="confirmButton")
    private WebElement confirmAlert;

    @FindBy(id="confirmResult")
    private WebElement confirmAlertResult;


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
        Assert.assertEquals(REDIRECT_URL,UIHelper.redirectUrl(),"Redirect-URL не совпадает с актуальной");
        Assert.assertTrue(UIHelper.checkElementVisible(redirectPageText),"Элемент не виден в DOM-дереве!");
        return this;
    }

    @Step("Клик по первой кнопке Alert")
    public AlertPage firstAlertClick(){
        clickButtonToSeeAlert.click();
        Assert.assertEquals(UIHelper.getAlertText(),FIRST_ALERT_TEXT," alert-сообщения не совпадают!");
        UIHelper.acceptAlert();
        return this;
    }

    @Step("Клик по второй кнопке Alert")
    public AlertPage secondAlertClick(){
        timeAlert.click();
        UIHelper.waitForAlertPresented(5);
        Assert.assertEquals(UIHelper.getAlertText(),SECOND_ALERT_TEXT," alert-сообщения не совпадают!");
        UIHelper.acceptAlert();
        return this;
    }

    @Step("Клик по второй кнопке Alert")
    public AlertPage thirdAlertClick(){
        confirmAlert.click();
        Assert.assertEquals(UIHelper.getAlertText(),THIRD_ALERT_TEXT," alert-сообщения не совпадают!");
        UIHelper.acceptAlert();
        Assert.assertTrue(UIHelper.checkElementVisible(confirmAlertResult),"Элемент не виден в DOM-дереве!");
        Assert.assertTrue(confirmAlertResult.getText().contains(TEXT_AFTER_ALERT_CLICK),"WebElement  не содержит данный текст!");
        return this;
    }
}
