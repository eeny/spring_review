package com.member.app;

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

import com.member.app.bean.SpBoardDTO;
import com.member.app.bean.SpMemberDTO;
import com.member.app.dao.SpBoardDAO;
import com.member.app.dao.SpMemberDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	SpMemberDAO dao; // 자동 생성 끝! 사용준비 완료!
	
	@Autowired
	SpBoardDAO bdao;
	
	@RequestMapping(value = "/main.do")
	public String home(Model model) {
		return "main";
	}
	
	// ===== 회원가입 관련 메서드 =====
	
	@RequestMapping("/MemberRegist.do")
	public String regist() {
		return "regist";
	}
	
	@RequestMapping("/RegistProc.do")
	public String registProc(SpMemberDTO dto) {
		dao.memberInsert(dto);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/MemberList.do")
	public String listMember(Model model) {
//		List<SpMemberDTO> list = dao.memberGetAll();
//		model.addAttribute("list", list);
		
		model.addAttribute("list", dao.memberGetAll());
		model.addAttribute("total", dao.memberGetCount());
		
		return "list";
	}
	
	@RequestMapping("/MemberRead.do")
	public String read(int idx, Model model) {
		model.addAttribute("read", dao.memberGetOne(idx));
		
		return "update";
	}
	
	@RequestMapping("/MemberUpdateProc.do")
	public String updateProc(SpMemberDTO dto) {
		dao.memberUpdate(dto);
		
		return "redirect:/MemberList.do";
	}
	
	@RequestMapping("/MemberDelete.do")
	public String deleteMember(int idx) {
		dao.memberDelete(idx);
		
		return "redirect:/MemberList.do";
	}
	
	// ===== 게시판 관련 메서드 =====
	@RequestMapping("/BoardList.do")
	public String listBoard(Model model) {
		model.addAttribute("blist", bdao.getAllBoard());
		model.addAttribute("bcnt", bdao.getBoardCount());
		
		return "blist";
	}
	
	@RequestMapping("/BoardInsert.do")
	public String insert() {
		return "binsert";
	}
	
	@RequestMapping("/BoardInsertProc.do")
	public String insertBoard(SpBoardDTO dto) {
		bdao.insertBoard(dto);
		
		return "redirect:/BoardList.do";
	}
	
	@RequestMapping("/BoardRead.do")
	public String readBoard(int idx, Model model) {
		model.addAttribute("bread", bdao.getOneBoard(idx));
		
		return "bupdate";
	}
	
	@RequestMapping("/BoardUpdateProc.do")
	public String updateBoard(SpBoardDTO dto) {
		bdao.updateBoard(dto);
		
		return "redirect:/BoardList.do";
	}
	
	@RequestMapping("/BoardDelete.do")
	public String deleteBoard(int idx) {
		bdao.deleteBoard(idx);
		
		return "redirect:/BoardList.do";
	}
	
	
	
}// HomeController 클래스 끝
