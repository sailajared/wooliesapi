package www.weatherbit.Main;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import www.weatherbit.Library.CommonUtil;
import www.weatherbit.Library.FileUtility;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DefaultTestBedSetupImplementer {

    final static Logger Logger = org.apache.log4j.Logger.getLogger(DefaultTestBedSetupImplementer.class.getName());
    public List<Map<String,String>>testData = null;
	public List<List<LocalDate>> daysList=null;
    public String testDataFileFromXML = "",apiURLFromXML = "",basePath=""; 
    
    @Parameters({"apiUrl","basePath","testDataFile"})
    @BeforeClass(alwaysRun = true)
    public void getParamsFromXML(@Optional("http://api.weatherbit.io/v2.0") String apiURL,
    							 @Optional("/forecast/daily") String basePath,	
                              @Optional("weatherdataSet_API.csv") String testDataFile) {
    
    	apiURLFromXML = apiURL;
    	this.basePath=basePath;
        testDataFileFromXML = testDataFile;
    }
    @BeforeMethod(alwaysRun=true)
	public void setUp()
	{
    	Logger.info("-----------Start Of Test------------");
    	try {
		 testData = FileUtility.loadDataFromCSV(testDataFileFromXML);
		 daysList=CommonUtil.getDates();
    	}catch (Exception e) {
            e.printStackTrace();
        }
	}

   // @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
    	String methodName = result.getName();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        File screenShotPath = new File("." + File.separator + "target" + File.separator + "FailedScreenShots/" + currentDate);

        if (!screenShotPath.isDirectory()) {
            screenShotPath.mkdir();
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        methodName = result.getName();
        if (!result.isSuccess()) {
            
        }
       
    	
        Logger.info("-----------End Of Test--" + methodName + "----------");
    
    }
  
    
    


}