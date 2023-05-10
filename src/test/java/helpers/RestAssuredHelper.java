package helpers;

import api.ApiData;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredHelper {
    private static TypesOfRequest type;
    private RequestSpecification request;

    /**
     * Виды запросов
     */
    public enum TypesOfRequest {
        GET,
        POST,
        DELETE
    }

    public RestAssuredHelper(final TypesOfRequest typesOfRequest) {
        this.type = typesOfRequest;
    }

    public RestAssuredHelper setSpecification() {
        request = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON);
        return this;
    }

    public RestAssuredHelper setBody(Object body) {
        request = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(body);
        return this;
    }

    public RestAssuredHelper setPathParam(String paramName, String paramValue) {
        request = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .pathParam(paramName, paramValue);
        return this;
    }

    public RestAssuredHelper setQueryParam(String paramName, String paramValue) {
        request = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .queryParam(paramName, paramValue);
        return this;
    }

    @Step("Отправка запроса на сервер")
    public Response makeRequest(final String basePath) {
        Response response;
        String requestUrl = ApiData.Endpoints.BASE_URL + basePath;
        switch (type) {
            case GET:
                response = request.get(requestUrl);
                break;
            case POST:
                response = request.post(requestUrl);
                break;
            case DELETE:
                response = request.delete(requestUrl);
                break;
            default:
                throw new IllegalStateException("Unexpected value:" + type);
        }
        return response;
    }

}
