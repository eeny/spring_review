package com.pluckit.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pluckit.app.dto.BoardDTO;
import com.pluckit.app.dto.BoardMainDTO;
import com.pluckit.app.dto.EmployeeDTO;
import com.pluckit.app.dto.PagingDTO;
import com.pluckit.app.service.AdminService;
import com.pluckit.app.service.BoardService;
import com.pluckit.app.service.MainService;
import com.pluckit.app.service.OfficeService;
import com.pluckit.util.DownloadView;

@Controller
public class HomeController {
	@Autowired
	private MainService msvc; // [main]

	@Autowired
	private OfficeService osvc; // [office]

	@Autowired
	private AdminService adsvc; // [admin]

	@Autowired
	private BoardService bsvc; // [board]

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
	@RequestMapping("/SearchProc.do")
	public String searchEmployeeList(Model model, String select, String search) {
		model.addAttribute("employeeList", osvc.selectEmployeeListOne(select, search));

		return "office/office_employee";
	}

	// ===================== 오피스:직원목록 끝 =====================

	// ===================== 관리자메뉴:게시판 관리 시작 =====================

	// 관리자메뉴 페이지 이동
	@RequestMapping("/Admin.do")
	public String admin(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pNum,
			Model model) {
		// 총 게시판 개수, 페이징 처리
		int totalCount = adsvc.getAllBoardCount();
		PagingDTO pDto = new PagingDTO(Integer.parseInt(pNum), 10, totalCount, 3);

		List<BoardDTO> boardList = adsvc.getAllBoardList(pDto.getOffset(), pDto.getPageSize());
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", pDto);

		return "admin/admin_mngboard";
	}

	// 게시판 등록 처리
	@RequestMapping("/MakeBoard.do")
	public String makeBoard(BoardDTO dto, Model model) {
		adsvc.makeBoard(dto);
		adsvc.createMainTable(dto.getB_id());
		adsvc.createReplyTable(dto.getB_id());

		return "redirect:/Admin.do";
	}

	// 게시판 코드 중복 확인 처리
	@RequestMapping(value = "/IsBIdExist.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Integer> isBIdExist(@RequestBody BoardDTO dto) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("result", adsvc.isBIdExist(dto.getB_id()));

		return map;
	}

	// 수정할 게시판 정보 가져오기
	@RequestMapping(value = "/GetBoardInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public BoardDTO getBoardInfo(@RequestBody BoardDTO dto) {
		return adsvc.getBoardInfo(dto.getB_id());
	}

	// 게시판 수정 처리
	@RequestMapping("/ModifyBoardProc.do")
	public String modifyBoard(BoardDTO dto) {
		adsvc.updateBoardInfo(dto);

		return "redirect:/Admin.do";
	}

	// 게시판 삭제 전 데이터 존재유무 확인
	@RequestMapping(value = "/IsBoardDataExist.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Integer> IsBoardDataExist(@RequestBody BoardDTO dto) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("result", adsvc.isBoardDataExist(dto.getB_id()));

		return map;
	}

	// 게시판 삭제 처리
	@RequestMapping("/DeleteBoardProc.do")
	public String deleteBoard(String b_id) {
		adsvc.dropReplyTable(b_id);
		adsvc.dropMainTable(b_id);
		adsvc.deleteBoardInfo(b_id);

		return "redirect:/Admin.do";
	}

	// 게시판 검색 처리
	@RequestMapping("/SearchBoardList.do")
	public String searchBoardList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pNum,
			Model model, String select, String search) {
		// 검색어 처리
		HashMap<String, String> map = new HashMap<>();

		if (select.equals("b_id") || select.equals("b_title")) {
			select = "b." + select;
		} else if (select.equals("dept_id")) {
			select = "d.dept_name";
		}

		map.put("select", select);
		map.put("search", search);

		// 총 게시판 개수, 페이징 처리
		int totalCount = adsvc.getSearchBoardCount(map);
		PagingDTO pDto = new PagingDTO(Integer.parseInt(pNum), 10, totalCount, 3);

		map.put("offset", Integer.toString(pDto.getOffset()));
		map.put("pageSize", Integer.toString(pDto.getPageSize()));

		List<BoardDTO> boardList = adsvc.searchBoardList(map);

		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", pDto);

		return "admin/admin_mngboard";
	}

	// ===================== 관리자메뉴:게시판 관리 끝 =====================

	// ===================== 게시판 메뉴 시작 =====================
	// [게시판] 메뉴 페이지 이동
	@RequestMapping("/Board.do")
	public String board(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pNum,
			String deptName, String empAuth, String pageName, Model model) {
		// 왼쪽 세부 메뉴 목록 가져오기
		if (Integer.parseInt(empAuth) < 5) { // 관리자 권한이 아닌 경우
			model.addAttribute("menuList", bsvc.getAllBoardTitle(deptName));
		} else {
			model.addAttribute("menuList", bsvc.getAllBoardTitle());
		}

		// 세부 메뉴 클릭시 클래스 적용할 때 사용될 변수
		model.addAttribute("pageName", pageName);

		// 어떤 게시판인지 구별하기 (아이콘 옆의 제목)
		model.addAttribute("pageTitle", bsvc.getBoardTitle(pageName));

		// 게시글 목록 가져오기 & 페이징
		int totalCount = bsvc.getAllBoardCount(pageName);
		PagingDTO pDto = new PagingDTO(Integer.parseInt(pNum), 10, totalCount, 3);

		List<BoardMainDTO> boardList = bsvc.getAllBoardList(pageName, pDto.getOffset(), pDto.getPageSize());
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", pDto);

		return "board/board_main";
	}

	// [게시판] 메뉴 글쓰기 페이지 이동
	@RequestMapping("/WritePost.do")
	public String writePost(String deptName, String empAuth, String pageName, Model model) {
		// 왼쪽 세부 메뉴 목록 가져오기
		if (Integer.parseInt(empAuth) < 5) { // 관리자 권한이 아닌 경우
			model.addAttribute("menuList", bsvc.getAllBoardTitle(deptName));
		} else {
			model.addAttribute("menuList", bsvc.getAllBoardTitle());
		}

		// 세부 메뉴 클릭시 클래스 적용할 때 사용될 변수
		model.addAttribute("pageName", pageName);

		// 어떤 게시판인지 구별하기 (아이콘 옆의 제목)
		model.addAttribute("pageTitle", bsvc.getBoardTitle(pageName));

		return "board/board_write";
	}

	// [게시판] 메뉴 글 작성하기
	@Autowired
	ServletContext context;

	@RequestMapping("/WritePostProc.do")
	public String writePostProc(String deptName, String empAuth, String pageName, MultipartHttpServletRequest mrequest,
			RedirectAttributes redirect) throws IOException {
		bsvc.writePostProc(context, mrequest);

		// 리다이렉트 시 같이 넘겨야하는 파라미터
		redirect.addAttribute("deptName", deptName);
		redirect.addAttribute("empAuth", empAuth);
		redirect.addAttribute("pageName", pageName);

		return "redirect:/Board.do";
	}

	// [게시판] 메뉴 글 읽기 페이지 이동
	@RequestMapping("/ReadPost.do")
	public String readPost(String deptName, String empAuth, String pageName, String bmNum, Model model) {
		// 왼쪽 세부 메뉴 목록 가져오기
		if (Integer.parseInt(empAuth) < 5) { // 관리자 권한이 아닌 경우
			model.addAttribute("menuList", bsvc.getAllBoardTitle(deptName));
		} else {
			model.addAttribute("menuList", bsvc.getAllBoardTitle());
		}

		// 세부 메뉴 클릭시 클래스 적용할 때 사용될 변수
		model.addAttribute("pageName", pageName);

		// 어떤 게시판인지 구별하기 (아이콘 옆의 제목)
		model.addAttribute("pageTitle", bsvc.getBoardTitle(pageName));

		// 조회수 증가
		bsvc.updateHitCount(pageName, bmNum);
		
		model.addAttribute("post", bsvc.getPost(pageName, bmNum));

		return "board/board_read";
	}

	// [게시판] 게시글 첨부파일 다운로드
	@RequestMapping("/FileDownload.do")
	public String fileDownload(String bmFile, String bmSFile, HttpSession session,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		bsvc.fileDownload(context, bmFile, bmSFile, session, request, response);
		
		return "";
	}

	// [게시판] 메뉴 글 수정 페이지 이동
	@RequestMapping("/ModifyPost.do")
	public String modifyPost() {

		return "";
	}

	// [게시판] 메뉴 글 수정
	@RequestMapping("/ModifyPostProc.do")
	public String modifyPostProc() {

		return "";
	}

	// [게시판] 메뉴 글 삭제
	@RequestMapping("/DeletePostProc.do")
	public String deletePostProc() {

		return "";
	}

	// ===================== 게시판 메뉴 끝 =====================

}// HomeController 끝
