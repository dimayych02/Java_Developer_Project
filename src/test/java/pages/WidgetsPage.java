package pages;

import helpers.UIHelper;
import io.qameta.allure.Step;
import org.checkerframework.checker.guieffect.qual.UI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class WidgetsPage {

    private WebDriver driver;

    private final String ATTRIBUTE = "class";
    private final String ATTRIBUTE_VALUE = "collapse show";

    @FindBy(xpath = "//span[text()='Accordian']")
    private WebElement spanAccordian;

    @FindBy(id = "section1Heading")
    private WebElement accordianOne;

    @FindBy(id = "section2Heading")
    private WebElement accordianTwo;

    @FindBy(id = "section3Heading")
    private WebElement accordianThree;

    @FindBy(css = "div[class=card-header]")
    private List<WebElement> hiddenElements;

    @FindBy(css = "div[class=collapse]")
    private List<WebElement> dynamicAttributeElement;

    @FindBy(css = "div[class=card]")
    private List<WebElement> accordianElements;

    public WidgetsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Клип по кнопке accordian")
    public WidgetsPage accordianClick() {
        spanAccordian.click();
        return this;
    }

    @Step("Прокликиваем по всем кнопкам в разделе accordian")
    public WidgetsPage clickToAllAccordianElements() {
        hiddenElements
                .stream()
                .findFirst()
                .ifPresent(x -> x.click());
        return this;
    }
}
