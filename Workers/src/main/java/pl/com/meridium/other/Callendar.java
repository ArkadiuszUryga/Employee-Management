package pl.com.meridium.other;

import java.util.Calendar;
import java.util.Date;

public class Callendar {
	public static Date getNextDate(Date nowDate) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(nowDate);
	    c.add(Calendar.MONTH, 1);
	    c.set(Calendar.DATE, c.getMaximum(Calendar.DATE));
	    Date nextDate = c.getTime();
	    return nextDate;
		}
	public static Date lastDayInNextMonth()	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 2);
	    c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
	    c.add(Calendar.DAY_OF_MONTH, -1);
	    Date lastDayInNextMonth=c.getTime();
		return lastDayInNextMonth;
		
	}
	public static Date firstDayInNextMonth()	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
	    c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
	    
	    Date firstDayInNextMonth=c.getTime();
		return firstDayInNextMonth;
		
	}
}
