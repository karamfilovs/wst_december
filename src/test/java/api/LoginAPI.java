package api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Credentials;
import io.restassured.RestAssured;

public class LoginAPI {
    private static final String BASE_URI = "https://api.inv.bg"; //localhost
    private static final String BASE_PATH = "/v3"; //v4
    private static final String EMAIL = "karamfilovs@gmail.com";
    private static final String PASSWORD = "111111";
    private static final String DOMAIN = "st2016";
    private static final String LOGIN_ENDPOINT = "/login/token";
    private static final Credentials creds = new Credentials(EMAIL, PASSWORD, DOMAIN);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String getToken(){
        return RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .body(gson.toJson(creds))
                .post(LOGIN_ENDPOINT)
                .then()
                .extract()
                .path("token");
    }
}
