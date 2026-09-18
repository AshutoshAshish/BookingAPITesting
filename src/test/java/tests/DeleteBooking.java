package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.BookingEndPoints;
import io.restassured.response.Response;
import payloads.BookingPayload;

public class DeleteBooking {
	
	int bookingID;
	
	@Test
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
	
	@Test
	public void testDeleteBooking() {
		Response res= BookingEndPoints.deleteBooking(bookingID);
		
		Assert.assertEquals(res.statusCode(), 201);
	}

}
