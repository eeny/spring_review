package com.pluckit.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pluckit.app.dao.MainDAO;
import com.pluckit.app.dto.BoardDTO;
import com.pluckit.app.dto.DepartmentDTO;
import com.pluckit.app.dto.EmployeeDTO;
import com.pluckit.app.dto.RankDTO;
import com.pluckit.app.service.AdminService;
import com.pluckit.app.service.MainService;
import com.pluckit.app.service.OfficeService;

@Controller
public class HomeController {
	@Autowired
	private MainService msvc;
	
	@Autowired 
	private OfficeService osvc;
	
	@Autowired
	private AdminService adsvc;
	
	// 웹사이트 최초 메인화면으로 이동
	@RequestMapping("/Home.do")
	public String home(Model model) {
		return "home";
	}
	
	// ===================== 로그인 & 회원가입 시작 =====================

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
			return "home";
		}		
	}
	
	// 그룹웨어 메인 페이지로 이동
	@RequestMapping("/GroupWareMain.do")
	public String groupWareMain() {
		return "main/main";
	}
	
	// 로그아웃 처리
	@RequestMapping("/LogoutProc.do")
	public String logoutProc(HttpSession session) {
		session.invalidate();
		
		return "redirect:/Home.do";
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
	
	// ===================== 로그인 & 회원가입 끝 =====================
	
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
		
		// 검색시 부서코드와 직급코드는 따로 구분해서 검색어 변경
		if (select.equals("dept_id")) {
			switch (search) {
				case "인사": search = "100"; break;
				case "영업": search = "200"; break;
				case "마케팅": search = "300"; break;
				case "총무회계": search = "400"; break;
				case "기술지원": search = "500"; break;
				case "전략기획": search = "600"; break;
				default: search = "";	break;
			}
		}
		if (select.equals("rank_id")) {
			switch (search) {
				case "사원": search = "1"; break;
				case "대리": search = "2"; break;
				case "과장": search = "3"; break;
				case "차장": search = "4"; break;
				case "부장": search = "5"; break;
				case "이사": search = "6"; break;
				case "전무": search = "7"; break;
				case "사장": search = "8"; break;
				default: search = "";	break;
			}
		}
		
		map.put("select", select);
		map.put("search", search);
		model.addAttribute("employeeList", osvc.selectEmployeeListOne(map));
		
		return "office/office_employee";
	}
	
	// ===================== 오피스:직원목록 끝 =====================
	
	// ===================== 관리자메뉴:게시판 관리 시작 =====================
	
	// 관리자메뉴 페이지 이동
	@RequestMapping("/Admin.do")
	public String admin(Model model) {
		List<BoardDTO> boardList = adsvc.getAllBoardList();
		model.addAttribute("boardList", boardList);
		
		return "admin/admin_mngboard";
	}
	
	// 게시판 등록 처리
	@RequestMapping("/MakeBoard.do")
	public String makeBoard(BoardDTO dto) {
		adsvc.makeBoard(dto);
		adsvc.createMainTable(dto.getB_id());
		adsvc.createReplyTable(dto.getB_id());
		
		return "redirect:/Admin.do";
	}
	
	// 수정할 게시판 정보 가져오기
	@RequestMapping("/GetBoardInfo.do")
	public String getBoardInfo(String b_id) {
		
		return "";
	}
	
	// 게시판 수정 처리
	@RequestMapping("/ModifyBoardProc.do")
	public String modifyBoard(String b_id) {
		
		return "";
	}
	
	// 게시판 삭제 처리
	@RequestMapping("/DeleteBoardProc.do")
	public String deleteBoard(String b_id) {
		
		return "";
	}
	
	// ===================== 관리자메뉴:게시판 관리 끝 =====================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// HomeController 끝
