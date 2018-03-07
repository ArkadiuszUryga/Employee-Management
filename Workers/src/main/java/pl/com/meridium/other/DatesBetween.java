package pl.com.meridium.other;
/*
 * ustawia dwie daty graniczne na potrzeby zmian w mysql komendą between
 * oznacza, to, że jeżeli uruchomimy skrypt w lutym, to datami granicznymi będą: 31 stycznia i 1 marca
 */
import java.util.Calendar;
import java.util.Date;

public class DatesBetween {
	public static Date thisMonthFirstD() {
	Calendar calendar = Calendar.getInstance();         
	
	calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
	calendar.add(Calendar.DATE, -1);
	return calendar.getTime();
//	calendar.add(Calendar.DATE, 1);
//	calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
//	calendar.add(Calendar.DATE, 1);
//	Date thistMonthLastD = calendar.getTime();
//	calendar.add(Calendar.DATE, 1);
//	int nameOfMonth=calendar.get(Calendar.MONTH);
//	
//	String title="Plan na miesiąc "+nameOfMonth;
}
	public static Date thistMonthLastD() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	public static int digitalNameOfMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH);
	}
}
