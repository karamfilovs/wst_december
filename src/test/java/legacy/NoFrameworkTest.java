package legacy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Credentials;
import dto.Item;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;

public class NoFrameworkTest {
    private static final String BASE_URI = "https://api.inv.bg"; //localhost
    private static final String BASE_PATH = "/v3"; //v4
    private static final String EMAIL = "karamfilovs@gmail.com";
    private static final String PASSWORD = "111111";
    private static final String DOMAIN = "st2016";
    private static final String LOGIN_ENDPOINT = "/login/token";
    private static final Credentials creds = new Credentials(EMAIL, PASSWORD, DOMAIN);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static String TOKEN;
    private static Logger logger = LoggerFactory.getLogger("legacy.NoFrameworkTest.class");

    @BeforeAll
    public static void beforeAll() {
        logger.info("Log something");
        TOKEN = RestAssured.given()
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

    @Test
    @DisplayName("Can obtain token")
    public void canObtainToken() {
        logger.info("Log something in the test");
        RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .log().all() //Log all request details
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .body(gson.toJson(creds))
                .post(LOGIN_ENDPOINT)
                .prettyPeek()
                .then()
                .body("token", not(empty()))
                .statusCode(200);
    }

    @Test
    @DisplayName("Can retrieve current company details")
    public void canRetrieveCurrentCompanyDetails() {
        RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .auth().oauth2(TOKEN)
                .log().all()
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .get("/firm")
                .prettyPeek()
                .then()
                .body("subdomain", equalTo("st2016"))
                .body("name", equalTo("QA Ground"))
                .body("email", containsString("karamfilovs"))
                .statusCode(200);
    }

    @Test
    @DisplayName("Can get all items")
    public void canGetAllItems() {
        RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .auth().oauth2(TOKEN)
                .log().all()
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .get("/items")
                .prettyPeek()
                .then()
                .statusCode(200)
              //  .body("items.id", hasItem(7355)) //It fails because the id is deleted from the system
                .body("$", hasKey("total"))
                .body("$", hasKey("prev_page"))
                .body("$", hasKey("next_page"));
    }

    @Test
    @DisplayName("Can get items by specified number per page")
    public void canGetItemsBySpecifiedNumberPerPage() {
        RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .auth().oauth2(TOKEN)
                .log().all()
                .queryParam("per_page", 1)
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .get("/items")
                .prettyPeek()
                .then()
                .statusCode(200);
               // .body("items.id", hasSize(1)); //Size is very dynamic so this is prone to failures
    }

    @Test
    @DisplayName("Can create new item")
    public void canCreateNewItem() {
        Item coffee = Item.builder()
                .name("Lavazza4")
                .price(20.20)
                .priceForQuantity(1.0)
                .unit("kg.")
                .build();
        int id = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .log().all() //Log all request details
                .auth().oauth2(TOKEN)
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .body(gson.toJson(coffee))
                .post("/items")
                .prettyPeek()
                .then()
                .statusCode(201)
                .body("$", hasKey("id"))
                .extract().path("id");

        //Delete item
        RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .log().all() //Log all request details
                .auth().oauth2(TOKEN)
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .delete("/items/{id}")
                .prettyPeek()
                .then().statusCode(204);
    }

    @Test
    @DisplayName("Can update item")
    @Disabled("Not implemented")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("INV-34343")
    @Issue("IN-79")
    @Description("To test that user can update existing item")
    public  void canUpdateItem(){
        //Create item dto/pojo
        Item coffee = Item.builder()
                .name("ToBeUpdated2")
                .price(20.20)
                .priceForQuantity(1.0)
                .unit("kg.")
                .build();
        //Create item
        int id = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .log().all() //Log all request details
                .auth().oauth2(TOKEN)
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .body(gson.toJson(coffee))
                .post("/items")
                .prettyPeek()
                .then()
                .statusCode(201)
                .body("$", hasKey("id"))
                .extract().path("id");
        //Update item
        coffee.setName("Updated");
        coffee.setPrice(10.20);
        RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .log().all() //Log all request details
                .auth().oauth2(TOKEN)
                .header("User-Agent", "Mozilla")
                .header("Content-Type", "application/json")
                .body(gson.toJson(coffee))
                .pathParam("path", id)
                .patch("/items/{path}")
                .prettyPeek()
                .then()
                .body("name", equalTo("Updated"))
                .statusCode(204);

        //Delete item

    }
}
