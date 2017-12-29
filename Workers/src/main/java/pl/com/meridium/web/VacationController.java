package pl.com.meridium.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.com.meridium.entity.Holidays;
import pl.com.meridium.repository.HolidaysRepository;


@Controller
public class VacationController {
	
	@Autowired
	private HolidaysRepository holidaysRepository;
	
	@RequestMapping(value = "/wniosek_urlopowy", method = RequestMethod.GET)
	public String vacation(Model model, HttpSession ses) {
		
		
		return "urlop";
	}
	
	@RequestMapping(value = "/wniosek_urlopowy", method = RequestMethod.POST)
	public String vacationa(Model model, HttpSession ses, @RequestParam String dates, @RequestParam String rodzaj) throws ParseException {
	
		String [] vacationDates=dates.split(",");
		String firstDate1=vacationDates[0];
		String secondDate1=vacationDates[1];
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = formatter.parse(vacationDates[0]);
		Date secondDate = formatter.parse(vacationDates[1]);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(firstDate);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(secondDate);
		
		
		long differenceInMillis = c2.getTimeInMillis() - c1.getTimeInMillis();
		long liczbaMSwDobie = 1000 * 60 * 60 * 24;
		long liczbaDniUrlopu = differenceInMillis/liczbaMSwDobie+1;
		
		
		int send=1;
		model.addAttribute("firstDate1", firstDate1);
		model.addAttribute("secondDate1", secondDate1);
		model.addAttribute("send",send);
		model.addAttribute("rodzaj",rodzaj);
		model.addAttribute("liczbaDniUrlopu",liczbaDniUrlopu);
		Date d = new Date();
		model.addAttribute("now", d);
		
		//adding dates from form to list. It will need to calculate count of working days
		int workDaysCounter=0;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		while (calendar.getTime().before(secondDate))	{
			
			Date result = calendar.getTime();
			//c.set(c.YEAR, c.MONTH, i);
	    	int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	    	if (dayOfWeek<6) {
	    		workDaysCounter++;
	    	}
	    	List<Holidays>	holidayDates = holidaysRepository.findAll();
	    	for	(Holidays holidays : holidayDates)	{
	    		Date holidayDay = holidays.getDate();
	    		Calendar e = Calendar.getInstance();
			    e.setTime(holidayDay);
			    if (e.equals(calendar))	{
			    	int dayWeek =e.get(Calendar.DAY_OF_WEEK);
			    	if (dayWeek<6)	{
			    		workDaysCounter--;
				    }
			    }
	    	}
	    	
			calendar.add(Calendar.DATE, 1);
			
		}
		workDaysCounter++;
		model.addAttribute("workDaysCounter",workDaysCounter);
		   
		//end of adding dates from form to list. It will need to calculate count of working days
		return "urlop";
	}
}
