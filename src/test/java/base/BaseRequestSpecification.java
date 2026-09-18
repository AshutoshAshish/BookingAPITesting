package base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import endpoints.Routes;

public class BaseRequestSpecification {
	
	public static RequestSpecification getRequestSpecs() {
		return given()
				.baseUri(Routes.BASE_URL)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON);
	}

}
