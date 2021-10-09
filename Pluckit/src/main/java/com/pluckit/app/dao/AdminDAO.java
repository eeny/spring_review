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

	public List<BoardDTO> getAllBoardList(HashMap<String, Integer> map) {
		return sqlSession.selectList("admin.getAllBoardList", map);
	}

	public int createMainTable(HashMap<String, String> map) {
		return sqlSession.insert("admin.createMainTable", map);
	}

	public int createReplyTable(HashMap<String, String> map) {
		return sqlSession.insert("admin.createReplyTable", map);
	}

	public int isBIdExist(String bId) {
		return sqlSession.selectOne("admin.isBIdExist", bId);
	}

	public BoardDTO getBoardInfo(String bId) {
		return sqlSession.selectOne("admin.getBoardInfo", bId);
	}

	public int updateBoardInfo(BoardDTO dto) {
		return sqlSession.update("admin.updateBoardInfo", dto);
	}

	public int isBoardDataExist(String tblName) {
		return sqlSession.selectOne("admin.isBoardDataExist", tblName);
	}

	public int dropReplyTable(HashMap<String, String> map) {
		return sqlSession.insert("admin.dropReplyTable", map);
	}

	public int dropMainTable(HashMap<String, String> map) {
		return sqlSession.insert("admin.dropMaintable", map);
	}

	public int deleteBoardInfo(String bId) {
		return sqlSession.delete("admin.deleteBoardInfo", bId);
	}

	public List<BoardDTO> searchBoardList(HashMap<String, String> map) {
		return sqlSession.selectList("admin.searchBoardList", map);
	}

	public int getAllBoardCount() {
		return sqlSession.selectOne("admin.getAllBoardCount");
	}

	public int getSearchBoardCount(HashMap<String, String> map) {
		return sqlSession.selectOne("admin.getSearchBoardCount", map);
	}
	
	
	
	
}// AdminDAO 끝
