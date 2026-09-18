package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.BookingEndPoints;
import io.restassured.response.Response;

public class GetBookingIDs {
	
	@Test
	public void testGetBookingIDs() {
		
		Response res= BookingEndPoints.getBookingIDs();
		
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.statusLine(), "HTTP/1.1 200 OK");
	}

}
