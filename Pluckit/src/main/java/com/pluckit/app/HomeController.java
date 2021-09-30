package com.pluckit.app;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pluckit.app.dao.MainDAO;
import com.pluckit.app.dto.EmployeeDTO;
import com.pluckit.app.service.MainService;
import com.pluckit.app.service.OfficeService;

@Controller
public class HomeController {
	@Autowired
	private MainService msvc;
	
	@Autowired 
	private OfficeService osvc;
	
	// 웹사이트 최초 메인화면으로 이동
	@RequestMapping("/Home.do")
	public String home(Model model) {
		return "home";
	}
	
	// ===================== 로그인 & 회원가입 & 내정보 시작 =====================

	// 로그인 페이지로 이동
	@RequestMapping("/Login.do")
	public String login() {
		return "main/login";
	}
	
	// 회원가입 페이지로 이동
	@RequestMapping("/Signup.do")
	public String signup() {
		return "main/signup";
	}
	
	// 회원가입 처리
	@RequestMapping("/SignupProc.do")
	public String signupProc(EmployeeDTO dto) {
		msvc.insertSignupData(dto);
		
		return "redirect:/Login.do";
	}
	
	// ===================== 로그인 & 회원가입 & 내정보 끝 =====================
	
	// ===================== 오피스:직원목록 시작 =====================
	@RequestMapping("/Employee.do")
	public String emloyeeList(Model model) {
		model.addAttribute("employeeList", osvc.selectEmployeeListAll());
		return "office/office_employee";
	}
	
	@RequestMapping("/SerchProc.do")
	public String serchEmployeeList(Model model, String select, String search) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("select", select);
		map.put("search", search);
		model.addAttribute("employeeList", osvc.selectEmployeeListOne(map));
		
		return "office/office_employee";
	}
	
	// ===================== 오피스:직원목록 끝 =====================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// HomeController 끝
