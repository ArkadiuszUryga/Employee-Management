package pl.com.meridium.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.com.meridium.entity.Dates;
import pl.com.meridium.entity.Holidays;
import pl.com.meridium.entity.User;
import pl.com.meridium.other.MonthsInDigits;
import pl.com.meridium.other.MonthsInPolish;
import pl.com.meridium.other.WorkdaysCounter;
import pl.com.meridium.repository.DateRepository;
import pl.com.meridium.repository.HolidaysRepository;
import pl.com.meridium.repository.UserRepository;

@Controller
public class PlanController {
	
	@Autowired
	private DateRepository dateRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HolidaysRepository holidaysRepository;
	
	@RequestMapping(value = "/plan1", method = RequestMethod.GET)
	public String calendar(Model model, HttpSession ses) {
		
		
		return "plan1";
		
	}
	@RequestMapping(value = "/plan1", method = RequestMethod.POST)
	public String datePicker(Model model, HttpSession ses, @RequestParam String dates) {
Calendar calendar = Calendar.getInstance();         
		
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		calendar.add(Calendar.DATE, -1);
		Date thisMonthFirstD = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		calendar.add(Calendar.DATE, 1);
		Date thistMonthLastD = calendar.getTime();
		dateRepository.changeStatusToTwo(thisMonthFirstD, thistMonthLastD, 1, 2);

		return "redirect:/plan/wydruk/";
		
	}
	@ModelAttribute("dates")

	private List<Dates> getDates(){

		 
		 
		Calendar calendar = Calendar.getInstance();         
		
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		Date thisMonthFirstDay = calendar.getTime();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date thistMonthLastDay = calendar.getTime();
		
		List<Dates> result = dateRepository.findByDateBetweenAndStatus1(thisMonthFirstDay, thistMonthLastDay, 1);
		
		return result;

	}
	@RequestMapping("/plan/wydruk/")

	public String plan_wydruk(Model model) {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		String m=MonthsInPolish.MonthInPolish(month);
		String n=MonthsInDigits.MonthInDigits(month);
		int workDaysCounter=WorkdaysCounter.WorkDayCounter(0);
		
		List<Holidays>	holidayDates = holidaysRepository.findAll();
	    int holidayDayCounter=0;
		for	(Holidays holidays : holidayDates)	{
						Date holidayDay = holidays.getDate();
						System.out.println(holidayDay);
						Calendar d = Calendar.getInstance();
					    d.setTime(holidayDay);
					    int cm=d.get(Calendar.MONTH);
					    cm++;
					    int cn = Integer.parseInt(n);
					    System.out.println(cn);
					    System.out.println(cm);
					    if (cm==cn)	{
					    int dayWeek =d.get(Calendar.DAY_OF_WEEK);
					    System.out.println(dayWeek);
					    if (dayWeek<6)	{
					    	holidayDayCounter++;
					    }
					    }
						
		}
		workDaysCounter-=holidayDayCounter;
		int monthHours=workDaysCounter*4;
		long userToShowId=2;
		User userToShow = userRepository.findOne(userToShowId);
		model.addAttribute("m", m);
		model.addAttribute("year", year);
		model.addAttribute("userToShow", userToShow);
		model.addAttribute("workDaysCounter", workDaysCounter);
		model.addAttribute("monthHours", monthHours);
		
		List <Date> monthDays = new ArrayList<Date>();
		c.set(Calendar.DATE, c.getMaximum(Calendar.DATE));
		int lastDay = c.get(Calendar.DATE);
			for (int i=1;i<=lastDay;i++) {
	    	
	    	c.set(c.YEAR, c.MONTH, i);
	    	Date currentDay = c.getTime();
	    	monthDays.add(currentDay);
	    	
	    }
			model.addAttribute("monthDays", monthDays);
		return "plan_wydruk";
	}

}
