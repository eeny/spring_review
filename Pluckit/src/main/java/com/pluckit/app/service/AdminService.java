package com.pluckit.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluckit.app.dao.AdminDAO;
import com.pluckit.app.dto.BoardDTO;

@Service
public class AdminService {
	@Autowired
	private AdminDAO addao;

	public void makeBoard(BoardDTO dto) {
		addao.makeBaord(dto);
	}

	
	
	
	
	
}// AdminService 끝
