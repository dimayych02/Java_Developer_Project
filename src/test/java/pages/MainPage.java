package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import helpers.UIHelper;

public class MainPage {

    private WebDriver driver;

    @FindBy(xpath = "//h5[text()='Elements']")
    private WebElement buttonElements;

    @FindBy(xpath = "//h5[text()='Forms']")
    private WebElement buttonForms;

    @FindBy(xpath = "//*[contains(text(),'Alerts')]")
    private WebElement buttonAlerts;

    @FindBy(xpath = "//h5[text()='Widgets']")
    private WebElement buttonWidgets;

    @FindBy(xpath = "//h5[text()='Interactions']")
    private WebElement buttonInteractions;

    @FindBy(xpath = "//h5[text()='Book Store Application']")
    private WebElement buttonBookStore;


    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public MainPage clickToElements() {
        UIHelper.scrollToElement(buttonElements);
        buttonElements.click();
        return this;
    }

    public MainPage clickToForms() {
        UIHelper.scrollToElement(buttonForms);
        buttonForms.click();
        return this;
    }

    public MainPage clickToAlerts() {
        UIHelper.scrollToElement(buttonAlerts);
        buttonAlerts.click();
        return this;
    }

    public MainPage clickToWidgets() {
        UIHelper.scrollToElement(buttonWidgets);
        buttonWidgets.click();
        return this;
    }

    public MainPage clickToInteractions() {
        buttonInteractions.click();
        return this;
    }

    public MainPage clickToBookStore() {
        UIHelper.scrollToElement(buttonElements);
        buttonBookStore.click();
        return this;
    }
}
