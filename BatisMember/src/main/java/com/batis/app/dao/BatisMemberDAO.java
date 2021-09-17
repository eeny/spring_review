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
public class BatisMemberDAO { // 회원 관련
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	// ========== SQL 쿼리문 ==========
	
	@Value("INSERT INTO batismember VALUE (NULL, ?, ?, ?, ?, ?, ?, NOW(), 1)")
	private String insertMember; // 회원가입
	
	@Value("SELECT COUNT(idx) FROM batismember WHERE id = ?")
	private String checkId; // 아이디 중복체크
	
	@Value("SELECT * FROM batismember")
	private String getAllMember; // 회원 목록
	
	@Value("SELECT * FROM batismember WHERE id = ? AND pw = ?")
	private String login; // 로그인
	
	// ========== SQL 실행 메서드 ==========
	
	public int insertMember(BatisMemberDTO dto) { // 회원가입
		return jdbcTmp.update(insertMember, dto.getId(), dto.getPw(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getBirth());
	}
	
	public String checkId(String id) { // 아이디 중복체크
		String str = "사용할 수 있는 아이디입니다.";
		
		int res = jdbcTmp.queryForObject(checkId, Integer.class, id);
		if (res > 0) {
			str = "이미 존재하는 아이디입니다.";
		}
		
		return str;
	}
	
	public List<BatisMemberDTO> getAllMember() { // 회원 목록 가져오기
		return jdbcTmp.query(getAllMember, new MemberMapper());
	}
	
	public List<BatisMemberDTO> login(BatisMemberDTO dto) { // 로그인
		return jdbcTmp.query(login, new MemberMapper(), dto.getId(), dto.getPw());
	}
	
	
	// Select 결과를 담기 위한 inner class
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
	
}// dao 끝
