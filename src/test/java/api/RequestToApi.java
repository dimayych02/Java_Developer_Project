package api;

import helpers.RestAssuredHelper;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static helpers.RestAssuredHelper.TypesOfRequest.*;


public class RequestToApi {

    @Step("Отправка запроса на сервер")
    public static Response requestToServer(String basePath) {
        return new RestAssuredHelper(GET)
                .setSpecification()
                .makeRequest(basePath);
    }

    @Step("Получение списка книг")
    public static List<BookStoreModel.Books> getBooks(String basePath) {
        return new RestAssuredHelper(GET)
                .setSpecification()
                .makeRequest(basePath)
                .as(BookStoreModel.class).getBooks();
    }

    @Step("Заполнение  JSON-параметрами body")
    public static Response fillJsonBody(Object jsonBody, String endpoint) {
        return new RestAssuredHelper(POST)
                .setBody(jsonBody)
                .makeRequest(endpoint);
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(String pathParam, String value, String endpoint) {
        return new RestAssuredHelper(DELETE)
                .setPathParam(pathParam, value)
                .makeRequest(endpoint);
    }

    @Step("Извлечение Response в BookStoreModel class")
    public static BookStoreModel extractResponse(Response response) {
        return response.as(BookStoreModel.class);
    }

}
