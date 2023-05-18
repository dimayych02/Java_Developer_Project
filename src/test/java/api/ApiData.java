package api;

import dataGenerator.Generator;

public class ApiData {

    public static final class Endpoints {
        public static final String BASE_URL = "https://demoqa.com/";
        public static final String CREATED_ENDPOINT = "created";
        public static final String NO_CONTENT_ENDPOINT = "no-content";
        public static final String MOVED_ENDPOINT = "moved";
        public static final String BAD_REQUEST_ENDPOINT = "bad-request";
        public static final String UNAUTHORIZED_ENDPOINT = "unauthorized";
        public static final String FORBIDDEN_ENDPOINT = "forbidden";
        public static final String NOT_FOUND_ENDPOINT = "invalid-url";
        public static final String GET_BOOKS_ENDPOINT = "BookStore/v1/Books";
        public static final String AUTHORIZATION_ENDPOINT = "Account/v1/Authorized";
        public static final String GENERATE_TOKEN_ENDPOINT = "Account/v1/GenerateToken";
        public static final String NEW_USER_ENDPOINT = "Account/v1/User";
        public static final String DELETE_USER_ENDPOINT="Account/v1/User/{UUID}";

    }

    public static final class UserData {
        public static final String USER_NAME = "dimyych02";
        public static final String USER_PASSWORD = "Dima301020022@";
        public static String newUser= Generator.generateString();
        public static String newPassword=Generator.generatePassword();
    }

    public static final class ResponseMessage {
        public final static String USER_EXIST_MESSAGE = "User exists!";
    }
    public static final class PathParams{
        public static final String UUID_PARAM= "UUID";
    }
}
