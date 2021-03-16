package www.weatherbit.Test;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import www.weatherbit.Main.DefaultTestBedSetupImplementer;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class WeatherbitAPITests extends DefaultTestBedSetupImplementer  {
	final static Logger Logger = org.apache.log4j.Logger.getLogger(WeatherbitAPITests.class.getName());
	@BeforeMethod(alwaysRun=true)
	public void setUp()
	{
		super.setUp();
		RestAssured.baseURI=apiURLFromXML;
		RestAssured.basePath=basePath;
	}
	@Test(groups= {"REGRESSION","SUITABLE_SPOTS"})
	public void listSuitableSpots()  
	{
		int counter=0;
		HashMap<String,String> parametersMap=new HashMap<>();
		parametersMap.put("Key","885e8ae77a404573a0e57688704e5d06");
		if(testData.size()>0)
		{
		postcode:	
		for(Map<String, String> data:testData)
		{	
		Reporter.log("EndPoint URL---"+apiURLFromXML+basePath+"?postal_code="+data.get("postcode")+"&Key=885e8ae77a404573a0e57688704e5d06");	
		parametersMap.put("postal_code",data.get("postcode"));
		Response response=
		given().params(parametersMap).when().get().then().statusCode(200).statusLine("HTTP/1.1 200 OK").assertThat()
		.body("city_name",equalTo(data.get("suburb")))
		.header("content-Type","application/json; charset=utf-8").extract().response();
		JSONObject jsonResponse= new JSONObject(response.body().asString());
		if(jsonResponse.toString().contains("data"))
		{
			JSONArray dataArray=jsonResponse.getJSONArray("data");
			for(int i=0;i<dataArray.length();i++)
			{
				LocalDate date=LocalDate.parse(dataArray.getJSONObject(i).getString("datetime"));
				if(daysList.get(0).stream().filter(d->d.equals(date)).anyMatch(s->true) || daysList.get(1).stream().filter(d->d.equals(date)).anyMatch(s->true))
				{
					int temp=(int)dataArray.getJSONObject(i).getFloat("temp");
					int windSpeed=(int)dataArray.getJSONObject(i).getDouble("wind_spd");
					int uvIndex=(int)dataArray.getJSONObject(i).getDouble("uv");
					if(temp>=12&&temp<=30&&windSpeed>=3&&windSpeed<=9&&uvIndex<=12)
					{
						String weatherCode=String.valueOf(dataArray.getJSONObject(i).getJSONObject("weather").getInt("code"));
						//Derived this condition based on the assumption that codes (801, 802 and 803) are suitable for surfing
						//Added counter to stop iterations after showing two suitable spots
						//if we remove counter it shows all the suitable outputs for next 3 months including (Monday,Friday) with suitable weather conditions 
						
						if((weatherCode.contains("801")||weatherCode.contains("802")||weatherCode.contains("803"))&&counter<2)
						{
						Reporter.log("JSON Response Iteration["+i+"]---"+response.asString());	
						counter++;
						Logger.info("Best Suitable Spot:  " +jsonResponse.getString("city_name"));
						Logger.info("Lonitude:  "+jsonResponse.getString("lon"));
						Logger.info("Latitude:  "+jsonResponse.getString("lat"));
						Logger.info("Suitable Temp.. Wind.. UV and Date is     :"+dataArray.getJSONObject(i).getString("datetime"));
						Logger.info("**************************************************************");
						}else if(counter>=2)break postcode;
					}
				}
			}
		}
		}
		}
//	
	}

	
	
	
}
	
	
