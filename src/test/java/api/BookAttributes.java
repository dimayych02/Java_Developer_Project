package api;


import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

public class BookAttributes {
    @Step("Получение название книг на api")
    public static List<String> getBooksTitleApi() {
        return RequestToApi.extractResponse(RequestToApi.methodGET(ApiData.Endpoints.GET_BOOKS_ENDPOINT))
                .getBooks().stream().map(x -> x.getTitle()).collect(Collectors.toList());
    }

    @Step("Получение  списка авторов книг на api")
    public static List<String> getBooksAuthorApi() {
        return RequestToApi.extractResponse(RequestToApi.methodGET(ApiData.Endpoints.GET_BOOKS_ENDPOINT))
                .getBooks()
                .stream()
                .map(x -> x.getAuthor().toString())
                .collect(Collectors.toList());
    }

    @Step("Получение списка писателей на api")
    public static List<String> getBooksPublisherApi() {
        return RequestToApi.extractResponse(RequestToApi.methodGET(ApiData.Endpoints.GET_BOOKS_ENDPOINT))
                .getBooks()
                .stream()
                .map(x -> x.getPublisher().toString())
                .collect(Collectors.toList());
    }

}
