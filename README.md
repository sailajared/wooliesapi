# wooliesapi
**Summary**

This repository contains test scripts for testing Weatherbit API.  

## Installation and Project Setup
Download Eclipse IDE - 4.8 
Download the code from Github
Open eclipse > Click File > Import > Maven > Existing maven projects > Select your project folder > Select pom.xml file
To execute any testsuites, Go to src/test/resources/xml_Suites/ , Select the xml suite you would want to run and right click on xml, select "Run as TestNG"

## Architecture
src/test/java --> www/weatherbit/Main — Consists of main SetUpImplementer which consists of setup and teardown methods
src/test/java --> www/weatherbit/Test -  Consists of Test file (invokes endpoint)
src/test/java --> www/weatherbit/Library — Consists of all files with methods that are common eg: utility files, logger files, file utils etc.
src/test/resources --> xml_suites - consists of testNG xml suites
src/test/resources --> testData/weatherdataSet_API.csv — Path of  TestData file
src/test/resources --> log4j.properties — Path of log4j properties to generate logs
target - Path of screen shots captured for failed test cases but this is API test so can’t take any screen shots
weatherbit.log — Path of Log file 
Test results can be found in target/surefire-reports folder. "emailable-report.html" is the file name to look into.

## Detailed Information about the Framework
API AUTOMATION
- How to run tests
/weatherbit/src/test/resources/xml_Suites/WEATHERBIT_API_Regression.xml - Path of xml file inside Eclipse IDE right click and run as TestNG test

  - Where to find the results
test-output folder will be generated in the project location once you execute the “WEATHERBIT_API_Regression.xml“ file
test-output/emailable-report.html— Inside test-output folder you can find html report
test-output/index.html— Inside test-output folder you can also find index.html file . Double click on the file and click once the file opens click on Reporter output where you can find append API Request and Response .

- Anything more you could have done if there was more time provided (Like, Any edge cases you could have covered)
I would have considered testing negative scenarios such as invalid postcode and blank postcode validations. However it was mentioned to emphasise the happy path scenarios.

- If any known issues and how to solve them
One of the conditions “I only like to surf on <Monday & Friday> in next 3 months” in the scenario asks for suitable day in the next 3 months where as the API 
(https://www.weatherbit.io/api/weather-forecast-16-day) is only available for 16 days.
It was difficult to get the test data(10 famous Sydney beaches post codes) as many of the valid post codes when used in the API returned many to be from different countries other than Australia. So I had to do it by trial and error by passing as many different Sydney post codes as possible.
For this condition “I Pick best suitable spot out of top two spots, based upon suitable weather forecast for the day “ I had to assume the weather codes (801, 802 and 803) are suitable for surfing as all other codes  had a description of extreme weather.

- Different components implemented in the framework
Language - Java to create user defined methods and libraries.
RESTAssured BDD Library to trigger API request/response and assertions.
TestNG for setup, teardown, basic reporting and capturing request & response.
MAVEN as build tool.
Log4j to generate logs.
JSONObject to extract and iterate through the response object.
 
 - Versions of libraries, plugin if you've used
I created a Maven Project in  Eclipse IDE, so once you download project folder from GitHub and import in to eclipse as Maven project there is no need to add any plugins.

   - Any challenges faced and how you solved it
One of the  challenges I faced was that I had to make some assumptions  that are mentioned in the known issues above with out which it was difficult to progress further.

 - Refer to the assignment for more
I double checked the assignment once again and confirm everything mentioned.

- Apart from the above these are the location of files which might be usefull
/weatherbit/src/test/java/www/weatherbit/Main — Path of Base Test Class
/weatherbit/src/test/java/www/weatherbit/Test/WeatherbitAPITests.java —Path of Test Class (invokes endpoint)
/weatherbit/src/test/java/www/weatherbit/Library — Path of helper classes (Libraries)
/weatherbit/src/test/resources/testData/weatherdataSet_API.csv — Path of  TestData file
/weatherbit/src/test/resources/log4j.properties — Path of log4j properties to generate logs
/weatherbit/target — Path of screen shots captured for failed test cases but this is API test so can’t take any screen shots
/weatherbit/weatherbit.log — Path of Log file 
