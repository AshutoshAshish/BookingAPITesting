package endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;

import static io.restassured.matcher.RestAssuredMatchers.*;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.BookingPayload;
import utilities.FilePaths;


public class BookingEndPoints {
	
	// GET API Request (Get Booking IDs)
	public static Response getBookingIDs() {
		
		return given()
			.contentType(ContentType.JSON)
			.baseUri(Routes.GET_URL)
		.when()
			.get()
		.then()
			.statusCode(200)
		.extract().response();
	}
	
	// POST API Request
	public static Response createBooking(String postBody) {
			
			return given()
				.filter(new AllureRestAssured())
				.contentType(ContentType.JSON)
				.body(postBody)
				.baseUri(Routes.POST_URL)
			.when()
				.post()
			.then()
				.statusCode(200)
				.body("booking.firstname", equalTo("api testing"))
			.extract().response();
	}
	
	// Token Generation
	public static String generateToken() {
		
		HashMap<String,String> tokenBody= new HashMap<>();
		tokenBody.put("username", "admin");
		tokenBody.put("password", "password123");
		
		return given()
			.filter(new AllureRestAssured())
			.contentType(ContentType.JSON)
			.body(tokenBody)
			.baseUri(Routes.TOKEN_URL)
		.when()
			.post()
			.jsonPath().getString("token");
	}
	
	// GET API Request (Get Booking details)
	public static Response getBooking(int bID) {
		
		return given()
			.contentType(ContentType.JSON)
			.header("Cookie","token="+generateToken())
			.baseUri(Routes.BASE_URL)
		.when()
			.get("booking/{b_id}",bID);
		
	}
	
	// PUT API Request
	public static Response updateBooking(int bID, String putBody) {
		
		return given()
			.contentType(ContentType.JSON)
			.header("Cookie","token="+generateToken())
			.pathParam("b_id", bID)
			.body(putBody)
			.baseUri(Routes.BASE_URL)
		.when()
			.put("booking/{b_id}")
		.then()
			.statusCode(200)
		.extract().response();	
	}
	
	// PATCH API Request
	public static Response partialUpdateBooking(int bID, String patchBody) {
		
		return given()
			.contentType(ContentType.JSON)
			.header("Cookie","token="+generateToken())
			.pathParam("b_id", bID)
			.body(patchBody)
			.baseUri(Routes.BASE_URL)
		.when()
			.patch("booking/{b_id}")
		.then()
			.statusCode(200)
			.body("firstname", equalTo("Golu"))
		.extract().response();
	}
	
	// DELETE API Request
	public static Response deleteBooking(int bID) {
		
		return given()
			.filter(new AllureRestAssured())
			.contentType(ContentType.JSON)
			.header("Cookie", "token="+generateToken())
			.baseUri(Routes.BASE_URL)
		.when()
			.delete("booking/{b_id}",bID)
		.then()
			.statusCode(201)
		.extract().response();
	}

}
