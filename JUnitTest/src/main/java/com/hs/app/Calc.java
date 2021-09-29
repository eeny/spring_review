package com.hs.app;

public class Calc {
	public int sum(int i, int j) {
		return i + j;
	}
	
	public int avg(int a, int b, int c) {
		return (a + b + c) / 3;
	}

	public static void main(String[] args) {
		Calc c = new Calc();
		System.out.println(c.sum(10, 20));
	}
}
