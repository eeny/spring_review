package com.pluckit.app.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pluckit.app.dto.BoardDTO;

@Repository
public class AdminDAO {
	@Autowired
	private SqlSession sqlSession;

	public int makeBaord(BoardDTO dto) {
		return sqlSession.insert("admin.makeBoard", dto);
	}

	public List<BoardDTO> getAllBoardList() {
		return sqlSession.selectList("admin.getAllBoardList");
	}

	public int createMainTable(HashMap<String, String> map) {
		return sqlSession.insert("admin.createMainTable", map);
	}

	public int createReplyTable(HashMap<String, String> map) {
		return sqlSession.insert("admin.createReplyTable", map);
	}
	
	
	
	
}// AdminDAO 끝
