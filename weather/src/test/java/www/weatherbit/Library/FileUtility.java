package www.weatherbit.Library;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileUtility {

	final static Logger Logger = org.apache.log4j.Logger.getLogger(FileUtility.class.getName());
	public static List<Map<String,String>> loadDataFromCSV(String fileName) throws IOException 
	{
		List<Map<String,String>> list=new ArrayList<>();			
		File file=new File("./src/test/resources/testData/"+fileName);
		byte[] bytes=FileUtils.readFileToByteArray(file);		
		String data=new String(bytes);
		data=StringUtils.replaceAll(data,"\r","");
		String[] dataArray=data.split("\n"); // Number of rows is dataArray.length		
		String keys=dataArray[0]; //Header of csv file
		String[] keyArr=keys.split(","); //splitting Header of csv file to make them individual keys		
		for(int i=1;i<dataArray.length;i++)
		{
			Map<String,String> mp=new LinkedHashMap<>();			
			String[] rowArr=dataArray[i].split(",");			
			for(int j=0;j<keyArr.length;j++)
				{
					mp.put(keyArr[j].trim(),rowArr[j].trim());	
				}	
			list.add(mp);
		}
		System.out.println(list);
		return list;	
	}
}