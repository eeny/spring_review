package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.dao.BootDAO;
import com.example.demo.dto.BootDTO;

@Controller
public class HomeController {
	@Autowired
	private BootDAO dao;

	@RequestMapping("/board/write.do")
	public String write() {
		return "board/write";// board/write.jsp => html
	}

	@RequestMapping("/board/writeProc.do")
	public String writeProc(BootDTO dto) {
		dao.insertData(dto);
		return "redirect:/board/list.do";
	}

	@RequestMapping("/board/list.do")
	public String list(Model model) {
		model.addAttribute("data", dao.selectAll());
		return "board/list";
	}

	@RequestMapping("/board/delete.do")
	public String list(int idx) {
		int result = dao.deleteData(idx);
		return "redirect:/board/list.do";
	}
}
