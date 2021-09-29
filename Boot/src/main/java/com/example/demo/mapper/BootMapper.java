package com.example.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.dto.BootDTO;

@Mapper
public interface BootMapper {
	// 메서드 이름이 mapper.xml 파일의 id와 같으면 알아서 실행됨.
	public int insertData(BootDTO dto);

	public int updateData(BootDTO dto);

	public int deleteData(int idx);

	public BootDTO selectOne(int idx);

	public List<BootDTO> selectAll();

	public int selectCnt();
}
