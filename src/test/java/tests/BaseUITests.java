package tests;

import api.ApiData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

public class BaseUITests {

    protected static WebDriver driver;

    protected MainPage mainPage;
    protected ElementsPage elementsPage;
    protected FormsPage formsPage;
    protected BookStorePage bookStorePage;
    protected AlertPage alertPage;
    protected WidgetsPage widgetsPage;

    protected final String URL = ApiData.Endpoints.BASE_URL;


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        elementsPage = new ElementsPage(driver);
        formsPage = new FormsPage(driver);
        bookStorePage = new BookStorePage(driver);
        alertPage = new AlertPage(driver);
        widgetsPage = new WidgetsPage(driver);
        driver.get(URL);
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
