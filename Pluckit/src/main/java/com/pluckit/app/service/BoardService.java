package com.pluckit.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluckit.app.dao.BoardDAO;
import com.pluckit.app.dto.BoardDTO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO bdao;

	public List<BoardDTO> getAllBoardTitle(String deptName) {
		return bdao.getAllBaordTitle(deptName);
	}
	
	public List<BoardDTO> getAllBoardTitle() {
		return bdao.getAllBoardTitle();
	}

	public String getBoardTitle(String pageName) {
		return bdao.getBoardTitle(pageName);
	}
	
	
	
}// BoardService 끝
