package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertPage {

    private WebDriver driver;
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

    public AlertPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
