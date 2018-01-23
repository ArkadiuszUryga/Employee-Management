package pl.com.meridium.other;

import java.util.Calendar;





public class WorkdaysCounter {
	

	
	public static int WorkDayCounter(int monthsToAdd) {
		Calendar c = Calendar.getInstance();
		if (monthsToAdd != 0) {
			c.add(Calendar.MONTH, 2);
			c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
			c.add(Calendar.DAY_OF_MONTH, -1);
		}
		else	{
			c.add(Calendar.MONTH, 1);
			c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
			c.add(Calendar.DAY_OF_MONTH, -1);
			
		}
		
		int lastDay = c.get(Calendar.DATE);
		int workDaysCounter=0;
	    for (int i=1;i<=lastDay;i++) {
	    	
	    	c.set(c.YEAR, c.MONTH, i);
	    	int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
	    	if (dayOfWeek<6) {
	    		workDaysCounter++;
	    	}
	    }
	    

		return workDaysCounter;

	}

}
