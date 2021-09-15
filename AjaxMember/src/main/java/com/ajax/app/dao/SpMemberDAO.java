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
	private String selectAllMember; // ��ü ȸ����� ������
	
	@Value("SELECT * FROM spajax LIMIT ?, ?")
	private String selectAllMemberPaging; // ����¡�� ���� ������
	
	@Value("SELECT COUNT(idx) FROM spajax")
	private String selectAllCntMember; // ��ü ȸ���� ������
	
	@Value("SELECT COUNT(*) FROM spajax WHERE id = ?")
	private String selectAjaxMember; // Ajax�� ���� ������
	
	@Value("SELECT * FROM spajax WHERE name LIKE ?")
	private String selectSearchMember; // �˻��� ���� ������
	
	@Value("SELECT * FROM spajax WHERE name LIKE ? LIMIT ?, ?")
	private String selectSearchMemberPaging; // ����¡�� ���� ������
	
	@Value("SELECT COUNT(idx) FROM spajax WHERE name LIKE ?")
	private String selectSearchCntMember; // �˻��� ���� ������
	
	@Value("INSERT INTO spajax VALUES (NULL, ?, ?, ?)")
	private String insertMember; // ȸ������ ������
	
	// SELECT ��� �������� (����¡ ����)
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
	
	// �� ����� �������� Ajax�� ���� ����� ������ ����̶� ������ ����� �ƴϴ�!!!
	/*
	public boolean isExistId(String id) { // ���̵� ���翩�� Ȯ��
		boolean boo = true;
		// false�� ���, �̹� �����ϴ� ���̵�(����� �� ���ٴ� ��)
		// true�� ���, ��밡���� ���̵�!
		
		int res = jdbcTmp.queryForObject(selectAjaxMember, Integer.class, id);
		if (res > 0) { // ���̵� �����Ѵٴ� ��
			boo = false;
		}
		
		return boo;
	}
	*/
	
	// Ajax
	public String isExistId(String id) { // ���̵� ���翩�� Ȯ��
		String str = "����� �� �ִ� ���̵��Դϴ�.";
		
		int res = jdbcTmp.queryForObject(selectAjaxMember, Integer.class, id);
		if (res > 0) { // ���̵� �����Ѵٴ� ��
			str = "�̹� �����ϴ� ���̵��Դϴ�.";
		}
		
		return str;
	}
	
	// �˻�
	public List<SpMemberDTO> getSearchList(String name) {
		return jdbcTmp.query(selectSearchMember, new MemberMapper(), "%" + name + "%");
	}

	public List<SpMemberDTO> getSearchList(String name, int start, int cnt) {
		return jdbcTmp.query(selectSearchMemberPaging, new MemberMapper(), "%" + name + "%", start, cnt);
	}

	public int getSearchCount(String name) {
		return jdbcTmp.queryForObject(selectSearchCntMember, Integer.class, "%" + name + "%");
	}
	
	// select�� ���� RowMapper�� ����� inner class
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
