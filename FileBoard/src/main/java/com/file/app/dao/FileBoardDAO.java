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
	// JDBC Template ����� �� �޼����
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
	 * public int insertBoard(String txt, String filename) { // MultipartRequest��
	 * �����Ƿ� �Ű������� DTO�� ���� �ʴ´�!!! // ���� �� txt, filename�� ������ ����� Multipart�� �ִ�
	 * getParameter �޼��带 ����Ѵ�! return jdbcTmp.update(insertBoard, txt, filename); }
	 * 
	 * // select�� ���� inner class class FileMapper implements RowMapper<FileBoardDTO>
	 * {
	 * 
	 * @Override public FileBoardDTO mapRow(ResultSet rs, int rowNum) throws
	 * SQLException { FileBoardDTO dto = new FileBoardDTO();
	 * dto.setIdx(rs.getInt("idx")); dto.setTxt(rs.getString("txt"));
	 * dto.setFilename(rs.getString("filename"));
	 * 
	 * return dto; } }
	 */

	// ���̹�Ƽ�� ����� �� �޼����
	@Autowired
	private SqlSession sqlSession;

	public int fileInsert(Map<String, String> map) {
		// ���� ������ �޾ƿ;� �� ���� map���� ó���ϱ�
		return sqlSession.insert("com.file.mybatis.insertData", map);
	}

	public List<FileBoardDTO> fileList() {
		return sqlSession.selectList("com.file.mybatis.selectData");
	}

	public int fileDelete(int idx) {
		// ���� �ϳ��� �޾ƿ��� �� ���� �׳� ���� ���ֱ�
		return sqlSession.delete("com.file.mybatis.deleteData", idx);
	}

	public String fileName(int idx) {
		return sqlSession.selectOne("com.file.mybatis.selectOneData", idx);
	}

	public int updateData(Map<String, Object> map) {
		// ������ map���� ó���ؾ��ϴ� ����? �ּ� 2���� ���� �޾ƿ;��ϱ� �����̴�! ������ ������ + idx
		return sqlSession.update("com.file.updateData", map);
	}

} // dao ��
