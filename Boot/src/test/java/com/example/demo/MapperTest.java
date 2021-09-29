package com.example.demo;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.dto.BootDTO;
import com.example.demo.mapper.BootMapper;

@SpringBootTest
public class MapperTest {
	@Autowired
	private BootMapper mapper;

	@Test
	public void testInsert() {
		BootDTO dto = new BootDTO();
		dto.setName("aaa");
		dto.setTitle("ttt");
		int result = mapper.insertData(dto);
		System.out.println("테스트 결과 : " + result);
	}

	@Test
	public void testSelect() {
		List<BootDTO> list = mapper.selectAll();
		for (BootDTO dto : list) {
			System.out.println(dto.getIdx() + "/" + dto.getName() + "/" + dto.getTitle());
		}
	}
}
