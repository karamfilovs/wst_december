package legacy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredBasicTests {
    private static final String BASE_URI = "https://st2016.inv.bg";
    private static final String BASE_PATH = "/RESTapi";
    private static final String USERNAME = "karamfilovs@gmail.com";
    private static final String PASSWORD = "111111";
    @Test
    @DisplayName("Can get all items")
    public void canGetAllItems(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
        RequestSpecification specification = RestAssured
                .given()
                .log().all()
                .auth().basic(USERNAME, PASSWORD)
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                .contentType(ContentType.JSON) //Content-Type = application/json
                //Log all details of my request verb, headers, body etc
                .when();

//                specification.get("/items").prettyPeek();
//                specification.get("/item/7353").prettyPeek();
                String id = "45";
                Map<String, String> headers = new HashMap<>();
                headers.put("header1", "value1");
                headers.put("header2", "value2");
                headers.put("X-API-VERSION", "2");
                headers.put("Authorization", "Bearer token"); //Long lived JWT token
//                Item item = specification.headers(headers)
//                        .pathParam("id", id)
//                        .pathParam("parentId", id)
//                        .get("/users/{parentId}/child/{id}")
//                        .then();


    }
}
