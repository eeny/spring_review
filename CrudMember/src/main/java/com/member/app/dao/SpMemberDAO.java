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
	// @Repository �ֳ����̼��� �ش� Ŭ������ �ֻ��� �����̳ʿ� �� ��ü�� �������ش�!
	
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	// ========= INSERT ========= 
	
	// ��� 1) @Value �ֳ����̼� �̿��ϱ�
	/*
	@Value("INSERT INTO spmember VALUES (NULL, ?, ?, ?, ?)")
	private String insert; // �� �ֱ�
	
	public int memberInsert(SpMemberDTO dto) {
		return jdbcTmp.update(insert, dto.getId(), dto.getPw(), dto.getName(), dto.getEmail());
	}
	*/
	
	// ��� 2) SQL�� �״�� ���ڿ��� ����ϱ�
	private String insertMember = "INSERT INTO spmember VALUES (NULL, ?, ?, ?, ?)";
	
	public int memberInsert(SpMemberDTO dto) {
		// �� �޼��� �̸��� update() �ΰ�?
		// insert, update, delete�� ���� DB�� ������ ���ŵǱ� �����̴�!
		
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
	private String countMember; // �� ����� �ϳ��� ���´�. �÷� 1���� �� 1��!
	
	public SpMemberDTO memberGetOne(int idx) {
		SpMemberDTO dto = jdbcTmp.queryForObject(selectOneMember, new MemberMapper(), idx);
		
		return dto;
	}
	
	public List<SpMemberDTO> memberGetAll() {
		return jdbcTmp.query(selectAllMember, new MemberMapper());
	}
	
	public List<SpMemberDTO> memberGetAll(int start, int cnt) { // ����¡ ó���� �� ����� �޼���
		return jdbcTmp.query(selectAllMember, new MemberMapper(), start, cnt);
	}
	
	public int memberGetCount() {
		return jdbcTmp.queryForObject(countMember, Integer.class);
		// DTO Ÿ������ �������� ���� �ƴ϶� ����� ������ ���� Ÿ���� ����� �Ѵ�!
	}
	
	// inner class
	class MemberMapper implements RowMapper<SpMemberDTO> {
		@Override
		public SpMemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpMemberDTO dto = new SpMemberDTO(); // �� �ٿ� ���ؼ��� �����ϸ� �ȴ�!
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
	

}// SpmemberDAO Ŭ���� ��
