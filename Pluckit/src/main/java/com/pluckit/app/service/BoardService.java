package com.pluckit.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluckit.app.dao.BoardDAO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO bdao;
	
	
}
