package com.pluckit.app.dao;

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
	
	
	
	
}// AdminDAO 끝
