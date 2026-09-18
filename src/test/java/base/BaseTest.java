package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;

public class BaseTest {
	
	public Logger logger = LogManager.getLogger(this.getClass());
	
	@BeforeMethod
	public void beforeMethod() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
}
