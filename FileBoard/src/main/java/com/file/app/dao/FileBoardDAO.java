package com.file.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.file.app.dto.FileBoardDTO;

@Repository
public class FileBoardDAO {
	// JDBC Template 사용할 때 메서드들
	/*
	 * @Autowired private JdbcTemplate jdbcTmp;
	 * 
	 * @Value("SELECT * FROM fileboard") private String getAllBoard;
	 * 
	 * @Value("INSERT INTO fileboard VALUES (NULL, ?, ?)") private String
	 * insertBoard;
	 * 
	 * public List<FileBoardDTO> getAllBoard() { return jdbcTmp.query(getAllBoard,
	 * new FileMapper()); }
	 * 
	 * public int insertBoard(String txt, String filename) { // MultipartRequest로
	 * 받으므로 매개변수를 DTO로 받지 않는다!!! // 위의 각 txt, filename은 고전적 방법인 Multipart에 있는
	 * getParameter 메서드를 사용한다! return jdbcTmp.update(insertBoard, txt, filename); }
	 * 
	 * // select에 쓰는 inner class class FileMapper implements RowMapper<FileBoardDTO>
	 * {
	 * 
	 * @Override public FileBoardDTO mapRow(ResultSet rs, int rowNum) throws
	 * SQLException { FileBoardDTO dto = new FileBoardDTO();
	 * dto.setIdx(rs.getInt("idx")); dto.setTxt(rs.getString("txt"));
	 * dto.setFilename(rs.getString("filename"));
	 * 
	 * return dto; } }
	 */

	// 마이바티스 사용할 때 메서드들
	@Autowired
	private SqlSession sqlSession;

	public int fileInsert(Map<String, String> map) {
		// 값을 여러개 받아와야 할 때는 map으로 처리하기
		return sqlSession.insert("com.file.mybatis.insertData", map);
	}

	public List<FileBoardDTO> fileList() {
		return sqlSession.selectList("com.file.mybatis.selectData");
	}

	public int fileDelete(int idx) {
		// 값을 하나만 받아오면 될 때는 그냥 값을 써주기
		return sqlSession.delete("com.file.mybatis.deleteData", idx);
	}

	public String fileName(int idx) {
		return sqlSession.selectOne("com.file.mybatis.selectOneData", idx);
	}

	public int updateData(Map<String, Object> map) {
		// 수정을 map으로 처리해야하는 이유? 최소 2개의 값을 받아와야하기 때문이다! 수정할 데이터 + idx
		return sqlSession.update("com.file.updateData", map);
	}

} // dao 끝
