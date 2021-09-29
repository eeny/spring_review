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
	//@PropertySource 지정된 클래스를 자바 기반의 설정 파일로 인식시키기
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();// 커넥션 풀중에 하나 인 Hikari 라이브러리 세팅 불러오기
	}

	//@ConfigurationProperties 불러온 application.properties 파일에서 시작이
	// 시작이 spring.datasource.hikari로 되어 있는 파일 읽어오기
	@Bean
	public DataSource dataSource() {// pool에서 하나씩 빼고 넣기 위한(커넥션 풀을 지원하기 위한) 인터페이스
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		// 마이바티스 연동 모듈 생성시 사용
		factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*MapperXML.xml"));
		// == 추가됨 ==
		factoryBean.setTypeAliasesPackage("com.example.demo.dto");// 기본 패키지 + dto 패키지
		factoryBean.setConfiguration(mybatisConfig());
		return factoryBean.getObject();
	}

	//== 추가됨 ==
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		// 실제로 마이바티스랑 연동하기 위한 모듈
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}