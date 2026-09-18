package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pojo.NameData;
import utilities.DataProviders;

public class DataDrivenTesting extends BaseTest{
	
	@Test(dataProvider="nameDataFromJson", dataProviderClass=DataProviders.class)
	public void testDataDrivenUsingJson(NameData data) {
		
		logger.info("Data driven using JSON");
		
		System.out.println("FirstName is: "+data.getFirstname());
		System.out.println("LastName is: "+data.getLastname());
	}
	
	@Test(dataProvider="nameDataFromExcel", dataProviderClass=DataProviders.class)
	public void testDataDrivenUsingExcel(String serialNo, String name, String degree, String branch, String clgName) {
		
		logger.info("Data driven using Excel");
		
		System.out.println(serialNo+"\t"+name+"\t"+degree+"\t"+branch+"\t"+clgName);
		
	}
}
