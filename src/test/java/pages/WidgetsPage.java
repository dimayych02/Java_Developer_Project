package pages;

import helpers.UIHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class WidgetsPage {

    private WebDriver driver;

    private final String ATTRIBUTE = "class";
    private final String ATTRIBUTE_VALUE = "collapse";

    @FindBy(xpath = "//span[text()='Accordian']")
    private WebElement spanAccordian;

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
        for (WebElement element : accordianElements) {
            element.click();

            for (WebElement attributeElement : dynamicAttributeElement) {
                Assert.assertEquals(UIHelper.getAttributeValue(attributeElement, ATTRIBUTE), ATTRIBUTE_VALUE);
            }
        }
        return this;
    }
}
