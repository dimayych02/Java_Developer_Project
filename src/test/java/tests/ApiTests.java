package tests;

import api.ApiData;
import api.RequestToApi;
import helpers.TestNGRetry;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ApiTests extends BaseApiTests {


    @Test
    public void successAuthorization() {
        Assert.assertEquals(RequestToApi.methodPOST(apiAuth, ApiData.Endpoints.AUTHORIZATION_ENDPOINT)
                .statusCode(), 200);
    }

    @Test
    public void failedUserRegister() {
        Assert.assertEquals(RequestToApi.methodPOST(apiAuth, ApiData.Endpoints.NEW_USER_ENDPOINT)
                .statusCode(), 406);
        Assert.assertEquals(RequestToApi.extractResponse(RequestToApi.methodPOST(apiAuth, ApiData.Endpoints.NEW_USER_ENDPOINT))
                .getMessage(), ApiData.ResponseMessage.USER_EXIST_MESSAGE);
    }

    @Test(retryAnalyzer = TestNGRetry.class, priority = -1)
    public void successUserRegister() {
        Assert.assertTrue(RequestToApi.extractResponse(RequestToApi.methodPOST(apiRegister, ApiData.Endpoints.NEW_USER_ENDPOINT))
                .getUserID() != null);
    }

    @Test
    public void getUserToken() {
        Assert.assertTrue(RequestToApi.extractResponse(RequestToApi.methodPOST(apiAuth, ApiData.Endpoints.GENERATE_TOKEN_ENDPOINT))
                .getToken() != null);
    }

    @Test
    public void deleteUser() {
        String userID = (String) RequestToApi.extractResponse(RequestToApi.methodPOST(apiRegister, ApiData.Endpoints.NEW_USER_ENDPOINT))
                .getUserID();
        Assert.assertEquals(RequestToApi.methodDELETE(ApiData.PathParams.UUID_PARAM, userID, ApiData.Endpoints.DELETE_USER_ENDPOINT).statusCode(), 200);
    }

    @Test
    public void createdLinkRequest() {
        Assert.assertEquals(RequestToApi.methodGET(ApiData.Endpoints.CREATED_ENDPOINT).statusCode(), 201);
    }

    @Test
    public void noContentLinkRequest() {
        Assert.assertEquals(RequestToApi.methodGET(ApiData.Endpoints.NO_CONTENT_ENDPOINT).statusCode(), 204);
    }

    @Test
    public void linkMovedRequest() {
        Assert.assertEquals(RequestToApi.methodGET(ApiData.Endpoints.MOVED_ENDPOINT).statusCode(),
                301);
    }

    @Test
    public void linkBadRequest() {
        Assert.assertEquals(RequestToApi.methodGET(ApiData.Endpoints.BAD_REQUEST_ENDPOINT).statusCode(),
                400);
    }

    @Test
    public void linkUnauthorizedRequest() {
        Assert.assertEquals(RequestToApi.methodGET(ApiData.Endpoints.UNAUTHORIZED_ENDPOINT).statusCode(),
                401);
    }

    @Test
    public void linkForbiddenRequest() {
        Assert.assertEquals(RequestToApi.methodGET(ApiData.Endpoints.FORBIDDEN_ENDPOINT).statusCode(),
                403);
    }

    @Test
    public void linkNotFoundRequest() {
        Assert.assertEquals(RequestToApi.methodGET(ApiData.Endpoints.NOT_FOUND_ENDPOINT).statusCode(),
                404);
    }

}
