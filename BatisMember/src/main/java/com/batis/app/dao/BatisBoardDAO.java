package com.batis.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.batis.app.dto.BatisBoardDTO;
import com.batis.app.dto.BatisMemberDTO;

@Repository
public class BatisBoardDAO { // 게시판 관련
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	// ========== SQL 쿼리문 ==========
	@Value("SELECT * FROM batisboard")
	private String getAllBoard; // 전체 게시판 목록 가져오기
	
	@Value("INSERT INTO batisboard VALUE (NULL, ?, ?, ?, ?, ?, NOW(), 0)")
	private String inserBoard; // 게시판 새글쓰기
	
	@Value("SELECT * FROM batisboard WHERE idx = ?")
	private String getBoardData; // 특정 게시글 가져오기
	
	@Value("DELETE FROM batisboard WHERE idx = ?")
	private String deleteBoard; // 게시판 글삭제
	
	@Value("UPDATE batisboard SET title = ?, content = ?, img = ?, regdate = NOW() WHERE idx = ?")
	private String updateBoard; // 게시판 글수정
	
	@Value("SELECT * FROM batisboard LIMIT ?, ?")
	private String getBoardPage; // 게시판 페이징
	
	@Value("SELECT COUNT(*) FROM batisboard")
	private String getAllBoardCount; // 전체 게시판 글 개수 가져오기
	
	
	
	// ========== SQL 실행 메서드 ==========
	public List<BatisBoardDTO> getAllBoard() { // 전체 게시판 목록 가져오기
		return jdbcTmp.query(getAllBoard, new BoardMapper());
	}

	public int insertBoard(BatisBoardDTO dto) { // 게시판 새글쓰기
		return jdbcTmp.update(inserBoard, dto.getId(), dto.getName(), dto.getTitle(), dto.getContent(), dto.getImg());
	}
	
	public BatisBoardDTO getBoardData(BatisBoardDTO dto) { // 특정 게시글 가져오기
		return jdbcTmp.queryForObject(getBoardData, new BoardMapper(), dto.getIdx());
	}
	
	public int deleteBoard(BatisBoardDTO dto) { // 게시판 글삭제
		return jdbcTmp.update(deleteBoard, dto.getIdx());
	}
	
	public int updateBoard(BatisBoardDTO dto) { // 게시판 글수정
		return jdbcTmp.update(updateBoard, dto.getTitle(), dto.getContent(), dto.getImg(), dto.getIdx());
	}
	
	public List<BatisBoardDTO> getBoardPage(int start, int cnt) { // 페이징 적용된 전체 게시판 목록 가져오기
		return jdbcTmp.query(getBoardPage, new BoardMapper(), start, cnt);
	}
	
	public int getAllBoardCount() { // 전체 게시글 개수 가져오기
		return jdbcTmp.queryForObject(getAllBoardCount, Integer.class);
	}
	
	
	// Select 결과를 담기 위한 inner class
	class BoardMapper implements RowMapper<BatisBoardDTO> {
		@Override
		public BatisBoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BatisBoardDTO dto = new BatisBoardDTO();
			dto.setIdx(rs.getInt("idx"));
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setTitle(rs.getString("title"));
			dto.setContent(rs.getString("content"));
			dto.setImg(rs.getString("img"));
			dto.setRegdate(rs.getString("regdate"));
			dto.setHit(rs.getInt("hit"));
			
			return dto;
		}
	}
}// dao 끝
