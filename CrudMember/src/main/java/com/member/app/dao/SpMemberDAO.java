package com.member.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.member.app.bean.SpMemberDTO;

@Repository
public class SpMemberDAO {
	// @Repository 애노테이션은 해당 클래스를 최상위 컨테이너에 빈 객체로 생성해준다!
	
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	// ========= INSERT ========= 
	
	// 방법 1) @Value 애노테이션 이용하기
	/*
	@Value("INSERT INTO spmember VALUES (NULL, ?, ?, ?, ?)")
	private String insert; // 값 넣기
	
	public int memberInsert(SpMemberDTO dto) {
		return jdbcTmp.update(insert, dto.getId(), dto.getPw(), dto.getName(), dto.getEmail());
	}
	*/
	
	// 방법 2) SQL문 그대로 문자열로 사용하기
	private String insertMember = "INSERT INTO spmember VALUES (NULL, ?, ?, ?, ?)";
	
	public int memberInsert(SpMemberDTO dto) {
		// 왜 메서드 이름이 update() 인가?
		// insert, update, delete는 실제 DB의 내용이 갱신되기 때문이다!
		
		return jdbcTmp.update(insertMember, dto.getId(), dto.getPw(), dto.getName(), dto.getEmail());
	}
	
	// ========= DELETE ========= 
	
	@Value("delete from spmember where idx=?")
	private String deleteMember;
	
	public int memberDelete(int idx) {
		return jdbcTmp.update(deleteMember, idx);
	}
	
	// ========= SELECT ========= 
	
	@Value("select * from spmember where idx=?")
	private String selectOneMember;

	@Value("select * from spmember")
	private String selectAllMember;
	
	@Value("select count(idx) from spmember")
	private String countMember; // 딱 결과가 하나만 나온다. 컬럼 1개에 값 1개!
	
	public SpMemberDTO memberGetOne(int idx) {
		SpMemberDTO dto = jdbcTmp.queryForObject(selectOneMember, new MemberMapper(), idx);
		
		return dto;
	}
	
	public List<SpMemberDTO> memberGetAll() {
		return jdbcTmp.query(selectAllMember, new MemberMapper());
	}
	
	public List<SpMemberDTO> memberGetAll(int start, int cnt) { // 페이징 처리할 때 사용할 메서드
		return jdbcTmp.query(selectAllMember, new MemberMapper(), start, cnt);
	}
	
	public int memberGetCount() {
		return jdbcTmp.queryForObject(countMember, Integer.class);
		// DTO 타입으로 가져오는 것이 아니라서 결과로 가져올 값의 타입을 적어야 한다!
	}
	
	// inner class
	class MemberMapper implements RowMapper<SpMemberDTO> {
		@Override
		public SpMemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpMemberDTO dto = new SpMemberDTO(); // 한 줄에 대해서만 설정하면 된다!
			dto.setIdx(rs.getInt("idx"));
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			
			return dto;
		}
	}
	
	// ========= UPDATE ========= 
	
	@Value("UPDATE spmember SET id = ?, pw = ?, NAME = ?, email = ? WHERE idx = ?")
	private String updateMember;
	
	public int memberUpdate(SpMemberDTO dto) {
		return jdbcTmp.update(updateMember, dto.getId(), dto.getPw(), dto.getName(), dto.getEmail(), dto.getIdx());
	}
	

}// SpmemberDAO 클래스 끝
