package api.utility;
import java.util.function.Supplier;

import io.restassured.response.Response;

public class RetryUtil {

	public static Response retryRequest(Supplier<Response> requestSupplier, int maxRetries) {
		Response response = null;
		int attempt = 0;

		while (attempt < maxRetries) {
			response = requestSupplier.get();
			if (response.getStatusCode() == 200) {
				return response;
			}
			attempt++;
			try {
				Thread.sleep(1000); // Optional delay between retries
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}

		return response;
	}

}
