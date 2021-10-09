package com.pluckit.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pluckit.app.dto.BoardDTO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardDTO> getAllBaordTitle(String deptName) {
		return sqlSession.selectList("board.getAllBoardTitleUser", deptName);
	}

	public List<BoardDTO> getAllBoardTitle() {
		return sqlSession.selectList("board.getAllBoardTitleAdmin");
	}

	public String getBoardTitle(String pageName) {
		return sqlSession.selectOne("board.getBoardTitle", pageName);
	}
	
	
}
