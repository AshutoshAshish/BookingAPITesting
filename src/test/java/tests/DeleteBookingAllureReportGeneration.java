package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.BookingEndPoints;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import payloads.BookingPayload;


@Epic("Epic-01")
@Feature("Create and Update the booking")
public class DeleteBookingAllureReportGeneration {
	
	int bookingID;
	
	@Story("Story-1")
	@Test(description="create booking api test")
	@Description("create booking api test")
	@Severity(SeverityLevel.CRITICAL)
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
	
	@Story("Story-2")
	@Test(description="delete booking api test")
	@Description("delete booking api test")
	@Severity(SeverityLevel.BLOCKER)
	public void testDeleteBooking() {
		Response res= BookingEndPoints.deleteBooking(bookingID);
		
		Assert.assertEquals(res.statusCode(), 201);
	}

}
