package com.company.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CrudDao {
	
	@Autowired
	private JdbcTemplate jdbcTmp; // �������� ��! �ٷ� ����� �� �ִ�.
	
	@Value("insert into dummy values (null, ?)")
	private String insert; // �� �ֱ�
	
	@Value("update dummy set name=? where idx=?")
	private String update; // �� ����
	
	@Value("delete from dummy where idx=?")
	private String delete; // �� ����

	@Value("select * from dummy where idx=?")
	private String getOne; // �� �ϳ��� ��������
	
	@Value("select * from dummy")
	private String getAll; // �� ���� ��������
	
	@Value("select count(idx) from dummy")
	private String getCnt; // ���� �ϳ��� �������µ� ����� int
	
	public int insert(CrudDto dto) {
		return jdbcTmp.update(insert, dto.getName());
	}
	
	public int update(CrudDto dto) {
		return jdbcTmp.update(update, dto.getName(), dto.getIdx());
	}
	
	public int delete(int idx) {
		return jdbcTmp.update(delete, idx);
	}
	
//	public SqlRowSet getCnt() {
//		return jdbcTmp.queryForRowSet(getCnt);
//	}
	
	public CrudDto getOne(int idx) {
		return jdbcTmp.queryForObject(getOne, new Integer[]{idx}, new DummyMapper());
	}
	
	public List<CrudDto> getAll() {
		return jdbcTmp.query(getAll, new DummyMapper());
	}
	
	class DummyMapper implements RowMapper<CrudDto> {
		@Override
		public CrudDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new CrudDto(rs.getInt("idx"), rs.getString("name"));
		}
	}
}
