package pages;

import helpers.UIHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class BookStorePage {
    
    private WebDriver driver;
    
    private final String ALERT_MESSAGE = "User Register Successfully.";

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement spanLogin;

    @FindBy(id = "newUser")
    private WebElement newUserButton;

    @FindBy(xpath = "//div[@class=\"rt-td\"]//a[@href]")
    private List<WebElement> listOfBooksTitle;

    @FindBy(xpath = "//div[@class=\"rt-td\"][3]")
    private List<WebElement> listOfBooksAuthor;

    @FindBy(xpath = "//div[@class=\"rt-td\"][4]")
    private List<WebElement> listOFBooksPublisher;

    @FindBy(id = "firstname")
    private WebElement firstNameInput;

    @FindBy(id = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "userName")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(text(),'User Name')]")
    private WebElement labelUserName;

    @FindBy(xpath = "//button[text()='Register']")
    private WebElement registerButton;

    @FindBy(css = "iframe[title=reCAPTCHA]")
    private WebElement iframeCaptcha;

    @FindBy(css = "div[class=recaptcha-checkbox-border]")
    private WebElement captchaButton;


    public BookStorePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Клик по span login")
    public BookStorePage spanLoginClick() {
        UIHelper.scrollToElement(spanLogin);
        spanLogin.click();
        return this;
    }

    @Step("Прокрутка вниз для каждого названия книги")
    public BookStorePage scrollToAllBooksTitle() {
        listOfBooksTitle = UIHelper.waitForElementsVisible(listOfBooksTitle, 5);
        UIHelper.scrollToElement(listOfBooksTitle.get(listOfBooksTitle.size() - 1));
        return this;
    }

    @Step("Прокрутка вниз для каждого названия книги")
    public BookStorePage scrollToAllBooksAuthor() {
        listOfBooksAuthor = UIHelper.waitForElementsVisible(listOfBooksAuthor, 5);
        UIHelper.scrollToElement(listOfBooksAuthor.get(listOfBooksAuthor.size() - 1));
        return this;
    }

    @Step("Прокрутка вниз для каждого названия книги")
    public BookStorePage scrollToAllBooksPublisher() {
        listOFBooksPublisher = UIHelper.waitForElementsVisible(listOFBooksPublisher, 5);
        UIHelper.scrollToElement(listOFBooksPublisher.get(listOFBooksPublisher.size() - 1));
        return this;
    }

    @Step("Клик по кнопке newUser")
    public BookStorePage newUserButtonClick() {
        newUserButton.click();
        return this;
    }

    @Step("Заполнение  firstName")
    public BookStorePage fillFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    @Step("Заполнение  LastName")
    public BookStorePage fillLastName(String lastName) {
        firstNameInput.sendKeys(lastName);
        return this;
    }

    @Step("Заполнение  логина")
    public BookStorePage fillLogin(String userName) {
        userNameInput.sendKeys(userName);
        return this;
    }

    @Step("Заполнение пароля")
    public BookStorePage fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Клик по капче")
    public BookStorePage captchaClick() {
        UIHelper.frameElement(iframeCaptcha);
        captchaButton = UIHelper.waitForElementVisible(captchaButton, 5);
        captchaButton.click();
        return this;
    }

    @Step("Клик по кнопке Register")
    public BookStorePage registerClick() {
        registerButton.click();
        Assert.assertTrue(UIHelper.isAlertPresent(),"Alert не представлен!");
        Assert.assertEquals(UIHelper.getAlertText(), ALERT_MESSAGE,"Текста WebElement и ожидаемый не совпадают!");
        UIHelper.acceptAlert();
        return this;
    }

    @Step("Клик по кнопке Login")
    public BookStorePage loginClick() {
        loginButton.click();
        labelUserName = UIHelper.waitForElementVisible(labelUserName, 5);
        Assert.assertTrue(UIHelper.checkElementVisible(labelUserName),"Ошибка,элемента нет в DOM-дереве!");
        //Проверка успешной авторизации путем видимости label-элемента userName
        return this;
    }

    public List<String> getBooksTitleUI() throws NullPointerException {
        return listOfBooksTitle
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());
    }

    public List<String> getBooksAuthorUI() throws NullPointerException {
        return listOfBooksAuthor
                .stream()
                .map(x -> x.getText())
                .filter(x -> x.trim().length()>0)
                .collect(Collectors.toList());
    }

    public List<String> getBooksPublisherUI() throws NullPointerException {
        return listOFBooksPublisher
                .stream()
                .map(x -> x.getText())
                .filter(x -> x.trim().length()>0)
                .collect(Collectors.toList());
    }

}
