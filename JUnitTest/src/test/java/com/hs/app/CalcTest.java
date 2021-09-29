package com.hs.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalcTest {
	// @Test �׽�Ʈ �޼��� ��� �ǹ�
	// @Test(timeout=5000) : �и��� �������� ������ �ð��� ������
	// @Test(excepted=����) : ex) @Test(execpted=NullPointerException.class)
	// - �������� NullPointerException �ϰ�� �������� ����.

	// �׽�Ʈ �޼��尡 ������ ���� ��� ��� �����ϰ� �ʹٸ�
	// @Ignore() �� �������ش�.

	// @Before() - �׽�Ʈ �ڵ尡 ����Ǳ� ���� ���� ���� ex) getConnection();
	// @After() - �׽�Ʈ �ڵ尡 ����� �� ����

	// @BeforeClass - �׽�Ʈ �ڵ尡 ����Ǳ� ���� ���������� �� �ѹ��� ����
	// @AfterClass - �׽�Ʈ �ڵ尡 ����� �� �� �ѹ��� ���� ex) db����� freeConnection();

	@Test
	public void sumTest() {
		// �׽�Ʈ ���̽� ��(���̺귯��) junit �� ����ϰ� �ִ�. �������� �⺻ ����
		// junit�� ���� �׽�Ʈ ���(����)
		// �޼��常 �����ϸ� �޼��� ��ü���� ��±���(sysout ����) ���ش�.

		// @Test ��� �ֳ����̼����� �׽�Ʈ �κ����� ����ϸ�, ���࿡ Junit Test�� ����
		// ����� ������. �ſ� �����ϰ� ǥ�õ�.
		// ������ ���, ���д� ������

		// ���� �ڵ��� ���κκ��̶�� �����ϰ� �ۼ��ϸ� �˴ϴ�.

		Calc c = new Calc();
		int result = c.sum(5, 10);
		assertEquals(10, result, 3);// ����, ���� ����� ��, ������

		// int[] aa = {1,2,3,4,5};
		// int[] bb = {1,2,3,4,5};

		// assertEquals(10,11,2);//10�� 11�� �������� 2 ���̹Ƿ� �����ɷ� ó��

		// assertArrayEquals(aa, bb);

		// assertArrayEquals(a,b); - �迭 A�� B�� ��ġ�ϴ°� Ȯ��
		// assertEquals(a,b); - ��ü A�� B�� ��ġ�ϴ°� Ȯ��
		// assertEquals(a,b,c); - �迭 A�� B�� ��ġ�ϴ°� Ȯ��
		// - c�� �������� �����ϰ�� ���������� �����ؼ� ���� ���̶�� �����ɷ� ó��

		// assertSame(a,b); - ��ü A�� B�� ��ġ�ϴ°� Ȯ��
		// assertTrue(a); - �� A�� ���ΰ� Ȯ��
		// assertNotNull(a); - �� A�� NULL �ƴ��� Ȯ��

	}
}
