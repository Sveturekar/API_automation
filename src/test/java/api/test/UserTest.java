package api.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.endpoint.UserEndpoints;
import api.payload.User;
import api.utility.ApiKeyUtil;
import api.utility.ExtentReportManager;
import api.utility.ResponseValidator;
import api.utility.RetryUtil;
import io.restassured.response.Response;

public class UserTest 
{
	User userPayload;
	Faker faker;
	ExtentReports extent;
	ExtentTest test;
	 String token;
	
	@BeforeTest
	public void setupdata() throws JsonProcessingException
	{
		faker = new Faker();
		userPayload = new User();
		
		//userPayload.setId(faker.idNumber().hashCode());
		userPayload.setId(Math.abs(faker.idNumber().hashCode()));
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//userPayload.setPhone(faker.internet().password(5, 10, true, true, false));
		
	
		
		System.out.println(new ObjectMapper().writeValueAsString(userPayload));



extent = ExtentReportManager.getReportInstance();
test = extent.createTest("Setup User Data");
test.info("User data generated: " + userPayload.getUsername());

//token = AuthUtil.getAuthToken();
	
				
}
	@Test(priority=1)
	public void TestPostUser()
	{
		test = extent.createTest("Create User Test");
		Response response= UserEndpoints.CreateUser(userPayload);
		//System.out.println(new ObjectMapper().writeValueAsString(userPayload));
		response.then().log().all();
		ResponseValidator.validateResponse(response, test, 200);
		test.info("Response: " + response.asString());
		test.pass("User created successfully");

Response retryResponse = RetryUtil.retryRequest(() -> UserEndpoints.CreateUser(userPayload), 3);
retryResponse.then().log().all();

	}
	
	@Test(priority=2)
	public void GetUser() throws InterruptedException
	{
		Thread.sleep(5000);
		test = extent.createTest("Get User Test");
		Response response = UserEndpoints.ReadUser(this.userPayload.getUsername());
		response.then().log().all();
		ResponseValidator.validateResponse(response, test, 200);
		test.info("Response: " + response.asString());
		test.pass("Read User successfully");
	}
	
	@Test(priority=3)
	public void UpdateUser()
	{
		test = extent.createTest("Update User Test");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response= UserEndpoints.UpdateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		ResponseValidator.validateResponse(response, test, 200);
		Response responseafterupdate=UserEndpoints.ReadUser(this.userPayload.getUsername());
				assertEquals(responseafterupdate.getStatusCode(),200);
				test.info("Response: " + response.asString());
				test.pass("Update User successfully");
	}
	@Test(priority=4)
	public void DeleteUser()
	{
		test = extent.createTest("Delete User Test");
		Response response = UserEndpoints.DeleteUser(this.userPayload.getUsername());
		response.then().log().all();
		ResponseValidator.validateResponse(response, test, 200);
		test.info("Response: " + response.asString());
		test.pass("Delete User successfully");
		
	}
	@Test(priority=5)
	public void testUserDetailsWithUtil() 
{Response response = ApiKeyUtil.withApiKey()
		            .when()
		            .get("/user/123");

		        assertEquals(response.getStatusCode(), 200);
	    ResponseValidator.validateResponse(response, test, 200);
		test.info("Response: " + response.asString());
		test.pass("API Key Token");
	}
	

@AfterSuite
public void tearDown() 
{
ExtentReportManager.getReportInstance().flush();
}

	
	@AfterTest
	public void flushExtent()
	{
		extent.flush();
	}

}
