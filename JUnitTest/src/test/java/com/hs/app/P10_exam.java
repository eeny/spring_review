package com.hs.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class P10_exam {
	
	@Test
	public void avgTest() {
		Calc c = new Calc();
		int result = c.avg(3, 6, 9);
		
		assertEquals(10, result, 4);
	}
}
