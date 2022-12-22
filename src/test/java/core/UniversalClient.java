package core;

import dto.ServiceSpec;
import io.restassured.RestAssured;
import io.restassured.authentication.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class UniversalClient {
    private AuthenticationScheme scheme;
    private String baseUri;
    private String basePath;
    private int port;
    private ServiceSpec spec;

    public UniversalClient (AuthenticationScheme scheme, String baseUri, String basePath, int port){
        this.scheme = scheme;
        this.baseUri = baseUri;
        this.basePath = basePath;
        this.port = port;
    }

    public  UniversalClient (ServiceSpec spec){
        this.spec = spec;
    }

    public RequestSpecification baseRequest() {
        RestAssured.authentication = spec.getScheme();
        return RestAssured.given()
                .header("User-Agent", "Mozilla")
                .header("XSRF-TOKEN", "")
                .baseUri(spec.getBaseUri())
                .basePath(spec.getBasePath())
                .port(spec.getPort())
                .log().all()
                .accept(ContentType.HTML)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }

    public Response get(String resource, Map<String, String> queryParams){
        return baseRequest()
                .when()
                .queryParams(queryParams)
                .get(resource);
    }

    public static void main(String[] args) {
        //XSRF token extraction 401/403 (missing xsrf token)
        //GET request to obtain the main page
        ServiceSpec xsrfSpec = ServiceSpec.builder().build();
        xsrfSpec.setBaseUri("https://pragmaticbg.atlassian.net");
        xsrfSpec.setBasePath("");
        xsrfSpec.setPort(443);
        xsrfSpec.setScheme(new NoAuthScheme());
        UniversalClient client = new UniversalClient(xsrfSpec);
        Response getMainPage = client.get("", Maps.of("", ""));
        System.out.println(getMainPage.body());
        Map<String, String> cookies = getMainPage.cookies();
        System.out.println("Cookies" + cookies.toString());
        cookies.values().forEach(cookie -> System.out.println(cookie.toUpperCase()));
        String extractedToken = "token";


        //Inv.bg client for version 2 of the API
        PreemptiveBasicAuthScheme basicAuthScheme = new PreemptiveBasicAuthScheme();
        basicAuthScheme.setUserName("karamfilovs@gmail.com");
        basicAuthScheme.setPassword("111111");
        ServiceSpec spec = ServiceSpec.builder().build();
        spec.setBaseUri("https://st2016.inv.bg");
        spec.setBasePath("/RESTapi");
        spec.setPort(443);
        spec.setScheme(basicAuthScheme);
        UniversalClient universalClient = new UniversalClient(spec);
        Response getItemsResp = universalClient.get("/items", Maps.of("", ""));
        getItemsResp.prettyPeek();

        //Inv.bg Client for the version 3 of the API
        PreemptiveOAuth2HeaderScheme ouath2Scheme = new PreemptiveOAuth2HeaderScheme();
        ouath2Scheme.setAccessToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmaXJtSUQiOiIyODkwMCIsInVzZXJJRCI6IjQiLCJzY29wZSI6WyJhZG1pbiJdLCJ1bnJlc3RyaWN0ZWQiOnRydWUsInRva2VuSUQiOiI1MSJ9.HCA6blRejIV_eNqc0H_xadtns96HkHN5czggCgHEUSk");
        ServiceSpec version3Spec = ServiceSpec.builder().build();
        version3Spec.setBaseUri("https://api.inv.bg");
        version3Spec.setBasePath("/v3");
        version3Spec.setPort(443);
        version3Spec.setScheme(ouath2Scheme);
        UniversalClient invBgClient = new UniversalClient(version3Spec);
        Response version3GetResp = invBgClient.get("/items", Maps.of("order", "asc"));
        version3GetResp.prettyPeek();

        //Pet Clinic client for the latest version of the api
        //http://localhost:9966/petclinic/api/vets
        ServiceSpec petClinicSpec = ServiceSpec.builder().build();
        petClinicSpec.setBaseUri("http://127.0.0.1");
        petClinicSpec.setBasePath("/petclinic/api");
        petClinicSpec.setPort(9966);
        AuthenticationScheme noAuth = new NoAuthScheme(); //This without Authentication
        PreemptiveBasicAuthScheme basicAuthForPetClinic = new PreemptiveBasicAuthScheme();
        basicAuthForPetClinic.setPassword("admin");
        basicAuthForPetClinic.setUserName("admin");
        petClinicSpec.setScheme(basicAuthForPetClinic);
        UniversalClient petClinicClient = new UniversalClient(petClinicSpec);
        Response getVetsRes = petClinicClient.get("/vets", Maps.of("",""));
        Assertions.assertEquals(200, getVetsRes.statusCode());
        getVetsRes.prettyPeek();
    }
}
