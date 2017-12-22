package pl.com.meridium.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.com.meridium.entity.User;
import pl.com.meridium.entity.User2;
import pl.com.meridium.repository.UserRepository;

@Controller

public class AdminController {
	@Autowired
	private UserRepository userRepository;
	
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
	@RequestMapping("/users")
	public String adminUsers(Model model, HttpSession ses) {
		List<User> workers = userRepository.findAll();
		model.addAttribute("workers", workers);
		return "users";
	}
}
