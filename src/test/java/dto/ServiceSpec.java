package dto;

import io.restassured.authentication.AuthenticationScheme;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ServiceSpec {
    private AuthenticationScheme scheme;
    private String baseUri;
    private String basePath;
    private Integer port;
    private Map<String, String> cookies;
}
