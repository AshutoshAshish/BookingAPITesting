package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.BookingEndPoints;
import io.restassured.response.Response;
import payloads.BookingPayload;

public class PartialUpdateBooking {
	
	int bookingID;
	
	@Test(priority=1)
	public void testCreateBooking() {
		try {
			Response res= BookingEndPoints.createBooking(BookingPayload.getPostRequestBody());
			//res.then().log().all();
			
			Assert.assertEquals(res.statusCode(), 200);
			Assert.assertEquals(res.path("booking.firstname"), "api testing");
			Assert.assertEquals(res.jsonPath().getString("booking.lastname"), "tutorial");
			
			bookingID= res.jsonPath().getInt("bookingid");
			System.out.println("Booking ID is: "+bookingID);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void testPartialUpdateBooking() {
		Response res= BookingEndPoints.partialUpdateBooking(bookingID, BookingPayload.getPatchRequestBody());
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("lastname"), "Polu");
	}

}
