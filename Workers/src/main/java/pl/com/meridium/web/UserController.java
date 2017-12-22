package pl.com.meridium.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	private MessagesRepository messagesRepository;
	
	@Autowired
	private DateRepository dateRepository;
	
	@Autowired
	private HolidaysRepository holidaysRepository;
	
	@ModelAttribute("grades")
	public List<Grade> getGrades(){
		return gradeRepository.findAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userLogin() {
		
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
		
		
		Date nowDate = new Date();
		Calendar c = Calendar.getInstance();
	    c.setTime(nowDate);
	    c.add(Calendar.MONTH, 1);
	    c.set(Calendar.DATE, c.getMaximum(Calendar.DATE));
	    Date nextDate = c.getTime();
	    int lastDay = nextDate.getDate();
	    int nextMonth = nextDate.getMonth();
	    String m;
	    String n;
	    switch (nextMonth) {
	    case 0:
	    	m="Styczeń";
	    	n="01";
	    	break;
	    case 1:
	    	m="Luty";
	    	n="02";
	    	break;
	    case 2:
	    	m="Marzec";
	    	n="03";
	    	break;
	    case 3:
	    	m="Kwiecień";
	    	n="04";
	    	break;
	    case 4:
	    	m="Maj";
	    	n="05";
	    	break;
	    case 5:
	    	m="Czerwiec";
	    	n="06";
	    	break;
	    case 6:
	    	m="Lipiec";
	    	n="07";
	    	break;
	    case 7:
	    	m="Sierpień";
	    	n="08";
	    	break;
	    case 8:
	    	m="Wrzesień";
	    	n="09";
	    	break;
	    case 9:
	    	m="Październik";
	    	n="10";
	    	break;
	    case 10:
	    	m="Listopad";
	    	n="11";
	    	break;
	    case 11:
	    	m="Grudzień";
	    	n="12";
	    	break;
	    	default:
	    		m="Miesiąc nie istnieje";
	    		n="00";
		    	break;	
	    	
	    }
	    int workDaysCounter=0;
	    for (int i=1;i<=lastDay;i++) {
	    	
	    	c.set(c.YEAR, c.MONTH, i);
	    	int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
	    	if (dayOfWeek<6) {
	    		workDaysCounter++;
	    	}
	    }

	    model.addAttribute("lastDay", lastDay);
	    model.addAttribute("m", m);
	    
	    String monthToFind="-";
	    monthToFind+=n;
	    monthToFind+="-";
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
		
		///////////////////////////////////////////////////////////////////////
		// user ranga=2
		////////////////////////////////////////////////////////////////////////
		List<User> workers = userRepository.findByranga(1);
		model.addAttribute("workers", workers);
		
		
		
		
		return "user_logged";
		
	}
	
	@ModelAttribute("dates")

	private List<Dates> getDates(){
//		List<Date> result = new ArrayList<>();
//		List<Dates> dates = dateRepository.findAll();
		// konwersja Dates na Date
//		for (Dates d: dates) {
//			result.add(d.getDate());
//		}
		 
		 
		Calendar calendar = Calendar.getInstance();         
		
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		Date thisMonthFirstDay = calendar.getTime();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date thistMonthLastDay = calendar.getTime();
		
		List<Dates> result = dateRepository.findByDateBetweenParam1AndParam2(thisMonthFirstDay, thistMonthLastDay);
		//List<Dates> result = dateRepository.findAll();

		
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
	
	@RequestMapping(value = "/wniosek_urlopowy", method = RequestMethod.GET)
	public String vacation(Model model, HttpSession ses) {
		User userLogged=(User) ses.getAttribute("userLogged");
		User2 user2Logged=(User2) ses.getAttribute("user2Logged");
		
		return "urlop";
	}
	
	@RequestMapping(value = "/wniosek_urlopowy", method = RequestMethod.POST)
	public String vacationa(Model model, HttpSession ses, @RequestParam String dates, @RequestParam String rodzaj) throws ParseException {
		User userLogged=(User) ses.getAttribute("userLogged");
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
		long liczbaDniUrlopu = differenceInMillis/liczbaMSwDobie;
		
		
		int send=1;
		model.addAttribute("firstDate1", firstDate1);
		model.addAttribute("secondDate1", secondDate1);
		model.addAttribute("send",send);
		model.addAttribute("rodzaj",rodzaj);
		model.addAttribute("liczbaDniUrlopu",liczbaDniUrlopu);
		Date d = new Date();
		model.addAttribute("now", d);
		
		
		return "urlop";
	}
	
	
	
	
	@RequestMapping("/menu")
	public String menu(Model model, HttpSession ses) {
		User userLogged=(User) ses.getAttribute("userLogged");
		
		
		return "menu";
	}
	
	
	@RequestMapping("/worker/{id}")
	public String worker(@PathVariable long id, Model model) {
		User toView = userRepository.findOne(id);
		model.addAttribute("toView", toView);
		
		
		return "worker";
	}
	
	
	
	

}
