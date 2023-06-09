package tests;

import api.ApiData;
import api.BookStoreModel;
import org.testng.annotations.BeforeMethod;


public class BaseApiTests {

    protected BookStoreModel apiAuth;
    protected BookStoreModel apiRegister;

    @BeforeMethod
    public void setApi() {
        apiAuth = BookStoreModel.builder()
                .userName(ApiData.UserData.USER_NAME)
                .password(ApiData.UserData.USER_PASSWORD)
                .build();

        apiRegister = BookStoreModel.builder()
                .userName(ApiData.UserData.newUser)
                .password(ApiData.UserData.newPassword)
                .build();
    }
}
