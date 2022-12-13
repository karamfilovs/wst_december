package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class Request {
    private static final String BASE_URI = "https://api.inv.bg"; //localhost
    private static final String BASE_PATH = "/v3"; //v4
    protected static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Logger logger = LoggerFactory.getLogger("NoFrameworkTest.class");
    private String token;

    public Request (String token){
        this.token = token;
    }

    protected RequestSpecification baseRequest(){
        return RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .log().all()
                .auth().oauth2(token)
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .when();
    }

    protected Response get(String resource, Map<String, ?> queryParams){
        return baseRequest()
                .queryParams(queryParams)
                .get(resource)
                .prettyPeek();
    }

    protected Response get(String resource){
        return baseRequest()
                .get(resource)
                .prettyPeek();
    }

    protected Response delete(String resource){
        return baseRequest()
                .delete(resource)
                .prettyPeek();
    }

    protected Response post(String resource, String body, Map<String, ?> queryParams){
        return baseRequest()
                .queryParams(queryParams)
                .body(body)
                .post(resource)
                .prettyPeek();
    }

    protected Response patch(String resource, String body, Map<String, ?> queryParams){
        return baseRequest()
                .queryParams(queryParams)
                .body(body)
                .put(resource)
                .prettyPeek();
    }

    protected Response post(String resource, String body){
        return post(resource, body, new HashMap<>());
    }

    protected Response patch(String resource, String body){
        return patch(resource, body, new HashMap<>());
    }
}
