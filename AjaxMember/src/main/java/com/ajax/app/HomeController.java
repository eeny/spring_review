package com.ajax.app;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ajax.app.bean.SpMemberDTO;
import com.ajax.app.dao.SpBoardDAO;
import com.ajax.app.dao.SpMemberDAO;

@Controller
public class HomeController {
	@Autowired
	private SpMemberDAO dao;
	
	@Autowired
	private SpBoardDAO bdao;
	
	@RequestMapping("/main.do")
	public String home(Model model) {		
		return "main";
	}
	
	@RequestMapping("/Regist.do")
	public String regist() {
		return "insert"; // 앞 뒤로 prefix / suffix 가 붙는다!
	}
	
	@RequestMapping("/InsertProc.do")
	public String insertMember(SpMemberDTO dto) {
		// 사용법 1) 기본적으로는 이렇게 사용하고~
		dao.insertMember(dto);
		
		// 사용법 2) 이런 식으로 처리할 수도 있다~
		/*
		int result = dao.insertMember(dto);
		if (result > 0) {
			return "redirect:/MemberList.do";
		} else {
			return "error"; // error.jsp
		}
		*/
		
		return "redirect:/MemberList.do";
	}
	
	@RequestMapping("/MemberList.do")
	public String listMember(@RequestParam(value = "pageNum", required = false, defaultValue = "1")String strNum, Model model) {
		// 매개변수 pageNum는 주소줄에서 가져올 현재 페이지값이다!
		int pageSize = 5; // 한 번에 보여줄 목록양
		int totalCount = dao.getAllMemberCount();
		int pageCount = totalCount/pageSize; // 더블이 아니기 때문에 12일경우 12/10 이면 1이 된다.
		    if(totalCount%pageSize > 0) { pageCount++; } // 따라서 나머지가 있을 경우 페이지수를 1 늘린다!
		int numTmp = (Integer.parseInt(strNum)-1)*pageSize;   
		
		model.addAttribute("list", dao.getAllMember());
		model.addAttribute("pageList", dao.getAllMemberPaging(numTmp, pageSize));
		model.addAttribute("cnt", dao.getAllMemberCount()); // 전체 개수는 별로 의미 없다.
		model.addAttribute("pageCount", pageCount); // 총 페이지수, jsp에서 for에 이용한다.
		
		return "list";
	} // 사실은 필요없다! 왜? 쿼리 결과가 search(검색)랑 같다!

	// =============== 검색 ===============
	
	// 방식 1) Model
	/*
	@RequestMapping("/SearchList.do")
	public String searchList(Model model, String name) {
		model.addAttribute("list", dao.getSearchList(name));
		model.addAttribute("cnt", dao.getSearchCount(name));	
		
		return "list";
	}
	*/
	
	// 방식 2) ModelAndView
	@RequestMapping("/SearchList.do")
	public ModelAndView searchList(ModelAndView mv, String name) {
		mv.setViewName("list");
		mv.addObject("list", dao.getSearchList(name));
		mv.addObject("cnt", dao.getSearchCount(name));

		return mv;
	}
	
	/*
		Model 방식 : 데이터만 저장한다.
		ModelAndView 방식 : 데이터와 이동하고자 하는 페이지를 같이 저장한다.
		차이점은 메서드를 제외하고 거의 없다!!!
	*/
	
	// =============== Ajax - 서블릿 방식 & 스프링 방식 ===============
	
	// Ajax 내용 자체를 여기에 뿌려버리고, 그것을 jsp에서 긁어가야 한다!
	// 이 방식은 서블릿에서 Ajax를 쓰던 방법을 응용한 방식이라서 스프링 방식이 아니다!!!
	/*
	@RequestMapping("/isExist.do") 
	public void idCheck(String id, HttpServletResponse response) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("res", dao.isExistId(id));
		
		response.getWriter().print(obj.toString());
	}
	*/
	
	@RequestMapping(value = "/isIdExist.do", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> ajaxCheck(@RequestBody SpMemberDTO dto) { 
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("res", dao.isExistId(dto.getId()));
		
		return map;
	}
	
	/*
		이전 페이지에서 값이 넘어온다. 그 name은 id이다.
		하지만 json데이터가 String으로 바뀌어서 넘어오게 되고, 
		우리는 그것을 받아서(받을 당시에는 java의 String 객체) json데이터로 바꿔야한다.
		이러한 작업을 하는 것이 @ResponseBody, @RequestBody 다!
		
		@RequestBody는 Http로 들어온 요청을 자바 객체에 매핑해준다.
		jsp페이지로 넘어온 값들을 java(SpMemberDTO)에 넣어준다.
		
		@ResponseBody는 자바 객체의 요청을 Http 요청으로 매핑해준다.
		실행된 결과를 jsp로 돌려준다.
	*/
	

	// ================= 게시판 만들기 (만들다 말았음) ===================
	@RequestMapping("/BoardList.do")
	public String listBoard() { // 게시글목록 불러오기
		// bdao.
		
		return "blist";
	}

	@RequestMapping("/BoardWrite.do")
	public String writeBoard() { // 게시글쓰기 페이지 이동
		return "binsert";
	}
	
	// =============== 로그인 / 로그아웃 ===============
	@RequestMapping("/Login.do")
	public String login(String id, String pw, HttpSession session) { // SpMemberDTO dto 이렇게 받아도 되고 각각 받아도 되고~
		/*
		SpMemberDTO dto = null;
		try {
			dto = dao.loginInfo(id, pw);
			
		} catch (EmptyResultDataAccessException e) {
			
		} finally {
			if (dto != null) { // id, pw가 맞는 경우
				session.setAttribute("userInfo", dto);
			}
			// id, pw가 틀리면 그냥 dto는 null이 된다!
		}
		*/
		SpMemberDTO dto = dao.loginInfo(id, pw);
		session.setAttribute("userInfo", dto);
		return "main";
	}
	
	@RequestMapping("/Logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "main";
	}
	
}// Controller 끝
