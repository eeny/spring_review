package com.hs.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	HiberDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value={"/","selectAll"})
	public String main(Model model) {		
		List<HiberDTO> list=dao.selectData();
		model.addAttribute("data", list);		
		return "home";
	}
	
	@RequestMapping("/selectOne") 
	public String selectOne(Model model, HiberDTO dto) {
		model.addAttribute("data", dao.selectData(dto));
		return "one"; 
	}
	
	@RequestMapping("/insertData") 
	public String insertData(HiberDTO dto) {
		
		dao.insertData(dto);
		return "redirect:/"; 
	}
	
	@RequestMapping("/deleteData") 
	public String deleteData(HiberDTO dto) {
		
		dao.deleteData(dto);
		return "redirect:/"; 
	}
	
	@RequestMapping("/updateData") 
	public String updateData(HiberDTO dto) {
		
		dao.updateData(dto);
		return "redirect:/"; 
	}
	
	
	
	
}










