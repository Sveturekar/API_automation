package api.utility;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;
public class ResponseValidator {


    public static void validateResponse(Response response, ExtentTest test, int expectedStatus) {
        int actualStatus = response.getStatusCode();

        if (actualStatus == expectedStatus) {
            test.pass("✅ Status code matched: " + actualStatus);
        } else if (actualStatus == 500) {
            test.fail("❌ Internal Server Error (500)");
            logErrorDetails(response, test);
        } else if (actualStatus == 400) {
            test.fail("❌ Bad Request (400)");
            logErrorDetails(response, test);
        } else if (actualStatus == 401 || actualStatus == 403) {
            test.fail("❌ Authentication/Authorization Failed: " + actualStatus);
            logErrorDetails(response, test);
        } else {
            test.fail("❌ Unexpected Status Code: " + actualStatus);
            logErrorDetails(response, test);
        }
    }

    private static void logErrorDetails(Response response, ExtentTest test) {
        test.info("Response Body: " + response.asString());
        test.info("Headers: " + response.getHeaders().toString());
        test.info("Status Line: " + response.getStatusLine());
    }
	
}
