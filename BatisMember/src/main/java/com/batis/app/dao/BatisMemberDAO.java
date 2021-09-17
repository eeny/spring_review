package com.batis.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.batis.app.dto.BatisMemberDTO;

@Repository
public class BatisMemberDAO { // ȸ�� ����
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	// ========== SQL ������ ==========
	
	@Value("INSERT INTO batismember VALUE (NULL, ?, ?, ?, ?, ?, ?, NOW(), 1)")
	private String insertMember; // ȸ������
	
	@Value("SELECT COUNT(idx) FROM batismember WHERE id = ?")
	private String checkId; // ���̵� �ߺ�üũ
	
	@Value("SELECT * FROM batismember")
	private String getAllMember; // ȸ�� ���
	
	@Value("SELECT * FROM batismember WHERE id = ? AND pw = ?")
	private String login; // �α���
	
	// ========== SQL ���� �޼��� ==========
	
	public int insertMember(BatisMemberDTO dto) { // ȸ������
		return jdbcTmp.update(insertMember, dto.getId(), dto.getPw(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getBirth());
	}
	
	public String checkId(String id) { // ���̵� �ߺ�üũ
		String str = "����� �� �ִ� ���̵��Դϴ�.";
		
		int res = jdbcTmp.queryForObject(checkId, Integer.class, id);
		if (res > 0) {
			str = "�̹� �����ϴ� ���̵��Դϴ�.";
		}
		
		return str;
	}
	
	public List<BatisMemberDTO> getAllMember() { // ȸ�� ��� ��������
		return jdbcTmp.query(getAllMember, new MemberMapper());
	}
	
	public List<BatisMemberDTO> login(BatisMemberDTO dto) { // �α���
		return jdbcTmp.query(login, new MemberMapper(), dto.getId(), dto.getPw());
	}
	
	
	// Select ����� ��� ���� inner class
	class MemberMapper implements RowMapper<BatisMemberDTO> {
		@Override
		public BatisMemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BatisMemberDTO dto = new BatisMemberDTO();
			dto.setIdx(rs.getInt("idx"));
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			dto.setPhone(rs.getString("phone"));
			dto.setBirth(rs.getString("birth"));
			dto.setRegdate(rs.getString("regdate"));
			dto.setLevel(rs.getInt("level"));
			
			return dto;
		}
	}
	
}// dao ��
