package com.company.app;

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
	private CrudDao dao; // �������� ���ؼ� ����غ� ��!
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		List<CrudDto> list = dao.getAll();
		model.addAttribute("list", list); // request.setAttribute�� ������ ����!
		
		return "home";
	}
	
	@RequestMapping(value = "/Insert")
	public String insert() {
		return "insert";
	}

	@RequestMapping(value = "/InsertProc")
	public String insertProc(String name) {
		
		// ���� �̷��� �����ٷ� �����������~
//		CrudDto dto = new CrudDto();
//		dto.setName(name);
//		dao.insert(dto);
		
		// �� �ٷ� ���� �� �ִ�!
		dao.insert(new CrudDto(name));
		
		return "redirect:/"; // �̰� �ٽ�!!! ������ �����ִ� �ּҷ� ����! �ּ����� �ٲ�ٴ� ��!
	}
	
	@RequestMapping(value = "/Delete")
	public String delete(int idx) {
		dao.delete(idx);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/School", method = RequestMethod.GET)
	public String schoolaaa(Locale locale, Model model) {
		
		return "school";
	}
}
