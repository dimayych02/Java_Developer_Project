package tests;

import api.ApiData;
import api.BookAttributes;
import api.BookStoreModel;
import api.RequestToApi;
import helpers.AttachOnFailedTest;
import helpers.TestNGRetry;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.*;
import dataGenerator.Generator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Listeners(AttachOnFailedTest.class)
public class UiTests {

    public static WebDriver driver;

    private MainPage mainPage;
    private ElementsPage elementsPage;
    private FormsPage formsPage;
    private BookStorePage bookStorePage;
    private AlertPage alertPage;
    
    private String userName;
    private String password;
    private final String URL = ApiData.Endpoints.BASE_URL;
    private final String NEW_USER_ENDPOINT = ApiData.Endpoints.NEW_USER_ENDPOINT;
    private final String AUTHORIZATION_ENDPOINT = ApiData.Endpoints.AUTHORIZATION_ENDPOINT;
    private final Object USER_DATA_AUTH = new BookStoreModel.AuthorizationData(ApiData.UserData.USER_NAME, ApiData.UserData.USER_PASSWORD);
    private Object userDataRegister = new BookStoreModel.AuthorizationData(ApiData.UserData.newUser, ApiData.UserData.newPassword);


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        elementsPage = new ElementsPage(driver);
        formsPage = new FormsPage(driver);
        bookStorePage = new BookStorePage(driver);
        alertPage = new AlertPage(driver);
        driver.get(URL);
    }

    @Test
    public void textBoxForm() {
        mainPage.clickToElements();
        elementsPage.textBoxClick();
        elementsPage.fillFullName(Generator.generateString());
        elementsPage.fillEmail(Generator.generateEmail());
        elementsPage.fillCurrentAddress(Generator.generateString());
        elementsPage.fillPermanentAddress(Generator.generateString());
        elementsPage.clickSubmitButton();
    }

    @Test
    public void checkBoxFolders() {
        mainPage.clickToElements();
        elementsPage.checkBoxClick();
        elementsPage.clickPathCheckmark();
    }

    @Test
    public void radioButton() {
        mainPage.clickToElements();
        elementsPage.radioButtonClick();
        elementsPage.clickRadioButtonElement();
    }

    @Test
    public void addUserToWebTable() {
        mainPage.clickToElements();
        elementsPage.webTablesClick();
        elementsPage.registrationClick();
        elementsPage.fillFirstName(Generator.generateString());
        elementsPage.fillLastName(Generator.generateString());
        elementsPage.fillEmail(Generator.generateEmail());
        elementsPage.fillAge(Generator.generateInteger());
        elementsPage.fillSalary(Generator.generateInteger());
        elementsPage.fillDepartment(Generator.generateString());
        elementsPage.submitButtonClick();
    }

    @Test
    public void deleteAllUsers() {
        mainPage.clickToElements();
        elementsPage.webTablesClick();
        elementsPage.deleteUsers();
    }

    @Test
    public void menuButtons() {
        mainPage.clickToElements();
        elementsPage.buttonsClick();
        elementsPage.buttonClickMe();
    }

    @Test
    public void links() {
        mainPage.clickToElements();
        elementsPage.linksClick();
        elementsPage.homeLinkClick();
    }

    @Test
    public void brokenLinks() {
        mainPage.clickToElements();
        elementsPage.brokenLinksClick();
        elementsPage.validLinkClick();
    }

    @Test
    public void downloadFile() throws InterruptedException {
        mainPage.clickToElements();
        elementsPage.uploadAndDownloadClick();
        elementsPage.downloadClick();
    }

    @Test
    public void dynamicProperties() {
        mainPage.clickToElements();
        elementsPage.dynamicPropertiesClick();
        elementsPage.colorChangeClick();
    }

    @Test
    public void fillForm() {
        mainPage.clickToForms();
        formsPage.practiseFormClick();
        formsPage.fillFirstName(Generator.generateString());
        formsPage.fillLastName(Generator.generateString());
        formsPage.fillUserEmail(Generator.generateEmail());
        formsPage.maleGenderClick();
        formsPage.fillNumber(Generator.generatePhoneNumber());
        formsPage.calendarFill();
        formsPage.fillSubjects(Generator.generateString());
        formsPage.hobbySportClick();
        formsPage.hobbyReadingClick();
        formsPage.hobbyMusicClick();
        formsPage.fillCurrentAddress(Generator.generateString());
    }

    @Test
    public void uiAndApiBooks() { //Сравнение книг на бэке и UI
        mainPage.clickToBookStore();
        bookStorePage.scrollToAllBooksTitle();
        Assert.assertEquals(bookStorePage.getBooksTitleUI(), BookAttributes.getBooksTitleApi(), "Названия книг на api и ui не совпадают!");
        Assert.assertEquals(bookStorePage.getBooksAuthorUI(), BookAttributes.getBooksAuthorApi(), "Авторы книг на api и ui не совпадают");
        Assert.assertEquals(bookStorePage.getBooksPublisherUI(), BookAttributes.getBooksPublisherApi(), "Писатели книг на api и ui не совпадают!");
    }

    @Test(retryAnalyzer = TestNGRetry.class)
    public void uiAuthorizationWithApiRegister() { //создание пользователя на бэке и авторизация на UI
        //Создание нового польхователя на бэке
        userName = Generator.generateString();
        password = Generator.generatePassword();
        Assert.assertEquals(RequestToApi.fillJsonBody(userDataRegister, NEW_USER_ENDPOINT).statusCode(), 201, "Ошибка,пользователь не был зарегистрирован на бэке!");
        mainPage.clickToBookStore();
        bookStorePage.spanLoginClick();
        bookStorePage.fillLogin(userName);
        bookStorePage.fillPassword(password);
        bookStorePage.loginClick();
    }

    @Test(retryAnalyzer = TestNGRetry.class)
    public void uiRegistrationWithApiAuth() { //создание пользователя на UI и авторизация на бэке
        userName = Generator.generateString();
        password = Generator.generatePassword();
        mainPage.clickToBookStore();
        bookStorePage.spanLoginClick();
        bookStorePage.newUserButtonClick();
        bookStorePage.fillFirstName(Generator.generateString());
        bookStorePage.fillLastName(Generator.generateString());
        bookStorePage.fillLogin(userName);
        bookStorePage.fillPassword(password);
        bookStorePage.captchaClick();
        bookStorePage.registerClick();
        Assert.assertEquals(RequestToApi.fillJsonBody(USER_DATA_AUTH, AUTHORIZATION_ENDPOINT), 201, "Ошибка статус-код не 201!");
    }

    @Test
    public void newTabAlert() {
        mainPage.clickToAlerts();
        alertPage.browserWindowsClick();
        alertPage.newTabClick();
    }

    @Test
    public void clickToAllTypeAlerts() {
        mainPage.clickToAlerts();
        alertPage.alertsClick();
        alertPage.firstAlertClick();
        alertPage.secondAlertClick();
        alertPage.thirdAlertClick();
        alertPage.fourthAlertClick();
    }

    @Test
    public void getFrameText() {
        mainPage.clickToAlerts();
        alertPage.framesClick();
        alertPage.getFrameText();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }
}
