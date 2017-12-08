package pl.com.meridium.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import pl.com.meridium.entity.Messages;
import pl.com.meridium.entity.User;
import pl.com.meridium.entity.User2;
import pl.com.meridium.repository.DateRepository;
import pl.com.meridium.repository.GradeRepository;
import pl.com.meridium.repository.MessagesRepository;
import pl.com.meridium.repository.User2Repository;
import pl.com.meridium.repository.UserRepository;
import pl.com.meridium.validation.LoginUserGroup;



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
//		User userLogged=(User) ses.getAttribute("userLogged");
		
		
//		model.addAttribute("dates", getDates());
//		
		return "user_logged";
	}
	
	@ModelAttribute("dates")
//	private List<Date> getDates(){
	private List<Dates> getDates(){
//		List<Date> result = new ArrayList<>();
//		List<Dates> dates = dateRepository.findAll();
		// konwersja Dates na Date
//		for (Dates d: dates) {
//			result.add(d.getDate());
//		}
		List<Dates> result = dateRepository.findAll();
		for(Dates d: result) {
			System.out.println(d.getDate().getDay() + ", "+ d.getDate().getMonth() + ", "+ d.getDate().getYear());
		}
		return result;
//		return dateRepository.findAll();
	}
	
	@RequestMapping(value = "/user_logged", method = RequestMethod.POST)
	public String datePicker(Model model, HttpSession ses, @RequestParam String dates) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String[] splitedDates=dates.split(",");
		for (int i=0;i<splitedDates.length;i++) {
			try {
				Dates datesToBase=new Dates();
				Date date = formatter.parse(splitedDates[i]);
				datesToBase.setDate(date);
				datesToBase.setStatus(1);
				dateRepository.save(datesToBase);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/user_logged";
	}
	
	
	
	@RequestMapping("/user_logged/admin")
	public String adminLogged(Model model, HttpSession ses) {
		long id=1;
		User user=userRepository.findOne(id);
		model.addAttribute("userLogged", user);
		return "user_logged";
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)	
	public String userAdd(Model model, HttpSession ses) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "add_user";
		
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)	
		public String userValidate2(@Validated({Default.class})  User user, BindingResult result, Model model) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		if (result.hasErrors()) {
			return "add_user";
		}

		else {
						
			userRepository.save(user);
			
			
			return "redirect:/user_logged";
		}
		
	}
	
	@RequestMapping(value = "/user/add2", method = RequestMethod.GET)	
	public String userAdd2(Model model, HttpSession ses) {
		User userLogged=(User) ses.getAttribute("userLogged");
		long userId=userLogged.getId();
		model.addAttribute("userId", userId);
		User2 user2 = new User2();
		model.addAttribute("user2", user2);
		
		
		return "add_user2";
		
	}
	
	@RequestMapping(value = "/user/add2", method = RequestMethod.POST)	
		public String userValidate3(@ModelAttribute User2 user2, Model model, HttpSession ses) {
		
		
			User user=(User) ses.getAttribute("userLogged");
			
			long userId=user2.getId();
			
			
			userRepository.save(user);
			//user2Repository.save(user2);
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
	public String vacationa(Model model, HttpSession ses, @RequestParam String dates, @RequestParam String rodzaj) {
		User userLogged=(User) ses.getAttribute("userLogged");
		String [] vacationDates=dates.split(",");
		String firstDate=vacationDates[0];
		String secondDate=vacationDates[1];
		int send=1;
		model.addAttribute("firstDate", firstDate);
		model.addAttribute("secondDate", secondDate);
		model.addAttribute("send",send);
		model.addAttribute("rodzaj",rodzaj);
		Date d = new Date();
		model.addAttribute("now", d);
		
		
		return "urlop";
	}
	
	
	
	
	@RequestMapping("/menu")
	public String menu(Model model, HttpSession ses) {
		User userLogged=(User) ses.getAttribute("userLogged");
		User2 user2Logged=(User2) ses.getAttribute("user2Logged");
		
		return "menu";
	}
	
	@RequestMapping(value ="/message", method = RequestMethod.GET)
	public String message(Model model, HttpSession ses) {
		User userLogged=(User) ses.getAttribute("userLogged");
		User2 user2Logged=(User2) ses.getAttribute("user2Logged");
		Date d = new Date();
		model.addAttribute("now", d);
		ses.removeAttribute("messageF");
		
		return "message";
	}
	@RequestMapping(value ="/message", method = RequestMethod.POST)
	public String messageSend(Model model, HttpSession ses, @RequestParam String messageTitle, @RequestParam String message, @RequestParam String firstName, @RequestParam String status, @RequestParam String secondName)  {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		User userLogged=(User) ses.getAttribute("userLogged");
		User2 user2Logged=(User2) ses.getAttribute("user2Logged");
		String messageF="Wiadomość została wysłana";
		Date d = new Date();
		
		int statu=Integer.parseInt(status);
		
		Messages messages = new Messages();
		messages.setAdded(d);
		messages.setForWhen(d);
		messages.setMessage(message);
		messages.setStatus(statu);
		messages.setFirstName(firstName);
		messages.setSecondName(secondName);
		messages.setMessageTitle(messageTitle);
		messages.setAnswer(null);
		
		
		messagesRepository.save(messages);
		ses.setAttribute("messageF", messageF);
		return "message";
		
		
	}
	@RequestMapping("/messages")
	public String messages(Model model, HttpSession ses) {
		User userLogged=(User) ses.getAttribute("userLogged");
		List<Messages> result = messagesRepository.findAll();
		model.addAttribute("result", result);
		return "messages";
	}
	
	@RequestMapping("/message/{id}/")
	public String messageOne(@PathVariable int id, Model model, HttpSession ses) {
		User userLogged=(User) ses.getAttribute("userLogged");
		Messages message=messagesRepository.findOne((long) id);
		
		model.addAttribute("result", message);
		return "one_message";
	}
	

}
