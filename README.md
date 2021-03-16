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
