package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class BookStoreModel {
    private List<Book> books;
    private String token;
    private String userID;
    private String message;
    private String userName;
    private String password;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Builder
    public static class Book {
        private String isbn;
        private String username;
        private String title;
        private String author;
        private String description;
        private String publisher;
        private int pages;
    }
}












