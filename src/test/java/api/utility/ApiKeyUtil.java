package api.utility;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class ApiKeyUtil
{
	 private static final String BASE_URL = ConfigReader.get("base.url");
	    private static final String API_KEY = ConfigReader.get("api.key");
	    private static final String HEADER_NAME = ConfigReader.get("api.key.header");

	    public static RequestSpecification withApiKey() {
	        return given()
	                .baseUri(BASE_URL)
	                .header(HEADER_NAME, API_KEY);
	    }

}
