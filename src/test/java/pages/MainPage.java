package pages;

import io.qameta.allure.Step;
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
    @Step("Клик по разделу элементы")
    public MainPage clickToElements() {
        UIHelper.scrollToElement(buttonElements);
        buttonElements.click();
        return this;
    }

    @Step("Клик по разделу форма")
    public MainPage clickToForms() {
        UIHelper.scrollToElement(buttonForms);
        buttonForms.click();
        return this;
    }

    @Step("Клик по разделу модальные окна")
    public MainPage clickToAlerts() {
        UIHelper.scrollToElement(buttonAlerts);
        buttonAlerts.click();
        return this;
    }

    @Step("Клик по разделу виджеты")
    public MainPage clickToWidgets() {
        UIHelper.scrollToElement(buttonWidgets);
        buttonWidgets.click();
        return this;
    }

    @Step("Клик по разделу взаимодействия")
    public MainPage clickToInteractions() {
        buttonInteractions.click();
        return this;
    }

    @Step("Клик по разделу книги")
    public MainPage clickToBookStore() {
        UIHelper.scrollToElement(buttonElements);
        buttonBookStore.click();
        return this;
    }
}
