package com.pluckit.app.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluckit.app.dao.AdminDAO;
import com.pluckit.app.dto.BoardDTO;

@Service
public class AdminService {
	@Autowired
	private AdminDAO addao;

	public void makeBoard(BoardDTO dto) {
		addao.makeBaord(dto);
	}

	public List<BoardDTO> getAllBoardList(int offset, int pageSize) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		return addao.getAllBoardList(map);
	}

	public void createMainTable(String bId) {
		String str = "CREATE TABLE board_main_" + bId;
			str += " (bm_num INT NOT NULL AUTO_INCREMENT,";
			str += " b_id VARCHAR(45),";
			str	+= " bm_title VARCHAR(200),";
			str	+= " bm_writer VARCHAR(45),";
			str	+= " bm_content TEXT,";
			str	+= " bm_regdate DATETIME,";
			str	+= " bm_hit INT,";
			str	+= " bm_file VARCHAR(300),";
			str	+= " bm_savedfile VARCHAR(300),";
			str	+= " bm_filepath VARCHAR(300),";
			str	+= " bm_grpnum INT,";
			str	+= " bm_grpord INT,";
			str	+= " bm_grpdepth INT,";
			str	+= " CONSTRAINT PK_board_main_" + bId + " PRIMARY KEY (bm_num),";
			str	+= " CONSTRAINT FK_board_main_"+ bId +"_b_id_board_b_id FOREIGN KEY (b_id)";
			str	+= " REFERENCES board (b_id) ON DELETE RESTRICT ON UPDATE RESTRICT);";
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("main", str);
		
		addao.createMainTable(map);
	}

	public void createReplyTable(String bId) {
		String str = "CREATE TABLE board_reply_" + bId;
			str += " (r_num INT NOT NULL AUTO_INCREMENT,";
			str += " bm_num INT NOT NULL,";
			str += " r_writer VARCHAR(45),";
			str += " r_content TEXT,";
			str += " r_regdate DATETIME,";
			str += " CONSTRAINT PK_board_reply_" + bId + " PRIMARY KEY (r_num, bm_num),";
			str += " CONSTRAINT FK_board_reply_" + bId + "_bm_num_board_main_" + bId + "_bm_num FOREIGN KEY (bm_num)";
			str += " REFERENCES board_main_" + bId + " (bm_num) ON DELETE RESTRICT ON UPDATE RESTRICT);";
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("reply", str);
		
		addao.createReplyTable(map);
	}

	public int isBIdExist(String bId) {		
		return addao.isBIdExist(bId);
	}

	public BoardDTO getBoardInfo(String bId) {
		return addao.getBoardInfo(bId);
	}

	public int updateBoardInfo(BoardDTO dto) {
		return addao.updateBoardInfo(dto);
	}

	public int isBoardDataExist(String bId) {
		String tblName = "board_main_" + bId;
		return addao.isBoardDataExist(tblName);
	}

	public void dropReplyTable(String bId) {
		String str = "DROP TABLE board_reply_" + bId;
		HashMap<String, String> map = new HashMap<>();
		map.put("reply", str);
		
		addao.dropReplyTable(map);
	}

	public void dropMainTable(String bId) {
		String str = "DROP TABLE board_main_" + bId;
		HashMap<String, String> map = new HashMap<>();
		map.put("main", str);
		
		addao.dropMainTable(map);
	}

	public int deleteBoardInfo(String bId) {
		return addao.deleteBoardInfo(bId);
	}

	public List<BoardDTO> searchBoardList(HashMap<String, String> map) {
		return addao.searchBoardList(map);
	}

	public int getAllBoardCount() {
		return addao.getAllBoardCount();
	}

	public int getSearchBoardCount(HashMap<String, String> map) {
		return addao.getSearchBoardCount(map);
	}

	
}// AdminService 끝
