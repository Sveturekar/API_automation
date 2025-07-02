package api.endpoint;
import static io.restassured.RestAssured.given;



import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
//import com.github.scribejava.core.model.Response;

import api.payload.User;
import api.utility.ConfigReader;

public class UserEndpoints 
{
	public static Response CreateUser(User payload)
	{
				
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
			.post(Routes.Post_url);
		
		return response;
	}
	
	public static Response ReadUser(String Username)
	{
		Response response = given()
				.pathParam("username", Username)
				
		.when()
			.get(Routes.Get_url);
		
		return response;
	}
	public static Response UpdateUser(String Username, User payload)
	{
		Response response = given()
				.pathParam("username", Username)
				.body(payload)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				
				
		.when()
			.put(Routes.Put_url);
		 	
		return response;
	}
	
	public static Response DeleteUser (String Username )
	{
		Response response = given()
					.pathParam("username", Username)
				
				.when()
					.delete(Routes.Delete_url);
		
		return response;
	}
	
	/*public void testGetUserWithApiKeyInQuery() 
	{
	   private static final String BASE_URL = ConfigReader.get("base.url");
	    private static final String API_KEY = ConfigReader.get("api.key");
	    private static final String HEADER_NAME = ConfigReader.get("api.key.header");

	    public static RequestSpecification withApiKey() {
	        return given()
	                .baseUri(BASE_URL)
	                .header(HEADER_NAME, API_KEY);
	    }
	}*/
	

}
