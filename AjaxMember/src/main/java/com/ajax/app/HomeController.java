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
		return "insert"; // �� �ڷ� prefix / suffix �� �ٴ´�!
	}
	
	@RequestMapping("/InsertProc.do")
	public String insertMember(SpMemberDTO dto) {
		// ���� 1) �⺻�����δ� �̷��� ����ϰ�~
		dao.insertMember(dto);
		
		// ���� 2) �̷� ������ ó���� ���� �ִ�~
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
		// �Ű����� pageNum�� �ּ��ٿ��� ������ ���� ���������̴�!
		int pageSize = 5; // �� ���� ������ ��Ͼ�
		int totalCount = dao.getAllMemberCount();
		int pageCount = totalCount/pageSize; // ������ �ƴϱ� ������ 12�ϰ�� 12/10 �̸� 1�� �ȴ�.
		    if(totalCount%pageSize > 0) { pageCount++; } // ���� �������� ���� ��� ���������� 1 �ø���!
		int numTmp = (Integer.parseInt(strNum)-1)*pageSize;   
		
		model.addAttribute("list", dao.getAllMember());
		model.addAttribute("pageList", dao.getAllMemberPaging(numTmp, pageSize));
		model.addAttribute("cnt", dao.getAllMemberCount()); // ��ü ������ ���� �ǹ� ����.
		model.addAttribute("pageCount", pageCount); // �� ��������, jsp���� for�� �̿��Ѵ�.
		
		return "list";
	} // ����� �ʿ����! ��? ���� ����� search(�˻�)�� ����!

	// =============== �˻� ===============
	
	// ��� 1) Model
	/*
	@RequestMapping("/SearchList.do")
	public String searchList(Model model, String name) {
		model.addAttribute("list", dao.getSearchList(name));
		model.addAttribute("cnt", dao.getSearchCount(name));	
		
		return "list";
	}
	*/
	
	// ��� 2) ModelAndView
	@RequestMapping("/SearchList.do")
	public ModelAndView searchList(ModelAndView mv, String name) {
		mv.setViewName("list");
		mv.addObject("list", dao.getSearchList(name));
		mv.addObject("cnt", dao.getSearchCount(name));

		return mv;
	}
	
	/*
		Model ��� : �����͸� �����Ѵ�.
		ModelAndView ��� : �����Ϳ� �̵��ϰ��� �ϴ� �������� ���� �����Ѵ�.
		�������� �޼��带 �����ϰ� ���� ����!!!
	*/
	
	// =============== Ajax - ���� ��� & ������ ��� ===============
	
	// Ajax ���� ��ü�� ���⿡ �ѷ�������, �װ��� jsp���� �ܾ�� �Ѵ�!
	// �� ����� �������� Ajax�� ���� ����� ������ ����̶� ������ ����� �ƴϴ�!!!
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
		���� ���������� ���� �Ѿ�´�. �� name�� id�̴�.
		������ json�����Ͱ� String���� �ٲ� �Ѿ���� �ǰ�, 
		�츮�� �װ��� �޾Ƽ�(���� ��ÿ��� java�� String ��ü) json�����ͷ� �ٲ���Ѵ�.
		�̷��� �۾��� �ϴ� ���� @ResponseBody, @RequestBody ��!
		
		@RequestBody�� Http�� ���� ��û�� �ڹ� ��ü�� �������ش�.
		jsp�������� �Ѿ�� ������ java(SpMemberDTO)�� �־��ش�.
		
		@ResponseBody�� �ڹ� ��ü�� ��û�� Http ��û���� �������ش�.
		����� ����� jsp�� �����ش�.
	*/
	

	// ================= �Խ��� ����� (����� ������) ===================
	@RequestMapping("/BoardList.do")
	public String listBoard() { // �Խñ۸�� �ҷ�����
		// bdao.
		
		return "blist";
	}

	@RequestMapping("/BoardWrite.do")
	public String writeBoard() { // �Խñ۾��� ������ �̵�
		return "binsert";
	}
	
	// =============== �α��� / �α׾ƿ� ===============
	@RequestMapping("/Login.do")
	public String login(String id, String pw, HttpSession session) { // SpMemberDTO dto �̷��� �޾Ƶ� �ǰ� ���� �޾Ƶ� �ǰ�~
		/*
		SpMemberDTO dto = null;
		try {
			dto = dao.loginInfo(id, pw);
			
		} catch (EmptyResultDataAccessException e) {
			
		} finally {
			if (dto != null) { // id, pw�� �´� ���
				session.setAttribute("userInfo", dto);
			}
			// id, pw�� Ʋ���� �׳� dto�� null�� �ȴ�!
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
	
}// Controller ��
