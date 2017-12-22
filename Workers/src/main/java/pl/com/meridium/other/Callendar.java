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
}
