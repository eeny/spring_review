package com.example.demo;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BootApplicationTests {
	@Autowired
	ApplicationContext context; // �� ���ø����̼��� ����Ǵ� ���� ����� �� �ִ� ����
	@Autowired
	SqlSessionFactory sessionFactory;

	@Test
	void contextLoads() {
	}

	// ���� �׽�Ʈ - Ư�� �κи� ���ư��°� �׽�Ʈ �ϱ� ���� ��� - jUnit
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
