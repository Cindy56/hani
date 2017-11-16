package com.game.draw.util;

public class math {

	/**
	 * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1
	 * 
	 * @param n
	 * @return
	 */
	public static long factorial(int n) {
		return (n > 1) ? n * factorial(n - 1) : 1;
	}

	/**
	 * 计算排列数，即A(n, m) = n!/(n-m)!
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public static long arrangement(int n, int m) {
		return (n >= m) ? factorial(n) / factorial(n - m) : 0;
	}

	/**
	 * 计算组合数，即C(n, m) = n!/((n-m)! * m!)
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public static long combination(int n, int m) {
		return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;
	}

}
