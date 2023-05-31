package helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseUITests;



public class AttachOnFailedTest extends BaseUITests implements ITestListener   {
    @Attachment(value="screen")
    public byte[] saveScreenshotsOnFailure(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result){
        saveScreenshotsOnFailure();
    }
}
