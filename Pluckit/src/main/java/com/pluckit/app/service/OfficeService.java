package com.pluckit.app.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluckit.app.dao.OfficeDAO;
import com.pluckit.app.dto.EmployeeDTO;

@Service
public class OfficeService {
	
	@Autowired
	private OfficeDAO odao;
	
	public List<EmployeeDTO> selectEmployeeListAll() {
		List<EmployeeDTO> list = odao.selectEmployeeListData();
		return list;
	}
	
	public List<EmployeeDTO> selectEmployeeListOne(HashMap<String, String> map) {
		List<EmployeeDTO> list = odao.selcectEmployeeListSearchData(map);
		return list;
	}

}
