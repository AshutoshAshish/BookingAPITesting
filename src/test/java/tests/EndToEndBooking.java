package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.BookingEndPoints;
import io.restassured.response.Response;
import payloads.BookingPayload;

public class EndToEndBooking extends BaseTest {
	
	int bookingID;
	
	@Test(priority=1)
	public void testGetBookingIDs() {
		
		logger.info("****Starting EndToEndBooking Test Case...****");
		
		Response res= BookingEndPoints.getBookingIDs();
		
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.statusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(priority=2)
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
	
	@Test(priority=3)
	public void testGetBooking() {
		
		Response res= BookingEndPoints.getBooking(bookingID);
		
		Assert.assertEquals(res.statusCode(), 200);
	}
	
	@Test(priority=4)
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
	
	@Test(priority=5)
	public void testPartialUpdateBooking() {
		Response res= BookingEndPoints.partialUpdateBooking(bookingID, BookingPayload.getPatchRequestBody());
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("lastname"), "Polu");
	}
	
	@Test(priority=6)
	public void testDeleteBooking() {
		Response res= BookingEndPoints.deleteBooking(bookingID);
		
		Assert.assertEquals(res.statusCode(), 201);
		logger.info("****Completed EndToEndBooking Test Case...****");
	}

}
