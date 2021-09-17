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

	// ========== 파일 업로드 처리 ==========
	@Autowired
	ServletContext context;

	@RequestMapping("/InsertBoardProc.do")
	public String insertBoardProc(MultipartHttpServletRequest mrequest) {
		MultipartFile file = mrequest.getFile("filename"); // 넘어온 파일 객체

		String filename = file.getOriginalFilename(); // 객체에서 이름을 뽑아낸다!

		// 넘어온 txt 가져오기
		String txt = mrequest.getParameter("txt");

		// 파일 저장 위치 - 실제 서버의 파일 위치를 쓴다. ex) c:/user/main/...
		String path = context.getRealPath("/resources/upload/");

		try {
			// 파일 업로드 메서드
			// file.transferTo(new File("저장될 위치를 포함한 파일명"));
			file.transferTo(new File(path + filename));
			// 이까지 하면 c:/~~~/resources/upload/asdf.jpg 이렇게 저장된다!

			System.out.println("업로드 완료! : " + path + filename);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		// jdbcTmp 사용할 때
		// dao.insertBoard(txt, filename);

		// 마이바티스 사용할 때
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("txt", txt);
		map.put("filename", filename);

		// 값을 여러개 넣을 때는 컨트롤러에서 미리 map을 만들어서 던지면 된다!
		dao.fileInsert(map);

		return "redirect:/ListBoard.do";
	}

	@RequestMapping("/FileDelete.do")
	public String delete(int idx) {

		// 실제 파일 지우는 부분
		String path = context.getRealPath("/resources/upload/");
		File file = new File(path + dao.fileName(idx));
		// D://~~~~/resources/upload/aaa.jpg
		// dao.fileName(idx) <== idx를 가지고 파일 이름 가져오는 메서드

		// 위에 생성한 위치 + 파일명으로 실제 파일을 생성해서 만약 해당하는 파일이 존재한다면 지워라!
		if (file.exists()) {
			file.delete();
			System.out.println("파일 삭제완료");
		}

		// DB에서 데이터 삭제하기
		dao.fileDelete(idx);

		return "redirect:/ListBoard.do";
	}

}// HomeController 끝
