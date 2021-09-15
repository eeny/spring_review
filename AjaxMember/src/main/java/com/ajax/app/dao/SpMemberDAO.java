package com.ajax.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ajax.app.bean.SpMemberDTO;

@Repository
public class SpMemberDAO {
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	@Value("SELECT * FROM spajax")
	private String selectAllMember; // 전체 회원목록 쿼리문
	
	@Value("SELECT * FROM spajax LIMIT ?, ?")
	private String selectAllMemberPaging; // 페이징에 쓰는 쿼리문
	
	@Value("SELECT COUNT(idx) FROM spajax")
	private String selectAllCntMember; // 전체 회원수 쿼리문
	
	@Value("SELECT COUNT(*) FROM spajax WHERE id = ?")
	private String selectAjaxMember; // Ajax에 쓰는 쿼리문
	
	@Value("SELECT * FROM spajax WHERE name LIKE ?")
	private String selectSearchMember; // 검색에 쓰는 쿼리문
	
	@Value("SELECT * FROM spajax WHERE name LIKE ? LIMIT ?, ?")
	private String selectSearchMemberPaging; // 페이징에 쓰는 쿼리문
	
	@Value("SELECT COUNT(idx) FROM spajax WHERE name LIKE ?")
	private String selectSearchCntMember; // 검색에 쓰는 쿼리문
	
	@Value("INSERT INTO spajax VALUES (NULL, ?, ?, ?)")
	private String insertMember; // 회원가입 쿼리문
	
	// SELECT 목록 가져오기 (페이징 포함)
	public List<SpMemberDTO> getAllMember() {
		return jdbcTmp.query(selectAllMember, new MemberMapper());
	}

	public int getAllMemberCount() {
		return jdbcTmp.queryForObject(selectAllCntMember, Integer.class);
	}
	
	public List<SpMemberDTO> getAllMemberPaging(int start, int cnt) {
		return jdbcTmp.query(selectAllMemberPaging, new MemberMapper(), start, cnt);
	}
	
	// INSERT
	public int insertMember(SpMemberDTO dto) {
		return jdbcTmp.update(insertMember, dto.getId(), dto.getPw(), dto.getName());
	}
	
	// 이 방식은 서블릿에서 Ajax를 쓰던 방법을 응용한 방식이라서 스프링 방식이 아니다!!!
	/*
	public boolean isExistId(String id) { // 아이디 존재여부 확인
		boolean boo = true;
		// false일 경우, 이미 존재하는 아이디(사용할 수 없다는 뜻)
		// true일 경우, 사용가능한 아이디!
		
		int res = jdbcTmp.queryForObject(selectAjaxMember, Integer.class, id);
		if (res > 0) { // 아이디가 존재한다는 뜻
			boo = false;
		}
		
		return boo;
	}
	*/
	
	// Ajax
	public String isExistId(String id) { // 아이디 존재여부 확인
		String str = "사용할 수 있는 아이디입니다.";
		
		int res = jdbcTmp.queryForObject(selectAjaxMember, Integer.class, id);
		if (res > 0) { // 아이디가 존재한다는 뜻
			str = "이미 존재하는 아이디입니다.";
		}
		
		return str;
	}
	
	// 검색
	public List<SpMemberDTO> getSearchList(String name) {
		return jdbcTmp.query(selectSearchMember, new MemberMapper(), "%" + name + "%");
	}

	public List<SpMemberDTO> getSearchList(String name, int start, int cnt) {
		return jdbcTmp.query(selectSearchMemberPaging, new MemberMapper(), "%" + name + "%", start, cnt);
	}

	public int getSearchCount(String name) {
		return jdbcTmp.queryForObject(selectSearchCntMember, Integer.class, "%" + name + "%");
	}
	
	// select를 위한 RowMapper를 만드는 inner class
	class MemberMapper implements RowMapper<SpMemberDTO> {
		@Override
		public SpMemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpMemberDTO dto = new SpMemberDTO();
			dto.setIdx(rs.getInt("idx"));
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			
			return dto;
		}
	}
}
