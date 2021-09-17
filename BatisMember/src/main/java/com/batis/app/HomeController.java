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
	
	@Autowired
	ServletContext context;
	
	String path;
	
	@RequestMapping("/BoardInsert.do")
	public String insertBoard(MultipartHttpServletRequest mrequest) throws Exception { // 게시판 입력 처리
		// 파일을 제외한 나머지 값 갖고오기
		String id = mrequest.getParameter("id");
		String name = mrequest.getParameter("name");
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");

		MultipartFile file = mrequest.getFile("img"); // 넘어온 파일 객체
		String filename = file.getOriginalFilename(); // 객체에서 이름 갖고오기
		
		System.out.println("넘어온 파일명: " + filename);
		
		// 파일 저장 위치 - 실제 서버의 파일 위치
		path = context.getRealPath("/resources/upload/");
		
		// 설정한 경로에 폴더가 없을 때 폴더 생성
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}

		if (!file.isEmpty()) {	
			// 파일명 중복 안되게 수정하기 (UUID방식)
			String saveFileName = uploadFile(filename, file.getBytes());
			
			System.out.println("생성된 파일명: " + saveFileName);
			
			file.transferTo(new File(path + saveFileName));
			
			System.out.println("업로드 완료 : " + path + saveFileName);

			bdao.insertBoard(id, name, title, content, saveFileName);
		} else {
			bdao.insertBoard(id, name, title, content, filename);			
		}
		
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
	public String updateBoard(MultipartHttpServletRequest mrequest) throws IOException { // 게시판 글 수정
		// 파일을 제외한 나머지 값 갖고오기
		int idx = Integer.parseInt(mrequest.getParameter("idx"));
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");

		MultipartFile file = mrequest.getFile("img"); // 넘어온 파일 객체
		String filename = file.getOriginalFilename(); // 객체에서 이름 갖고오기
		
		System.out.println("넘어온 파일명: " + filename);
		
		// 파일 저장 위치 - 실제 서버의 파일 위치
		path = context.getRealPath("/resources/upload/");
		
		// 설정한 경로에 폴더가 없을 때 폴더 생성
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}

		if (!file.isEmpty()) {	
			// 기존에 저장된 파일 삭제하기
			File oldFile = new File(path + bdao.getFileName(idx));
			// 실제 파일이 존재한다면 삭제해라!
			if (oldFile.exists()) {
				oldFile.delete();
				System.out.println("기존 파일 삭제완료");
			}
			
			// 파일명 중복 안되게 수정하기 (UUID방식)
			String saveFileName = uploadFile(filename, file.getBytes());
			
			System.out.println("생성된 파일명: " + saveFileName);
			
			file.transferTo(new File(path + saveFileName));
			
			System.out.println("업로드 완료 : " + path + saveFileName);

			bdao.updateBoard(title, content, saveFileName, idx);
		} else {
			bdao.updateBoard(title, content, idx);			
		}
		
		//bdao.updateBoard(dto);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/DeleteBoard.do")
	public String deleteBoard(int idx) { // 게시판 글 삭제
		// 파일 저장경로 + 저장된 파일명을 이용해 실제 파일 지우기
		path = context.getRealPath("/resources/upload/");
		File file = new File(path + bdao.getFileName(idx));
		
		System.out.println("저장된 파일명" + bdao.getFileName(idx));
		
		// 실제 파일이 존재한다면 삭제해라!
		if (file.exists()) {
			file.delete();
			System.out.println("파일 삭제완료");
		}
		
		// DB에서 데이터 삭제하기
		bdao.deleteBoard(idx);
		
		return "redirect:/main.do";
	}
	
	
	// 파일명 중복 안되게 수정하는 메서드 (UUID방식)
	private String uploadFile(String originalName, byte[] fileData) throws IOException {
		// UUID(범용 고유 식별자) 생성
		UUID uuid = UUID.randomUUID();
		
		// 랜덤 생성 + 파일이름 저장
		String saveFileName = uuid.toString() + "_" + originalName;
		File target = new File(path, saveFileName);
		
		//임시디렉토리에 저장된 업로드파일을 지정된 디렉토리로 복사
		//FileCopyUtils (바이트배열, 파일객체)
		FileCopyUtils.copy(fileData, target);
		
		return saveFileName;
	}
	
	
	
}// HomeController 끝
