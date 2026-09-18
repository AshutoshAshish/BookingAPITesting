package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.BookingEndPoints;
import io.restassured.response.Response;
import payloads.BookingPayload;

public class UpdateBooking {
	
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
	public void testUpdateBooking() {
		
		try {
			//String putBody= BookingPayload.getPutRequestBody();
			Response res= BookingEndPoints.updateBooking(bookingID, BookingPayload.getPutRequestBody());
			
			Assert.assertEquals(res.statusCode(), 200);
			Assert.assertEquals(res.path("firstname"), "Ashutosh");
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
