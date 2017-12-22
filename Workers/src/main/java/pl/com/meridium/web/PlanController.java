package pl.com.meridium.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlanController {
	
	@RequestMapping(value = "/plan1", method = RequestMethod.GET)
	public String calendar(Model model, HttpSession ses) {
		
		
		return "plan1";
		
	}
	@RequestMapping(value = "/plan1", method = RequestMethod.POST)
	public String datePicker(Model model, HttpSession ses, @RequestParam String dates) {
		return "redirect:/plan1";
		
	}

}
