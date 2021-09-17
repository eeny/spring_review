package com.batis.app;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.batis.app.dao.BatisBoardDAO;
import com.batis.app.dao.BatisMemberDAO;
import com.batis.app.dto.BatisBoardDTO;
import com.batis.app.dto.BatisMemberDTO;

@Controller
public class HomeController {
	@Autowired
	private BatisMemberDAO mdao;
	
	@Autowired
	private BatisBoardDAO bdao;
	
	@RequestMapping("/main.do")
	public String home(@RequestParam(value = "pageNum", required = false, defaultValue = "1")String strNum, Model model) { // 메인 페이지 이동
		int pageSize = 5;
		int totalCount = bdao.getAllBoardCount();
		int pageCount = totalCount / pageSize;
		if(totalCount%pageSize > 0) { pageCount++; }
		int numTmp = (Integer.parseInt(strNum) - 1) * pageSize;
		
		//model.addAttribute("blist", bdao.getAllBoard()); // 게시판 목록 가져오기
		model.addAttribute("bPageList", bdao.getBoardPage(numTmp, pageSize)); // 페이징 적용된 게시판 목록
		model.addAttribute("pagecount", pageCount); // 총 페이지 수
		model.addAttribute("totalCount", totalCount); // 총 게시글 수

		return "main";
	}
	
	@RequestMapping("/Login.do")
	public String login(BatisMemberDTO dto, HttpSession session) { // 로그인 처리
		List<BatisMemberDTO> list = mdao.login(dto);
		if (list.size() < 1) { // 로그인이 안된 경우
			list = null;
		} else {
			session.setAttribute("userInfo", list.get(0));
		}
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/Logout.do")
	public String logout(HttpSession session) { // 로그아웃 처리
		session.invalidate();
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/Regist.do")
	public String regist(BatisMemberDTO dto) { // 회원가입 페이지 이동
		
		return "regist";
	}
	
	@RequestMapping("/MemberInsert.do")
	public String InsertMember(BatisMemberDTO dto) { // 회원가입 처리
		mdao.insertMember(dto);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping(value = "/isIdExist.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> ChekId(@RequestBody BatisMemberDTO dto) { // 아이디 중복체크
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", mdao.checkId(dto.getId()));
		
		return map;
	}
	
	@RequestMapping("/AllMembers.do")
	public String getAllMember(Model model) {
		model.addAttribute("mlist", mdao.getAllMember());
		
		return "members";
	}
	
	@RequestMapping("/WriteBoard.do")
	public String writeBoard() { // 글쓰기 페이지 이동
		return "newBoard";
	}
	
	@RequestMapping("/BoardInsert.do")
	public String insertBoard(BatisBoardDTO dto) { // 게시판 입력 처리
		bdao.insertBoard(dto);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/ReadBoard.do")
	public String readBoard(BatisBoardDTO dto, Model model) { // 게시판 내용 읽어오기
		model.addAttribute("bdata", bdao.getBoardData(dto));
		
		return "board";
	}
	
	@RequestMapping("/ModifyBoard.do")
	public String modifyBoard(BatisBoardDTO dto, Model model) { // 게시글 수정 페이지 이동
		model.addAttribute("bdata", bdao.getBoardData(dto));
		
		return "updateBoard";
	}
	
	@RequestMapping("/UpdateBoard.do")
	public String updateBoard(BatisBoardDTO dto) { // 게시판 글 수정
		bdao.updateBoard(dto);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/DeleteBoard.do")
	public String deleteBoard(BatisBoardDTO dto) { // 게시판 글 삭제
		bdao.deleteBoard(dto);
		
		return "redirect:/main.do";
	}
	
	
}// HomeController 끝
