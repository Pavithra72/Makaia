package tests.rest;

import java.io.File;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.rest.PreAndTest;
import lib.rest.PreAndTest_Weather;
import lib.rest.RESTAssuredBase_Weather;
import lib.utils.DataInputProvider;

public class GetWeatherForCity2 extends RESTAssuredBase_Weather {
	
	
	@BeforeTest
	public void setValues() {
		testCaseName = "Create weather report for five cities (REST)";
		testDescription = "Create weather report for five cities and Verify";
		nodes = "Weather report";
		authors = "Pavithra";
		category = "API";
		dataFileName = "TC01_Places";
		dataFileType = "Excel";
	}

	
	@Test(dataProvider = "fetchData")
	public void getWeather(String city){
	//	RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather";
		
		Response response = getWithParams(city);
					response.prettyPrint();

					// Verify the response status code
					verifyResponseCode(response, 200);	
					
					// Verify the response time
					verifyResponseTime(response, 3000);
				
	JsonPath jsonResponse = response.jsonPath();
	float maxTemp = jsonResponse.get("main.temp_max");
	System.out.println("The maximum temperature is " + maxTemp);
	int sunsetTime = jsonResponse.get("sys.sunset");
System.out.println("The sunset time is " + sunsetTime);
float windSpeed = jsonResponse.get("wind.speed");
System.out.println("The wind speed is " + windSpeed);

//		var maxTemp = response.main.temp_max;
//		console.log("The maximum temperature is " + maxTemp);
//		var sunsetTime = response.sys.sunset;
//		console.log("The sunset time is " + sunsetTime);
//		var windspeed = response.wind.speed;
//		console.log("The wind speed is " + windspeed);
		
	}
}
