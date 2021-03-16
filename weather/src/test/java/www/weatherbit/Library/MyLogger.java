package www.weatherbit.Library;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyLogger {
	
	public Logger Logger = org.apache.log4j.Logger.getLogger(this.getClass().getName());
	public Logger returnLogger(){
		String log4JPropertyFile = "/weatherbit/src/test/resources/log4j.properties";
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(log4JPropertyFile));
			PropertyConfigurator.configure(p);
			Logger.info("Wow! I'm configured!");
			return Logger;
		} catch (IOException e) {
			e.printStackTrace();
	    }
		return null;
	}

}
