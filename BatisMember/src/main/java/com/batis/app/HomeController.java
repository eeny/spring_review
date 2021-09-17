package com.batis.app;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	@Autowired
	ServletContext context;
	
	String path;
	
	@RequestMapping("/BoardInsert.do")
	public String insertBoard(MultipartHttpServletRequest mrequest) throws Exception { // �Խ��� �Է� ó��
		// ������ ������ ������ �� �������
		String id = mrequest.getParameter("id");
		String name = mrequest.getParameter("name");
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");

		MultipartFile file = mrequest.getFile("img"); // �Ѿ�� ���� ��ü
		String filename = file.getOriginalFilename(); // ��ü���� �̸� �������
		
		System.out.println("�Ѿ�� ���ϸ�: " + filename);
		
		// ���� ���� ��ġ - ���� ������ ���� ��ġ
		path = context.getRealPath("/resources/upload/");
		
		// ������ ��ο� ������ ���� �� ���� ����
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}

		if (!file.isEmpty()) {	
			// ���ϸ� �ߺ� �ȵǰ� �����ϱ� (UUID���)
			String saveFileName = uploadFile(filename, file.getBytes());
			
			System.out.println("������ ���ϸ�: " + saveFileName);
			
			file.transferTo(new File(path + saveFileName));
			
			System.out.println("���ε� �Ϸ� : " + path + saveFileName);

			bdao.insertBoard(id, name, title, content, saveFileName);
		} else {
			bdao.insertBoard(id, name, title, content, filename);			
		}
		
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
	public String updateBoard(MultipartHttpServletRequest mrequest) throws IOException { // �Խ��� �� ����
		// ������ ������ ������ �� �������
		int idx = Integer.parseInt(mrequest.getParameter("idx"));
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");

		MultipartFile file = mrequest.getFile("img"); // �Ѿ�� ���� ��ü
		String filename = file.getOriginalFilename(); // ��ü���� �̸� �������
		
		System.out.println("�Ѿ�� ���ϸ�: " + filename);
		
		// ���� ���� ��ġ - ���� ������ ���� ��ġ
		path = context.getRealPath("/resources/upload/");
		
		// ������ ��ο� ������ ���� �� ���� ����
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}

		if (!file.isEmpty()) {	
			// ������ ����� ���� �����ϱ�
			File oldFile = new File(path + bdao.getFileName(idx));
			// ���� ������ �����Ѵٸ� �����ض�!
			if (oldFile.exists()) {
				oldFile.delete();
				System.out.println("���� ���� �����Ϸ�");
			}
			
			// ���ϸ� �ߺ� �ȵǰ� �����ϱ� (UUID���)
			String saveFileName = uploadFile(filename, file.getBytes());
			
			System.out.println("������ ���ϸ�: " + saveFileName);
			
			file.transferTo(new File(path + saveFileName));
			
			System.out.println("���ε� �Ϸ� : " + path + saveFileName);

			bdao.updateBoard(title, content, saveFileName, idx);
		} else {
			bdao.updateBoard(title, content, idx);			
		}
		
		//bdao.updateBoard(dto);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/DeleteBoard.do")
	public String deleteBoard(int idx) { // �Խ��� �� ����
		// ���� ������ + ����� ���ϸ��� �̿��� ���� ���� �����
		path = context.getRealPath("/resources/upload/");
		File file = new File(path + bdao.getFileName(idx));
		
		System.out.println("����� ���ϸ�" + bdao.getFileName(idx));
		
		// ���� ������ �����Ѵٸ� �����ض�!
		if (file.exists()) {
			file.delete();
			System.out.println("���� �����Ϸ�");
		}
		
		// DB���� ������ �����ϱ�
		bdao.deleteBoard(idx);
		
		return "redirect:/main.do";
	}
	
	
	// ���ϸ� �ߺ� �ȵǰ� �����ϴ� �޼��� (UUID���)
	private String uploadFile(String originalName, byte[] fileData) throws IOException {
		// UUID(���� ���� �ĺ���) ����
		UUID uuid = UUID.randomUUID();
		
		// ���� ���� + �����̸� ����
		String saveFileName = uuid.toString() + "_" + originalName;
		File target = new File(path, saveFileName);
		
		//�ӽõ��丮�� ����� ���ε������� ������ ���丮�� ����
		//FileCopyUtils (����Ʈ�迭, ���ϰ�ü)
		FileCopyUtils.copy(fileData, target);
		
		return saveFileName;
	}
	
	
	
}// HomeController ��
