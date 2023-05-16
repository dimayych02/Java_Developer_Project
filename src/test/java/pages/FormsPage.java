package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import helpers.UIHelper;


public class FormsPage extends UIHelper {
    private WebDriver driver;

    @FindBy(xpath = "//li//*[text()='Practice Form']")
    private WebElement practiseForm;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(css = "label[for=gender-radio-1]")
    private WebElement maleGender;

    @FindBy(css = "label[for=gender-radio-2]")
    private WebElement femaleGender;

    @FindBy(css = "label[for=gender-radio-3]")
    private WebElement otherGender;

    @FindBy(id = "userNumber")
    private WebElement userNumber;

    @FindBy(css = "input[id=dateOfBirthInput]")
    private WebElement dateOFBirth;

    @FindBy(id = "subjectsInput")
    private WebElement subjects;

    @FindBy(css = "label[for=hobbies-checkbox-1]")
    private WebElement hobbySports;

    @FindBy(css = "label[for=hobbies-checkbox-2]")
    private WebElement hobbyReading;

    @FindBy(css = "label[for=hobbies-checkbox-3]")
    private WebElement hobbyMusic;

    @FindBy(id = "currentAddress")
    private WebElement currentAddress;

    @FindBy(css = "select[class=react-datepicker__month-select]")
    private WebElement selectMonthButton;

    @FindBy(xpath = "//*[contains(text(),'October')]")
    private WebElement monthSelect;

    @FindBy(css = "select[class=react-datepicker__year-select]")
    private WebElement selectYearButton;

    @FindBy(xpath = "//*[text()='2002']")
    private WebElement yearSelect;

    @FindBy(xpath = "//*[text()='Select State']")
    private WebElement stateSelect;

    @FindBy(xpath = "//*[text()='Select City']")
    private WebElement citySelect;

    @FindBy(css = "div[class=\"react-datepicker__day react-datepicker__day--030\"]")
    private WebElement day;


    public FormsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Клик по PractiseForm")
    public FormsPage practiseFormClick() {
        practiseForm.click();
        return this;
    }

    @Step("Заполнение поля firstName")
    public FormsPage fillFirstName(String param) {
        firstName.sendKeys(param);
        return this;
    }

    @Step("Заполнение поля lastName")
    public FormsPage fillLastName(String param) {
        lastName.sendKeys(param);
        return this;
    }

    @Step("Заполнение поля userEmail")
    public FormsPage fillUserEmail(String param) {
        userEmail.sendKeys(param);
        return this;
    }

    @Step("Клик по gender male")
    public FormsPage maleGenderClick() {
        maleGender.click();
        return this;
    }

    @Step("Клик по gender female")
    public FormsPage femaleGenderClick() {
        femaleGender.click();
        return this;
    }

    @Step("Клик по gender other")
    public FormsPage otherGenderClick() {
        otherGender.click();
        return this;
    }

    @Step("Заполнение поля mobileNumber")
    public FormsPage fillNumber(String param) {
        userNumber.sendKeys(param);
        return this;
    }

    @Step("Заполнение календаря")
    public FormsPage calendarFill() {
        dateOFBirth.click();
        selectMonthButton.click();
        monthSelect.click();
        selectYearButton.click();
        yearSelect.click();
        day.click();
        Assert.assertTrue(getAttributeValue(dateOFBirth, "value")
                .equals("30 Oct 2002"), "Выбранный атрибут не совпадает с ожидаемым аттрибутом!");
        return this;
    }


    @Step("Заполнение поля subjects")
    public FormsPage fillSubjects(String param) {
        subjects.sendKeys(param);
        return this;
    }

    @Step("Клик по hobby sports")
    public FormsPage hobbySportClick() {
        hobbySports.click();
        return this;
    }

    @Step("Клик по hobby music")
    public FormsPage hobbyMusicClick() {
        hobbyMusic.click();
        return this;
    }

    @Step("Клик по hobby reading")
    public FormsPage hobbyReadingClick() {
        hobbyReading.click();
        return this;
    }

    @Step("Заполнение поля currentAddress")
    public FormsPage fillCurrentAddress(String param) {
        currentAddress.sendKeys(param);
        return this;
    }

}