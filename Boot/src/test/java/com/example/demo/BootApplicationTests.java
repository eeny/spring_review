package com.example.demo;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BootApplicationTests {
	@Autowired
	ApplicationContext context; // 이 애플리케이션이 실행되는 동안 사용할 수 있는 공간
	@Autowired
	SqlSessionFactory sessionFactory;

	@Test
	void contextLoads() {
	}

	// 단위 테스트 - 특정 부분만 돌아가는가 테스트 하기 위한 모듈 - jUnit
	@Test
	public void testApplicationContext() {
		try {
			System.out.println("======testApplicationContext()=====");
			System.out.println(context.getBean("sqlSessionFactory"));
			System.out.println("===================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sessionFactory() {
		try {
			System.out.println("======sessionFactory()=====");
			System.out.println(sessionFactory.toString());
			System.out.println("===================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
