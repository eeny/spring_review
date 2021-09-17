package com.file.app;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.file.app.dao.FileBoardDAO;
import com.file.app.dto.FileBoardDTO;

@Controller
public class HomeController {
	@Autowired
	FileBoardDAO dao;

	@RequestMapping("/main.do")
	public String home(Model model) {
		return "home";
	}

	@RequestMapping("/ListBoard.do")
	public String listBoard(Model model) {
		model.addAttribute("list", dao.fileList());

		return "list";
	}

	@RequestMapping("/InsertBoard.do")
	public String insertBoard() {
		return "insert";
	}

	// ========== ���� ���ε� ó�� ==========
	@Autowired
	ServletContext context;

	@RequestMapping("/InsertBoardProc.do")
	public String insertBoardProc(MultipartHttpServletRequest mrequest) {
		MultipartFile file = mrequest.getFile("filename"); // �Ѿ�� ���� ��ü

		String filename = file.getOriginalFilename(); // ��ü���� �̸��� �̾Ƴ���!

		// �Ѿ�� txt ��������
		String txt = mrequest.getParameter("txt");

		// ���� ���� ��ġ - ���� ������ ���� ��ġ�� ����. ex) c:/user/main/...
		String path = context.getRealPath("/resources/upload/");

		try {
			// ���� ���ε� �޼���
			// file.transferTo(new File("����� ��ġ�� ������ ���ϸ�"));
			file.transferTo(new File(path + filename));
			// �̱��� �ϸ� c:/~~~/resources/upload/asdf.jpg �̷��� ����ȴ�!

			System.out.println("���ε� �Ϸ�! : " + path + filename);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		// jdbcTmp ����� ��
		// dao.insertBoard(txt, filename);

		// ���̹�Ƽ�� ����� ��
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("txt", txt);
		map.put("filename", filename);

		// ���� ������ ���� ���� ��Ʈ�ѷ����� �̸� map�� ���� ������ �ȴ�!
		dao.fileInsert(map);

		return "redirect:/ListBoard.do";
	}

	@RequestMapping("/FileDelete.do")
	public String delete(int idx) {

		// ���� ���� ����� �κ�
		String path = context.getRealPath("/resources/upload/");
		File file = new File(path + dao.fileName(idx));
		// D://~~~~/resources/upload/aaa.jpg
		// dao.fileName(idx) <== idx�� ������ ���� �̸� �������� �޼���

		// ���� ������ ��ġ + ���ϸ����� ���� ������ �����ؼ� ���� �ش��ϴ� ������ �����Ѵٸ� ������!
		if (file.exists()) {
			file.delete();
			System.out.println("���� �����Ϸ�");
		}

		// DB���� ������ �����ϱ�
		dao.fileDelete(idx);

		return "redirect:/ListBoard.do";
	}

}// HomeController ��
