package pages;

import helpers.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class WidgetsPage {

    private final String ATTRIBUTE="class";

    private final String ATTRIBUTE_VALUE="collapse show";

    private WebDriver driver;

    @FindBy(xpath = "//span[text()='Accordian']")
    private WebElement spanAccordian;

    @FindBy(id = "section1Heading")
    private WebElement accordianOne;

    @FindBy(id = "section2Heading")
    private WebElement accordianTwo;

    @FindBy(id = "section3Heading")
    private WebElement accordianThree;

    @FindBy(xpath = ".//div[not(contains(@class,'card'))]")
    private WebElement hiddenElements;

    @FindBy(css = "div[class=card]")
    private List<WebElement> accordianElements;

    public WidgetsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WidgetsPage accordianClick() {
        spanAccordian.click();
        return this;
    }

    public WidgetsPage clickToAllAccordianElements() {
        for (WebElement element : accordianElements) {
            element.click();
            hiddenElements = UIHelper.waitForElementChangeAttribute(hiddenElements, ATTRIBUTE, ATTRIBUTE_VALUE, 5);
            Assert.assertEquals(UIHelper.getAttributeValue(hiddenElements, ATTRIBUTE), ATTRIBUTE_VALUE);
            element.click();
        }
        return this;
    }
}
