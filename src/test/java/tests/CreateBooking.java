package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.BookingEndPoints;
import io.restassured.response.Response;
import payloads.BookingPayload;
import utilities.RetryAnalyzer;

public class CreateBooking extends BaseTest {
	
	@Test(retryAnalyzer= RetryAnalyzer.class)
	public void testCreateBooking(ITestContext context) {
		try {
			Response res= BookingEndPoints.createBooking(BookingPayload.getPostRequestBody());
			//res.then().log().ifValidationFails();type name = new type();
			
			Assert.assertEquals(res.statusCode(), 200);
			Assert.assertEquals(res.path("booking.firstname"), "api testing");
			Assert.assertEquals(res.jsonPath().getString("booking.lastname"), "tutorial");
			
			int bId= res.jsonPath().getInt("bookingid");
			//int bid= res.path("bookingid");
			System.out.println("Booking ID is: "+bId);
			context.setAttribute("bookingID", bId);
			System.out.println("Context Booking ID is: "+context.getAttribute("bookingID"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
