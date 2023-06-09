package pages;

import helpers.ActionsHelper;
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

    private final String ACCORDIAN_ATTRIBUTE = "class";
    private final String ACCORDIAN_ATTRIBUTE_VALUE = "collapse";
    private final String SLIDER_ATTRIBUTE = "value";
    private final String SLIDER_VALUE_AFTER_MOVING = "56";
    private final String RESET_BUTTON_TEXT="Reset";

    @FindBy(xpath = "//span[text()='Accordian']")
    private WebElement spanAccordian;

    @FindBy(xpath="//span[text()='Progress Bar']")
    private WebElement spanProgressBar;

    @FindBy(xpath = "//span[text()='Slider']")
    private WebElement slider;

    @FindBy(css = "div[class=card-header]")
    private List<WebElement> hiddenElements;

    @FindBy(css = "div[class=collapse]")
    private List<WebElement> dynamicAttributeElement;

    @FindBy(css = "div[class=card]")
    private List<WebElement> accordianElements;

    @FindBy(css = "input[type=range]")
    private WebElement dragSliderToX;

    @FindBy(id = "sliderValue")
    private WebElement sliderValueInput;

    @FindBy(css = "button[id=startStopButton]")
    private WebElement startButton;

    @FindBy(id="resetButton")
    private WebElement resetButton;


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
                Assert.assertEquals(UIHelper.getAttributeValue(attributeElement, ACCORDIAN_ATTRIBUTE), ACCORDIAN_ATTRIBUTE_VALUE, "Аттрибуты не совпадают!");
            }
        }
        return this;
    }

    @Step("Перемещение элемента  slider по оси OX")
    public WidgetsPage clickToSlider() {
        slider.click();
        new ActionsHelper(driver).DragAndDropElement(dragSliderToX, 30, 0);
        Assert.assertEquals(UIHelper.getAttributeValue(sliderValueInput, SLIDER_ATTRIBUTE), SLIDER_VALUE_AFTER_MOVING);
        return this;
    }

    @Step("Клик по кнопке progress Bar")
    public WidgetsPage clickToProgressBar() {
        spanProgressBar.click();
        return this;
    }

    @Step("Клик по кнопке Start в разделе progressBar")
    public WidgetsPage clickToButtonStart(){
        startButton.click();
        return this;
    }

    @Step("Проверка что аттрибут изменил значение текст")
    public  WidgetsPage checkThatElementChangedText(){
       resetButton=UIHelper.waitForElementClickable(resetButton,20);
       Assert.assertEquals(resetButton.getText(),RESET_BUTTON_TEXT);
       return this;
    }
}