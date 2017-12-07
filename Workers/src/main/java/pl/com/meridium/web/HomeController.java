package pl.com.meridium.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping({"/homepage", "/index" })

	public String body() {
		return "index";
	}
	
}
