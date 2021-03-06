package pl.com.meridium.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.com.meridium.entity.Dates;
import pl.com.meridium.entity.Grade;
import pl.com.meridium.entity.Holidays;
import pl.com.meridium.entity.Messages;
import pl.com.meridium.entity.User;
import pl.com.meridium.entity.User2;
import pl.com.meridium.other.Callendar;
import pl.com.meridium.other.MonthsInDigits;
import pl.com.meridium.other.MonthsInPolish;
import pl.com.meridium.other.WorkdaysCounter;
import pl.com.meridium.other.WorkersList;
import pl.com.meridium.repository.DateRepository;
import pl.com.meridium.repository.GradeRepository;
import pl.com.meridium.repository.HolidaysRepository;
import pl.com.meridium.repository.MessagesRepository;
import pl.com.meridium.repository.User2Repository;
import pl.com.meridium.repository.UserRepository;



@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private User2Repository user2Repository;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	private DateRepository dateRepository;
	
	@Autowired
	private HolidaysRepository holidaysRepository;
	
	@ModelAttribute("grades")
	public List<Grade> getGrades(){
		return gradeRepository.findAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userLogin(HttpSession ses) {
		ses.invalidate();
		return "index";
	}

	@Autowired
	Validator validator;

	

	@RequestMapping(value = "/", method = RequestMethod.POST)
	//public String userValidate(@Validated({LoginUserGroup.class}) User user, BindingResult result, Model model, HttpSession ses) {
		public String userValidate(@RequestParam String user, @RequestParam String password, Model model, HttpSession ses) {
		
		User userLogged=userRepository.findByUser(user);
		long id=userLogged.getId();
		User2 user2Logged=user2Repository.findOne(id);
		
		if (userLogged.getPassword().equals(password)) {
			
			ses.setAttribute("userLogged", userLogged);
			ses.setAttribute("user2Logged", user2Logged);
			model.addAttribute("userLogged", userLogged);
			model.addAttribute("user2Logged", user2Logged);
			
			return "redirect:/user_logged";
		}

		else {

			return "index";
			
		}
	}
	@RequestMapping(value = "/user_logged", method = RequestMethod.GET)
	public String calendar(Model model, HttpSession ses) {
		
		
		SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy, MM, dd");
		Calendar c = Calendar.getInstance();
	    //c.set(2017, 11, 30);
	    c.add(Calendar.MONTH, 2);
	    c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
	    c.add(Calendar.DAY_OF_MONTH, -1);
	   
	    String timeString = dateFormat.format(c.getTime());
	    
	    
	    int nextMonth = c.get(Calendar.MONTH);
	    int lastDay = c.get(Calendar.DATE);
	    System.out.println("miesiąc: "+nextMonth+" lastday: "+lastDay);
	   
	    String m=MonthsInPolish.MonthInPolish(nextMonth);
	    String n=MonthsInDigits.MonthInDigits(nextMonth);
	
	    int workDaysCounter=WorkdaysCounter.WorkDayCounter(1);
	    

	    model.addAttribute("lastDay", lastDay);
	    model.addAttribute("m", m);
	    

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
		User userLogged=(User) ses.getAttribute("userLogged");
		int hours = userLogged.getDefaultHour();
		int hoursFromLastMonth = userLogged.getHoursFromLastMonth();
		int monthHours=workDaysCounter*4;
		int finalHours = monthHours-hoursFromLastMonth;
		int hoursForNextMonth = finalHours%hours;
		int days = finalHours/hours;
		model.addAttribute("workDaysCounter", workDaysCounter);
		model.addAttribute("monthHours", monthHours);
		model.addAttribute("finalHours", finalHours);
		model.addAttribute("hoursForNextMonth", hoursForNextMonth);
		model.addAttribute("hoursFromLastMonth", hoursFromLastMonth);
		model.addAttribute("days", days);
		
		/////////////////////////////////////////////////////
		//sprawdzam czy są daty ze statudem 1 w przyszłym miesiącu
		// jeżeli tak to znaczy, że plan na następny m-c jest wysłany i trzeba wyświetlić odpowiednią informację
		//////////////////////////////////////////////////////////////
		Date date1=Callendar.firstDayInNextMonth();
		Date date2=Callendar.lastDayInNextMonth();
		List<Dates> datesWithStatus1 = dateRepository.findByDateBetweenAndStatus1(date1, date2, 1);
		boolean isPlanSended=true;
		if (datesWithStatus1.isEmpty())	{
			isPlanSended=false;
		}
		model.addAttribute("isPlanSended", isPlanSended);
		///////////////////////////////////////////////////////////////////////
		// user ranga=2
		////////////////////////////////////////////////////////////////////////
		List<User> workers = userRepository.findByranga(1);
		model.addAttribute("workers", workers);
		
		
		
		
		return "user_logged";
		
	}
	
	@ModelAttribute("dates")

	private List<Dates> getDates(){

		 
		 
		Calendar calendar = Calendar.getInstance();         
		
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		Date thisMonthFirstDay = calendar.getTime();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date thistMonthLastDay = calendar.getTime();
		
		List<Dates> result = dateRepository.findByDateBetweenParam1AndParam2(thisMonthFirstDay, thistMonthLastDay);
		
		return result;

	}
	
	@RequestMapping(value = "/user_logged", method = RequestMethod.POST)
	public String datePicker(Model model, HttpSession ses, @RequestParam String dates) {
		
		
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
	    //c.add(Calendar.MONTH, 1);
	    c.set(Calendar.DATE, c.getMaximum(Calendar.DATE));
	    Date expiryDate = c.getTime();
		dateRepository.deleteBydateAfter(expiryDate);
		
		String[] splitedDates=dates.split(",");
		Calendar calendar = Calendar.getInstance();         
		
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date thisMonthLastDay = calendar.getTime();
		calendar.add(Calendar.MONTH, 2);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		Date firstDayForTwoMonths = calendar.getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (int i=0;i<splitedDates.length;i++) {
			try {
				Dates datesToBase=new Dates();
				Date date = formatter.parse(splitedDates[i]);
		
				if (date.after(thisMonthLastDay)&&date.before(firstDayForTwoMonths))	{
					
					datesToBase.setDate(date);
					datesToBase.setStatus(1);
					dateRepository.save(datesToBase);
					
					
				}
				User userLogged= (User) ses.getAttribute("userLogged");
				
				long id=userLogged.getId();
				dateRepository.changeUserId(thisMonthLastDay, firstDayForTwoMonths, 1, id);		
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		
		}
		
		return "redirect:/user_logged";
	}
	
	
	
	
	
	@RequestMapping("/calendar")

	public String calendar() {
		return "calendar";
	}
	
	
	
	
	
	
	@RequestMapping("/menu")
	public String menu(Model model, HttpSession ses) {
	
		return "menu";
	}
	
	
	@RequestMapping("/worker/{id}")
	public String worker(@PathVariable long id, Model model) {
		User toView = userRepository.findOne(id);
		model.addAttribute("toView", toView);
		
		
		return "worker";
	}
	
	
	
	

}
