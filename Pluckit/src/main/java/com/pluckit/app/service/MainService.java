package com.pluckit.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluckit.app.dao.MainDAO;
import com.pluckit.app.dto.DepartmentDTO;
import com.pluckit.app.dto.EmployeeDTO;

@Service
public class MainService {
	@Autowired
	private MainDAO mdao;
	
	public void insertSignupData(EmployeeDTO dto) {
		// 사원번호 생성 (현재년도 + 부서코드 + 직급코드) + 직원코드
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy");
		String year = now.format(fmt);
		int deptId = dto.getDept_id();
		int rankId = dto.getRank_id();
		
		String empNum = year + deptId + rankId;
		dto.setEmp_num(empNum);
		
		mdao.insertSignupData(dto);
	}

	public EmployeeDTO selectLoginData(EmployeeDTO dto) {
		return mdao.selectLoginData(dto);
	}
	
	
	
	
	
	
	
}// MainService 끝
