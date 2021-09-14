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
	private JdbcTemplate jdbcTmp; // 생성까지 끝! 바로 사용할 수 있다.
	
	@Value("insert into dummy values (null, ?)")
	private String insert; // 값 넣기
	
	@Value("update dummy set name=? where idx=?")
	private String update; // 값 수정
	
	@Value("delete from dummy where idx=?")
	private String delete; // 값 삭제

	@Value("select * from dummy where idx=?")
	private String getOne; // 값 하나만 가져오기
	
	@Value("select * from dummy")
	private String getAll; // 값 전부 가져오기
	
	@Value("select count(idx) from dummy")
	private String getCnt; // 값을 하나만 가져오는데 결과가 int
	
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
