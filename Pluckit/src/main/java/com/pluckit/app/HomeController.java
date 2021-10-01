package com.pluckit.app;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pluckit.app.dao.MainDAO;
import com.pluckit.app.dto.DepartmentDTO;
import com.pluckit.app.dto.EmployeeDTO;
import com.pluckit.app.dto.RankDTO;
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
	
	// 로그인 처리
	@RequestMapping("/LoginProc.do")
	public String loginProc(Model model, HttpSession session, EmployeeDTO dto) {
		EmployeeDTO empInfo = msvc.selectLoginData(dto);
		
		String msg;
		// 로그인 실패
		if (empInfo == null) { 
			msg = "<i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;사원 정보가 올바르지 않습니다.";
			model.addAttribute("msg", msg);
			return "main/login";
			
		// 로그인 성공
		} else {
			int auth = empInfo.getEmp_auth();
			// 권한 확인
			if (auth == 0) {
				msg = "<i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;접근 권한이 없습니다. 관리자에게 문의하세요";
				model.addAttribute("msg", msg);
				return "main/login";
			} 
			
			session.setAttribute("empInfo", empInfo);
			return "main/main";
		}		
	}
	
	// 로그아웃 처리
	@RequestMapping("/LogoutProc.do")
	public String logoutProc(HttpSession session) {
		session.invalidate();
		
		return "redirect:/Login.do";
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
	
	// 직원목록 리스트 출력
	@RequestMapping("/Employee.do")
	public String emloyeeList(Model model) { 
		model.addAttribute("employeeList", osvc.selectEmployeeListAll());
		return "office/office_employee";
	}

	// 직원목록 검색 처리
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
