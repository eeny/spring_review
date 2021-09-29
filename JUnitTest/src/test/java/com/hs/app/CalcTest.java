package com.hs.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalcTest {
	// @Test 테스트 메서드 라는 의미
	// @Test(timeout=5000) : 밀리초 기준으로 실행할 시간을 정의함
	// @Test(excepted=예외) : ex) @Test(execpted=NullPointerException.class)
	// - 실행결과가 NullPointerException 일경우 성공으로 간주.

	// 테스트 메서드가 여러개 있을 경우 몇개는 제외하고 싶다면
	// @Ignore() 를 설정해준다.

	// @Before() - 테스트 코드가 실행되기 전에 먼저 실행 ex) getConnection();
	// @After() - 테스트 코드가 실행된 후 실행

	// @BeforeClass - 테스트 코드가 실행되기 전에 실행이지만 딱 한번만 실행
	// @AfterClass - 테스트 코드가 실행된 후 딱 한번만 실행 ex) db연결시 freeConnection();

	@Test
	public void sumTest() {
		// 테스트 케이스 툴(라이브러리) junit 을 사용하고 있다. 스프링에 기본 내장
		// junit은 단위 테스트 모듈(도구)
		// 메서드만 실행하면 메서드 자체에서 출력까지(sysout 포함) 해준다.

		// @Test 라는 애노테이션으로 테스트 부분임을 명시하면, 실행에 Junit Test가 생김
		// 결과는 단정문. 매우 간단하게 표시됨.
		// 성공은 녹색, 실패는 빨간색

		// 실제 코드의 메인부분이라고 생각하고 작성하면 됩니다.

		Calc c = new Calc();
		int result = c.sum(5, 10);
		assertEquals(10, result, 3);// 예상값, 실제 실행된 값, 오차값

		// int[] aa = {1,2,3,4,5};
		// int[] bb = {1,2,3,4,5};

		// assertEquals(10,11,2);//10과 11은 오차범위 2 안이므로 같은걸로 처리

		// assertArrayEquals(aa, bb);

		// assertArrayEquals(a,b); - 배열 A와 B가 일치하는가 확인
		// assertEquals(a,b); - 객체 A와 B가 일치하는가 확인
		// assertEquals(a,b,c); - 배열 A와 B가 일치하는가 확인
		// - c는 오차범위 정수일경우 오차범위를 지정해서 범위 안이라면 같은걸로 처리

		// assertSame(a,b); - 객체 A와 B가 일치하는가 확인
		// assertTrue(a); - 값 A가 참인가 확인
		// assertNotNull(a); - 값 A가 NULL 아님을 확인

	}
}
