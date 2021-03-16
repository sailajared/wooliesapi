package www.weatherbit.Library;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;

public class CommonUtil {

	final static Logger Logger = org.apache.log4j.Logger.getLogger(CommonUtil.class.getName());

	public static List<List<LocalDate>> getDates()
	{
		LocalDate startDate=LocalDate.now();
		LocalDate endDate=startDate.plusMonths(3);
		long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
		List<LocalDate> mondaysRange = Stream.iterate(startDate, date-> date.plusDays(1)).limit(numOfDays).filter( date -> date.getDayOfWeek()==DayOfWeek.MONDAY ).collect(Collectors.toList());
		List<LocalDate> fridaysRange = Stream.iterate(startDate, date-> date.plusDays(1)).limit(numOfDays).filter( date -> date.getDayOfWeek()==DayOfWeek.FRIDAY ).collect(Collectors.toList());
		Logger.info("mondaysRange.."+mondaysRange);
		Logger.info("fridaysRange.."+fridaysRange);
		return Arrays.asList(mondaysRange,fridaysRange);
	}


}