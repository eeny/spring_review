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
	public String home(@RequestParam(value = "pageNum", required = false, defaultValue = "1")String strNum, Model model) { // ���� ������ �̵�
		int pageSize = 5;
		int totalCount = bdao.getAllBoardCount();
		int pageCount = totalCount / pageSize;
		if(totalCount%pageSize > 0) { pageCount++; }
		int numTmp = (Integer.parseInt(strNum) - 1) * pageSize;
		
		//model.addAttribute("blist", bdao.getAllBoard()); // �Խ��� ��� ��������
		model.addAttribute("bPageList", bdao.getBoardPage(numTmp, pageSize)); // ����¡ ����� �Խ��� ���
		model.addAttribute("pagecount", pageCount); // �� ������ ��
		model.addAttribute("totalCount", totalCount); // �� �Խñ� ��

		return "main";
	}
	
	@RequestMapping("/Login.do")
	public String login(BatisMemberDTO dto, HttpSession session) { // �α��� ó��
		List<BatisMemberDTO> list = mdao.login(dto);
		if (list.size() < 1) { // �α����� �ȵ� ���
			list = null;
		} else {
			session.setAttribute("userInfo", list.get(0));
		}
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/Logout.do")
	public String logout(HttpSession session) { // �α׾ƿ� ó��
		session.invalidate();
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/Regist.do")
	public String regist(BatisMemberDTO dto) { // ȸ������ ������ �̵�
		
		return "regist";
	}
	
	@RequestMapping("/MemberInsert.do")
	public String InsertMember(BatisMemberDTO dto) { // ȸ������ ó��
		mdao.insertMember(dto);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping(value = "/isIdExist.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> ChekId(@RequestBody BatisMemberDTO dto) { // ���̵� �ߺ�üũ
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
	public String writeBoard() { // �۾��� ������ �̵�
		return "newBoard";
	}
	
	@RequestMapping("/BoardInsert.do")
	public String insertBoard(BatisBoardDTO dto) { // �Խ��� �Է� ó��
		bdao.insertBoard(dto);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/ReadBoard.do")
	public String readBoard(BatisBoardDTO dto, Model model) { // �Խ��� ���� �о����
		model.addAttribute("bdata", bdao.getBoardData(dto));
		
		return "board";
	}
	
	@RequestMapping("/ModifyBoard.do")
	public String modifyBoard(BatisBoardDTO dto, Model model) { // �Խñ� ���� ������ �̵�
		model.addAttribute("bdata", bdao.getBoardData(dto));
		
		return "updateBoard";
	}
	
	@RequestMapping("/UpdateBoard.do")
	public String updateBoard(BatisBoardDTO dto) { // �Խ��� �� ����
		bdao.updateBoard(dto);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/DeleteBoard.do")
	public String deleteBoard(BatisBoardDTO dto) { // �Խ��� �� ����
		bdao.deleteBoard(dto);
		
		return "redirect:/main.do";
	}
	
	
}// HomeController ��
