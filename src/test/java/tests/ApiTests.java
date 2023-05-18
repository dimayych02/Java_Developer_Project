package tests;

import api.ApiData;
import api.BookStoreModel;
import api.RequestToApi;
import helpers.TestNGRetry;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ApiTests {
    private final Object USER_DATA_AUTH = new BookStoreModel.AuthorizationData(ApiData.UserData.USER_NAME, ApiData.UserData.USER_PASSWORD);
    private Object userDataRegister = new BookStoreModel.AuthorizationData(ApiData.UserData.newUser, ApiData.UserData.newPassword);
    private final String USER_EXIST_MESSAGE = ApiData.ResponseMessage.USER_EXIST_MESSAGE;
    private final String AUTHORIZATION_ENDPOINT = ApiData.Endpoints.AUTHORIZATION_ENDPOINT;
    private final String NEW_USER_ENDPOINT = ApiData.Endpoints.NEW_USER_ENDPOINT;
    private final String GENERATE_TOKEN_ENDPOINT = ApiData.Endpoints.GENERATE_TOKEN_ENDPOINT;
    private final String CREATED_ENDPOINT = ApiData.Endpoints.CREATED_ENDPOINT;
    private final String NO_CONTENT_ENDPOINT = ApiData.Endpoints.NO_CONTENT_ENDPOINT;
    private final String MOVED_ENDPOINT = ApiData.Endpoints.MOVED_ENDPOINT;
    private final String BAD_REQUEST_ENDPOINT = ApiData.Endpoints.BAD_REQUEST_ENDPOINT;
    private final String UNAUTHORIZED_ENDPOINT = ApiData.Endpoints.UNAUTHORIZED_ENDPOINT;
    private final String FORBIDDEN_ENDPOINT = ApiData.Endpoints.FORBIDDEN_ENDPOINT;
    private final String NOT_FOUND_ENDPOINT = ApiData.Endpoints.NOT_FOUND_ENDPOINT;
    private final String DELETE_USER_ENDPOINT = ApiData.Endpoints.DELETE_USER_ENDPOINT;
    private final String UUID_PARAM = ApiData.PathParams.UUID_PARAM;

    @Test
    public void successAuthorization() {
        Assert.assertEquals(RequestToApi.fillJsonBody(USER_DATA_AUTH, AUTHORIZATION_ENDPOINT)
                .statusCode(), 200);
    }

    @Test
    public void failedUserRegister() {
        Assert.assertEquals(RequestToApi.fillJsonBody(USER_DATA_AUTH, NEW_USER_ENDPOINT)
                .statusCode(), 406);
        Assert.assertEquals(RequestToApi.extractResponse(RequestToApi.fillJsonBody(USER_DATA_AUTH, NEW_USER_ENDPOINT))
                .getMessage(), USER_EXIST_MESSAGE);
    }

    @Test(retryAnalyzer = TestNGRetry.class,priority = -1)
    public void successUserRegister() {
        Assert.assertTrue(RequestToApi.extractResponse(RequestToApi.fillJsonBody(userDataRegister, NEW_USER_ENDPOINT))
                .getUserID() != null);
    }

    @Test
    public void getUserToken() {
        Assert.assertTrue(RequestToApi.extractResponse(RequestToApi.fillJsonBody(USER_DATA_AUTH, GENERATE_TOKEN_ENDPOINT))
                .getToken() != null);
    }

    @Test
    public void deleteUser() {
        String userID = (String) RequestToApi.extractResponse(RequestToApi.fillJsonBody(userDataRegister, NEW_USER_ENDPOINT)).getUserID();
        Assert.assertEquals(RequestToApi.deleteUser(UUID_PARAM, userID, DELETE_USER_ENDPOINT).statusCode(), 200);
    }

    @Test
    public void createdLinkRequest() {
        Assert.assertEquals(RequestToApi.requestToServer(CREATED_ENDPOINT).statusCode(), 201);
    }

    @Test
    public void noContentLinkRequest() {
        Assert.assertEquals(RequestToApi.requestToServer(NO_CONTENT_ENDPOINT).statusCode(), 204);
    }

    @Test
    public void linkMovedRequest() {
        Assert.assertEquals(RequestToApi.requestToServer(MOVED_ENDPOINT).statusCode(),
                301);
    }

    @Test
    public void linkBadRequest() {
        Assert.assertEquals(RequestToApi.requestToServer(BAD_REQUEST_ENDPOINT).statusCode(),
                400);
    }

    @Test
    public void linkUnauthorizedRequest() {
        Assert.assertEquals(RequestToApi.requestToServer(UNAUTHORIZED_ENDPOINT).statusCode(),
                401);
    }

    @Test
    public void linkForbiddenRequest() {
        Assert.assertEquals(RequestToApi.requestToServer(FORBIDDEN_ENDPOINT).statusCode(),
                403);
    }

    @Test
    public void linkNotFoundRequest() {
        Assert.assertEquals(RequestToApi.requestToServer(NOT_FOUND_ENDPOINT).statusCode(),
                404);
    }

}
