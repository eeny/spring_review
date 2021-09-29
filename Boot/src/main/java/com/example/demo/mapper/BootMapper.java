package com.example.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.dto.BootDTO;

@Mapper
public interface BootMapper {
	// �޼��� �̸��� mapper.xml ������ id�� ������ �˾Ƽ� �����.
	public int insertData(BootDTO dto);

	public int updateData(BootDTO dto);

	public int deleteData(int idx);

	public BootDTO selectOne(int idx);

	public List<BootDTO> selectAll();

	public int selectCnt();
}
