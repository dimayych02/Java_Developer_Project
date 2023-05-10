package api;

import java.util.List;
import java.util.stream.Collectors;

public class BookAttributes {
    public static List<String> getBooksTitleApi() {
        return RequestToApi.getBooks(ApiData.Endpoints.GET_BOOKS_ENDPOINT)
                .stream()
                .map(x -> x.getTitle().toString())
                .collect(Collectors.toList());
    }

    public static List<String> getBooksAuthorApi() {
        return RequestToApi.getBooks(ApiData.Endpoints.GET_BOOKS_ENDPOINT)
                .stream()
                .map(x -> x.getAuthor().toString())
                .collect(Collectors.toList());
    }

    public static List<String> getBooksPublisherApi() {
        return RequestToApi.getBooks(ApiData.Endpoints.GET_BOOKS_ENDPOINT)
                .stream()
                .map(x -> x.getPublisher().toString())
                .collect(Collectors.toList());
    }
}
