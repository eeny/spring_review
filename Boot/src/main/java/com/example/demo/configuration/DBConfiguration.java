package com.example.demo.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {
	//@PropertySource ������ Ŭ������ �ڹ� ����� ���� ���Ϸ� �νĽ�Ű��
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();// Ŀ�ؼ� Ǯ�߿� �ϳ� �� Hikari ���̺귯�� ���� �ҷ�����
	}

	//@ConfigurationProperties �ҷ��� application.properties ���Ͽ��� ������
	// ������ spring.datasource.hikari�� �Ǿ� �ִ� ���� �о����
	@Bean
	public DataSource dataSource() {// pool���� �ϳ��� ���� �ֱ� ����(Ŀ�ؼ� Ǯ�� �����ϱ� ����) �������̽�
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		// ���̹�Ƽ�� ���� ��� ������ ���
		factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*MapperXML.xml"));
		// == �߰��� ==
		factoryBean.setTypeAliasesPackage("com.example.demo.dto");// �⺻ ��Ű�� + dto ��Ű��
		factoryBean.setConfiguration(mybatisConfig());
		return factoryBean.getObject();
	}

	//== �߰��� ==
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		// ������ ���̹�Ƽ���� �����ϱ� ���� ���
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}