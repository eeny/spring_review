package com.member.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.member.app.bean.SpBoardDTO;

@Repository
public class SpBoardDAO {
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	@Value("SELECT * FROM spboard")
	private String selectAllBoard;
	
	@Value("SELECT * FROM spboard WHERE idx = ?")
	private String selectOneBoard;
	
	@Value("SELECT COUNT(idx) FROM spboard")
	private String countBoard;
	
	@Value("INSERT INTO spboard VALUES (NULL, ?, ?, ?, NOW())")
	private String insertBoard;
	
	@Value("DELETE FROM spboard WHERE idx = ?")
	private String deleteBoard;
	
	@Value("UPDATE spboard SET name = ?, title = ?, contents = ?, regdate = NOW() WHERE idx = ?")
	private String updateBoard;
	
	// SELECT ���� �޼���
	public List<SpBoardDTO> getAllBoard() {
		return jdbcTmp.query(selectAllBoard, new BoardMapper());
	}
	
	public List<SpBoardDTO> getAllBoard(int start, int cnt) { // ����¡ ó���� ���
		return jdbcTmp.query(selectAllBoard, new BoardMapper(), start, cnt);
	}
	
	public int getBoardCount() {
		return jdbcTmp.queryForObject(countBoard, Integer.class);
	}
	
	public SpBoardDTO getOneBoard(int idx) {
		return jdbcTmp.queryForObject(selectOneBoard, new BoardMapper(), idx);
	}
	
	// inner class - �� �ٿ� ���ؼ��� �����ϸ� �ȴ�!
	class BoardMapper implements RowMapper<SpBoardDTO> {
		@Override
		public SpBoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpBoardDTO dto = new SpBoardDTO();
			dto.setIdx(rs.getInt("idx"));
			dto.setName(rs.getString("name"));
			dto.setTitle(rs.getString("title"));
			dto.setContents(rs.getString("contents"));
			dto.setRegdate(rs.getString("regdate"));
			
			return dto;
		}
	}
	
	// INSERT ���� �޼���
	public int insertBoard(SpBoardDTO dto) {
		return jdbcTmp.update(insertBoard, dto.getName(), dto.getTitle(), dto.getContents());
	}
	
	// UPDATE ���� �޼���
	public int updateBoard(SpBoardDTO dto) {
		return jdbcTmp.update(updateBoard, dto.getName(), dto.getTitle(), dto.getContents(), dto.getIdx());
	}
	
	// DELETE ���� �޼���
	public int deleteBoard(int idx) {
		return jdbcTmp.update(deleteBoard, idx);
	}
	
	
}// SpBoardDAO Ŭ���� ��
