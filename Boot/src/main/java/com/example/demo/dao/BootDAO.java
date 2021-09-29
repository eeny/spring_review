package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.dto.BootDTO;
import com.example.demo.mapper.BootMapper;

@Repository
public class BootDAO implements BootMapper {
	@Autowired
	BootMapper mapper;

	@Override
	public int insertData(BootDTO dto) {
		return mapper.insertData(dto);
	}

	@Override
	public int updateData(BootDTO dto) {
		return mapper.updateData(dto);
	}

	@Override
	public int deleteData(int idx) {
		return mapper.deleteData(idx);
	}

	@Override
	public BootDTO selectOne(int idx) {
		return mapper.selectOne(idx);
	}

	@Override
	public List<BootDTO> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int selectCnt() {
		return mapper.selectCnt();
	}
}
