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
public class BatisBoardDAO { // �Խ��� ����
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	// ========== SQL ������ ==========
	@Value("SELECT * FROM batisboard")
	private String getAllBoard; // ��ü �Խ��� ��� ��������
	
	@Value("INSERT INTO batisboard VALUE (NULL, ?, ?, ?, ?, ?, NOW(), 0)")
	private String inserBoard; // �Խ��� ���۾���
	
	@Value("SELECT * FROM batisboard WHERE idx = ?")
	private String getBoardData; // Ư�� �Խñ� ��������
	
	@Value("DELETE FROM batisboard WHERE idx = ?")
	private String deleteBoard; // �Խ��� �ۻ���
	
	@Value("UPDATE batisboard SET title = ?, content = ?, img = ?, regdate = NOW() WHERE idx = ?")
	private String updateBoard; // �Խ��� �ۼ���
	
	@Value("SELECT * FROM batisboard LIMIT ?, ?")
	private String getBoardPage; // �Խ��� ����¡
	
	@Value("SELECT COUNT(*) FROM batisboard")
	private String getAllBoardCount; // ��ü �Խ��� �� ���� ��������
	
	
	
	// ========== SQL ���� �޼��� ==========
	public List<BatisBoardDTO> getAllBoard() { // ��ü �Խ��� ��� ��������
		return jdbcTmp.query(getAllBoard, new BoardMapper());
	}

	public int insertBoard(BatisBoardDTO dto) { // �Խ��� ���۾���
		return jdbcTmp.update(inserBoard, dto.getId(), dto.getName(), dto.getTitle(), dto.getContent(), dto.getImg());
	}
	
	public BatisBoardDTO getBoardData(BatisBoardDTO dto) { // Ư�� �Խñ� ��������
		return jdbcTmp.queryForObject(getBoardData, new BoardMapper(), dto.getIdx());
	}
	
	public int deleteBoard(BatisBoardDTO dto) { // �Խ��� �ۻ���
		return jdbcTmp.update(deleteBoard, dto.getIdx());
	}
	
	public int updateBoard(BatisBoardDTO dto) { // �Խ��� �ۼ���
		return jdbcTmp.update(updateBoard, dto.getTitle(), dto.getContent(), dto.getImg(), dto.getIdx());
	}
	
	public List<BatisBoardDTO> getBoardPage(int start, int cnt) { // ����¡ ����� ��ü �Խ��� ��� ��������
		return jdbcTmp.query(getBoardPage, new BoardMapper(), start, cnt);
	}
	
	public int getAllBoardCount() { // ��ü �Խñ� ���� ��������
		return jdbcTmp.queryForObject(getAllBoardCount, Integer.class);
	}
	
	
	// Select ����� ��� ���� inner class
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
}// dao ��
