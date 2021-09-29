package com.pluckit.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pluckit.app.dao.MainDAO;
import com.pluckit.app.dto.EmployeeDTO;

@Controller
public class HomeController {
	@Autowired
	private MainDAO mdao;
	
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
		mdao.insertSignupData(dto);
		
		return "redirect:/Login.do";
	}
	
	// ===================== 로그인 & 회원가입 & 내정보 끝 =====================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// HomeController 끝
