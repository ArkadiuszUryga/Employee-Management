package pl.com.meridium.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.com.meridium.entity.Dates;
import pl.com.meridium.entity.Messages;
import pl.com.meridium.entity.User;
import pl.com.meridium.entity.User2;
import pl.com.meridium.repository.DateRepository;
import pl.com.meridium.repository.MessagesRepository;
import pl.com.meridium.repository.UserRepository;

@Controller
public class MessageController {
	
	@Autowired
	private MessagesRepository messagesRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DateRepository dateRepository;
	
	@RequestMapping(value ="/message", method = RequestMethod.GET)
	public String message(Model model, HttpSession ses) {
		User userLogged=(User) ses.getAttribute("userLogged");
		List<User> workersForMessage = userRepository.findByranga(1);
		Date d = new Date();
		model.addAttribute("now", d);
		model.addAttribute("workersForMessage", workersForMessage);
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
		//messages.setFirstName(firstName);
		//messages.setSecondName(secondName);
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
		Date nowDate = new Date();
		Calendar c = Calendar.getInstance();
	    c.setTime(nowDate);
	    c.set(Calendar.DATE, c.getMinimum(Calendar.DATE));
	    Date firstDayThisMonth = c.getTime();
	    c.add(Calendar.MONTH, 1);
	    c.set(Calendar.DATE, c.getMinimum(Calendar.DATE));
	    c.add(Calendar.DATE, -1);
	    Date lastDayThisMonth = c.getTime();
	    List<Dates> thisMonth = dateRepository.findByDateBetweenAndStatus1(firstDayThisMonth, lastDayThisMonth, 1);
	    
	    c.add(Calendar.MONTH, 1);
	    c.set(Calendar.DATE, c.getMinimum(Calendar.DATE));
	    Date firstDayNextMonth = c.getTime();
	    c.set(Calendar.DATE, c.getMaximum(Calendar.DATE));
	    Date lastDayNextMonth = c.getTime();
	    List<Dates> nextMonth = dateRepository.findByDateBetweenAndStatus1(firstDayNextMonth, lastDayNextMonth, 1);
	    
	    model.addAttribute("thisMonth", thisMonth);
	    model.addAttribute("nextMonth", nextMonth);
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
