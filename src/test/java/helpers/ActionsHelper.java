package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsHelper {

    private WebDriver driver;


    public ActionsHelper(WebDriver driver){
        this.driver=driver;
    }


    @Step("Action-перемещение элемента по осям OX,OY")
    public ActionsHelper DragAndDropElement(WebElement element, int x, int y) {
        new Actions(driver)
                .dragAndDropBy(element, x, y)
                .build()
                .perform();
        return this;
    }
}
