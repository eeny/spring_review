package com.pluckit.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/Home.do")
	public String home(Model model) {
		return "home";
	}
	
	
	
}
