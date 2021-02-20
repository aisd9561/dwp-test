package dwp.techtest.util;

public class Endpoints {
    private static final String BASE_URL =
            "https://bpdts-test-app.herokuapp.com";
    public static final String USERS_ENDPOINT =
            BASE_URL + "/users"; ;
    public static String CITY_USERS_ENDPOINT =
            BASE_URL + "/city/{city}/users"; ;
}
