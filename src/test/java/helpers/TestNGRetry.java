package helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class TestNGRetry  implements IRetryAnalyzer   {
    private int min = 0;
    private int max = 2;



    //перезапуск тестов,если они упали
    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (min < max) {
                min++;
                return true;
            }
        }
        return false;
    }

}
