package tests.selenium;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import lib.selenium.PreAndPost;
import lib.utils.HTMLReporter;
import pages.selenium.LoginPage;

public class TC004_CreateChangeRequestUsingRestAndVerifyUsingSelenium extends PreAndPost{

	@BeforeTest
	public void setValues() {

		testCaseName = "Search Change Request (Using Selenium) After Creating Change request (Using REST Assured)";
		testDescription = "Create Change Request (Using REST Assured) and Search with Selenium";
		nodes = "Change Request Management";
		authors = "Pavithra";
		category = "UI & API";
		dataSheetName = "TC004";

	}

	@Test(dataProvider = "fetchData")
	public void createCR(String filter) {

		// Post the request
		Response response = RESTAssuredBase.post("table/change_request");

		RESTAssuredBase.verifyResponseCode(response, 201);

		//Verify the Content by Specific Key
		crNumber = RESTAssuredBase.getContentWithKey(response, "result.number");

		// Selenium - Find Incident		
		new LoginPage(driver,test)
			.loginApp()
			.searchUsingFilter(filter)
			.clickOpen()
			.typeAndEnterSearch(crNumber)
			.verifyResult(crNumber);
	
	}


}





