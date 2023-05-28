package pages;

import api.ApiData;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import helpers.DownloadFile;
import helpers.UIHelper;

import java.util.List;
import java.util.stream.Collectors;


public class ElementsPage {

    private String generatedFirstName;
    private final String URL = ApiData.Endpoints.BASE_URL;

    private WebDriver driver;

    @FindBy(xpath = "//li//*[text()='Text Box']")
    private WebElement textBox;

    @FindBy(xpath = "//li//*[text()='Check Box']")
    private WebElement checkBox;

    @FindBy(xpath = "//li//*[text()='Radio Button']")
    private WebElement radioButton;

    @FindBy(xpath = "//li//*[text()='Web Tables']")
    private WebElement webTables;

    @FindBy(xpath = "//li//*[text()='Buttons']")
    private WebElement buttons;

    @FindBy(xpath = "//li//*[text()='Links']")
    private WebElement links;

    @FindBy(xpath = "//li//*[text()='Broken Links - Images']")
    private WebElement brokenLinks;

    @FindBy(xpath = "//li//*[text()='Upload and Download']")
    private WebElement uploadAndDownload;

    @FindBy(xpath = "//li//*[text()='Dynamic Properties']")
    private WebElement dynamicProperties;

    @FindBy(id = "userName")
    private WebElement fieldFullName;

    @FindBy(id = "userEmail")
    private WebElement emailElement;

    @FindBy(id = "currentAddress")
    private WebElement fieldCurrentAddress;

    @FindBy(id = "permanentAddress")
    private WebElement fieldPermanentAddress;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//div//p")
    private WebElement checkFillForm;

    @FindBy(css = "svg[class=\"rct-icon rct-icon-uncheck\"]")
    private WebElement pathCheckmark;

    @FindBy(xpath = "//span[text()='You have selected :']")
    private WebElement checkSelectedFolders;

    @FindBy(xpath = "//*[contains(text(),'Impressive')]")
    private WebElement radioButtonElement;

    @FindBy(xpath = "//p[@class=\"mt-3\"]")
    private WebElement checkSelectedRadioButton;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement registrationForm;

    @FindBy(xpath = "//div[@class=\"rt-td\"][1]")
    private List<WebElement> usersByFirstName;

    @FindBy(id = "firstName")
    private WebElement firstNameElement;

    @FindBy(id = "lastName")
    private WebElement lastNameElement;

    @FindBy(id = "age")
    private WebElement ageElement;

    @FindBy(id = "salary")
    private WebElement salaryElement;

    @FindBy(id = "department")
    private WebElement departmentElement;

    @FindBy(css = "span[title='Delete']")
    private List<WebElement> buttonDeleteUsers;
    @FindBy(xpath = "//button[text()='Click Me']")
    private WebElement clickMeButton;

    @FindBy(id = "dynamicClickMessage")
    private WebElement messageAfterClick;

    @FindBy(id = "simpleLink")
    private WebElement homeLink;

    @FindBy(xpath = "//a[text()='Click Here for Valid Link']")
    private WebElement validLink;

    @FindBy(id = "downloadButton")
    private WebElement downloadButton;

    @FindBy(id = "colorChange")
    private WebElement colorChange;

    @FindBy(id = "visibleAfter")
    private WebElement visibleAfterSeconds;


    public ElementsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Клик по кнопке checkBox")
    public ElementsPage checkBoxClick() {
        checkBox.click();
        return this;
    }

    @Step("Клик по кнопке textBox")
    public ElementsPage textBoxClick() {
        textBox.click();
        return this;
    }

    @Step("Клик по кнопке radioButton")
    public ElementsPage radioButtonClick() {
        radioButton.click();
        return this;
    }

    @Step("Клик по кнопке webTables")
    public ElementsPage webTablesClick() {
        webTables.click();
        return this;
    }

    @Step("Клик по кнопке buttons")
    public ElementsPage buttonsClick() {
        buttons.click();
        return this;
    }

    @Step("Клик по кнопке links")
    public ElementsPage linksClick() {
        links.click();
        return this;
    }

    @Step("Клик по кнопке brokenLinks")
    public ElementsPage brokenLinksClick() {
        brokenLinks.click();
        return this;
    }

    @Step("Клик по кнопке uploadAndDownload")
    public ElementsPage uploadAndDownloadClick() {
        uploadAndDownload.click();
        return this;
    }

    @Step("Клик по кнопке dynamic Properties")
    public ElementsPage dynamicPropertiesClick() {
        dynamicProperties.click();
        return this;
    }

    @Step("Заполнение имени")
    public ElementsPage fillFullName(String param) {
        fieldFullName.sendKeys(param);
        return this;
    }

    @Step("Заполнение  поля email")
    public ElementsPage fillEmail(String email) {
        emailElement.sendKeys(email);
        return this;
    }

    @Step("Заполнение текущего адреса")
    public ElementsPage fillCurrentAddress(String param) {
        fieldCurrentAddress.sendKeys(param);
        return this;
    }

    @Step("Заполнение временного адреса")
    public ElementsPage fillPermanentAddress(String param) {
        fieldPermanentAddress.sendKeys(param);
        return this;
    }

    @Step("Клик по кнопке submit")
    public ElementsPage clickSubmitButton() {
        UIHelper.scrollToElement(submitButton);
        submitButton.click();
        Assert.assertTrue(UIHelper.checkElementVisible(checkFillForm), "Элемент не виден в DOM-дереве!");
        return this;
    }

    @Step("Выбор пути")
    public ElementsPage clickPathCheckmark() {
        pathCheckmark.click();
        Assert.assertTrue(UIHelper.checkElementVisible(checkSelectedFolders), "Элемент не виден в DOM-дереве!");
        return this;
    }

    @Step("Клик по одному из элементов Radio Button")
    public ElementsPage clickRadioButtonElement() {
        radioButtonElement.click();
        Assert.assertTrue(UIHelper.checkElementVisible(checkSelectedRadioButton), "Элемент не виден в DOM-дереве!");
        return this;
    }

    @Step("Клик по кнопке Add в WebTable")
    public ElementsPage registrationClick() {
        registrationForm.click();
        return this;
    }

    @Step("Заполнение поля firstName в WebTable")
    public ElementsPage fillFirstName(String firstName) {
        firstNameElement.sendKeys(firstName);
        generatedFirstName = firstName;
        return this;
    }

    @Step("Заполнение поля lastName в WebTable")
    public ElementsPage fillLastName(String lastName) {
        lastNameElement.sendKeys(lastName);
        return this;
    }

    @Step("Заполнение поля age в WebTable")
    public ElementsPage fillAge(Integer age) {
        ageElement.sendKeys(Integer.toString(age));
        return this;
    }

    @Step("Заполнение поля age в WebTable")
    public ElementsPage fillSalary(Integer salary) {
        salaryElement.sendKeys(Integer.toString(salary));
        return this;
    }

    @Step("Заполнение поля department в WebTable")
    public ElementsPage fillDepartment(String department) {
        departmentElement.sendKeys(department);
        return this;
    }

    @Step("Клик по кнопке submit после создания нового пользователя в WebTable")
    public ElementsPage submitButtonClick() {
        submitButton.click();
        Assert.assertTrue(getUsers(usersByFirstName)
                .stream()
                .anyMatch(x -> x.contains(generatedFirstName)), "Пользователь с такими данными не был найден!");
        return this;
    }

    @Step("Удаление всех пользователей")
    public ElementsPage deleteUsers() throws NullPointerException {
        for (WebElement element : buttonDeleteUsers) {
            UIHelper.getWaiter(2);
            buttonDeleteUsers.get(0).click();
        }
        Assert.assertTrue(getUsers(usersByFirstName).stream().allMatch(x -> x.matches("^\\s*$")));
        return this;
    }

    @Step("Клик по кнопке clickMe")
    public ElementsPage buttonClickMe() {
        clickMeButton.click();
        Assert.assertTrue(UIHelper.checkElementVisible(messageAfterClick), "Элемент не виден в DOM-дереве!");
        return this;
    }

    @Step("Клик по  homeLink")
    public ElementsPage homeLinkClick() {
        homeLink.click();
        Assert.assertEquals(UIHelper.redirectUrl(), URL, "Redirect URLs не совпадают!");
        return this;
    }

    @Step("Клик по  validLink")
    public ElementsPage validLinkClick() {
        validLink.click();
        Assert.assertEquals(UIHelper.redirectUrl(), URL, "Redirect URLs не совпадают!");
        return this;
    }

    @Step("Клик по кнопке download")
    public ElementsPage downloadClick() throws InterruptedException {
        downloadButton.click();
        Assert.assertTrue(DownloadFile.checkDownloadedFile(), "Файл не был скачен!");
        return this;
    }

    @Step("Клик по кнопке colorChange и проверка видимости элемента")
    public ElementsPage colorChangeClick() {
        colorChange.click();
        visibleAfterSeconds = UIHelper.waitForElementVisible(visibleAfterSeconds, 5);
        Assert.assertTrue(UIHelper.checkElementVisible(visibleAfterSeconds), "Элемент не виден в DOM-дереве!");
        return this;
    }

    @Step("Список пользователей")
    private List<String> getUsers(List<WebElement> usersElement) throws NullPointerException {
        return usersElement.stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());
    }
}
